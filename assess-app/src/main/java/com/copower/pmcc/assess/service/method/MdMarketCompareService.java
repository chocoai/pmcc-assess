package com.copower.pmcc.assess.service.method;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.AssessProjectTypeEnum;
import com.copower.pmcc.assess.common.enums.ExamineTypeEnum;
import com.copower.pmcc.assess.common.enums.basic.BasicApplyTypeEnum;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.dao.method.MdMarketCompareDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdMarketCompareItemDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.method.MarketCompareItemDto;
import com.copower.pmcc.assess.dto.input.method.MarketCompareResultDto;
import com.copower.pmcc.assess.dto.output.basic.BasicApplyVo;
import com.copower.pmcc.assess.dto.output.method.MdCompareCaseVo;
import com.copower.pmcc.assess.dto.output.method.MdCompareInitParamVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.data.DataBuildingNewRateService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kings on 2018-7-23.
 */
@Service
public class MdMarketCompareService {
    @Autowired
    private MdMarketCompareDao mdMarketCompareDao;
    @Autowired
    private MdMarketCompareItemDao mdMarketCompareItemDao;
    @Autowired
    private DataSetUseFieldService dataSetUseFieldService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private MdMarketCompareFieldService mdMarketCompareFieldService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataBuildingNewRateService dataBuildingNewRateService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private MdBaseLandPriceService mdBaseLandPriceService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private BasicHouseTradingService basicHouseTradingService;

    public MdMarketCompare getMdMarketCompare(Integer id) {
        return mdMarketCompareDao.getMarketCompareById(id);
    }


    /**
     * 根据设定用途类型获取配置的字段
     *
     * @return
     */
    public List<DataSetUseField> getSetUseFieldList(String fieldName) {
        List<DataSetUseField> setUseFields = dataSetUseFieldService.getCacheSetUseFieldList(fieldName);
        List<DataSetUseField> fieldList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(setUseFields)) {
            for (DataSetUseField setUseField : setUseFields) {
                fieldList.add(setUseField);
                getSubFieldList(fieldList, setUseField.getId());
            }
        }
        return fieldList;
    }

    private void getSubFieldList(List<DataSetUseField> fieldList, Integer pid) {
        List<DataSetUseField> useFields = dataSetUseFieldService.getCacheSetUseFieldListByPid(pid);
        if (CollectionUtils.isEmpty(useFields)) return;
        for (DataSetUseField useField : useFields) {
            fieldList.add(useField);
            getSubFieldList(fieldList, useField.getId());
        }
    }

    public List<DataSetUseField> getShowSetUseFieldList(AssessProjectTypeEnum projectTypeEnum) {
        List<DataSetUseField> setUseFields = null;
        List<DataSetUseField> fieldList = Lists.newArrayList();
        switch (projectTypeEnum) {
            case ASSESS_PROJECT_TYPE_HOUSE:
                setUseFields = dataSetUseFieldService.getShowSetUseFieldList(BaseConstant.ASSESS_DATA_SET_USE_FIELD_HOUSE);
                break;
            case ASSESS_PROJECT_TYPE_LAND:
                setUseFields = dataSetUseFieldService.getShowSetUseFieldList(BaseConstant.ASSESS_DATA_SET_USE_FIELD_LAND);
                break;
        }
        if (CollectionUtils.isNotEmpty(setUseFields)) {
            for (DataSetUseField setUseField : setUseFields) {
                fieldList.add(setUseField);
                getShowSubFieldList(fieldList, setUseField.getId());
            }
        }
        return fieldList;
    }


    private void getShowSubFieldList(List<DataSetUseField> fieldList, Integer pid) {
        List<DataSetUseField> useFields = dataSetUseFieldService.getShowSetUseFieldListByPid(pid);
        if (CollectionUtils.isEmpty(useFields)) return;
        for (DataSetUseField useField : useFields) {
            fieldList.add(useField);
            getShowSubFieldList(fieldList, useField.getId());
        }
    }

    /**
     * 初始化查勘字段数据信息
     */
    public MdMarketCompare initExplore(SchemeJudgeObject schemeJudgeObject, boolean isLand) {
        if (schemeJudgeObject == null) return null;
        SchemeAreaGroup areaGroup = schemeAreaGroupService.getSchemeAreaGroup(schemeJudgeObject.getAreaGroupId());
        MdMarketCompare mdMarketCompare = new MdMarketCompare();
        mdMarketCompare.setName(schemeJudgeObject.getName());
        mdMarketCompare.setValueTimePoint(areaGroup.getValueTimePoint());
        mdMarketCompare.setCreator(commonService.thisUserAccount());
        mdMarketCompareDao.addMarketCompare(mdMarketCompare);
        //如果对象是合并的估价对象，则取该合并估价对象的标准估价对象
        if (schemeJudgeObject.getBisMerge() == Boolean.TRUE)
            schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(schemeJudgeObject.getStandardJudgeId());

        String setUseFieldType = isLand ? BaseConstant.ASSESS_DATA_SET_USE_FIELD_LAND : BaseConstant.ASSESS_DATA_SET_USE_FIELD_HOUSE;
        List<DataSetUseField> setUseFieldList = getSetUseFieldList(setUseFieldType);
        if (schemeJudgeObject.getBasicApplyId() != null && schemeJudgeObject.getBasicApplyId()!= 0) {
            setJudgeCompareItem(areaGroup, schemeJudgeObject, basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId()), mdMarketCompare.getId(), setUseFieldList, isLand);
        } else {
            List<BasicApply> basicApplyList = basicApplyService.getListByDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
            if (CollectionUtils.isNotEmpty(basicApplyList)) {//检查估价对象是否有多个标准 如果有多个标准则不处理 由前端选择后初始化
                setJudgeCompareItem(areaGroup, schemeJudgeObject, basicApplyList.get(basicApplyList.size() - 1), mdMarketCompare.getId(), setUseFieldList, isLand);
            }
        }
        return mdMarketCompare;
    }

    private void setJudgeCompareItem(SchemeAreaGroup areaGroup, SchemeJudgeObject schemeJudgeObject, BasicApply basicApply, Integer mcId, List<DataSetUseField> setUseFieldList, Boolean isLand) {
        if (CollectionUtils.isEmpty(setUseFieldList)) return;
        //清除原估价对象信息
        MdMarketCompareItem compareItem = getEvaluationByMcId(mcId);
        if (compareItem != null) {
            mdMarketCompareItemDao.deleteMarketCompareItem(compareItem.getId());
        }
        MdMarketCompareItem mdMarketCompareItem = new MdMarketCompareItem();
        mdMarketCompareItem.setMcId(mcId);
        mdMarketCompareItem.setName("估价对象");
        mdMarketCompareItem.setArea(schemeJudgeObject.getEvaluationArea());
        mdMarketCompareItem.setType(ExamineTypeEnum.EXPLORE.getId());
        mdMarketCompareItem.setCreator(commonService.thisUserAccount());
        mdMarketCompareItem.setPlanDetailsId(basicApply.getPlanDetailsId());
        mdMarketCompareItem.setBasicApplyId(basicApply.getId());
        mdMarketCompareItem.setJsonContent(mdMarketCompareFieldService.getCompareInfo(areaGroup, schemeJudgeObject, basicApply, setUseFieldList, false));
        if (isLand) {//如果是土地比较法 则需额外处理 年期修正系数与容积率修正系数
            setCoefficient(areaGroup, schemeJudgeObject, mdMarketCompareItem, basicApply, false);
        }
        //获取成新率相关参数
        setResidueRatioParam(mdMarketCompareItem, basicApply, areaGroup.getValueTimePoint());
        mdMarketCompareItemDao.addMarketCompareItem(mdMarketCompareItem);
    }

    /**
     * 获取标准估价对象数据
     *
     * @param schemeJudgeObject
     * @return
     */
    public List<BasicApply> getStandardJudgeList(SchemeJudgeObject schemeJudgeObject) {
        if (schemeJudgeObject == null) return null;
        List<BasicApply> basicApplyList = basicApplyService.getListByDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
        return basicApplyList;
    }

    /**
     * 设置修正系数
     *
     * @param areaGroup
     * @param mdMarketCompareItem
     * @param basicApply
     */
    private void setCoefficient(SchemeAreaGroup areaGroup, SchemeJudgeObject schemeJudgeObject, MdMarketCompareItem mdMarketCompareItem, BasicApply basicApply, Boolean isCase) {
        BasicEstate examineEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
        BasicEstateLandState landState = basicEstateLandStateService.getLandStateByEstateId(examineEstate.getId());
        BigDecimal volumetricRate = null;
        MdMarketCompare marketCompare = getMdMarketCompare(mdMarketCompareItem.getMcId());
        //年期修正系数
        BasicHouse basicHouse = basicHouseService.getHouseByApplyId(basicApply.getId());
        BasicHouseTrading houseTrading = basicHouseTradingService.getTradingByHouseId(basicHouse.getId());
        if (basicHouse != null && basicHouse.getUseYear() != null) {
            BigDecimal legalAge = new BigDecimal(basicHouse.getUseYear());
            BigDecimal surplusYear = null;
            if (isCase) { //计算剩余年限=使用年限-已使用年限 已使用年限=评估基准日-交易时间
                int diffDays = DateUtils.diffDate(areaGroup.getValueTimePoint(), houseTrading.getTradingTime());
                BigDecimal yearCount = new BigDecimal(diffDays).divide(new BigDecimal(DateUtils.DAYS_PER_YEAR), 2, BigDecimal.ROUND_HALF_UP);
                surplusYear = legalAge.subtract(yearCount);
            } else {//估价对象则直接取剩余年限
                surplusYear = schemeJudgeObject.getLandRemainingYear();
            }
            BigDecimal periodAmend = mdBaseLandPriceService.getPeriodAmend(marketCompare.getRewardRate(), legalAge, surplusYear);
            if (periodAmend != null)
                mdMarketCompareItem.setAnnualCoefficient(periodAmend);
        }
        //容积率修正系数
        if (volumetricRate != null)
            mdMarketCompareItem.setVolumeRatioCoefficient(volumetricRate);
    }

    /**
     * 设置成新率
     *
     * @param mdMarketCompareItem
     * @param basicApply
     * @param timePoint
     */
    private void setResidueRatioParam(MdMarketCompareItem mdMarketCompareItem, BasicApply basicApply, Date timePoint) {
        if (mdMarketCompareItem == null || basicApply == null || timePoint == null) return;
        BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
        BasicHouse basicHouse = basicHouseService.getHouseByApplyId(basicApply.getId());
        if (basicBuilding == null || basicBuilding.getBeCompletedTime() == null) return;
        Integer usedYear = DateUtils.diffDate(timePoint, basicBuilding.getBeCompletedTime()) / DateUtils.DAYS_PER_YEAR;//已用年限
        if (basicApply.getType().equals(BasicApplyTypeEnum.RESIDENCE.getId())) {
            BaseDataDic dataDic = baseDataDicService.getDataDicById(basicBuilding.getResidenceUseYear());
            if (dataDic != null)
                mdMarketCompareItem.setUsableYear(Integer.valueOf(StringUtils.trim(dataDic.getRemark())));
        } else {
            DataBuildingNewRate buildingNewRate = dataBuildingNewRateService.getByiDdataBuildingNewRate(basicBuilding.getIndustryUseYear());
            if (buildingNewRate != null)
                mdMarketCompareItem.setUsableYear(buildingNewRate.getDurableLife());
        }
        mdMarketCompareItem.setResidueRatioId(0);
        mdMarketCompareItem.setUsedYear(usedYear);
        mdMarketCompareItem.setHouseId(basicHouse.getId());
    }


    /**
     * 选择估价对象
     */
    public MdCompareInitParamVo selectJudge(Integer judgeObjectId, Integer applyId, Integer mcId, boolean isLand) throws Exception {
        MdCompareInitParamVo mdCompareInitParamVo = new MdCompareInitParamVo();
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
        BasicApply basicApply = basicApplyService.getByBasicApplyId(applyId);
        SchemeAreaGroup areaGroup = schemeAreaGroupService.getSchemeAreaGroup(schemeJudgeObject.getAreaGroupId());
        String setUseFieldType = isLand ? BaseConstant.ASSESS_DATA_SET_USE_FIELD_LAND : BaseConstant.ASSESS_DATA_SET_USE_FIELD_HOUSE;
        List<DataSetUseField> setUseFieldList = getSetUseFieldList(setUseFieldType);
        setJudgeCompareItem(areaGroup, schemeJudgeObject, basicApply, mcId, setUseFieldList, isLand);
        mdCompareInitParamVo.setMcId(mcId);
        mdCompareInitParamVo.setJudgeObjectId(judgeObjectId);
        mdCompareInitParamVo.setMarketCompare(getMdMarketCompare(mcId));
        mdCompareInitParamVo.setFields(setUseFieldList);
        mdCompareInitParamVo.setEvaluation(getEvaluationByMcId(mcId));
        mdCompareInitParamVo.setCases(getCaseListByMcId(mcId));
        return mdCompareInitParamVo;
    }

    /**
     * 选择案例
     *
     * @param mcId
     * @param planDetailsIdList
     * @param judgeObjectId
     * @param isLand
     * @return
     * @throws Exception
     */
    public MdCompareInitParamVo selectCase(Integer mcId, String planDetailsIdList, Integer judgeObjectId, boolean isLand, String jsonData) throws Exception {
        MdCompareInitParamVo mdCompareInitParamVo = new MdCompareInitParamVo();
        //添加选择后的案例信息
        if (StringUtils.isBlank(planDetailsIdList))
            throw new BusinessException("请选择有效案例");
        List<Integer> integers = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(planDetailsIdList));
        List<MdMarketCompareItem> compareItemList = getCaseListByMcId(mcId);
        List<Integer> planDetailsIds = LangUtils.transform(compareItemList, o -> o.getPlanDetailsId());
        List<Integer> removePlanDetailsIds = Lists.newArrayList();
        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()) {
            if (CollectionUtils.isNotEmpty(planDetailsIds) && planDetailsIds.contains(iterator.next())) {
                iterator.remove();
            }else{
                removePlanDetailsIds.add(iterator.next());
            }
        }
        planDetailsIds.forEach(o -> mdMarketCompareItemDao.deleteMarketCompareItem(o));//清除原案例信息
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
        SchemeAreaGroup areaGroup = schemeAreaGroupService.getSchemeAreaGroup(schemeJudgeObject.getAreaGroupId());
        String setUseFieldType = isLand ? BaseConstant.ASSESS_DATA_SET_USE_FIELD_LAND : BaseConstant.ASSESS_DATA_SET_USE_FIELD_HOUSE;
        List<DataSetUseField> setUseFieldList = getSetUseFieldList(setUseFieldType);

        int i = 1;
        for (Integer planDetailsId : integers) {
            ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
            MdMarketCompareItem mdMarketCompareItem = new MdMarketCompareItem();
            mdMarketCompareItem.setMcId(mcId);
            mdMarketCompareItem.setPlanDetailsId(planDetailsId);
            mdMarketCompareItem.setName(String.format("案例%s", i));
            mdMarketCompareItem.setRemark(projectPlanDetails.getProjectPhaseName());
            mdMarketCompareItem.setType(ExamineTypeEnum.CASE.getId());
            mdMarketCompareItem.setCreator(commonService.thisUserAccount());
            mdMarketCompareItem.setMustAdjustPrice(false);
            BasicApply basicApply = basicApplyService.getBasicApplyByPlanDetailsId(projectPlanDetails.getId());
            mdMarketCompareItem.setJsonContent(mdMarketCompareFieldService.getCompareInfo(areaGroup, schemeJudgeObject, basicApply, setUseFieldList, true));
            if (isLand) {//在估价对象中获取法定年限与剩余年限，如果未获取到则无年期修正系数
                setCoefficient(areaGroup, schemeJudgeObject, mdMarketCompareItem, basicApply, true);
            }
            setResidueRatioParam(mdMarketCompareItem, basicApply, areaGroup.getValueTimePoint());//获取成新率相关参数
            mdMarketCompareItemDao.addMarketCompareItem(mdMarketCompareItem);
            i++;

        }
        mdCompareInitParamVo.setMcId(mcId);
        mdCompareInitParamVo.setJudgeObjectId(judgeObjectId);
        mdCompareInitParamVo.setMarketCompare(getMdMarketCompare(mcId));
        mdCompareInitParamVo.setFields(setUseFieldList);
        mdCompareInitParamVo.setEvaluation(getEvaluationByMcId(mcId));
        List<MdMarketCompareItem> itemList = getCaseListByMcId(mcId);
        mdCompareInitParamVo.setCases(itemList);
        return mdCompareInitParamVo;
    }

    /**
     * 获取数据by id
     *
     * @param id
     * @return
     */
    public MdMarketCompareItem getMarketCompareItemById(Integer id) {
        MdMarketCompareItem mdMarketCompareItem = mdMarketCompareItemDao.getMarketCompareItemById(id);
        return mdMarketCompareItem;
    }

    /**
     * 保存数据
     *
     * @param mdMarketCompareItem
     */
    public void saveMarketCompareItem(MdMarketCompareItem mdMarketCompareItem) {
        if (mdMarketCompareItem.getId() == null || mdMarketCompareItem.getId() <= 0) {
            mdMarketCompareItem.setCreator(commonService.thisUserAccount());
            mdMarketCompareItemDao.addMarketCompareItem(mdMarketCompareItem);
        } else {
            mdMarketCompareItemDao.updateMarketCompareItem(mdMarketCompareItem);
        }
    }

    /**
     * 获取委估对象信息by mcid
     *
     * @param mcId
     * @return
     */
    public MdMarketCompareItem getEvaluationByMcId(Integer mcId) {
        MdMarketCompareItem mdMarketCompareItem = new MdMarketCompareItem();
        mdMarketCompareItem.setMcId(mcId);
        mdMarketCompareItem.setType(ExamineTypeEnum.EXPLORE.getId());
        List<MdMarketCompareItem> marketCompareItemList = mdMarketCompareItemDao.getMarketCompareItemList(mdMarketCompareItem);
        if (CollectionUtils.isEmpty(marketCompareItemList)) return null;
        return marketCompareItemList.get(0);
    }

    /**
     * 获取案例信息by mcid
     *
     * @param mcId
     * @return
     */
    public List<MdMarketCompareItem> getCaseListByMcId(Integer mcId) {
        MdMarketCompareItem mdMarketCompareItem = new MdMarketCompareItem();
        mdMarketCompareItem.setMcId(mcId);
        mdMarketCompareItem.setType(ExamineTypeEnum.CASE.getId());
        List<MdMarketCompareItem> marketCompareItemList = mdMarketCompareItemDao.getMarketCompareItemList(mdMarketCompareItem);
        return marketCompareItemList;
    }

    /**
     * 保存市场比较法的结果信息
     *
     * @param marketCompareResultDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public MdMarketCompare saveResult(MarketCompareResultDto marketCompareResultDto) {
        //1.委估对象的平均价保存 2.案例数据相关信息存储
        MdMarketCompare marketCompare = mdMarketCompareDao.getMarketCompareById(marketCompareResultDto.getId());
        MdMarketCompareItem evaluationItem = marketCompareResultDto.getEvaluationItem();
        mdMarketCompareItemDao.updateMarketCompareItem(evaluationItem);
        List<MdMarketCompareItem> caseItemList = marketCompareResultDto.getCaseItemList();
        if (CollectionUtils.isNotEmpty(caseItemList)) {
            for (MdMarketCompareItem mdMarketCompareItem : caseItemList) {
                mdMarketCompareItemDao.updateMarketCompareItem(mdMarketCompareItem);
            }
        }
        marketCompare.setPrice(evaluationItem.getAveragePrice());
        mdMarketCompareDao.updateMarketCompare(marketCompare);
        return marketCompare;
    }

    /**
     * 加载项目所有案例数据
     *
     * @param projectId
     * @return
     */
    public BootstrapTableVo getCasesAll(Integer projectId, String projectPhaseName) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.CASE_STUDY_EXTEND);
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ProjectPlanDetails> planDetails = projectPlanDetailsDao.getProjectPlanDetailsList(projectId, projectPhase.getId(), projectPhaseName);
        List<MdCompareCaseVo> voList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(planDetails)) {
            for (ProjectPlanDetails planDetail : planDetails) {
                MdCompareCaseVo mdCompareCaseVo = new MdCompareCaseVo();
                BasicApply basicApply = basicApplyService.getBasicApplyByPlanDetailsId(planDetail.getId());
                if (basicApply == null) continue;
                mdCompareCaseVo.setPlanDetailsId(planDetail.getId());
                mdCompareCaseVo.setName(planDetail.getProjectPhaseName());
                BasicHouse basicHouse = basicHouseService.getHouseByApplyId(basicApply.getId());
                if (basicHouse == null) continue;
                mdCompareCaseVo.setArea(basicHouse.getArea());
                mdCompareCaseVo.setAreaDesc(basicHouse.getAreaDesc());
                voList.add(mdCompareCaseVo);
            }
        }
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(voList == null ? new ArrayList() : voList);
        return bootstrapTableVo;
    }

    /**
     * 更新年期修正系数
     *
     * @param mcId
     * @param rewardRateId
     * @param rewardRate
     */
    @Transactional(rollbackFor = Exception.class)
    public List<KeyValueDto> updateAnnualCoefficient(Integer judgeObjectId, Integer mcId, Integer rewardRateId, BigDecimal rewardRate) {
        MdMarketCompare mdMarketCompare = mdMarketCompareDao.getMarketCompareById(mcId);
        mdMarketCompare.setRewardRateId(rewardRateId);
        mdMarketCompare.setRewardRate(rewardRate);
        mdMarketCompareDao.updateMarketCompare(mdMarketCompare);

        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
        SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.getSchemeAreaGroup(schemeJudgeObject.getAreaGroupId());

        MdMarketCompareItem mdMarketCompareItem = new MdMarketCompareItem();
        mdMarketCompareItem.setMcId(mcId);
        List<MdMarketCompareItem> marketCompareItemList = mdMarketCompareItemDao.getMarketCompareItemList(mdMarketCompareItem);
        if (CollectionUtils.isEmpty(marketCompareItemList)) return null;
        List<KeyValueDto> keyValueDtos = Lists.newArrayList();
        for (MdMarketCompareItem marketCompareItem : marketCompareItemList) {
            BasicApply basicApply = basicApplyService.getBasicApplyByPlanDetailsId(marketCompareItem.getPlanDetailsId());
            BasicHouse basicHouse = basicHouseService.getHouseByApplyId(basicApply.getId());
            BasicHouseTrading houseTrading = basicHouseTradingService.getTradingByHouseId(basicHouse.getId());
            if (basicHouse != null) {
                //计算剩余年限=使用年限-已使用年限  //已使用年限=评估基准日-交易时间
                BigDecimal legalAge = new BigDecimal(basicHouse.getUseYear());
                int diffDays = DateUtils.diffDate(schemeAreaGroup.getValueTimePoint(), houseTrading.getTradingTime());
                BigDecimal yearCount = new BigDecimal(diffDays).divide(new BigDecimal(DateUtils.DAYS_PER_YEAR), 2, BigDecimal.ROUND_HALF_UP);
                BigDecimal periodAmend = mdBaseLandPriceService.getPeriodAmend(rewardRate, legalAge, legalAge.subtract(yearCount));
                if (periodAmend != null) {
                    marketCompareItem.setAnnualCoefficient(periodAmend);
                    mdMarketCompareItemDao.updateMarketCompareItem(marketCompareItem);
                    KeyValueDto keyValueDto = new KeyValueDto();
                    keyValueDto.setKey(String.valueOf(marketCompareItem.getId()));
                    keyValueDto.setValue(String.valueOf(periodAmend));
                    keyValueDtos.add(keyValueDto);
                }
            }
        }
        return keyValueDtos;
    }

    public MdCompareInitParamVo refreshData(Integer mcId, Integer judgeObjectId, Boolean isLand) {
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
        if (schemeJudgeObject.getBisMerge() == Boolean.TRUE)
            schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(schemeJudgeObject.getStandardJudgeId());
        SchemeAreaGroup areaGroup = schemeAreaGroupService.getSchemeAreaGroup(schemeJudgeObject.getAreaGroupId());
        String setUseFieldType = isLand ? BaseConstant.ASSESS_DATA_SET_USE_FIELD_LAND : BaseConstant.ASSESS_DATA_SET_USE_FIELD_HOUSE;
        List<DataSetUseField> setUseFieldList = getSetUseFieldList(setUseFieldType);
        //修改查勘信息
        MdMarketCompareItem mdMarketCompareItem = new MdMarketCompareItem();
        mdMarketCompareItem.setMcId(mcId);
        mdMarketCompareItem.setType(ExamineTypeEnum.EXPLORE.getId());
        List<MdMarketCompareItem> marketCompareItemList = mdMarketCompareItemDao.getMarketCompareItemList(mdMarketCompareItem);
        if (CollectionUtils.isNotEmpty(marketCompareItemList)) {
            mdMarketCompareItem = marketCompareItemList.get(0);
            BasicApply basicApply = basicApplyService.getBasicApplyByPlanDetailsId(mdMarketCompareItem.getPlanDetailsId());
            mdMarketCompareItem.setJsonContent(mdMarketCompareFieldService.getCompareInfo(areaGroup, schemeJudgeObject, basicApply, setUseFieldList, false));
            mdMarketCompareItemDao.updateMarketCompareItem(mdMarketCompareItem);
        }

        //所有案例信息
        MdMarketCompareItem mdMarketCompareItemCase = new MdMarketCompareItem();
        mdMarketCompareItemCase.setMcId(mcId);
        mdMarketCompareItemCase.setType(ExamineTypeEnum.CASE.getId());
        List<MdMarketCompareItem> marketCompareItemCaseList = mdMarketCompareItemDao.getMarketCompareItemList(mdMarketCompareItemCase);
        if (CollectionUtils.isNotEmpty(marketCompareItemCaseList)) {
            for (MdMarketCompareItem caseItem : marketCompareItemCaseList) {
                ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(caseItem.getPlanDetailsId());
                BasicApply basicApply = basicApplyService.getBasicApplyByPlanDetailsId(projectPlanDetails.getId());
                String newData = mdMarketCompareFieldService.getCompareInfo(areaGroup, schemeJudgeObject, basicApply, setUseFieldList, true);
                List<MarketCompareItemDto> newDataList = JSON.parseArray(newData, MarketCompareItemDto.class);
                List<MarketCompareItemDto> oldDataList = JSON.parseArray(caseItem.getJsonContent(), MarketCompareItemDto.class);
                //分值不改变
                for (MarketCompareItemDto newItem : newDataList) {
                    for (MarketCompareItemDto oldItem : oldDataList) {
                        if (newItem.getName().equals(oldItem.getName())) {
                            newItem.setScore(oldItem.getScore());
                            newItem.setRatio(oldItem.getRatio());
                        }
                    }
                }
                caseItem.setJsonContent(JSON.toJSONString(newDataList));
                mdMarketCompareItemDao.updateMarketCompareItem(caseItem);
            }
        }


        MdCompareInitParamVo mdCompareInitParamVo = new MdCompareInitParamVo();
        mdCompareInitParamVo.setMcId(mcId);
        mdCompareInitParamVo.setJudgeObjectId(judgeObjectId);
        mdCompareInitParamVo.setMarketCompare(getMdMarketCompare(mcId));
        mdCompareInitParamVo.setFields(setUseFieldList);
        mdCompareInitParamVo.setEvaluation(getEvaluationByMcId(mcId));
        mdCompareInitParamVo.setCases(getCaseListByMcId(mcId));
        return mdCompareInitParamVo;
    }

    //获取jsonContent中基础数据value值
    public String getBaseValueData(List<MarketCompareItemDto> marketCompareItemDtos, String fieldName) {
        DataSetUseField cacheSetUseFieldList = dataSetUseFieldService.getCacheSetUseFieldByFieldName(fieldName);
        String s = new String();
        for (MarketCompareItemDto item : marketCompareItemDtos) {
            if (cacheSetUseFieldList.getFieldName().equals(item.getName())) {
                s = item.getValue();
            }
        }
        return s;
    }
}
