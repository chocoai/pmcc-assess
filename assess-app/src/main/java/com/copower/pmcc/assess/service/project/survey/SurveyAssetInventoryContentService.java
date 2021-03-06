package com.copower.pmcc.assess.service.project.survey;


import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.AssessProjectTypeEnum;
import com.copower.pmcc.assess.common.enums.DeclareCertificateTypeEnum;
import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryContentDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.basic.BasicBuildingVo;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateVillageVo;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyAssetInventoryContentVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.declare.DeclareBuildEngineeringAndEquipmentCenterService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyHouseCertService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyLandCertService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
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

import java.math.BigDecimal;
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
    private ProjectInfoService projectInfoService;
    @Autowired
    private DeclareBuildEngineeringAndEquipmentCenterService declareBuildEngineeringAndEquipmentCenterService;
    @Autowired
    private DeclareRealtyLandCertService declareRealtyLandCertService;
    @Autowired
    private DeclareRealtyHouseCertService declareRealtyHouseCertService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private BasicEstateStreetInfoService basicEstateStreetInfoService;
    @Autowired
    private SurveyAssetInfoService surveyAssetInfoService;
    @Autowired
    private BasicEstateLandCategoryInfoService basicEstateLandCategoryInfoService;
    @Autowired
    private BasicEstateVillageService basicEstateVillageService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;


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

    public List<SurveyAssetInventoryContent> getSurveyAssetInventoryContentListByMasterId(Integer masterId) {
        return surveyAssetInventoryContentDao.getSurveyAssetInventoryContentListByMasterId(masterId);
    }

    public List<SurveyAssetInventoryContent> getInventoryContentListByItemId(Integer itemId) {
        return surveyAssetInventoryContentDao.getInventoryContentListByItemId(itemId);
    }

    public boolean deleteInventoryContentByItemId(Integer itemId) {
        if (itemId == null) return false;
        return surveyAssetInventoryContentDao.deleteInventoryContentByItemId(itemId);
    }

    /**
     * 初始化清查项
     *
     * @param infoItem
     * @return
     */
    public List<SurveyAssetInventoryContent> initContentByInfoItem(SurveyAssetInfoItem infoItem) throws BusinessException {
        if (infoItem == null) return null;
        SurveyAssetInfo assetInfo = surveyAssetInfoService.getSurveyAssetInfoById(infoItem.getAssetInfoId());
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(assetInfo.getProjectId());
        AssessProjectTypeEnum assessProjectType = projectInfoService.getAssessProjectType(projectInfo.getProjectCategoryId());
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(infoItem.getDeclareId());
        List<BaseDataDic> baseDataDicList = baseDataDicService.getCacheDataDicList(declareRecord.getInventoryContentKey());
        if (CollectionUtils.isNotEmpty(baseDataDicList)) {
            for (BaseDataDic dataDic : baseDataDicList) {
                if (AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_HOUSE.equals(assessProjectType)) {//房产
                    if (AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_FOUR_TO_LAND.equals(dataDic.getFieldName())) {
                        continue;//四至
                    }
                    if (DeclareCertificateTypeEnum.REAL_ESTATE.getKey().equals(declareRecord.getType()) &&
                            AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_HOUSE_LAND_ADDRESS.equals(dataDic.getFieldName())) {
                        continue;//房产证与土地证地址
                    }
                }
                if (AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_LAND.equals(assessProjectType)) {//土地
                    if (AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_STRUCTURE.equals(dataDic.getFieldName())) {
                        continue;//结构
                    }
                    if (AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_SPACE.equals(dataDic.getFieldName())) {
                        continue;//空间
                    }
                    if (AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_HOUSE_LAND_ADDRESS.equals(dataDic.getFieldName())) {
                        continue;//房产证与土地证地址
                    }
                }
                SurveyAssetInventoryContent inventoryContent = new SurveyAssetInventoryContent();
                inventoryContent.setInfoItemId(infoItem.getId());
                inventoryContent.setProjectId(assetInfo.getProjectId());
                inventoryContent.setPlanDetailsId(assetInfo.getPlanDetailId());
                inventoryContent.setDeclareId(declareRecord.getId());
                inventoryContent.setInventoryContent(dataDic.getId());
                inventoryContent.setCreator(commonService.thisUserAccount());
                saveAssetInventoryContent(inventoryContent);
            }
        }
        List<SurveyAssetInventoryContent> list = getInventoryContentListByItemId(infoItem.getId());
        setContentInitialValue(infoItem, list);
        return list;
    }

    /**
     * 为清查项设置初始值
     *
     * @param infoItem
     * @param inventoryContents
     */
    public void setContentInitialValue(SurveyAssetInfoItem infoItem, List<SurveyAssetInventoryContent> inventoryContents) {
        if (infoItem == null) return;
        if (CollectionUtils.isEmpty(inventoryContents)) return;
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(infoItem.getDeclareId());
        BasicEstate basicEstate = null;
        BasicBuilding basicBuilding = null;
        BasicUnit basicUnit = null;
        BasicHouse basicHouse = null;
        List<BasicEstateStreetInfo> streetInfoList = null;
        List<BasicApply> applyList = basicApplyService.getListByDeclareRecordId(declareRecord.getId());
        if (CollectionUtils.isNotEmpty(applyList)) {
            List<KeyValueDto> keyValueDtos = JSON.parseArray(applyList.get(0).getStructuralInfo(), KeyValueDto.class);
            if (CollectionUtils.isNotEmpty(keyValueDtos)) {
                for (KeyValueDto keyValueDto : keyValueDtos) {
                    if (keyValueDto.getKey().startsWith(BasicFormClassifyEnum.ESTATE.getKey())) {
                        basicEstate = basicEstateService.getBasicEstateById(Integer.valueOf(keyValueDto.getValue()));
                        streetInfoList = basicEstateStreetInfoService.getStreetInfoListByEstateId(Integer.valueOf(keyValueDto.getValue()));
                    } else if (keyValueDto.getKey().startsWith(BasicFormClassifyEnum.BUILDING.getKey()) && !keyValueDto.getKey().equals(BasicFormClassifyEnum.BUILDING_DIFFERENCE.getKey())) {
                        basicBuilding = basicBuildingService.getBasicBuildingById(Integer.valueOf(keyValueDto.getValue()));
                    } else if (keyValueDto.getKey().startsWith(BasicFormClassifyEnum.UNIT.getKey())) {
                        basicUnit = basicUnitService.getBasicUnitById(Integer.valueOf(keyValueDto.getValue()));
                    } else if (keyValueDto.getKey().startsWith(BasicFormClassifyEnum.HOUSE.getKey())) {
                        basicHouse = basicHouseService.getBasicHouseById(Integer.valueOf(keyValueDto.getValue()));
                    }
                }
            }
        }
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(declareRecord.getProjectId());
        AssessProjectTypeEnum assessProjectType = projectInfoService.getAssessProjectType(projectInfo.getProjectCategoryId());
        for (SurveyAssetInventoryContent inventoryContent : inventoryContents) {
            try {
                BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(inventoryContent.getInventoryContent());
                String key = baseDataDic.getFieldName();
                if (AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_ACTUAL_ADDRESS.equals(key)) {//登记地址与实际地址
                    inventoryContent.setRegistration(declareRecord.getSeat());
                    if (AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_HOUSE.equals(assessProjectType)) {//房产
                        Boolean isStreetNumberSame = false;
                        if (CollectionUtils.isNotEmpty(streetInfoList)) {
                            inventoryContent.setActual(StringUtils.defaultString(inventoryContent.getActual()) + StringUtils.defaultString(streetInfoList.get(0).getStreetNumber()));
                            if (LangUtils.transform(streetInfoList, o -> o.getStreetNumber()).contains(declareRecord.getStreetNumber())) {
                                isStreetNumberSame = true;
                            }
                        }
                        Boolean isBuildingNumberSame = false;
                        if (basicBuilding != null && StringUtils.isNotBlank(basicBuilding.getBuildingNumber())) {
                            if (StringUtils.isNotBlank(inventoryContent.getActual())) {
                                inventoryContent.setActual(StringUtils.defaultString(inventoryContent.getActual()) + basicBuilding.getBuildingNumber());
                            }
                            if (basicBuilding.getBuildingNumber().contains(StringUtils.defaultString(declareRecord.getBuildingNumber()))) {
                                isBuildingNumberSame = true;
                            }
                        }
                        Boolean isUnitNumberSame = false;
                        if (basicUnit != null && StringUtils.isNotBlank(basicUnit.getUnitNumber())) {
                            if (StringUtils.isNotBlank(inventoryContent.getActual())) {
                                inventoryContent.setActual(StringUtils.defaultString(inventoryContent.getActual()) + basicUnit.getUnitNumber());
                            }
                            if (basicUnit.getUnitNumber().contains(StringUtils.defaultString(declareRecord.getUnit()))) {
                                isUnitNumberSame = true;
                            }
                        }
                        Boolean isHouseNumberSame = false;
                        if (basicHouse != null && StringUtils.isNotBlank(basicHouse.getHouseNumber())) {
                            if (StringUtils.isNotBlank(inventoryContent.getActual())) {
                                inventoryContent.setActual(StringUtils.defaultString(inventoryContent.getActual()) + basicHouse.getHouseNumber());
                            }
                            if (basicHouse.getHouseNumber().contains(StringUtils.defaultString(declareRecord.getRoomNumber()))) {
                                isHouseNumberSame = true;
                            }
                        }
                        if (isStreetNumberSame && isBuildingNumberSame && isUnitNumberSame && isHouseNumberSame) {
                            inventoryContent.setActual(inventoryContent.getRegistration());
                        }
                    } else if (AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_LAND.equals(assessProjectType)) {//土地
                        if (basicEstate != null) {
                            List<BasicEstateVillageVo> villageVoList = basicEstateVillageService.getVillageListByEstateId(basicEstate.getId());
                            if (CollectionUtils.isNotEmpty(villageVoList)) {
                                inventoryContent.setActual(villageVoList.get(0).getVillageStreet());
                            }
                        }
                    }
                } else if (AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_STRUCTURE.equals(key)) { //登记结构与实际结构
                    inventoryContent.setRegistration(declareRecord.getHousingStructure());
                    if (basicBuilding != null) {
                        String structure = baseDataDicService.getNameById(basicBuilding.getBuildingStructureCategory());
                        if (StringUtils.defaultString(structure).contains(StringUtils.defaultString(declareRecord.getHousingStructure()))) {
                            inventoryContent.setActual(declareRecord.getHousingStructure());
                        } else {
                            inventoryContent.setActual(structure);
                        }
                    }
                } else if (AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_USE.equals(key)) {//登记用途与实际用途
                    inventoryContent.setRegistration(declareRecord.getCertUse());
                    if (basicHouse != null) {
                        if (AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_HOUSE.equals(assessProjectType)) {//房产
                            inventoryContent.setActual(basicHouse.getPracticalUse());
                        } else if (AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_LAND.equals(assessProjectType)) {//土地
                            BasicEstateLandCategoryInfo landCategoryInfo = basicEstateLandCategoryInfoService.getBasicEstateLandCategoryInfoByHouseId(basicHouse.getId());
                            if (landCategoryInfo != null) {
                                inventoryContent.setActual(landCategoryInfo.getLandUseCategory());
                            }
                        }
                    }
                } else if (AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_SPACE.equals(key)) {//登记空间位置与实际空间位置
                    inventoryContent.setRegistration(declareRecord.getFloor());
                    if (basicHouse != null) {
                        inventoryContent.setActual(basicHouse.getFloor());
                    }
                } else if (AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_AREA.equals(key)) {//登记面积与实际面积
                    inventoryContent.setRegistration(String.valueOf(declareRecord.getFloorArea()));
                    if (basicHouse != null) {
                        inventoryContent.setActual(String.valueOf(basicHouse.getArea()));
                    }
                } else if (AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_HOUSE_LAND_ADDRESS.equals(key)) {//房产证与土地证证载地址
                    validEstateLandAddressSame(inventoryContent, declareRecord);
                } else if (AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_FOUR_TO_LAND.equals(key)) {//四至
                    if (basicEstate != null) {
                        BasicEstateLandState estateLandState = basicEstateLandStateService.getLandStateByEstateId(basicEstate.getId());
                        if (estateLandState != null) {
                            StringBuilder stringBuilder = new StringBuilder();
                            if (StringUtils.isNotBlank(estateLandState.getEastTo())) {
                                stringBuilder.append("东至").append(estateLandState.getEastTo()).append(",");
                            }
                            if (StringUtils.isNotBlank(estateLandState.getSouthTo())) {
                                stringBuilder.append("南至").append(estateLandState.getSouthTo()).append(",");
                            }
                            if (StringUtils.isNotBlank(estateLandState.getWestTo())) {
                                stringBuilder.append("西至").append(estateLandState.getWestTo()).append(",");
                            }
                            if (StringUtils.isNotBlank(estateLandState.getNorthTo())) {
                                stringBuilder.append("北至").append(estateLandState.getNorthTo());
                            }
                            inventoryContent.setActual(stringBuilder.toString());
                        }
                    }
                }
                if (StringUtils.equals(inventoryContent.getRegistration(), inventoryContent.getActual())) {
                    inventoryContent.setAreConsistent("一致");
                } else {
                    inventoryContent.setAreConsistent("不一致");
                }
                saveAssetInventoryContent(inventoryContent);
            } catch (BusinessException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 验证房产证与土地证是否一致
     *
     * @param inventoryContent
     * @param declareRecord
     */
    private void validEstateLandAddressSame(SurveyAssetInventoryContent inventoryContent, DeclareRecord declareRecord) {
        if (inventoryContent == null || declareRecord == null) return;
        inventoryContent.setRegistration(declareRecord.getSeat());
        DeclareBuildEngineeringAndEquipmentCenter equipmentCenter = declareBuildEngineeringAndEquipmentCenterService.getDataByDeclareRecord(declareRecord.getId());
        if (equipmentCenter == null) return;
        if (DeclareCertificateTypeEnum.HOUSE.getKey().equals(declareRecord.getType())) {
            DeclareRealtyLandCert landCert = declareRealtyLandCertService.getDeclareRealtyLandCertById(equipmentCenter.getLandId());
            if (landCert != null) {
                inventoryContent.setActual(landCert.getBeLocated());
            }
        } else if (DeclareCertificateTypeEnum.LAND.getKey().equals(declareRecord.getType())) {
            DeclareRealtyHouseCert houseCert = declareRealtyHouseCertService.getDeclareRealtyHouseCertById(equipmentCenter.getHouseId());
            if (houseCert != null) {
                inventoryContent.setActual(houseCert.getBeLocated());
            }
        }
        if (StringUtils.isBlank(inventoryContent.getActual())) {
            inventoryContent.setActual(inventoryContent.getRegistration());
        }
    }
}
