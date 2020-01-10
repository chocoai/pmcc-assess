package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.common.enums.AssessProjectTypeEnum;
import com.copower.pmcc.assess.common.enums.DeclareCertificateTypeEnum;
import com.copower.pmcc.assess.common.enums.DeclareTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRealtyRealEstateCertDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRealtyRealEstateCertVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
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
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
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
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/19 10:03
 * @Description:不动产证
 */
@Service
public class DeclareRealtyRealEstateCertService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeclareRealtyRealEstateCertDao declareRealtyRealEstateCertDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private DeclarePublicService declarePublicService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private DeclareRecordExtendService declareRecordExtendService;
    @Autowired
    private DeclareBuildEngineeringAndEquipmentCenterService declareBuildEngineeringAndEquipmentCenterService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private BaseService baseService;

    public void attachmentAutomatedWarrants(DeclarePublicService.AutomatedWarrants automatedWarrants)throws Exception{
        declarePublicService.attachmentAutomatedWarrants(automatedWarrants);
    }

    public String importData(DeclareRealtyRealEstateCert declareRealtyRealEstateCert, MultipartFile multipartFile) throws Exception {
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
        //读取数据的起始行
        int startRowNumber = 1;
        //导入成功数据条数
        int successCount = 0;
        //工作表的第一行
        row = sheet.getRow(0);
        //总列数
        int colLength = row.getPhysicalNumberOfCells() != 0 ? row.getPhysicalNumberOfCells() : row.getLastCellNum();
        //总行数
        int rowLength = sheet.getPhysicalNumberOfRows() != 0 ? sheet.getPhysicalNumberOfRows() : sheet.getLastRowNum();
        rowLength = rowLength - startRowNumber;
        if (rowLength == 0) {
            builder.append("没有数据!");
            return builder.toString();
        }
        //----------------------------||----------------------
        for (int i = startRowNumber; i <= rowLength; i++) {
            //标识符
            boolean flag = true;
            DeclareRealtyRealEstateCert oo = null;
            try {
                row = sheet.getRow(i);
                if (row == null) {
                    builder.append(String.format("\n第%s行异常：%s", i, "没有数据"));
                    continue;
                }
                oo = new DeclareRealtyRealEstateCert();
                BeanUtils.copyProperties(declareRealtyRealEstateCert, oo);
                oo.setEnable(DeclareTypeEnum.MasterData.getKey());
                oo.setId(null);
                //excel处理
                if (!declarePublicService.realEstateCert(oo, builder, row, i)) {
                    continue;
                }
            } catch (Exception e) {
                flag = false;
                builder.append(String.format("\n第%s行异常：%s", i, e.getMessage()));
            }
            if (flag) {
                oo.setCreator(commonService.thisUserAccount());
                declareRealtyRealEstateCertDao.addDeclareRealtyRealEstateCert(oo);
                DeclareBuildEngineeringAndEquipmentCenter center = new DeclareBuildEngineeringAndEquipmentCenter();
                center.setPlanDetailsId(oo.getPlanDetailsId());
                center.setRealEstateId(oo.getId());
                center.setType(DeclareRealtyRealEstateCert.class.getSimpleName());
                declareBuildEngineeringAndEquipmentCenterService.saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(center);
                successCount++;
            }
        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }

    public Integer saveAndUpdateDeclareRealtyRealEstateCert(DeclareRealtyRealEstateCert declareRealtyRealEstateCert) {
        return saveAndUpdateDeclareRealtyRealEstateCert(declareRealtyRealEstateCert, false);
    }

    public Integer saveAndUpdateDeclareRealtyRealEstateCert(DeclareRealtyRealEstateCert declareRealtyRealEstateCert, boolean updateNull) {
        if (declareRealtyRealEstateCert.getId() == null) {
            declareRealtyRealEstateCert.setCreator(commonService.thisUserAccount());
            Integer id = declareRealtyRealEstateCertDao.addDeclareRealtyRealEstateCert(declareRealtyRealEstateCert);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class), id);
            return id;
        } else {
            declareRealtyRealEstateCertDao.updateDeclareRealtyRealEstateCert(declareRealtyRealEstateCert, updateNull);
            updateDeclareRealtyRealEstateCertAndUpdateDeclareRecordOrJudgeObject(declareRealtyRealEstateCert);
            return null;
        }
    }

    private void updateDeclareRealtyRealEstateCertAndUpdateDeclareRecordOrJudgeObject(DeclareRealtyRealEstateCert declareRealtyRealEstateCert) {
        if (declareRealtyRealEstateCert == null) {
            return;
        }
        if (declareRealtyRealEstateCert.getId() == null) {
            return;
        }
        if (declareRealtyRealEstateCert.getId() == 0) {
            return;
        }
        DeclareRealtyRealEstateCert oo = getDeclareRealtyRealEstateCertById(declareRealtyRealEstateCert.getId());
        if (oo == null) {
            return;
        }
        declareRealtyRealEstateCert.setBisRecord(oo.getBisRecord());
        declareRealtyRealEstateCert.setPlanDetailsId(oo.getPlanDetailsId());
        if (declareRealtyRealEstateCert.getPlanDetailsId() == null) {
            return;
        }
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(declareRealtyRealEstateCert.getPlanDetailsId());
        if (projectPlanDetails == null) {
            return;
        }
        if (declareRealtyRealEstateCert.getBisRecord() == null) {
            return;
        }
        if (!declareRealtyRealEstateCert.getBisRecord()) {
            return;
        }
        List<DeclareRecord> declareRecordList = declareRecordService.getDeclareRecordListByDataTableId(FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class), declareRealtyRealEstateCert.getId(), projectPlanDetails.getProjectId());
        //当明确是更新的时候确找不到申报id那么则不再更新这条数据
        if (CollectionUtils.isEmpty(declareRecordList)) {
            return;
        }
        DeclareRecord declareRecord = new DeclareRecord();
        setDeclareRealtyRealEstateCertForDeclareRecordProperties(declareRealtyRealEstateCert, declareRecord, projectPlanDetails.getProjectId());
        declareRecord.setId(declareRecordList.stream().findFirst().get().getId());
        try {
            declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
            declareRecordService.reStartDeclareApplyByDeclareRecordId(Arrays.asList(declareRecord.getId()), projectPlanDetails.getProjectId());
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
        }
    }

    public DeclareRealtyRealEstateCert getDeclareRealtyRealEstateCertById(Integer id) {
        return declareRealtyRealEstateCertDao.getDeclareRealtyRealEstateCertById(id);
    }

    public BootstrapTableVo getDeclareRealtyRealEstateCertListVos(DeclareRealtyRealEstateCert declareRealtyRealEstateCert) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareRealtyRealEstateCertVo> vos = landLevels(declareRealtyRealEstateCert);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DeclareRealtyRealEstateCertVo> landLevels(DeclareRealtyRealEstateCert declareRealtyRealEstateCert) {
        List<DeclareRealtyRealEstateCert> declareRealtyRealEstateCerts = declareRealtyRealEstateCertDao.getDeclareRealtyRealEstateCertList(declareRealtyRealEstateCert);
        List<DeclareRealtyRealEstateCertVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(declareRealtyRealEstateCerts)) {
            for (DeclareRealtyRealEstateCert landLevel : declareRealtyRealEstateCerts) {
                vos.add(getDeclareRealtyRealEstateCertVo(landLevel));
            }
        }
        return vos;
    }


    public boolean deleteDeclareRealtyRealEstateCertById(Integer id) {
        return declareRealtyRealEstateCertDao.deleteDeclareRealtyRealEstateCertById(id);
    }

    public DeclareRealtyRealEstateCertVo getDeclareRealtyRealEstateCertVo(DeclareRealtyRealEstateCert declareRealtyRealEstateCert) {
        if (declareRealtyRealEstateCert == null) {
            return null;
        }
        DeclareRealtyRealEstateCertVo vo = new DeclareRealtyRealEstateCertVo();
        BeanUtils.copyProperties(declareRealtyRealEstateCert, vo);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (declareRealtyRealEstateCert.getUseEndDate() != null) {
            vo.setUseEndDateFmt(sdf.format(declareRealtyRealEstateCert.getUseEndDate()));
        }
        if (declareRealtyRealEstateCert.getRegistrationTime() != null) {
            vo.setRegistrationTimeFmt(sdf.format(declareRealtyRealEstateCert.getRegistrationTime()));
        }
        if (declareRealtyRealEstateCert.getUseStartDate() != null) {
            vo.setUseStartDateFmt(sdf.format(declareRealtyRealEstateCert.getUseStartDate()));
        }
//        if (declareRealtyRealEstateCert.getHouseCertUseCategory() != null) {
//            vo.setHouseCertUseCategoryName(baseDataDicService.getNameById(declareRealtyRealEstateCert.getHouseCertUseCategory()));
//        }
        if (StringUtils.isNotBlank(declareRealtyRealEstateCert.getProvince())) {
            if (NumberUtils.isNumber(declareRealtyRealEstateCert.getProvince())) {
                //省
                vo.setProvinceName(erpAreaService.getSysAreaName(declareRealtyRealEstateCert.getProvince()));
            } else {
                vo.setProvinceName(declareRealtyRealEstateCert.getProvince());
            }
        }
        if (StringUtils.isNotBlank(declareRealtyRealEstateCert.getCity())) {
            if (NumberUtils.isNumber(declareRealtyRealEstateCert.getCity())) {
                //市
                vo.setCityName(erpAreaService.getSysAreaName(declareRealtyRealEstateCert.getCity()));
            } else {
                vo.setCityName(declareRealtyRealEstateCert.getCity());
            }
        }
        if (StringUtils.isNotBlank(declareRealtyRealEstateCert.getDistrict())) {
            if (NumberUtils.isNumber(declareRealtyRealEstateCert.getDistrict())) {
                //县或者县
                vo.setDistrictName(erpAreaService.getSysAreaName(declareRealtyRealEstateCert.getDistrict()));
            } else {
                vo.setDistrictName(declareRealtyRealEstateCert.getDistrict());
            }
        }
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(declareRealtyRealEstateCert.getId(), null, FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class));
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
        vo.setPlanningUseName(baseDataDicService.getNameById(declareRealtyRealEstateCert.getHouseCertUse()));
        String certUseCategoryName = baseDataDicService.getNameById(declareRealtyRealEstateCert.getLandCertUseCategory());
        if (StringUtils.isNotEmpty(certUseCategoryName)) {
            vo.setLandCertUseCategoryName(certUseCategoryName);
        } else {
            vo.setLandCertUseCategoryName(declareRealtyRealEstateCert.getLandCertUseCategory());
        }
        String purposeName = baseDataDicService.getNameById(declareRealtyRealEstateCert.getLandCertUse());
        if (StringUtils.isNotEmpty(purposeName)) {
            vo.setPurposeName(purposeName);
        } else {
            vo.setPurposeName(declareRealtyRealEstateCert.getLandCertUse());
        }
        vo.setTypeName(baseDataDicService.getNameById(declareRealtyRealEstateCert.getLandRightType()));
        vo.setPublicSituationName(baseDataDicService.getNameById(declareRealtyRealEstateCert.getPublicSituation()));
        vo.setUseRightTypeName(baseDataDicService.getNameById(declareRealtyRealEstateCert.getLandRightNature()));
        vo.setNatureName(baseDataDicService.getNameById(declareRealtyRealEstateCert.getNature()));

        DeclareBuildEngineeringAndEquipmentCenter centerQuery = new DeclareBuildEngineeringAndEquipmentCenter();
        centerQuery.setRealEstateId(declareRealtyRealEstateCert.getId());
        centerQuery.setPlanDetailsId(declareRealtyRealEstateCert.getPlanDetailsId());
        centerQuery.setType(DeclareRealtyRealEstateCert.class.getSimpleName());
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
        if (declareApply == null) {
            return;
        }
        DeclareRealtyRealEstateCert query = new DeclareRealtyRealEstateCert();
        query.setPlanDetailsId(declareApply.getPlanDetailsId());
        query.setEnable(DeclareTypeEnum.MasterData.getKey());
        query.setBisRecord(false);
        List<DeclareRealtyRealEstateCert> lists = declareRealtyRealEstateCertDao.getDeclareRealtyRealEstateCertList(query);
        if (CollectionUtils.isEmpty(lists)) return;
        AssessProjectTypeEnum projectTypeEnum = projectInfoService.getAssessProjectType(projectInfoService.getProjectInfoById(declareApply.getProjectId()).getProjectCategoryId());
        for (DeclareRealtyRealEstateCert declareRealtyRealEstateCert : lists) {
            declareRecord = new DeclareRecord();
            setDeclareRealtyRealEstateCertForDeclareRecordProperties(declareRealtyRealEstateCert, declareRecord, declareApply.getProjectId());
            declareRecord.setInventoryContentKey(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT);
            declareRecord.setCreator(declareApply.getCreator());
            declareRecord.setBisPartIn(true);
            declareRecord.setType(DeclareCertificateTypeEnum.REAL_ESTATE.getKey());
            declareRecord.setNumber(declareRecordService.getMaxNumber(declareApply.getProjectId()));
            //项目为房产则取房产的证载面积  项目为土地则去土地的宗地面积
            if (AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_HOUSE.equals(projectTypeEnum))
                declareRecord.setFloorArea(declareRealtyRealEstateCert.getEvidenceArea());
            if (AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_LAND.equals(projectTypeEnum))
                declareRecord.setFloorArea(declareRealtyRealEstateCert.getUseRightArea());
            try {
                int declareId = declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
                DeclareRecordExtend declareRecordExtend = new DeclareRecordExtend();
                declareRecordExtend.setCreator(commonService.thisUserAccount());
                declareRecordExtend.setRegistrationAuthority(declareRealtyRealEstateCert.getRegistrationAuthority());
                declareRecordExtend.setDeclareId(declareId);
                declareRecordExtendService.addDeclareRecord(declareRecordExtend);
                declareRealtyRealEstateCert.setBisRecord(true);
                declareRealtyRealEstateCertDao.updateDeclareRealtyRealEstateCert(declareRealtyRealEstateCert);
            } catch (Exception e1) {
                baseService.writeExceptionInfo(e1, "申报");
            }
        }
    }

    private void setDeclareRealtyRealEstateCertForDeclareRecordProperties(DeclareRealtyRealEstateCert declareRealtyRealEstateCert, DeclareRecord declareRecord, Integer projectId) {
        BeanUtils.copyProperties(declareRealtyRealEstateCert, declareRecord);
        declareRecord.setId(null);
        declareRecord.setProjectId(projectId);
        declareRecord.setDataTableName(FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class));
        declareRecord.setDataTableId(declareRealtyRealEstateCert.getId());
        declareRecord.setName(declareRealtyRealEstateCert.getCertName());
        declareRecord.setOwnership(declareRealtyRealEstateCert.getOwnership());
        declareRecord.setPublicSituation(baseDataDicService.getNameById(declareRealtyRealEstateCert.getPublicSituation()));
        declareRecord.setRegistrationDate(declareRealtyRealEstateCert.getRegistrationDate());
        if (declareRecord.getRegistrationDate() == null) {
            declareRecord.setRegistrationDate(declareRealtyRealEstateCert.getRegistrationTime());
        }
        declareRecord.setSeat(declareRealtyRealEstateCert.getBeLocated());
        declareRecord.setStreetNumber(declareRealtyRealEstateCert.getStreetNumber());
        declareRecord.setAttachedNumber(declareRealtyRealEstateCert.getAttachedNumber());
        declareRecord.setBuildingNumber(declareRealtyRealEstateCert.getBuildingNumber());
        declareRecord.setUnit(declareRealtyRealEstateCert.getUnit());
        declareRecord.setFloor(declareRealtyRealEstateCert.getFloor());
        declareRecord.setRoomNumber(declareRealtyRealEstateCert.getRoomNumber());
        declareRecord.setCertUse(declareRealtyRealEstateCert.getHouseCertUseCategory());
        declareRecord.setHousingStructure(declareRealtyRealEstateCert.getHousingStructure());
        declareRecord.setNature(baseDataDicService.getNameById(declareRealtyRealEstateCert.getNature()));
        declareRecord.setLandCertUse(declareRealtyRealEstateCert.getLandCertUse());
        declareRecord.setLandCertUseCategory(declareRealtyRealEstateCert.getLandCertUseCategory());

        if (declareRealtyRealEstateCert.getLandRightType() != null) {//权利类型
            BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(declareRealtyRealEstateCert.getLandRightType());
            if (baseDataDic != null) {
                if (StringUtils.isNotEmpty(baseDataDic.getRemark())) {
                    declareRecord.setLandRightType(baseDataDic.getRemark());
                } else {
                    declareRecord.setLandRightType(baseDataDic.getName());
                }
            }
        }

        declareRecord.setLandRightNature(baseDataDicService.getNameById(declareRealtyRealEstateCert.getLandRightNature()));//权利性质
        declareRecord.setLandUseEndDate(declareRealtyRealEstateCert.getUseEndDate());
        declareRecord.setLandUseRightArea(declareRealtyRealEstateCert.getUseRightArea());

        if (declareRealtyRealEstateCert.getLandCertGetQuestion() != null) {
            String name = baseDataDicService.getNameById(declareRealtyRealEstateCert.getLandCertGetQuestion());
            if (Objects.equal(name, BaseConstant.ASSESS_CertGetQuestion_YES_NAME)) {
                declareRecord.setHasCert(true);
                declareRecord.setDataFromType("不动产证");
            }
            if (Objects.equal(name, BaseConstant.ASSESS_CertGetQuestion_NO_NAME)) {
                declareRecord.setHasCert(false);
                declareRecord.setDataFromType(declareRealtyRealEstateCert.getCertName());
            }
        }
    }

}
