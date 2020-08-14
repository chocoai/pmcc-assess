package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.common.enums.DeclareCertificateTypeEnum;
import com.copower.pmcc.assess.common.enums.DeclareTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRealtyHouseCertDao;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRealtyLandCertDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.declare.AutomatedWarrants;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRealtyLandCertVo;
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
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
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
import java.util.*;

/**
 * @Auther: zch
 * @Date: 2018/9/19 10:06
 * @Description:土地证
 */
@Service
public class DeclareRealtyLandCertService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeclareRealtyLandCertDao declareRealtyLandCertDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private DeclareRealtyHouseCertDao declareRealtyHouseCertDao;
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

    public void attachmentAutomatedWarrants(AutomatedWarrants automatedWarrants) throws Exception {
        declarePublicService.attachmentAutomatedWarrants(automatedWarrants);
    }


    /**
     * 功能描述: 关联房产证
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/9/25 18:31
     */
    public String importLandAndHouse(MultipartFile multipartFile, Integer planDetailsId) throws Exception {
        Workbook workbook = null;
        Row row = null;
        StringBuilder stringBuilder = new StringBuilder();
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
        int startRowNumber = 2;
        //导入成功数据条数
        int successCount = 0;
        //总列数
        int colLength = row.getPhysicalNumberOfCells() != 0 ? row.getPhysicalNumberOfCells() : row.getLastCellNum();
        //总行数
        int rowLength = sheet.getPhysicalNumberOfRows() != 0 ? sheet.getPhysicalNumberOfRows() : sheet.getLastRowNum();
        rowLength = rowLength - startRowNumber;
        if (rowLength == 0) {
            stringBuilder.append("没有数据!");
            return stringBuilder.toString();
        }
        Map<DeclareBuildEngineeringAndEquipmentCenter, DeclareRealtyLandCert> relatedMap = Maps.newHashMap();
        Multimap<String, Map.Entry<Class<?>, Integer>> classArrayListMultimap = declarePublicService.getMultimapByClass(DeclareRealtyHouseCert.class, row);
        for (int i = startRowNumber; i < rowLength + startRowNumber; i++) {
            DeclareRealtyHouseCert houseCert = null;
            try {
                row = sheet.getRow(i);
                if (row == null) {
                    declarePublicService.excelImportWriteErrorInfo(i, "没有数据", stringBuilder);
                    continue;
                }
                houseCert = new DeclareRealtyHouseCert();
                if (!declarePublicService.house(classArrayListMultimap, houseCert, stringBuilder, row)) {
                    continue;
                }
                houseCert.setPlanDetailsId(planDetailsId);
                houseCert.setEnable(DeclareTypeEnum.BranchData.getKey());
                houseCert.setCreator(commonService.thisUserAccount());
            } catch (Exception e) {
                declarePublicService.excelImportWriteErrorInfo(i, e.getMessage(), stringBuilder);
            }
            //使用 excel导入编号关联
            //-----------------------------------start----------------------------------
            if (houseCert.getAutoInitNumber() == null) {
                declarePublicService.excelImportWriteErrorInfo(i, "编号没有", stringBuilder);
                continue;
            }
            DeclareRealtyLandCert select = new DeclareRealtyLandCert();
            select.setPlanDetailsId(planDetailsId);
            select.setAutoInitNumber(houseCert.getAutoInitNumber());
            select.setEnable(DeclareTypeEnum.MasterData.getKey());
            List<DeclareRealtyLandCert> landCertList = declareRealtyLandCertDao.getDeclareRealtyLandCertList(select);
            boolean yes = false;
            if (CollectionUtils.isNotEmpty(landCertList)) {
                ListIterator<DeclareRealtyLandCert> declareRealtyLandCertListIterator = landCertList.listIterator();
                while (declareRealtyLandCertListIterator.hasNext()) {
                    DeclareRealtyLandCert realtyLandCert = declareRealtyLandCertListIterator.next();
                    DeclareBuildEngineeringAndEquipmentCenter query = new DeclareBuildEngineeringAndEquipmentCenter();
                    query.setLandId(realtyLandCert.getId());
                    query.setPlanDetailsId(realtyLandCert.getPlanDetailsId());
                    query.setType(DeclareRealtyLandCert.class.getSimpleName());
                    List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(query);
                    if (CollectionUtils.isEmpty(centerList)) {
                        continue;
                    }
                    Iterator<DeclareBuildEngineeringAndEquipmentCenter> iterator = centerList.iterator();
                    while (iterator.hasNext()) {
                        DeclareBuildEngineeringAndEquipmentCenter equipmentCenter = iterator.next();
                        if (equipmentCenter.getHouseId() == null || equipmentCenter.getHouseId() == 0) {
                            relatedMap.put(equipmentCenter, realtyLandCert);
                        }
                        if (equipmentCenter.getHouseId() != null && equipmentCenter.getHouseId() != 0) {
                            yes = true;
                        }
                    }
                }
            }
            if (yes) {
                relatedMap.clear();
                declarePublicService.excelImportWriteErrorInfo(i, "此编号已经有匹配的土地证了,请修改编号再行匹配", stringBuilder);
                continue;
            }
            if (relatedMap.isEmpty()) {
                declarePublicService.excelImportWriteErrorInfo(i, "未找到匹配的土地证", stringBuilder);
                continue;
            }
            Integer id = declareRealtyHouseCertDao.addDeclareRealtyHouseCert(houseCert);
            successCount++;
            Iterator<Map.Entry<DeclareBuildEngineeringAndEquipmentCenter, DeclareRealtyLandCert>> entryIterator = relatedMap.entrySet().iterator();
            while (entryIterator.hasNext()) {
                Map.Entry<DeclareBuildEngineeringAndEquipmentCenter, DeclareRealtyLandCert> realtyHouseCertEntry = entryIterator.next();
                DeclareBuildEngineeringAndEquipmentCenter equipmentCenter = realtyHouseCertEntry.getKey();
                equipmentCenter.setHouseId(id);
                declareBuildEngineeringAndEquipmentCenterService.saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(equipmentCenter);
            }
            relatedMap.clear();
            //-----------------------------------end----------------------------------
        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, stringBuilder.toString());
    }


    /**
     * 功能描述: 导入土地证
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/9/25 18:30
     */
    public String importData(DeclareRealtyLandCert declareRealtyLandCert, MultipartFile multipartFile) throws Exception {
        Workbook workbook = null;
        Row row = null;
        StringBuilder stringBuilder = new StringBuilder();
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
        int startRowNumber = 2;
        //导入成功数据条数
        int successCount = 0;
        //总行数
        int rowLength = sheet.getPhysicalNumberOfRows() != 0 ? sheet.getPhysicalNumberOfRows() : sheet.getLastRowNum();
        rowLength = rowLength - startRowNumber;
        if (rowLength == 0) {
            stringBuilder.append("没有数据!");
            return stringBuilder.toString();
        }
        Multimap<String, Map.Entry<Class<?>, Integer>> classArrayListMultimap = declarePublicService.getMultimapByClass(DeclareRealtyLandCert.class, row);
        for (int i = startRowNumber; i < startRowNumber + rowLength; i++) {
            DeclareRealtyLandCert realtyLandCert = null;
            try {
                realtyLandCert = new DeclareRealtyLandCert();
                row = sheet.getRow(i);
                if (row == null) {
                    declarePublicService.excelImportWriteErrorInfo(i, "没有数据", stringBuilder);
                    continue;
                }
                BeanUtils.copyProperties(declareRealtyLandCert, realtyLandCert);
                realtyLandCert.setId(null);
                if (!declarePublicService.land(classArrayListMultimap, realtyLandCert, stringBuilder, row)) {
                    continue;
                }
                realtyLandCert.setCreator(commonService.thisUserAccount());
                realtyLandCert.setPid(0);
                realtyLandCert.setEnable(DeclareTypeEnum.MasterData.getKey());
                int count = declarePublicService.getCountByPlanDetailsIdGetAutoInitNumberSize(realtyLandCert.getPlanDetailsId(), realtyLandCert.getAutoInitNumber());
                if (count > 0) {
                    declarePublicService.excelImportWriteErrorInfo(i, "编号重复", stringBuilder);
                    continue;
                }
                saveDeclareRealtyLandCert(realtyLandCert, true);
                DeclareBuildEngineeringAndEquipmentCenter center = new DeclareBuildEngineeringAndEquipmentCenter();
                center.setPlanDetailsId(declareRealtyLandCert.getPlanDetailsId());
                center.setLandId(realtyLandCert.getId());
                center.setType(DeclareRealtyLandCert.class.getSimpleName());
                declareBuildEngineeringAndEquipmentCenterService.saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(center);
                successCount++;
            } catch (Exception e) {
                declarePublicService.excelImportWriteErrorInfo(i, e.getMessage(), stringBuilder);
            }
        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, stringBuilder.toString());
    }


    public Integer saveAndUpdateDeclareRealtyLandCert(DeclareRealtyLandCert declareRealtyLandCert) {
        return saveAndUpdateDeclareRealtyLandCert(declareRealtyLandCert, false);
    }

    public Integer saveAndUpdateDeclareRealtyLandCert(DeclareRealtyLandCert declareRealtyLandCert, boolean updateNull) {
        if (declareRealtyLandCert.getId() == null || declareRealtyLandCert.getId() == 0) {
            if (StringUtils.isBlank(declareRealtyLandCert.getCreator())) {
                declareRealtyLandCert.setCreator(commonService.thisUserAccount());
            }
            if (declareRealtyLandCert.getAutoInitNumber() == null && com.google.common.base.Objects.equal(DeclareTypeEnum.MasterData.getKey(), declareRealtyLandCert.getEnable())) {
                declareRealtyLandCert.setAutoInitNumber(declarePublicService.getCountByPlanDetailsIdGetMaxAutoInitNumber(declareRealtyLandCert.getPlanDetailsId()));
            }
            declareRealtyLandCertDao.addDeclareRealtyLandCert(declareRealtyLandCert);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class), declareRealtyLandCert.getId());
            return declareRealtyLandCert.getId();
        } else {
            declareRealtyLandCertDao.updateDeclareRealtyLandCert(declareRealtyLandCert, updateNull);
            syncUpdateDeclareAndJudge(declareRealtyLandCert);
            return declareRealtyLandCert.getId();
        }
    }

    public void saveDeclareRealtyLandCert(DeclareRealtyLandCert declareRealtyLandCert, boolean updateNull) {
        if (declareRealtyLandCert.getId() == null || declareRealtyLandCert.getId() == 0) {
            if (StringUtils.isBlank(declareRealtyLandCert.getCreator())) {
                declareRealtyLandCert.setCreator(commonService.thisUserAccount());
            }
            if (declareRealtyLandCert.getAutoInitNumber() == null && com.google.common.base.Objects.equal(DeclareTypeEnum.MasterData.getKey(), declareRealtyLandCert.getEnable())) {
                declareRealtyLandCert.setAutoInitNumber(declarePublicService.getCountByPlanDetailsIdGetMaxAutoInitNumber(declareRealtyLandCert.getPlanDetailsId()));
            }
            declareRealtyLandCertDao.addDeclareRealtyLandCert(declareRealtyLandCert);
        } else {
            declareRealtyLandCertDao.updateDeclareRealtyLandCert(declareRealtyLandCert, updateNull);
            syncUpdateDeclareAndJudge(declareRealtyLandCert);
        }
    }

    private void syncUpdateDeclareAndJudge(DeclareRealtyLandCert declareRealtyLandCert) {
        if (declareRealtyLandCert == null) return;
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(declareRealtyLandCert.getPlanDetailsId());
        if (projectPlanDetails == null) return;
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordByDataTableId(FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class), declareRealtyLandCert.getId());
        if (declareRecord == null) return;
        setDeclareRecordProperties(declareRealtyLandCert, declareRecord, projectPlanDetails.getProjectId());
        try {
            declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
            declareRecordService.updateJudgeObjectDeclareInfo(declareRecord, projectPlanDetails.getProjectId());
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
        }
    }

    public DeclareRealtyLandCert getDeclareRealtyLandCertById(Integer id) {
        return declareRealtyLandCertDao.getDeclareRealtyLandCertById(id);
    }

    public BootstrapTableVo getDeclareRealtyLandCertListVos(DeclareRealtyLandCert declareRealtyLandCert) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareRealtyLandCertVo> vos = lists(declareRealtyLandCert);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DeclareRealtyLandCertVo> getList(Integer planDetailsId) {
        List<DeclareRealtyLandCert> declareRealtyLandCerts = declareRealtyLandCertDao.getList(planDetailsId);
        List<DeclareRealtyLandCertVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(declareRealtyLandCerts)) {
            for (DeclareRealtyLandCert landLevel : declareRealtyLandCerts) {
                vos.add(getDeclareRealtyLandCertVo(landLevel));
            }
        }
        return vos;
    }

    public List<DeclareRealtyLandCertVo> lists(DeclareRealtyLandCert declareRealtyLandCert) {
        List<DeclareRealtyLandCert> declareRealtyLandCerts = declareRealtyLandCertDao.getDeclareRealtyLandCertList(declareRealtyLandCert);
        List<DeclareRealtyLandCertVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(declareRealtyLandCerts)) {
            for (DeclareRealtyLandCert landLevel : declareRealtyLandCerts) {
                vos.add(getDeclareRealtyLandCertVo(landLevel));
            }
        }
        return vos;
    }


    public boolean deleteDeclareRealtyLandCertById(Integer id) {
        return declareRealtyLandCertDao.deleteDeclareRealtyLandCertById(id);
    }

    public boolean deleteDeclareRealtyLandCertById(List<Integer> ids){
        return declareRealtyLandCertDao.deleteDeclareRealtyLandCertById(ids);
    }

    public DeclareRealtyLandCertVo getDeclareRealtyLandCertVo(DeclareRealtyLandCert declareRealtyLandCert) {
        if (declareRealtyLandCert == null) {
            return null;
        }
        DeclareRealtyLandCertVo vo = new DeclareRealtyLandCertVo();
        BeanUtils.copyProperties(declareRealtyLandCert, vo);
        if (declareRealtyLandCert.getTerminationDate() != null) {
            vo.setTerminationDateFmt(DateUtils.formatDate(declareRealtyLandCert.getTerminationDate()));
        }
        vo.setLandRightTypeName(baseDataDicService.getNameById(declareRealtyLandCert.getLandRightType()));
        vo.setLandRightNatureName(baseDataDicService.getNameById(declareRealtyLandCert.getLandRightNature()));
        String purposeName = baseDataDicService.getNameById(declareRealtyLandCert.getCertUse());
        if (StringUtils.isNotEmpty(purposeName)) {
            vo.setPurposeName(purposeName);
        } else {
            vo.setPurposeName(declareRealtyLandCert.getCertUse());
        }
        vo.setPublicSituationName(baseDataDicService.getNameById(declareRealtyLandCert.getPublicSituation()));
        String certUseCategoryName = baseDataDicService.getNameById(declareRealtyLandCert.getCertUseCategory());
        if (StringUtils.isNotEmpty(certUseCategoryName)) {
            vo.setCertUseCategoryName(certUseCategoryName);
        } else {
            vo.setCertUseCategoryName(declareRealtyLandCert.getCertUseCategory());
        }
        if (StringUtils.isNotBlank(declareRealtyLandCert.getProvince())) {
            if (NumberUtils.isNumber(declareRealtyLandCert.getProvince())) {
                //省
                vo.setProvinceName(erpAreaService.getSysAreaName(declareRealtyLandCert.getProvince()));
            } else {
                vo.setProvinceName(declareRealtyLandCert.getProvince());
            }
        }
        if (StringUtils.isNotBlank(declareRealtyLandCert.getCity())) {
            if (NumberUtils.isNumber(declareRealtyLandCert.getCity())) {
                //市
                vo.setCityName(erpAreaService.getSysAreaName(declareRealtyLandCert.getCity()));
            } else {
                vo.setCityName(declareRealtyLandCert.getCity());
            }
        }
        if (StringUtils.isNotBlank(declareRealtyLandCert.getDistrict())) {
            if (NumberUtils.isNumber(declareRealtyLandCert.getDistrict())) {
                //县或者区
                vo.setDistrictName(erpAreaService.getSysAreaName(declareRealtyLandCert.getDistrict()));
            } else {
                vo.setDistrictName(declareRealtyLandCert.getDistrict());
            }
        }
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(declareRealtyLandCert.getId(), null, FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class));
        StringBuilder builder = new StringBuilder();
        if (!ObjectUtils.isEmpty(sysAttachmentDtos)) {
            if (sysAttachmentDtos.size() >= 1) {
                for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtos) {
                    if (sysAttachmentDto != null) {
                        builder.append(baseAttachmentService.getViewHtml(sysAttachmentDto));
                        builder.append(" ");
                    }
                }
            }
            vo.setFileViewName(builder.toString());
        }
        DeclareBuildEngineeringAndEquipmentCenter centerQuery = new DeclareBuildEngineeringAndEquipmentCenter();
        centerQuery.setLandId(declareRealtyLandCert.getId());
        centerQuery.setPlanDetailsId(declareRealtyLandCert.getPlanDetailsId());
        centerQuery.setType(DeclareRealtyLandCert.class.getSimpleName());
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
        DeclareRealtyLandCert query = new DeclareRealtyLandCert();
        query.setPlanDetailsId(declareApply.getPlanDetailsId());
        query.setEnable(DeclareTypeEnum.MasterData.getKey());
        query.setBisRecord(false);
        List<DeclareRealtyLandCert> lists = declareRealtyLandCertDao.getDeclareRealtyLandCertList(query);
        if (CollectionUtils.isEmpty(lists)) return;
        for (DeclareRealtyLandCert oo : lists) {
            declareRecord = new DeclareRecord();
            setDeclareRecordProperties(oo, declareRecord, declareApply.getProjectId());
            declareRecord.setInventoryContentKey(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT);
            declareRecord.setCreator(declareApply.getCreator());
            declareRecord.setBisPartIn(true);
            declareRecord.setType(DeclareCertificateTypeEnum.LAND.getKey());
            declareRecord.setNumber(oo.getAutoInitNumber());
            try {
                int declareId = declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
                DeclareRecordExtend declareRecordExtend = new DeclareRecordExtend();
                declareRecordExtend.setCreator(commonService.thisUserAccount());
                declareRecordExtend.setRegistrationAuthority(oo.getRegistrationAuthority());
                declareRecordExtend.setDeclareId(declareId);
                declareRecordExtendService.addDeclareRecord(declareRecordExtend);
                oo.setBisRecord(true);
                declareRealtyLandCertDao.updateDeclareRealtyLandCert(oo);
            } catch (Exception e1) {
                baseService.writeExceptionInfo(e1, "土地证写入申报记录表");
            }
        }
    }

    private void setDeclareRecordProperties(DeclareRealtyLandCert oo, DeclareRecord declareRecord, Integer projectId) {
        BeanUtils.copyProperties(oo,declareRecord,BaseConstant.ASSESS_IGNORE_ARRAY);
        declareRecord.setName(oo.getLandCertName());
        declareRecord.setProjectId(projectId);
        declareRecord.setDataTableName(FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class));
        declareRecord.setDataTableId(oo.getId());
        declareRecord.setRegistrationDate(oo.getRegistrationDate());
        declareRecord.setOwnership(oo.getOwnership());
        declareRecord.setPublicSituation(baseDataDicService.getNameById(oo.getPublicSituation()));
        declareRecord.setSeat(oo.getBeLocated());
        declareRecord.setStreetNumber(oo.getStreetNumber());
        declareRecord.setAttachedNumber(oo.getAttachedNumber());
        declareRecord.setBuildingNumber(oo.getBuildingNumber());
        declareRecord.setUnit(oo.getUnit());
        declareRecord.setFloor(oo.getFloor());
        declareRecord.setRoomNumber(oo.getRoomNumber());
        declareRecord.setLandCertUse(oo.getCertUse());
        declareRecord.setLandCertUseCategory(oo.getCertUseCategory());
        if (oo.getLandRightType() != null) {//权利类型
            BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(oo.getLandRightType());
            if (baseDataDic != null) {
                if (StringUtils.isNotEmpty(baseDataDic.getRemark())) {
                    declareRecord.setLandRightType(baseDataDic.getRemark());
                } else {
                    declareRecord.setLandRightType(baseDataDic.getName());
                }
            }
        }
        declareRecord.setLandRightNature(baseDataDicService.getNameById(oo.getLandRightNature()));//权利性质
        declareRecord.setFloorArea(oo.getUseRightArea());
        declareRecord.setLandUseEndDate(oo.getTerminationDate());
        if (oo.getLandCertGetQuestion() != null) {
            String name = baseDataDicService.getNameById(oo.getLandCertGetQuestion());
            if (Objects.equal(name, BaseConstant.ASSESS_CertGetQuestion_YES_NAME)) {
                declareRecord.setHasCert(true);
                declareRecord.setDataFromType("土地证");
            }
            if (Objects.equal(name, BaseConstant.ASSESS_CertGetQuestion_NO_NAME)) {
                declareRecord.setHasCert(false);
                declareRecord.setDataFromType(oo.getLandCertName());
            }
        }
        DeclareRealtyHouseCert realtyHouseCert = null;
        if (oo.getPid() != null && oo.getPid() != 0) {
            realtyHouseCert = declareRealtyHouseCertDao.getDeclareRealtyHouseCertById(oo.getPid());
        }
        DeclareBuildEngineeringAndEquipmentCenter centerQuery = new DeclareBuildEngineeringAndEquipmentCenter();
        centerQuery.setLandId(oo.getId());
        centerQuery.setPlanDetailsId(oo.getPlanDetailsId());
        centerQuery.setType(DeclareRealtyHouseCert.class.getSimpleName());
        List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(centerQuery);
        if (CollectionUtils.isNotEmpty(centerList)) {
            if (centerList.stream().anyMatch(obj -> obj.getHouseId() != null)) {
                realtyHouseCert = declareRealtyHouseCertDao.getDeclareRealtyHouseCertById(centerList.stream().filter(obj -> obj.getHouseId() != null).findFirst().get().getHouseId());
            }
        }
        if (realtyHouseCert != null) {
            //写入房产证的证载用途
            declareRecord.setCertUse(realtyHouseCert.getCertUse());
            declareRecord.setHousingStructure(realtyHouseCert.getHousingStructure());
            declareRecord.setNature(baseDataDicService.getNameById(realtyHouseCert.getNature()));
        }
    }

    public void changeAutoInitNumber(Integer autoInitNumber, Integer id) {
        DeclareRealtyLandCert declareRealtyLandCert = getDeclareRealtyLandCertById(id);
        if (declareRealtyLandCert == null) {
            return;
        }
        declareRealtyLandCert.setAutoInitNumber(autoInitNumber);

        saveAndUpdateDeclareRealtyLandCert(declareRealtyLandCert, true);
        DeclareBuildEngineeringAndEquipmentCenter query = new DeclareBuildEngineeringAndEquipmentCenter();
        query.setLandId(declareRealtyLandCert.getId());
        query.setPlanDetailsId(declareRealtyLandCert.getPlanDetailsId());
        query.setType(DeclareRealtyLandCert.class.getSimpleName());
        List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(query);
        if (CollectionUtils.isEmpty(centerList)) {
            return;
        }
        Iterator<DeclareBuildEngineeringAndEquipmentCenter> iterator = centerList.iterator();
        while (iterator.hasNext()) {
            DeclareBuildEngineeringAndEquipmentCenter equipmentCenter = iterator.next();
            if (equipmentCenter.getHouseId() == null || equipmentCenter.getHouseId() == 0) {
                continue;
            }
            DeclareRealtyHouseCert declareRealtyHouseCert = declareRealtyHouseCertDao.getDeclareRealtyHouseCertById(equipmentCenter.getHouseId());
            if (declareRealtyHouseCert == null) {
                continue;
            }
            declareRealtyHouseCert.setAutoInitNumber(autoInitNumber);

            declareRealtyHouseCertDao.updateDeclareRealtyHouseCert(declareRealtyHouseCert, true);
        }
    }

    public List<DeclareRealtyLandCert> getDataIds(List<Integer> dataIds) {
        return declareRealtyLandCertDao.getDataIds(dataIds);
    }
}
