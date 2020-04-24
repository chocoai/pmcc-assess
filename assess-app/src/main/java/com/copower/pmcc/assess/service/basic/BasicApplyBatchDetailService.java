package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.common.enums.basic.ExamineCommonQuoteFieldEnum;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.survey.BasicApplyBatchDetailVo;
import com.copower.pmcc.assess.proxy.face.BasicEntityAbstract;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseQrcodeService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class BasicApplyBatchDetailService {
    @Autowired
    private BasicApplyBatchDetailDao basicApplyBatchDetailDao;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private BasicUnitHuxingService basicUnitHuxingService;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private BasicHouseTradingService basicHouseTradingService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private PublicBasicService publicBasicService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicCommonQuoteFieldInfoService basicCommonQuoteFieldInfoService;
    @Autowired
    private BaseQrcodeService baseQrcodeService;

    /**
     * 通过applyBatchId获取
     *
     * @param applyBatchId
     * @return
     */
    public List<BasicApplyBatchDetail> getBasicApplyBatchDetailByApplyBatchId(Integer applyBatchId) {
        if (applyBatchId == null) return null;
        BasicApplyBatchDetail basicApplyBatchDetail = new BasicApplyBatchDetail();
        basicApplyBatchDetail.setApplyBatchId(applyBatchId);
        return basicApplyBatchDetailDao.getInfoList(basicApplyBatchDetail);
    }

    public BasicApplyBatchDetail getBasicApplyBatchDetail(String tableName, Integer tableId) {
        BasicApplyBatchDetail basicApplyBatchDetail = new BasicApplyBatchDetail();
        basicApplyBatchDetail.setTableId(tableId);
        basicApplyBatchDetail.setTableName(tableName);
        List<BasicApplyBatchDetail> infoList = basicApplyBatchDetailDao.getInfoList(basicApplyBatchDetail);
        if (CollectionUtils.isNotEmpty(infoList))
            return basicApplyBatchDetailDao.getInfoList(basicApplyBatchDetail).get(0);
        return null;
    }

    public void saveBasicApplyBatchDetail(BasicApplyBatchDetail basicApplyBatchDetail) {
        if (basicApplyBatchDetail.getId() != null && basicApplyBatchDetail.getId() > 0) {
            basicApplyBatchDetailDao.updateInfo(basicApplyBatchDetail);
        } else {
            basicApplyBatchDetail.setCreator(processControllerComponent.getThisUser());
            basicApplyBatchDetailDao.addInfo(basicApplyBatchDetail);
        }
    }

    public List<BasicApplyBatchDetail> getBasicApplyBatchDetailList(BasicApplyBatchDetail basicApplyBatchDetail) {
        return basicApplyBatchDetailDao.getInfoList(basicApplyBatchDetail);
    }

    /**
     * 新增
     *
     * @param basicApplyBatchDetail
     * @return
     */
    public BasicApplyBatchDetail saveAndUpdateComplete(BasicApplyBatchDetail basicApplyBatchDetail, Integer planDetailsId) throws Exception {
        basicApplyBatchDetail.setDisplayName(basicApplyBatchDetail.getName());
        basicApplyBatchDetail.setName(basicApplyBatchDetail.getName());
        basicApplyBatchDetail.setCreator(processControllerComponent.getThisUser());
        basicApplyBatchDetail.setExecutor(processControllerComponent.getThisUser());
        BasicFormClassifyEnum enumByKey = BasicFormClassifyEnum.getEnumByKey(basicApplyBatchDetail.getType());
        basicApplyBatchDetail.setTableName(enumByKey.getTableName());
        switch (enumByKey) {
            case ESTATE:
            case ESTATE_LAND:
            case ESTATE_LAND_INCLUD:
                BasicEstate estateData = basicEstateService.getBasicEstateById(basicApplyBatchDetail.getTableId());
                estateData.setName(basicApplyBatchDetail.getName());
                basicEstateService.saveAndUpdate(estateData, false);
                break;
            case BUILDING:
            case BUILDING_MONOLAYER:
            case BUILDING_BASE:
            case BUILDING_DIFFERENCE:
            case BUILDING_LAND_INCLUD:
                BasicBuilding buildingData = basicBuildingService.getBasicBuildingById(basicApplyBatchDetail.getTableId());
                BasicBuilding building = buildingData == null ? new BasicBuilding() : buildingData;
                building.setBuildingNumber(basicApplyBatchDetail.getName());
                BasicEstate basicEstate = getBasicEstateByBatchDetailId(basicApplyBatchDetail.getPid());
                if (basicEstate != null)
                    building.setEstateId(basicEstate.getId());
                building.setBuildingName(basicApplyBatchDetail.getDisplayName());

                String value = basicCommonQuoteFieldInfoService.getValue(basicApplyBatchDetail.getApplyBatchId(), ExamineCommonQuoteFieldEnum.OPEN_TIME_ENUM);
                if(StringUtils.isNotBlank(value)){
                    building.setOpenTime(DateUtils.convertDate(value));
                }
                value = basicCommonQuoteFieldInfoService.getValue(basicApplyBatchDetail.getApplyBatchId(), ExamineCommonQuoteFieldEnum.COVER_AN_AREA);
                if(StringUtils.isNotBlank(value)){
                    building.setCoverAnArea(new BigDecimal(value));
                }
                value = basicCommonQuoteFieldInfoService.getValue(basicApplyBatchDetail.getApplyBatchId(), ExamineCommonQuoteFieldEnum.LAND_USE_YEAR_ENUM);
                if(StringUtils.isNotBlank(value)){
                    building.setLandUseYear(new BigDecimal(value));
                }

                basicBuildingService.saveAndUpdate(building, false);
                basicApplyBatchDetail.setTableId(building.getId());
                break;
            case UNIT:
            case UNIT_RESIDENCE:
            case UNIT_LAND_INCLUD:
                BasicUnit unitData = basicUnitService.getBasicUnitById(basicApplyBatchDetail.getTableId());
                BasicUnit unit = unitData == null ? new BasicUnit() : unitData;
                BasicBuilding basicBuilding = getBasicBuildingByBatchDetailId(basicApplyBatchDetail.getPid());
                if (basicBuilding != null) {
                    unit.setBuildingId(basicBuilding.getId());
                    unit.setEstateId(basicBuilding.getEstateId());
                }
                unit.setUnitNumber(basicApplyBatchDetail.getName());
                basicUnitService.saveAndUpdate(unit, false);
                basicApplyBatchDetail.setTableId(unit.getId());
                break;
            case HOUSE:
            case HOUSE_LAND_INCLUD:
                BasicHouse houseData = basicHouseService.getBasicHouseById(basicApplyBatchDetail.getTableId());
                BasicHouse house = houseData == null ? new BasicHouse() : houseData;
                house.setHouseNumber(basicApplyBatchDetail.getName());
                BasicUnit basicUnit = getBasicUnitByBatchDetailId(basicApplyBatchDetail.getPid());
                if (basicUnit != null) {
                    house.setUnitId(basicUnit.getId());
                    house.setBuildingId(basicUnit.getBuildingId());
                    house.setEstateId(basicUnit.getEstateId());
                }
                //申报表代入的信息
                Integer declareRecordId = basicApplyBatchDetail.getDeclareRecordId();
                if (declareRecordId != null) {
                    DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(basicApplyBatchDetail.getDeclareRecordId());
                    if (declareRecord != null) {
                        house.setCertUse(declareRecord.getCertUse());
                        house.setPracticalUse(declareRecord.getCertUse());
                    }
                }

                basicHouseService.saveAndUpdate(house, false);
                basicApplyBatchDetail.setTableId(house.getId());

                BasicHouseTrading houseTrading = new BasicHouseTrading();
                houseTrading.setHouseId(house.getId());
                ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
                if (projectPlanDetails != null) {
                    ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
                    houseTrading.setScopeInclude(projectInfo.getScopeInclude());
                    houseTrading.setScopeNotInclude(projectInfo.getScopeNotInclude());
                    if (NumberUtils.isNumber(projectInfo.getPropertyScope())) {
                        houseTrading.setScopeProperty(Integer.valueOf(projectInfo.getPropertyScope()));
                    }
                }
                basicHouseTradingService.saveAndUpdateBasicHouseTrading(houseTrading, false);

                BasicUnitHuxing huxing = new BasicUnitHuxing();
                huxing.setHouseId(house.getId());
                huxing.setEstateId(house.getEstateId());
                basicUnitHuxingService.saveAndUpdateBasicUnitHuxing(huxing, false);
                break;
        }
        basicApplyBatchDetailService.saveBasicApplyBatchDetail(basicApplyBatchDetail);
        this.insertBasicApply(basicApplyBatchDetail, planDetailsId); //存入basicApply
        baseQrcodeService.createQrCode(basicApplyBatchDetail);
        return basicApplyBatchDetail;
    }

    //存入basicApply表单
    public void insertBasicApply(BasicApplyBatchDetail houseBasicApplyBatchDetail, Integer planDetailsId) throws Exception {
        if (!houseBasicApplyBatchDetail.getType().equals(BasicFormClassifyEnum.HOUSE.getKey())) return;
        BasicApply basicApply = basicApplyService.getBasicApplyByBatchDetailId(houseBasicApplyBatchDetail.getId());
        if (basicApply == null) {
            basicApply = new BasicApply();
        }
        basicApply.setBasicHouseId(houseBasicApplyBatchDetail.getTableId());
        basicApply.setDeclareRecordId(houseBasicApplyBatchDetail.getDeclareRecordId());
        BasicHouse basicHouse = basicHouseService.getBasicHouseById(houseBasicApplyBatchDetail.getTableId());
        if (basicHouse != null)
            basicApply.setArea(basicHouse.getArea());
        basicApply.setBatchDetailId(houseBasicApplyBatchDetail.getId());
        basicApply.setPlanDetailsId(planDetailsId);
        List<BasicApplyBatchDetail> list = Lists.newArrayList();
        collectionParentBatchDetails(houseBasicApplyBatchDetail.getId(), list);
        if (CollectionUtils.isNotEmpty(list)) {
            StringBuilder stringBuilder = new StringBuilder();
            StringBuilder addressBuilder = new StringBuilder();
            List<KeyValueDto> keyValueDtos = Lists.newArrayList();
            for (int i = list.size() - 1; i >= 0; i--) {
                BasicApplyBatchDetail batchDetail = list.get(i);
                stringBuilder.append(batchDetail.getName());
                if (BasicFormClassifyEnum.ESTATE.getKey().equals(batchDetail.getType())) {
                    BasicEstate estate = basicEstateService.getBasicEstateById(batchDetail.getTableId());
                    if (estate != null) {
                        addressBuilder.append(estate.getStreetNumber());
                        if (StringUtils.isNotBlank(estate.getAttachNumber()))
                            addressBuilder.append("附").append(estate.getAttachNumber());
                    }
                } else {
                    addressBuilder.append(batchDetail.getName());
                }
                KeyValueDto keyValueDto = new KeyValueDto();
                keyValueDto.setKey(batchDetail.getType());
                keyValueDto.setValue(String.valueOf(batchDetail.getTableId()));
                keyValueDtos.add(keyValueDto);
            }
            basicApply.setName(stringBuilder.toString());
            basicApply.setAddress(addressBuilder.toString());
            basicApply.setStructuralInfo(JSON.toJSONString(keyValueDtos));
        }
        basicApplyService.saveBasicApply(basicApply);
    }

    /**
     * 通过pid获取
     *
     * @param pid
     * @return
     */
    public List<BasicApplyBatchDetail> getBasicApplyBatchDetailByPid(Integer pid, Integer applyBatchId) {
        BasicApplyBatchDetail basicApplyBatchDetail = new BasicApplyBatchDetail();
        basicApplyBatchDetail.setPid(pid);
        basicApplyBatchDetail.setApplyBatchId(applyBatchId);
        return basicApplyBatchDetailDao.getInfoList(basicApplyBatchDetail);
    }

    public List<BasicApplyBatchDetail> getBasicApplyBatchDetailListByType(String type, Integer applyBatchId, Integer pid, Boolean isFuzzyMatching) {
        List<String> types = Lists.newArrayList();
        if (isFuzzyMatching) {
            types.addAll(LangUtils.transform(BasicFormClassifyEnum.getEnumByFuzzyKey(type), o -> o.getKey()));
        } else {
            types.add(type);
        }
        return basicApplyBatchDetailDao.getBasicApplyBatchDetailListByTypes(types, applyBatchId, pid);
    }


    /**
     * 删除
     *
     * @param id
     * @return
     */
    public void deleteBasicApplyBatchDetail(Integer id) throws Exception {
        BasicApplyBatchDetail basicApplyBatchDetail = basicApplyBatchDetailDao.getInfoById(id);
        List<BasicApplyBatchDetail> list = Lists.newArrayList();
        collectionChildBatchDetails(basicApplyBatchDetail, list);
        for (BasicApplyBatchDetail applyBatchDetail : list) {
            BasicEntityAbstract entityAbstract = publicBasicService.getServiceBeanByKey(applyBatchDetail.getType());
            if (entityAbstract != null)
                entityAbstract.clearInvalidData(applyBatchDetail.getTableId());
            basicApplyBatchDetailDao.deleteInfo(applyBatchDetail.getId());
        }
    }

    /**
     * 通过id获取
     *
     * @param id
     * @return
     */
    public BasicApplyBatchDetail getDataById(Integer id) {
        return basicApplyBatchDetailDao.getInfoById(id);
    }

    public BasicApplyBatchDetailVo getBasicApplyBatchDetailVo(BasicApplyBatchDetail basicApplyBatchDetail) {
        BasicApplyBatchDetailVo vo = new BasicApplyBatchDetailVo();
        BeanUtils.copyProperties(basicApplyBatchDetail, vo);
        if (StringUtils.isNotEmpty(basicApplyBatchDetail.getExecutor())) {
            vo.setExecutorName(publicService.getUserNameByAccount(basicApplyBatchDetail.getExecutor()));
        }
        if (StringUtils.isNotEmpty(basicApplyBatchDetail.getCreator())) {
            vo.setCreatorName(publicService.getUserNameByAccount(basicApplyBatchDetail.getCreator()));
        }
        return vo;
    }

    public BasicApplyBatchDetail getBasicApplyBatchDetail(Integer basicApplyBatchId, String tableName, Integer tableId) {
        BasicApplyBatchDetail where = new BasicApplyBatchDetail();
        where.setApplyBatchId(basicApplyBatchId);
        where.setTableName(tableName);
        where.setTableId(tableId);
        return basicApplyBatchDetailDao.getBasicApplyBatchDetail(where);
    }


    /**
     * 获取楼盘
     *
     * @param basicApplyBatchDetailId
     * @return
     */
    public BasicEstate getBasicEstateByBatchDetailId(Integer basicApplyBatchDetailId) {
        BasicApplyBatchDetail applyBatchDetail = getDataById(basicApplyBatchDetailId);
        if (applyBatchDetail == null) return null;
        if (FormatUtils.entityNameConvertToTableName(BasicEstate.class).equalsIgnoreCase(applyBatchDetail.getTableName())) {
            return basicEstateService.getBasicEstateById(applyBatchDetail.getTableId());
        } else {
            if (applyBatchDetail.getPid() == null || applyBatchDetail.getPid() <= 0)
                return null;
            else
                return getBasicEstateByBatchDetailId(applyBatchDetail.getPid());
        }
    }

    /**
     * 获取楼栋
     *
     * @param basicApplyBatchDetailId
     * @return
     */
    public BasicBuilding getBasicBuildingByBatchDetailId(Integer basicApplyBatchDetailId) {
        BasicApplyBatchDetail applyBatchDetail = getDataById(basicApplyBatchDetailId);
        if (applyBatchDetail == null) return null;
        if (FormatUtils.entityNameConvertToTableName(BasicBuilding.class).equalsIgnoreCase(applyBatchDetail.getTableName())) {
            return basicBuildingService.getBasicBuildingVoById(applyBatchDetail.getTableId());
        } else {
            if (applyBatchDetail.getPid() == null || applyBatchDetail.getPid() <= 0)
                return null;
            else
                return getBasicBuildingByBatchDetailId(applyBatchDetail.getPid());
        }
    }

    /**
     * 获取单元
     *
     * @param basicApplyBatchDetailId
     * @return
     */
    public BasicUnit getBasicUnitByBatchDetailId(Integer basicApplyBatchDetailId) {
        BasicApplyBatchDetail applyBatchDetail = getDataById(basicApplyBatchDetailId);
        if (applyBatchDetail == null) return null;
        if (FormatUtils.entityNameConvertToTableName(BasicUnit.class).equalsIgnoreCase(applyBatchDetail.getTableName())) {
            return basicUnitService.getBasicUnitById(applyBatchDetail.getTableId());
        } else {
            if (applyBatchDetail.getPid() == null || applyBatchDetail.getPid() <= 0)
                return null;
            else
                return getBasicUnitByBatchDetailId(applyBatchDetail.getPid());
        }
    }

    /**
     * 收集该节点的所有上级节点
     *
     * @param basicApplyBatchDetailId
     * @param list
     */
    public void collectionParentBatchDetails(Integer basicApplyBatchDetailId, List<BasicApplyBatchDetail> list) {
        if (list == null) return;
        BasicApplyBatchDetail batchDetail = getDataById(basicApplyBatchDetailId);
        if (batchDetail == null) return;
        list.add(batchDetail);
        if (batchDetail.getPid() != null && batchDetail.getPid() > 0)
            collectionParentBatchDetails(batchDetail.getPid(), list);
    }

    /**
     * 收集该节点的所有下级节点
     *
     * @param batchDetail
     * @param list
     */
    public void collectionChildBatchDetails(BasicApplyBatchDetail batchDetail, List<BasicApplyBatchDetail> list) {
        if (list == null || batchDetail == null) return;
        list.add(batchDetail);
        List<BasicApplyBatchDetail> details = getBasicApplyBatchDetailByPid(batchDetail.getId(), batchDetail.getApplyBatchId());
        if (CollectionUtils.isNotEmpty(details)) {
            for (BasicApplyBatchDetail detail : details) {
                collectionChildBatchDetails(detail, list);
            }
        }
    }

    /**
     * 获取完整名称
     *
     * @param batchDetailId
     * @return
     */
    public String getFullNameByBatchDetailId(Integer batchDetailId) {
        List<BasicApplyBatchDetail> list = Lists.newArrayList();
        collectionParentBatchDetails(batchDetailId, list);
        if (CollectionUtils.isEmpty(list)) return null;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--) {
            stringBuilder.append(list.get(i).getName());
        }
        return stringBuilder.toString();
    }

    public List<BasicApplyBatchDetail> getBasicApplyBatchDetailList(List<Integer> basicApplyBatchIds, String type) {
        if (CollectionUtils.isEmpty(basicApplyBatchIds)) return null;
        return basicApplyBatchDetailDao.getBasicApplyBatchDetailList(basicApplyBatchIds, type);
    }

    public List<BasicApplyBatchDetail> getHouseBatchDetailList(Integer batchDetailId) {
        BasicApplyBatchDetail batchDetail = getDataById(batchDetailId);
        List<BasicApplyBatchDetail> list = Lists.newArrayList();
        collectionChildBatchDetails(batchDetail, list);
        List<BasicApplyBatchDetail> filter = LangUtils.filter(list, o -> BasicFormClassifyEnum.HOUSE.getKey().equals(o.getType()));
        return filter;
    }

    /**
     * 获取查勘楼盘信息
     *
     * @param projectPlanDetails
     * @return
     */
    public List<BasicApplyBatchDetail> getExploreEstateList(ProjectPlanDetails projectPlanDetails) {
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        List<ProjectPlanDetails> planDetailsList = projectPlanDetailsService.getProjectPlanDetailsByPhaseKey(projectInfo.getId(), projectInfo.getProjectCategoryId(), AssessPhaseKeyConstant.SCENE_EXPLORE);
        List<BasicApplyBatch> applyBatchList = basicApplyBatchService.getBasicApplyBatchsByPlanDetailsIds(LangUtils.transform(planDetailsList, o -> o.getId()));
        List<BasicApplyBatchDetail> batchDetailList = basicApplyBatchDetailService.getBasicApplyBatchDetailList(LangUtils.transform(applyBatchList, o -> o.getId()), BasicFormClassifyEnum.ESTATE.getKey());
        return batchDetailList;
    }
}
