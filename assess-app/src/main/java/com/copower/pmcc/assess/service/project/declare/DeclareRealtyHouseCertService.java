package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.common.enums.DeclareCertificateTypeEnum;
import com.copower.pmcc.assess.common.enums.DeclareTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRealtyHouseCertDao;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRealtyLandCertDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRealtyHouseCertVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/19 10:04
 * @Description:房产证
 */
@Service
public class DeclareRealtyHouseCertService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeclareRealtyHouseCertDao declareRealtyHouseCertDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private DeclareRealtyLandCertDao declareRealtyLandCertDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private DeclarePublicService declarePublicService;
    @Autowired
    private DeclareBuildEngineeringAndEquipmentCenterService declareBuildEngineeringAndEquipmentCenterService;
    @Autowired
    private DeclareRecordExtendService declareRecordExtendService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;


    public void attachmentAutomatedWarrants(DeclarePublicService.AutomatedWarrants automatedWarrants)throws Exception{
        declarePublicService.attachmentAutomatedWarrants(automatedWarrants);
    }

    /**
     * 功能描述: 导入土地证 并且和房产证关联
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/9/25 17:58
     */
    public String importLandAndHouse(MultipartFile multipartFile, Integer planDetailsId) throws Exception {
        Workbook workbook = null;
        Row row = null;
        StringBuilder builder = new StringBuilder();
        //1.保存文件
        String filePath = baseAttachmentService.saveUploadFile(multipartFile);
        //2.读取文件
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            workbook = WorkbookFactory.create(inputStream);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        //只取第一个sheet
        Sheet sheet = workbook.getSheetAt(0);
        //工作表的第一行
        row = sheet.getRow(0);
        //读取数据的起始行
        int startRowNumber = 1;
        //导入成功数据条数
        int successCount = 0;
        //总列数
        int colLength = row.getPhysicalNumberOfCells() != 0 ? row.getPhysicalNumberOfCells() : row.getLastCellNum();
        //总行数
        int rowLength = sheet.getPhysicalNumberOfRows() != 0 ? sheet.getPhysicalNumberOfRows() : sheet.getLastRowNum();
        rowLength = rowLength - startRowNumber;
        if (rowLength == 0) {
            builder.append("没有数据!");
            return builder.toString();
        }
        for (int i = startRowNumber; i < startRowNumber + rowLength; i++) {
            DeclareRealtyLandCert landCert = null;
            try {
                landCert = new DeclareRealtyLandCert();
                row = sheet.getRow(i);
                if (row == null) {
                    builder.append(String.format("\n第%s行异常：%s", i, "没有数据"));
                    continue;
                }
                if (!declarePublicService.land(landCert, builder, row, i)) {
                    continue;
                }
                landCert.setEnable(DeclareTypeEnum.BranchData.getKey());
                landCert.setPlanDetailsId(planDetailsId);
                landCert.setCreator(commonService.thisUserAccount());
            } catch (Exception e) {
                builder.append(String.format("\n第%s行异常：%s", i, e.getMessage()));
                continue;
            }
            List<DeclareRealtyHouseCert> declareRealtyHouseCertList = Lists.newArrayList();
            //房产证的土地证号和土地证图号
            DeclareRealtyHouseCert houseCert = new DeclareRealtyHouseCert();
            houseCert.setPlanDetailsId(planDetailsId);
            //  房产证座落和土地证座落匹配
            if (CollectionUtils.isEmpty(declareRealtyHouseCertList)) {
                if (StringUtils.isNotBlank(landCert.getBeLocated())) {
                    houseCert.setBeLocated(landCert.getBeLocated());
                    List<DeclareRealtyHouseCert> listB = declareRealtyHouseCertDao.getDeclareRealtyHouseCertList(houseCert);
                    if (CollectionUtils.isNotEmpty(listB)) {
                        declareRealtyHouseCertList.addAll(listB);
                    } else {
                        houseCert.setBeLocated(null);
                    }
                }
            }
            LinkedHashMap<DeclareRealtyHouseCert, DeclareBuildEngineeringAndEquipmentCenter> map = Maps.newLinkedHashMap();
            if (CollectionUtils.isNotEmpty(declareRealtyHouseCertList)) {
                for (DeclareRealtyHouseCert po : declareRealtyHouseCertList) {
                    DeclareBuildEngineeringAndEquipmentCenter center = new DeclareBuildEngineeringAndEquipmentCenter();
                    center.setHouseId(po.getId());
                    center.setPlanDetailsId(po.getPlanDetailsId());
                    List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(center);
                    if (CollectionUtils.isNotEmpty(centerList)) {
                        if (centerList.stream().anyMatch(oo -> oo.getLandId() == null)) {
                            map.put(po, centerList.stream().filter(oo -> oo.getLandId() == null).findFirst().get());
                        }
                    }
                }
            }
            Integer id = declareRealtyLandCertDao.addDeclareRealtyLandCert(landCert);
            successCount++;
            if (map.isEmpty()) {
                builder.append(String.format("\n第%s行：%s", i, "未找到匹配的房产证"));
            } else {
                DeclareBuildEngineeringAndEquipmentCenter center = map.entrySet().stream().findFirst().get().getValue();
                center.setLandId(id);
                declareBuildEngineeringAndEquipmentCenterService.saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(center);
            }
        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }

    /**
     * 功能描述: 导入房产证数据
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/9/25 17:57
     */
    public String importData(DeclareRealtyHouseCert declareRealtyHouseCert, MultipartFile multipartFile) throws Exception {
        Workbook workbook = null;
        Row row = null;
        StringBuilder builder = new StringBuilder();
        //1.保存文件
        String filePath = baseAttachmentService.saveUploadFile(multipartFile);
        //2.读取文件
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            workbook = WorkbookFactory.create(inputStream);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        //只取第一个sheet
        Sheet sheet = workbook.getSheetAt(0);
        //工作表的第一行
        row = sheet.getRow(0);
        //读取数据的起始行
        int startRowNumber = 1;
        //导入成功数据条数
        int successCount = 0;
        //总列数
        int colLength = row.getPhysicalNumberOfCells() != 0 ? row.getPhysicalNumberOfCells() : row.getLastCellNum();
        //总行数
        int rowLength = sheet.getPhysicalNumberOfRows() != 0 ? sheet.getPhysicalNumberOfRows() : sheet.getLastRowNum();
        rowLength = rowLength - startRowNumber;
        if (rowLength == 0) {
            builder.append("没有数据!");
            return builder.toString();
        }
        for (int i = startRowNumber; i < rowLength + startRowNumber; i++) {
            DeclareRealtyHouseCert oo = null;
            //标识符
            boolean flag = true;
            try {
                row = sheet.getRow(i);
                if (row == null) {
                    builder.append(String.format("\n第%s行异常：%s", i, "没有数据"));
                    continue;
                }
                oo = new DeclareRealtyHouseCert();
                BeanUtils.copyProperties(declareRealtyHouseCert, oo);
                oo.setId(null);
                //excel 处理
                if (!declarePublicService.house(oo, builder, row, i)) {
                    continue;
                }
            } catch (Exception e) {
                flag = false;
                builder.append(String.format("\n第%s行异常：%s", i + 1, e.getMessage()));
            }
            if (flag) {
                oo.setCreator(commonService.thisUserAccount());
                oo.setEnable(DeclareTypeEnum.MasterData.getKey());
                declareRealtyHouseCertDao.addDeclareRealtyHouseCert(oo);
                DeclareBuildEngineeringAndEquipmentCenter center = new DeclareBuildEngineeringAndEquipmentCenter();
                center.setPlanDetailsId(oo.getPlanDetailsId());
                center.setHouseId(oo.getId());
                center.setType(DeclareRealtyHouseCert.class.getSimpleName());
                declareBuildEngineeringAndEquipmentCenterService.saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(center);
                successCount++;
            }
        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }


    public Integer saveAndUpdateDeclareRealtyHouseCert(DeclareRealtyHouseCert declareRealtyHouseCert) {
        return saveAndUpdateDeclareRealtyHouseCert(declareRealtyHouseCert, false);
    }

    public Integer saveAndUpdateDeclareRealtyHouseCert(DeclareRealtyHouseCert declareRealtyHouseCert, boolean updateNull) {
        if (declareRealtyHouseCert.getId() == null) {
            declareRealtyHouseCert.setCreator(commonService.thisUserAccount());
            Integer id = null;
            id = declareRealtyHouseCertDao.addDeclareRealtyHouseCert(declareRealtyHouseCert);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class), id);
            return id;
        } else {
            declareRealtyHouseCertDao.updateDeclareRealtyHouseCert(declareRealtyHouseCert, updateNull);
            updateDeclareRealtyHouseCertAndUpdateDeclareRecordOrJudgeObject(declareRealtyHouseCert);
            return declareRealtyHouseCert.getId();
        }
    }

    private void updateDeclareRealtyHouseCertAndUpdateDeclareRecordOrJudgeObject(DeclareRealtyHouseCert declareRealtyHouseCert) {
        if (declareRealtyHouseCert == null) {
            return;
        }
        if (declareRealtyHouseCert.getId() == null) {
            return;
        }
        if (declareRealtyHouseCert.getId() == 0) {
            return;
        }
        DeclareRealtyHouseCert oo = getDeclareRealtyHouseCertById(declareRealtyHouseCert.getId());
        if (oo == null) {
            return;
        }
        declareRealtyHouseCert.setBisRecord(oo.getBisRecord());
        declareRealtyHouseCert.setPlanDetailsId(oo.getPlanDetailsId());
        if (declareRealtyHouseCert.getPlanDetailsId() == null) {
            return;
        }
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(declareRealtyHouseCert.getPlanDetailsId());
        if (projectPlanDetails == null) {
            return;
        }
        if (declareRealtyHouseCert.getBisRecord() == null) {
            return;
        }
        if (!declareRealtyHouseCert.getBisRecord()) {
            return;
        }
        List<DeclareRecord> declareRecordList = declareRecordService.getDeclareRecordListByDataTableId(FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class), declareRealtyHouseCert.getId(), projectPlanDetails.getProjectId());
        //当明确是更新的时候确找不到申报id那么则不再更新这条数据
        if (CollectionUtils.isEmpty(declareRecordList)) {
            return;
        }
        DeclareRecord declareRecord = new DeclareRecord();
        setDeclareRealtyHouseCertForDeclareRecordProperties(declareRealtyHouseCert, declareRecord, projectPlanDetails.getProjectId());
        declareRecord.setId(declareRecordList.stream().findFirst().get().getId());
        try {
            declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
            declareRecordService.reStartDeclareApplyByDeclareRecordId(Arrays.asList(declareRecord.getId()), projectPlanDetails.getProjectId());
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
        }
    }

    public DeclareRealtyHouseCert getDeclareRealtyHouseCertById(Integer id) {
        return declareRealtyHouseCertDao.getDeclareRealtyHouseCertById(id);
    }

    public BootstrapTableVo getDeclareRealtyHouseCertListVos(DeclareRealtyHouseCert declareRealtyHouseCert) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareRealtyHouseCertVo> vos = lists(declareRealtyHouseCert);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DeclareRealtyHouseCertVo> lists(DeclareRealtyHouseCert declareRealtyHouseCert) {
        List<DeclareRealtyHouseCert> declareRealtyHouseCerts = declareRealtyHouseCertDao.getDeclareRealtyHouseCertList(declareRealtyHouseCert);
        List<DeclareRealtyHouseCertVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(declareRealtyHouseCerts)) {
            for (DeclareRealtyHouseCert landLevel : declareRealtyHouseCerts) {
                vos.add(getDeclareRealtyHouseCertVo(landLevel));
            }
        }
        return vos;
    }


    public boolean deleteDeclareRealtyHouseCertById(Integer id) {
        return declareRealtyHouseCertDao.deleteDeclareRealtyHouseCertById(id);
    }

    public DeclareRealtyHouseCertVo getDeclareRealtyHouseCertVo(DeclareRealtyHouseCert declareRealtyHouseCert) {
        if (declareRealtyHouseCert == null) {
            return null;
        }
        DeclareRealtyHouseCertVo vo = new DeclareRealtyHouseCertVo();
        BeanUtils.copyProperties(declareRealtyHouseCert, vo);
        vo.setPlanningUseName(baseDataDicService.getNameById(declareRealtyHouseCert.getCertUse()));
//        if (declareRealtyHouseCert.getCertUseCategory() != null) {
//            vo.setCertUseCategoryName(baseDataDicService.getNameById(declareRealtyHouseCert.getCertUseCategory()));
//        }
        vo.setTypeName(baseDataDicService.getNameById(declareRealtyHouseCert.getType()));
        vo.setNatureName(baseDataDicService.getNameById(declareRealtyHouseCert.getNature()));
        vo.setPublicSituationName(baseDataDicService.getNameById(declareRealtyHouseCert.getPublicSituation()));
        vo.setLandAcquisitionName(baseDataDicService.getNameById(declareRealtyHouseCert.getLandAcquisition()));
        if (StringUtils.isNotBlank(declareRealtyHouseCert.getProvince())) {
            if (NumberUtils.isNumber(declareRealtyHouseCert.getProvince())) {
                //省
                vo.setProvinceName(erpAreaService.getSysAreaName(declareRealtyHouseCert.getProvince()));
            } else {
                //省
                vo.setProvinceName(declareRealtyHouseCert.getProvince());
            }
        }
        if (StringUtils.isNotBlank(declareRealtyHouseCert.getCity())) {
            if (NumberUtils.isNumber(declareRealtyHouseCert.getCity())) {
                //市或者县
                vo.setCityName(erpAreaService.getSysAreaName(declareRealtyHouseCert.getCity()));
            } else {
                vo.setCityName(declareRealtyHouseCert.getCity());
            }
        }
        if (StringUtils.isNotBlank(declareRealtyHouseCert.getDistrict())) {
            if (NumberUtils.isNumber(declareRealtyHouseCert.getDistrict())) {
                //县
                vo.setDistrictName(erpAreaService.getSysAreaName(declareRealtyHouseCert.getDistrict()));
            } else {
                vo.setDistrictName(declareRealtyHouseCert.getDistrict());
            }
        }
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(declareRealtyHouseCert.getId(), null, FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class));
        StringBuilder builder = new StringBuilder();
        if (!ObjectUtils.isEmpty(sysAttachmentDtos)) {
            for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtos) {
                builder.append(baseAttachmentService.getViewHtml(sysAttachmentDto)).append(" ");
            }
            vo.setFileViewName(builder.toString());
        }
        DeclareBuildEngineeringAndEquipmentCenter centerQuery = new DeclareBuildEngineeringAndEquipmentCenter();
        centerQuery.setHouseId(declareRealtyHouseCert.getId());
        centerQuery.setPlanDetailsId(declareRealtyHouseCert.getPlanDetailsId());
        centerQuery.setType(DeclareRealtyHouseCert.class.getSimpleName());
        List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(centerQuery);
        if (CollectionUtils.isNotEmpty(centerList)) {
            vo.setCenterId(centerList.stream().findFirst().get().getId());
        }
        return vo;
    }

    /**
     * 写入申报数据
     *
     * @param declareApply
     */
    public void eventWriteDeclareApply(DeclareApply declareApply) {
        DeclareRecord declareRecord = null;
        if (declareApply == null) return;
        DeclareRealtyHouseCert query = new DeclareRealtyHouseCert();
        query.setPlanDetailsId(declareApply.getPlanDetailsId());
        query.setEnable(DeclareTypeEnum.MasterData.getKey());
        query.setBisRecord(false);
        List<DeclareRealtyHouseCert> lists = declareRealtyHouseCertDao.getDeclareRealtyHouseCertList(query);
        if (CollectionUtils.isEmpty(lists)) return;
        for (DeclareRealtyHouseCert declareRealtyHouseCert : lists) {
            declareRecord = new DeclareRecord();
            setDeclareRealtyHouseCertForDeclareRecordProperties(declareRealtyHouseCert, declareRecord, declareApply.getProjectId());
            declareRecord.setType(DeclareCertificateTypeEnum.HOUSE.getKey());
            declareRecord.setNumber(declareRecordService.getMaxNumber(declareApply.getProjectId()));
            try {
                int declareId = declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
                DeclareRecordExtend declareRecordExtend = new DeclareRecordExtend();
                declareRecordExtend.setCreator(commonService.thisUserAccount());
                declareRecordExtend.setRegistrationAuthority(declareRealtyHouseCert.getRegistrationAuthority());
                declareRecordExtend.setDeclareId(declareId);
                declareRecordExtendService.addDeclareRecord(declareRecordExtend);
                declareRealtyHouseCert.setBisRecord(true);
                declareRealtyHouseCertDao.updateDeclareRealtyHouseCert(declareRealtyHouseCert);
            } catch (Exception e1) {
                baseService.writeExceptionInfo(e1, "申报");
            }
        }
    }

    private void setDeclareRealtyHouseCertForDeclareRecordProperties(DeclareRealtyHouseCert oo, DeclareRecord declareRecord, Integer projectId) {
        BeanUtils.copyProperties(oo, declareRecord);
        declareRecord.setId(null);
        declareRecord.setProjectId(projectId);
        declareRecord.setDataTableName(FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class));
        declareRecord.setDataTableId(oo.getId());
        declareRecord.setDataFromType("房产证");
        declareRecord.setType("");
        declareRecord.setName(oo.getCertName());
        declareRecord.setOwnership(oo.getOwnership());
        declareRecord.setPublicSituation(baseDataDicService.getNameById(oo.getPublicSituation()));
        declareRecord.setSeat(oo.getBeLocated());
        declareRecord.setStreetNumber(oo.getStreetNumber());
        declareRecord.setAttachedNumber(oo.getAttachedNumber());
        declareRecord.setBuildingNumber(oo.getBuildingNumber());
        declareRecord.setUnit(oo.getUnit());
        declareRecord.setRegistrationDate(oo.getRegistrationDate());
        if (declareRecord.getRegistrationDate() == null) {
            declareRecord.setRegistrationDate(oo.getRegistrationTime());
        }
        declareRecord.setFloor(oo.getFloor());
        declareRecord.setRoomNumber(oo.getRoomNumber());
        declareRecord.setCertUse(oo.getCertUseCategory());
        declareRecord.setFloorArea(oo.getEvidenceArea());
        declareRecord.setLandUseEndDate(oo.getUseEndDate());
        declareRecord.setHousingStructure(oo.getHousingStructure());
        declareRecord.setNature(baseDataDicService.getNameById(oo.getNature()));
        declareRecord.setInventoryContentKey(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT);
        declareRecord.setPublicSituation(baseDataDicService.getNameById(oo.getPublicSituation()));
        declareRecord.setCreator(oo.getCreator());
        declareRecord.setBisPartIn(true);
        DeclareRealtyLandCert realtyLandCert = null;
        //写入土地证的证载用途
        //从中间表获取到土地证信息
        DeclareBuildEngineeringAndEquipmentCenter centerQuery = new DeclareBuildEngineeringAndEquipmentCenter();
        centerQuery.setHouseId(oo.getId());
        centerQuery.setPlanDetailsId(oo.getPlanDetailsId());
        centerQuery.setType(DeclareRealtyHouseCert.class.getSimpleName());
        List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(centerQuery);
        if (CollectionUtils.isNotEmpty(centerList)) {
            if (centerList.stream().anyMatch(obj -> obj.getLandId() != null)) {
                realtyLandCert = declareRealtyLandCertDao.getDeclareRealtyLandCertById(centerList.stream().filter(obj -> obj.getLandId() != null).findFirst().get().getLandId());
            }
        }
        if (oo.getPid() != null && oo.getPid() != 0) {
            realtyLandCert = declareRealtyLandCertDao.getDeclareRealtyLandCertById(oo.getPid());
        }
        if (realtyLandCert != null) {
            declareRecord.setLandCertUse(realtyLandCert.getCertUse());
            declareRecord.setLandCertUseCategory(realtyLandCert.getCertUseCategory());

            if (realtyLandCert.getLandRightType() != null) {
                BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(realtyLandCert.getLandRightType());
                if (baseDataDic != null) {
                    if (StringUtils.isNotEmpty(baseDataDic.getRemark())) {
                        declareRecord.setLandRightType(baseDataDic.getRemark());
                    }else {
                        declareRecord.setLandRightType(baseDataDic.getName());
                    }
                }
            }

            declareRecord.setLandRightNature(baseDataDicService.getNameById(realtyLandCert.getLandRightNature()));
            declareRecord.setLandUseRightArea(realtyLandCert.getUseRightArea());
        }
    }
}
