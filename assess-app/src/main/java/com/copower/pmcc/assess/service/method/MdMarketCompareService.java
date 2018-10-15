package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.common.enums.ExamineTypeEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.method.MdMarketCompareDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdMarketCompareItemDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.dao.project.examine.ExamineHouseTradingDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.method.MarketCompareResultDto;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    private ExamineHouseTradingDao examineHouseTradingDao;
    @Autowired
    private BaseDataDicService baseDataDicService;

    public MdMarketCompare getMdMarketCompare(Integer id) {
        return mdMarketCompareDao.getMarketCompareById(id);
    }


    /**
     * 根据设定用途类型获取配置的字段
     *
     * @param setUse
     * @return
     */
    public List<DataSetUseField> getFieldList(Integer setUse) {
        DataSetUseField dataSetUseField = dataSetUseFieldService.getSetUseFieldById(setUse);
        if (dataSetUseField == null) return null;
        List<DataSetUseField> fieldList = Lists.newArrayList();
        List<DataSetUseField> setUseFields = dataSetUseFieldService.getCacheSetUseFieldListByPid(dataSetUseField.getId());
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

    /**
     * 初始化查勘字段数据信息
     */
    public MdMarketCompare initExplore(SchemeJudgeObject judgeObject) {
        if (judgeObject == null) return null;
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObject.getId());
        if (schemeJudgeObject == null) return null;
        List<DataSetUseField> setUseFieldList = getFieldList(judgeObject.getSetUse());
        if (CollectionUtils.isEmpty(setUseFieldList)) return null;
        MdMarketCompare mdMarketCompare = new MdMarketCompare();
        mdMarketCompare.setName(String.format("%s号委估对象", schemeJudgeObject.getNumber()));
        mdMarketCompare.setCreator(commonService.thisUserAccount());
        mdMarketCompareDao.addMarketCompare(mdMarketCompare);

        MdMarketCompareItem mdMarketCompareItem = new MdMarketCompareItem();
        mdMarketCompareItem.setMcId(mdMarketCompare.getId());
        mdMarketCompareItem.setName("估价对象");
        mdMarketCompareItem.setType(ExamineTypeEnum.EXPLORE.getId());
        mdMarketCompareItem.setCreator(commonService.thisUserAccount());

        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.SCENE_EXPLORE);
        List<ProjectPlanDetails> planDetailsList = projectPlanDetailsService.getProjectPlanDetails(schemeJudgeObject.getDeclareRecordId(), projectPhase.getId());
        ProjectPlanDetails planDetails = planDetailsList.get(0);
        mdMarketCompareItem.setJsonContent(mdMarketCompareFieldService.getJsonContent(schemeJudgeObject.getDeclareRecordId(), planDetails.getId(), setUseFieldList));
        mdMarketCompareItemDao.addMarketCompareItem(mdMarketCompareItem);

        return mdMarketCompare;
    }

    /**
     * 选择案例
     *
     * @param planDetailsIdString
     */
    public void selectCase(Integer mcId, Integer setUse, String planDetailsIdString) {
        //清除原案例信息
        MdMarketCompareItem where = new MdMarketCompareItem();
        where.setMcId(mcId);
        where.setType(ExamineTypeEnum.CASE.getId());
        List<MdMarketCompareItem> compareItemList = mdMarketCompareItemDao.getMarketCompareItemList(where);
        if (CollectionUtils.isNotEmpty(compareItemList)) {
            for (MdMarketCompareItem item : compareItemList) {
                mdMarketCompareItemDao.deleteMarketCompareItem(item.getId());
            }
        }
        //添加选择后的案例信息
        List<Integer> planDetailsIdList = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(planDetailsIdString));
        if (CollectionUtils.isNotEmpty(planDetailsIdList)) {
            List<DataSetUseField> setUseFieldList = getFieldList(setUse);
            for (Integer planDetailsId : planDetailsIdList) {
                ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
                MdMarketCompareItem mdMarketCompareItem = new MdMarketCompareItem();
                mdMarketCompareItem.setMcId(mcId);
                mdMarketCompareItem.setName(projectPlanDetails.getProjectPhaseName());
                mdMarketCompareItem.setType(ExamineTypeEnum.CASE.getId());
                mdMarketCompareItem.setCreator(commonService.thisUserAccount());
                mdMarketCompareItem.setInitialPrice(getTradingPrice(planDetailsId));
                mdMarketCompareItem.setMustAdjustPrice(mustAdjustPrice(planDetailsId));
                mdMarketCompareItem.setJsonContent(mdMarketCompareFieldService.getJsonContent(projectPlanDetails.getDeclareRecordId(), projectPlanDetails.getId(), setUseFieldList));
                mdMarketCompareItemDao.addMarketCompareItem(mdMarketCompareItem);
            }
        }
    }

    /**
     * 获取成交价
     *
     * @param planDetailsId
     * @return
     */
    public BigDecimal getTradingPrice(Integer planDetailsId) {
        ExamineHouseTrading houseTrading = examineHouseTradingDao.getHouseTradingByPlanDetailsId(planDetailsId);
        if (houseTrading == null) return null;
        return houseTrading.getTradingPrice();
    }

    /**
     * 是否必须调整成交价
     *
     * @param planDetailsId
     * @return
     */
    public Boolean mustAdjustPrice(Integer planDetailsId) {
        ExamineHouseTrading houseTrading = examineHouseTradingDao.getHouseTradingByPlanDetailsId(planDetailsId);
        if (houseTrading != null) {
            BaseDataDic transactionDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.EXAMINE_HOUSE_NORMAL_TRANSACTION_YES);
            BaseDataDic disposableDic = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.EXAMINE_HOUSE_PAYMENT_METHOD_DISPOSABLE);
            if (houseTrading.getNormalTransaction() != null && !houseTrading.getNormalTransaction().equals(transactionDic.getId()))
                return true;
            if (houseTrading.getPaymentMethod() != null && !houseTrading.getPaymentMethod().equals(disposableDic.getId()))
                return true;
        }
        return false;
    }

    /**
     * 获取委估对象信息by mcid
     *
     * @param mcId
     * @return
     */
    public MdMarketCompareItem getEvaluationListByMcId(Integer mcId) {
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
     * 获取所有案例信息
     *
     * @param declareId
     * @return
     */
    public List<ProjectPlanDetails> getCaseAll(Integer declareId) {
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.CASE_STUDY);
        ProjectPlanDetails projectPlanDetails = new ProjectPlanDetails();
        projectPlanDetails.setDeclareRecordId(declareId);
        projectPlanDetails.setProjectPhaseId(projectPhase.getId());
        projectPlanDetails.setBisStart(false);
        List<ProjectPlanDetails> detailsList = projectPlanDetailsDao.getListObject(projectPlanDetails);
        return detailsList;
    }
}
