package com.copower.pmcc.assess.service.project.survey;


import com.copower.pmcc.assess.common.enums.AssessProjectTypeEnum;
import com.copower.pmcc.assess.common.enums.DeclareCertificateTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryContentDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.basic.BasicBuildingVo;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyAssetInventoryContentVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchDetailService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.BasicBuildingService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.declare.DeclareBuildEngineeringAndEquipmentCenterService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyHouseCertService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyLandCertService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SurveyAssetInventoryContentService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SurveyAssetInventoryContentDao surveyAssetInventoryContentDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private SurveyAssetInventoryContentService surveyAssetInventoryContentService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private DeclareBuildEngineeringAndEquipmentCenterService declareBuildEngineeringAndEquipmentCenterService;
    @Autowired
    private SurveyAssetInfoItemService surveyAssetInfoItemService;
    @Autowired
    private DeclareRealtyLandCertService declareRealtyLandCertService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;

    public BootstrapTableVo getList(Integer planDetailsId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SurveyAssetInventoryContent> surveyAssetInventoryContentsList = surveyAssetInventoryContentDao.getSurveyAssetInventoryContent(planDetailsId);
        List<SurveyAssetInventoryContentVo> surveyAssetInventoryContentVos = getVoList(surveyAssetInventoryContentsList);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(surveyAssetInventoryContentVos) ? new ArrayList<SurveyAssetInventoryContent>() : surveyAssetInventoryContentVos);
        return vo;
    }

    public List<SurveyAssetInventoryContent> getContentListByPlanDetailsId(Integer planDetailsId) {
        List<SurveyAssetInventoryContent> surveyAssetInventoryContentsList = surveyAssetInventoryContentDao.getSurveyAssetInventoryContent(planDetailsId);
        return surveyAssetInventoryContentsList;
    }

    public List<SurveyAssetInventoryContentVo> getVoList(List<SurveyAssetInventoryContent> list) {
        if (CollectionUtils.isEmpty(list)) return null;
        return LangUtils.transform(list, p -> {
            SurveyAssetInventoryContentVo surveyAssetInventoryContentVo = new SurveyAssetInventoryContentVo();
            BeanUtils.copyProperties(p, surveyAssetInventoryContentVo);
            surveyAssetInventoryContentVo.setInventoryContentName(baseDataDicService.getNameById(p.getInventoryContent()));
            return surveyAssetInventoryContentVo;
        });
    }

    public void saveAssetInventoryContent(SurveyAssetInventoryContent surveyAssetInventoryContent) throws BusinessException {
        if (surveyAssetInventoryContent == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (surveyAssetInventoryContent.getId() != null && surveyAssetInventoryContent.getId() > 0) {
            surveyAssetInventoryContentDao.update(surveyAssetInventoryContent);
        } else {
            surveyAssetInventoryContent.setCreator(commonService.thisUserAccount());
            surveyAssetInventoryContentDao.add(surveyAssetInventoryContent);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(SurveyAssetInventoryContent.class), surveyAssetInventoryContent.getId());
        }
    }

    public boolean delete(Integer id) throws BusinessException {
        if (id == null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        return surveyAssetInventoryContentDao.delete(id);
    }

    /**
     * 初始化资产清查项
     *
     * @param projectPlanDetails
     * @param declareRecord
     * @return
     */
    public List<SurveyAssetInventoryContent> initAssetInventoryContent(ProjectPlanDetails projectPlanDetails, DeclareRecord declareRecord) {
        return initAssetInventoryContentNew(projectPlanDetails.getId(), projectPlanDetails.getProjectId(), null, declareRecord);
    }

    public List<SurveyAssetInventoryContent> getSurveyAssetInventoryContentListByMasterId(Integer masterId) {
        return surveyAssetInventoryContentDao.getSurveyAssetInventoryContentListByMasterId(masterId);
    }

    /**
     * 初始化资产清查项
     *
     * @param planDetailId
     * @param projectId
     * @param declareRecord
     * @return
     */
    public List<SurveyAssetInventoryContent> initAssetInventoryContentNew(Integer planDetailId, Integer projectId, Integer masterId, DeclareRecord declareRecord) {
        if (planDetailId != null && planDetailId != 0) {
            List<SurveyAssetInventoryContent> inventoryContents = getContentListByPlanDetailsId(planDetailId);
            if (CollectionUtils.isNotEmpty(inventoryContents)) {
                return inventoryContents;
            }
        }
        List<SurveyAssetInventoryContent> inventoryContentList = Lists.newArrayList();
        if (declareRecord == null) {
            return inventoryContentList;
        }
        List<BaseDataDic> baseDataDicList = baseDataDicService.getCacheDataDicList(declareRecord.getInventoryContentKey());
        if (CollectionUtils.isEmpty(baseDataDicList)) return inventoryContentList;
        AssessProjectTypeEnum projectType = projectInfoService.getAssessProjectType(projectInfoService.getProjectInfoById(projectId).getProjectCategoryId());
        for (BaseDataDic baseDataDic : baseDataDicList) {
            SurveyAssetInventoryContent surveyAssetInventoryContent = new SurveyAssetInventoryContent();
            surveyAssetInventoryContent.setProjectId(projectId);
            surveyAssetInventoryContent.setPlanDetailsId(planDetailId);
            surveyAssetInventoryContent.setDeclareId(declareRecord.getId());
            surveyAssetInventoryContent.setInventoryContent(baseDataDic.getId());

            if (AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_ACTUAL_ADDRESS.equals(baseDataDic.getFieldName())) {//登记地址与实际地址
                SurveyAssetInventoryContent inventoryAddress = getInventoryAddress(declareRecord);
                if (inventoryAddress != null) {
                    surveyAssetInventoryContent.setRegistration(inventoryAddress.getRegistration());
                    surveyAssetInventoryContent.setActual(inventoryAddress.getActual());
                    if (Objects.equal(surveyAssetInventoryContent.getRegistration(), surveyAssetInventoryContent.getActual())) {
                        surveyAssetInventoryContent.setAreConsistent("一致");
                    } else {
                        surveyAssetInventoryContent.setAreConsistent("不一致");
                    }
                }
                inventoryContentList.add(surveyAssetInventoryContent);
            }

            if (AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_STRUCTURE.equals(baseDataDic.getFieldName())) {//登记结构与实际结构
                if (AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_HOUSE.equals(projectType)) {
                    surveyAssetInventoryContent.setRegistration(declareRecord.getHousingStructure());
                    inventoryContentList.add(surveyAssetInventoryContent);
                }
            }

            if (AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_USE.equals(baseDataDic.getFieldName())) {//登记用途与实际用途
                if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class))
                        || Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class))) {
                    surveyAssetInventoryContent.setRegistration(declareRecord.getCertUse());
                }
                if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class))) {
                    surveyAssetInventoryContent.setRegistration(declareRecord.getLandCertUse());
                }
                if (StringUtils.isNotBlank(surveyAssetInventoryContent.getRegistration()) && StringUtils.isNotBlank(surveyAssetInventoryContent.getActual())) {
                    if (Objects.equal(surveyAssetInventoryContent.getRegistration(), surveyAssetInventoryContent.getActual())) {
                        surveyAssetInventoryContent.setAreConsistent("一致");
                    } else {
                        surveyAssetInventoryContent.setAreConsistent("不一致");
                    }
                }
                inventoryContentList.add(surveyAssetInventoryContent);
            }

            if (AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_SPACE.equals(baseDataDic.getFieldName())) {//登记空间位置与实际空间位置
                if (AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_HOUSE.equals(projectType)) {
                    inventoryContentList.add(surveyAssetInventoryContent);
                }
            }
            if (AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_HOUSE_LAND_ADDRESS.equals(baseDataDic.getFieldName())) {//房产证与土地证证载地址
                DeclareBuildEngineeringAndEquipmentCenter query = new DeclareBuildEngineeringAndEquipmentCenter();
                if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class))) {
                    query.setType(DeclareRealtyHouseCert.class.getSimpleName());
                    query.setHouseId(declareRecord.getDataTableId());
                    List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(query);
                    if (CollectionUtils.isNotEmpty(centerList)) {
                        DeclareBuildEngineeringAndEquipmentCenter center = centerList.get(0);
                        if (center.getLandId() != null && center.getLandId() != 0) {
                            surveyAssetInventoryContent.setRegistration(declareRecord.getSeat());
                            DeclareRealtyLandCert landCert = declareRealtyLandCertService.getDeclareRealtyLandCertById(center.getLandId());
                            if (landCert != null) {
                                if (StringUtils.isNotBlank(landCert.getBeLocated())) {
                                    surveyAssetInventoryContent.setActual(landCert.getBeLocated());
                                    if (Objects.equal(landCert.getBeLocated(), declareRecord.getSeat())) {
                                        surveyAssetInventoryContent.setAreConsistent("一致");
                                    } else {
                                        surveyAssetInventoryContent.setAreConsistent("不一致");
                                    }
                                }
                                inventoryContentList.add(surveyAssetInventoryContent);
                            }
                        }
                    }
                }
            }
            if (AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_AREA.equals(baseDataDic.getFieldName())) {//登记面积与实际面积
                if (declareRecord.getFloorArea() != null) {
                    surveyAssetInventoryContent.setRegistration(String.valueOf(declareRecord.getFloorArea()));
                }
                if (declareRecord.getPracticalArea() != null) {
                    surveyAssetInventoryContent.setActual(String.valueOf(declareRecord.getPracticalArea()));
                }
                if (declareRecord.getFloorArea() != null && declareRecord.getPracticalArea() != null) {
                    if (Objects.equal(declareRecord.getFloorArea(), declareRecord.getPracticalArea())) {
                        surveyAssetInventoryContent.setAreConsistent("一致");
                    } else {
                        surveyAssetInventoryContent.setAreConsistent("不一致");
                    }
                }
                inventoryContentList.add(surveyAssetInventoryContent);
            }
            if (AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_FOUR_TO_LAND.equals(baseDataDic.getFieldName())) {//土地四至
                if (AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_LAND.equals(projectType)) {
                    inventoryContentList.add(surveyAssetInventoryContent);
                }
            }
        }
        for (SurveyAssetInventoryContent item : inventoryContentList) {
            try {
                item.setMasterId(masterId);
                surveyAssetInventoryContentService.saveAssetInventoryContent(item);
            } catch (BusinessException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return inventoryContentList;
    }

    /**
     * 获取权证登记地址及实际地址，当权证为一组时取其中相同部分
     *
     * @param declareRecord
     * @return
     */
    public SurveyAssetInventoryContent getInventoryAddress(DeclareRecord declareRecord) {
        SurveyAssetInventoryContent content = new SurveyAssetInventoryContent();
        if (declareRecord == null) return content;
        SurveyAssetInfoItem assetInfoItem = surveyAssetInfoItemService.getSurveyAssetInfoItemByDeclareId(declareRecord.getId());
        List<String> actualList = Lists.newArrayList();
        if (assetInfoItem == null || assetInfoItem.getGroupId() == null || assetInfoItem.getGroupId() <= 0) {
            List<BasicApply> applyList = basicApplyService.getListByDeclareRecordId(declareRecord.getId());
            if(CollectionUtils.isNotEmpty(applyList)){
                for (BasicApply basicApply : applyList) {
                    BasicBuildingVo building =  basicBuildingService.getBasicBuildingByBasicApply(basicApply);
                    actualList.add(building.getStreetNumber()+basicApply.getAddress());
                }
            }
            String fusinString = publicService.fusinString(actualList, true);
            content.setRegistration(declareRecord.getSeat());
            content.setActual(fusinString);
            return content;
        } else {
            List<Integer> ids = surveyAssetInfoItemService.getSurveyAssetInfoItemIdsByGroupId(assetInfoItem.getGroupId());
            List<SurveyAssetInfoItem> infoItems = surveyAssetInfoItemService.getSurveyAssetInfoItemByIds(ids);
            List<DeclareRecord> declareRecords = declareRecordService.getDeclareRecordListByIds(LangUtils.transform(infoItems, o -> o.getDeclareId()));
            if (CollectionUtils.isNotEmpty(declareRecords)) {
                List<String> registrationList = LangUtils.transform(declareRecords, o -> o.getSeat());
                for (DeclareRecord record : declareRecords) {
                    List<BasicApply> applyList = basicApplyService.getListByDeclareRecordId(record.getId());
                    if (CollectionUtils.isNotEmpty(applyList)) {
                        for (BasicApply basicApply : applyList) {
                            BasicBuildingVo building =  basicBuildingService.getBasicBuildingByBasicApply(basicApply);
                            actualList.add(building.getStreetNumber()+basicApply.getAddress());
                        }
                    }
                }
                content.setRegistration(publicService.fusinString(registrationList, true));
                content.setActual(publicService.fusinString(actualList, true));
            }
            return content;
        }
    }


}
