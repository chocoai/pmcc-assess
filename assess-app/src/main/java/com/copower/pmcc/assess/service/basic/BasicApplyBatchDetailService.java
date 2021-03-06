package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.basic.BasicDataHandleEnum;
import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.common.enums.basic.ExamineCommonQuoteFieldEnum;
import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDetailDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateLandCategoryInfoDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.survey.BasicApplyBatchDetailVo;
import com.copower.pmcc.assess.proxy.face.BasicEntityAbstract;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.CacheConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

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
    private BasicEstateStreetInfoService basicEstateStreetInfoService;
    @Autowired
    private BasicApplyDao basicApplyDao;
    @Autowired
    private BasicEstateLandCategoryInfoDao basicEstateLandCategoryInfoDao;
    @Autowired
    private BasicAlternativeCaseService basicAlternativeCaseService;
    @Autowired
    private BasicEstateSurveyRecordService basicEstateSurveyRecordService;

    public List<BasicApplyBatchDetail> getBasicApplyBatchDetailByProjectId(Integer projectId) {
        BasicApplyBatchDetail query = new BasicApplyBatchDetailVo();
        query.setProjectId(projectId);
        query.setBisDelete(false);
        return basicApplyBatchDetailDao.getInfoList(query);
    }


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

    public List<BasicApplyBatchDetail> getBasicApplyBatchDetailsByPlanDetailsId(Integer planDetailsId) {
        if (planDetailsId == null) return null;
        BasicApplyBatchDetail basicApplyBatchDetail = new BasicApplyBatchDetail();
        basicApplyBatchDetail.setPlanDetailsId(planDetailsId);
        return basicApplyBatchDetailDao.getInfoList(basicApplyBatchDetail);
    }

    public BasicApplyBatchDetail getBasicApplyBatchDetail(String tableName, Integer tableId) {
        BasicApplyBatchDetail basicApplyBatchDetail = new BasicApplyBatchDetail();
        basicApplyBatchDetail.setTableId(tableId);
        basicApplyBatchDetail.setBisFromCase(false);
        basicApplyBatchDetail.setTableName(tableName);
        List<BasicApplyBatchDetail> infoList = basicApplyBatchDetailDao.getInfoList(basicApplyBatchDetail);
        if (CollectionUtils.isNotEmpty(infoList))
            return basicApplyBatchDetailDao.getInfoList(basicApplyBatchDetail).get(0);
        return null;
    }

    /**
     * 缓存中获取数据
     *
     * @param id
     * @return
     */
    public BasicApplyBatchDetail getCacheBasicApplyBatchDetailById(Integer id) {
        String rdsKey = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_BASIC_APPLY_BATCH_DETAIL_ID, String.valueOf(id));
        try {
            BasicApplyBatchDetail basicApplyBatchDetail = LangUtils.singleCache(rdsKey, id, BasicApplyBatchDetail.class, o -> basicApplyBatchDetailDao.getInfoById(o));
            return basicApplyBatchDetail;
        } catch (Exception e) {
            return basicApplyBatchDetailDao.getInfoById(id);
        }
    }

    public void saveBasicApplyBatchDetail(BasicApplyBatchDetail basicApplyBatchDetail) {
        ProjectPlanDetails projectPlanDetails = null;
        if (basicApplyBatchDetail.getPlanDetailsId() != null && basicApplyBatchDetail.getPlanDetailsId() > 0) {
            projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(basicApplyBatchDetail.getPlanDetailsId());
            if (projectPlanDetails != null) {
                basicApplyBatchDetail.setProjectId(projectPlanDetails.getProjectId());
            }
        }
        if (basicApplyBatchDetail.getId() != null && basicApplyBatchDetail.getId() > 0) {
            //传递的名称与数据库名称比较，确定是否需要更新一次名称
            BasicApplyBatchDetail dbBatchDetail = getDataById(basicApplyBatchDetail.getId());
            Boolean needUpdateName = !dbBatchDetail.getName().equals(basicApplyBatchDetail.getName());
            basicApplyBatchDetailDao.updateInfo(basicApplyBatchDetail);
            processControllerComponent.removeRedisKeyValues(AssessCacheConstant.PMCC_ASSESS_BASIC_APPLY_BATCH_DETAIL_ID, String.valueOf(basicApplyBatchDetail.getId()));//清除缓存
            if (needUpdateName) {//更新apply表中名称
                basicApplyService.updateNameByBatchDetailId(basicApplyBatchDetail.getId());
                updateBatchDetailFullName(basicApplyBatchDetail, dbBatchDetail.getName(), basicApplyBatchDetail.getName());
            }
        } else {
            String parentFullName = getFullNameByBatchDetailId(basicApplyBatchDetail.getPid());
            basicApplyBatchDetail.setFullName(StringUtils.defaultString(parentFullName, "/") + basicApplyBatchDetail.getName() + "/");
            basicApplyBatchDetail.setCreator(processControllerComponent.getThisUser());
            if (StringUtils.isBlank(basicApplyBatchDetail.getModifyType())) {
                basicApplyBatchDetail.setModifyType(BasicDataHandleEnum.NOMAL.getKey());
            }
            basicApplyBatchDetailDao.addInfo(basicApplyBatchDetail);
        }

        validIsSameBatchDetal(basicApplyBatchDetail);
    }

    //验证是否为项目下相同数据
    public void validIsSameBatchDetal(BasicApplyBatchDetail basicApplyBatchDetail) {
        //如果在相同项目下，fullName相同，并且是正常状态，并排除当前数据。那该数据则为同数据
        if (basicApplyBatchDetail.getProjectId() != null) {
            BasicApplyBatchDetail where = new BasicApplyBatchDetail();
            where.setProjectId(basicApplyBatchDetail.getProjectId());
            where.setFullName(basicApplyBatchDetail.getFullName());
            where.setModifyType(BasicDataHandleEnum.NOMAL.getKey());
            List<BasicApplyBatchDetail> batchDetailList = getBasicApplyBatchDetailList(where);
            batchDetailList = LangUtils.filter(batchDetailList, o -> !o.getId().equals(basicApplyBatchDetail.getId()));
            if (CollectionUtils.isNotEmpty(batchDetailList)) {
                basicApplyBatchDetail.setModifyType(BasicDataHandleEnum.SAME.getKey());
                basicApplyBatchDetailDao.updateInfo(basicApplyBatchDetail);
            } else {
                if (BasicDataHandleEnum.SAME.getKey().equalsIgnoreCase(basicApplyBatchDetail.getModifyType())) {
                    basicApplyBatchDetail.setModifyType(BasicDataHandleEnum.NOMAL.getKey());
                    basicApplyBatchDetailDao.updateInfo(basicApplyBatchDetail);
                }
            }
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
        basicApplyBatchDetail.setPlanDetailsId(planDetailsId);
        basicApplyBatchDetail.setCreator(processControllerComponent.getThisUser());
        basicApplyBatchDetail.setExecutor(processControllerComponent.getThisUser());
        if (basicApplyBatchDetail.getId() == null || basicApplyBatchDetail.getId() == 0) {
            if (StringUtils.isBlank(basicApplyBatchDetail.getModifyType())) {
                basicApplyBatchDetail.setModifyType(BasicDataHandleEnum.NOMAL.getKey());
            }
        }
        BasicFormClassifyEnum enumByKey = BasicFormClassifyEnum.getEnumByKey(basicApplyBatchDetail.getType());
        basicApplyBatchDetail.setTableName(enumByKey.getTableName());
        //楼盘----------------------------------------------
        if (enumByKey.getKey().startsWith(BasicFormClassifyEnum.ESTATE.getKey())) {
            BasicEstate estateData = basicEstateService.getBasicEstateById(basicApplyBatchDetail.getTableId());
            estateData.setName(basicApplyBatchDetail.getName());
            basicEstateService.saveAndUpdate(estateData, false);
            //楼栋----------------------------------------------
        } else if (enumByKey.getKey().startsWith(BasicFormClassifyEnum.BUILDING.getKey())) {
            BasicBuilding buildingData = basicBuildingService.getBasicBuildingById(basicApplyBatchDetail.getTableId());
            BasicBuilding building = buildingData == null ? new BasicBuilding() : buildingData;
            building.setBuildingNumber(basicApplyBatchDetail.getName());
            BasicEstate basicEstate = getBasicEstateByBatchDetailId(basicApplyBatchDetail.getPid());
            if (basicEstate != null) {
                building.setEstateId(basicEstate.getId());
                //街道号
                BasicEstateStreetInfo basicEstateStreetInfo = new BasicEstateStreetInfo();
                basicEstateStreetInfo.setEstateId(basicEstate.getId());
                List<BasicEstateStreetInfo> streetInfoList = basicEstateStreetInfoService.basicEstateStreetInfoList(basicEstateStreetInfo);
                if (CollectionUtils.isNotEmpty(streetInfoList)) {
                    building.setStreetInfoId(streetInfoList.get(0).getId());
                }
            }
            building.setBuildingName(basicApplyBatchDetail.getName());

            String value = basicCommonQuoteFieldInfoService.getValue(basicApplyBatchDetail.getApplyBatchId(), ExamineCommonQuoteFieldEnum.OPEN_TIME_ENUM);
            if (StringUtils.isNotBlank(value)) {
                building.setOpenTime(DateUtils.convertDate(value));
            }
            value = basicCommonQuoteFieldInfoService.getValue(basicApplyBatchDetail.getApplyBatchId(), ExamineCommonQuoteFieldEnum.COVER_AN_AREA);
            if (StringUtils.isNotBlank(value)) {
                building.setCoverAnArea(new BigDecimal(value));
            }
            value = basicCommonQuoteFieldInfoService.getValue(basicApplyBatchDetail.getApplyBatchId(), ExamineCommonQuoteFieldEnum.LAND_USE_YEAR_ENUM);
            if (StringUtils.isNotBlank(value) && !"null".equals(value)) {
                building.setLandUseYear(new BigDecimal(value));
            }

            basicBuildingService.saveAndUpdate(building, false);
            basicApplyBatchDetail.setTableId(building.getId());
            //单元----------------------------------------------
        } else if (enumByKey.getKey().startsWith(BasicFormClassifyEnum.UNIT.getKey())) {
            BasicUnit unitData = basicUnitService.getBasicUnitById(basicApplyBatchDetail.getTableId());
            BasicUnit unit = unitData == null ? new BasicUnit() : unitData;
            unit.setUnitNumber(basicApplyBatchDetail.getName());
            basicUnitService.saveAndUpdate(unit, false);
            basicApplyBatchDetail.setTableId(unit.getId());
            //房屋------------------------------------------------
        } else if (enumByKey.getKey().startsWith(BasicFormClassifyEnum.HOUSE.getKey())) {
            BasicHouse houseData = basicHouseService.getBasicHouseById(basicApplyBatchDetail.getTableId());
            BasicHouse house = houseData == null ? new BasicHouse() : houseData;
            house.setHouseNumber(basicApplyBatchDetail.getName());
            BasicUnit basicUnit = getBasicUnitByBatchDetailId(basicApplyBatchDetail.getPid());
            if (basicUnit != null) {
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
            BasicHouseTrading tradingByHouseId = basicHouseTradingService.getTradingByHouseId(house.getId());
            if (tradingByHouseId != null && tradingByHouseId.getId() != null && tradingByHouseId.getId() > 0) {
                houseTrading.setId(tradingByHouseId.getId());
            }
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
            basicEstateSurveyRecordService.initBasicEstateSurveyRecord(house.getId());
            BasicUnitHuxing huxing = new BasicUnitHuxing();
            huxing.setHouseId(house.getId());
            huxing.setEstateId(house.getEstateId());
            List<BasicUnitHuxing> basicUnitHuxingList = basicUnitHuxingService.basicUnitHuxingList(huxing);
            if (CollectionUtils.isEmpty(basicUnitHuxingList)) {
                basicUnitHuxingService.saveAndUpdateBasicUnitHuxing(huxing, false);
            }
            if (enumByKey.getKey().equals(BasicFormClassifyEnum.HOUSE_LAND.getKey())) {
                BasicEstateLandCategoryInfo categoryInfo = new BasicEstateLandCategoryInfo();
                categoryInfo.setHouseId(house.getId());
                categoryInfo.setLandId(0);
                categoryInfo.setCreator(processControllerComponent.getThisUser());
                basicEstateLandCategoryInfoDao.saveBasicEstateLandCategoryInfo(categoryInfo);
            }

        }
        basicApplyBatchDetailService.saveBasicApplyBatchDetail(basicApplyBatchDetail);
        this.insertBasicApply(basicApplyBatchDetail); //存入basicApply
        return basicApplyBatchDetail;
    }

    //存入basicApply表单
    public void insertBasicApply(BasicApplyBatchDetail houseBasicApplyBatchDetail) throws Exception {
        if (!houseBasicApplyBatchDetail.getType().startsWith(BasicFormClassifyEnum.HOUSE.getKey())) return;
        BasicApply where = new BasicApply();
        where.setBatchDetailId(houseBasicApplyBatchDetail.getId());
        List<BasicApply> basicApplyList = basicApplyDao.getBasicApplyList(where);
        BasicApply basicApply = null;
        if (CollectionUtils.isNotEmpty(basicApplyList)) {
            basicApply = basicApplyList.get(0);
        }
        if (basicApply == null) {
            basicApply = new BasicApply();
        }
        basicApply.setBasicHouseId(houseBasicApplyBatchDetail.getTableId());

        BasicEstateLandCategoryInfo query = new BasicEstateLandCategoryInfo();
        query.setHouseId(houseBasicApplyBatchDetail.getTableId());
        List<BasicEstateLandCategoryInfo> basicEstateLandCategoryInfoList = basicEstateLandCategoryInfoDao.basicEstateLandCategoryInfoList(query);
        if (CollectionUtils.isNotEmpty(basicEstateLandCategoryInfoList)) {
            basicApply.setLandCategoryId(basicEstateLandCategoryInfoList.get(0).getId());
        }
        basicApply.setApplyBatchId(houseBasicApplyBatchDetail.getApplyBatchId());
        basicApply.setDeclareRecordId(houseBasicApplyBatchDetail.getDeclareRecordId());
        BasicHouse basicHouse = basicHouseService.getBasicHouseById(houseBasicApplyBatchDetail.getTableId());
        if (basicHouse != null)
            basicApply.setArea(basicHouse.getArea());
        basicApply.setBatchDetailId(houseBasicApplyBatchDetail.getId());
        basicApply.setPlanDetailsId(houseBasicApplyBatchDetail.getPlanDetailsId());
        List<BasicApplyBatchDetail> list = Lists.newArrayList();
        collectionParentBatchDetails(houseBasicApplyBatchDetail.getId(), list);
        if (CollectionUtils.isNotEmpty(list)) {
            StringBuilder stringBuilder = new StringBuilder();
            List<KeyValueDto> keyValueDtos = Lists.newArrayList();
            for (int i = list.size() - 1; i >= 0; i--) {
                BasicApplyBatchDetail batchDetail = list.get(i);
                stringBuilder.append(batchDetail.getName()).append("/");
                KeyValueDto keyValueDto = new KeyValueDto();
                keyValueDto.setKey(batchDetail.getType());
                keyValueDto.setValue(String.valueOf(batchDetail.getTableId()));
                keyValueDto.setExplain(String.valueOf(batchDetail.getId()));
                keyValueDtos.add(keyValueDto);
            }
            basicApply.setName(StringUtils.strip(stringBuilder.toString(), "/"));
            basicApply.setAddress(basicApply.getName().replaceAll("^.*?/", "").replaceAll("/", ""));//资产清查使用
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

    public List<BasicApplyBatchDetail> getBasicApplyBatchDetailByName(String type, String name, Integer applyBatchId) {
        BasicApplyBatchDetail basicApplyBatchDetail = new BasicApplyBatchDetail();
        basicApplyBatchDetail.setType(type);
        basicApplyBatchDetail.setName(name);
        basicApplyBatchDetail.setApplyBatchId(applyBatchId);
        return basicApplyBatchDetailDao.getInfoList(basicApplyBatchDetail);
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
            basicAlternativeCaseService.deleteDataByBatchDetailId(applyBatchDetail.getId());
            processControllerComponent.removeRedisKeyValues(AssessCacheConstant.PMCC_ASSESS_BASIC_APPLY_BATCH_DETAIL_ID, String.valueOf(applyBatchDetail.getId()));//清除缓存
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void batchDeleteBasicApplyBatchDetail(String ids) throws Exception {
        if (!StringUtils.isEmpty(ids)) {
            List<Integer> integers = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(ids));
            for (Integer id : integers) {
                deleteBasicApplyBatchDetail(id);
            }
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
        BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchById(basicApplyBatchDetail.getApplyBatchId());
        if (applyBatch != null) {
            vo.setFormClassify(applyBatch.getClassify());
            vo.setFormType(applyBatch.getType());
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
            stringBuilder.append("/").append(list.get(i).getName()).append("/");
        }
        return stringBuilder.toString().replaceAll("//", "/");
    }

    public List<BasicApplyBatchDetail> getBasicApplyBatchDetailList(List<Integer> basicApplyBatchIds, String type) {
        if (CollectionUtils.isEmpty(basicApplyBatchIds)) return null;
        return basicApplyBatchDetailDao.getBasicApplyBatchDetailList(basicApplyBatchIds, type);
    }

    public List<BasicApplyBatchDetail> getBatchDetailListByIds(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) return null;
        return basicApplyBatchDetailDao.getBatchDetailListByIds(ids);
    }

    public List<BasicApplyBatchDetail> getHouseBatchDetailList(Integer batchDetailId) {
        BasicApplyBatchDetail batchDetail = getDataById(batchDetailId);
        List<BasicApplyBatchDetail> list = Lists.newArrayList();
        collectionChildBatchDetails(batchDetail, list);
        List<BasicApplyBatchDetail> filter = LangUtils.filter(list, o -> o.getType().startsWith(BasicFormClassifyEnum.HOUSE.getKey()));
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

    //批量设置权证id
    public void batchSaveDeclareRecordId(String applyBatchDetailIds, String declareRecordIds, String declareRecordName) {
        if (!StringUtils.isEmpty(applyBatchDetailIds)) {
            List<Integer> integers = FormatUtils.transformString2Integer(applyBatchDetailIds);
            List<BasicApplyBatchDetail> detailList = getBatchDetailListByIds(integers);
            List<BasicApplyBatchDetail> filter = LangUtils.filter(detailList, o -> o.getType().startsWith(BasicFormClassifyEnum.HOUSE.getKey()));
            if (CollectionUtils.isNotEmpty(filter)) {
                for (BasicApplyBatchDetail item : filter) {
                    List<Integer> recordIds = FormatUtils.transformString2Integer(declareRecordIds);
                    item.setDeclareRecordId(recordIds.get(0));
                    recordIds.remove(0);
                    if (CollectionUtils.isNotEmpty(recordIds)) {
                        item.setOtherDeclareRecoreId(String.format(",%s,", FormatUtils.transformListString(recordIds)));
                    } else {
                        item.setOtherDeclareRecoreId("");
                    }
                    item.setDeclareRecordName(declareRecordName);
                    saveBasicApplyBatchDetail(item);
                    //修改basic_apply表
                    BasicApply basicApply = basicApplyService.getBasicApplyByHouseId(item.getTableId());
                    if (basicApply != null) {
                        basicApply.setDeclareRecordId(item.getDeclareRecordId());
                        basicApply.setOtherDeclareRecordId(item.getOtherDeclareRecoreId());
                        basicApplyService.saveBasicApply(basicApply);
                    }
                }
            }
        }
    }

    /**
     * 获取楼栋、单元、房屋案例数据
     *
     * @param quoteId
     * @param name
     * @return
     */
    public BootstrapTableVo getCaseOtherListByName(Integer quoteId, String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        if (quoteId == null) return vo;
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicApplyBatchDetail> detailList = basicApplyBatchDetailDao.getBasicApplyBatchDetailList(quoteId, name);
        BasicApplyBatchDetail parentBatchDetail = basicApplyBatchDetailDao.getInfoById(quoteId);
        if (CollectionUtils.isNotEmpty(detailList) && parentBatchDetail != null) {
            for (BasicApplyBatchDetail batchDetail : detailList) {
                batchDetail.setName(parentBatchDetail.getName() + "/" + batchDetail.getName());
            }
        }
        vo.setTotal(page.getTotal());
        vo.setRows(detailList);
        return vo;
    }

    public List<BasicApplyBatchDetail> getBasicApplyBatchDetailList(Integer quoteId, String name) {
        if (quoteId == null || StringUtils.isBlank(name)) return null;
        return basicApplyBatchDetailDao.getBasicApplyBatchDetailList(quoteId, name);
    }

    /**
     * 递归更新全名
     *
     * @param batchDetail
     * @param sourceName
     * @param targetName
     */
    public void updateBatchDetailFullName(BasicApplyBatchDetail batchDetail, String sourceName, String targetName) {
        //1.先检查名称是否发生变化，如果有变化，先更新自身的全名，再递归更新下级的全名
        if (batchDetail == null || StringUtils.isBlank(sourceName) || StringUtils.isBlank(targetName)) return;
        if (sourceName.equalsIgnoreCase(targetName)) return;
        batchDetail.setFullName(batchDetail.getFullName().replaceAll(sourceName, targetName));
        basicApplyBatchDetailDao.updateInfo(batchDetail);
        validIsSameBatchDetal(batchDetail);
        List<BasicApplyBatchDetail> detailList = getBasicApplyBatchDetailByPid(batchDetail.getId(), batchDetail.getApplyBatchId());
        if (CollectionUtils.isEmpty(detailList)) return;
        for (BasicApplyBatchDetail basicApplyBatchDetail : detailList) {
            updateBatchDetailFullName(basicApplyBatchDetail, sourceName, targetName);
        }
    }
}
