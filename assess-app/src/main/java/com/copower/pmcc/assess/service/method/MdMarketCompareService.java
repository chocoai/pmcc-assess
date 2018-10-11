package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.common.enums.ExamineTypeEnum;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.method.MdMarketCompareDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdMarketCompareFieldDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdMarketCompareItemDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.method.MarketCompareResultDto;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.common.CommonService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kings on 2018-7-23.
 */
@Service
public class MdMarketCompareService {
    @Autowired
    private MdMarketCompareDao mdMarketCompareDao;
    @Autowired
    private MdMarketCompareFieldDao mdMarketCompareFieldDao;
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

    public MdMarketCompare getMdMarketCompare(Integer id) {
        return mdMarketCompareDao.getMarketCompareById(id);
    }

    /**
     * 获取字段信息by mcid
     *
     * @param mcId
     * @return
     */
    public List<MdMarketCompareField> getFieldListByMcId(Integer mcId) {
        MdMarketCompareField mdMarketCompareField = new MdMarketCompareField();
        mdMarketCompareField.setMcId(mcId);
        return mdMarketCompareFieldDao.getMarketCompareFieldList(mdMarketCompareField);
    }

    /**
     * 根据设定用途类型获取配置的字段
     *
     * @param setUse
     * @return
     */
    public List<DataSetUseField> getFieldList(Integer setUse) {
        DataSetUseField dataSetUseField = dataSetUseFieldService.getSetUseFieldByType(setUse);
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
    public void initExplore(SchemeJudgeObject judgeObject) {
        if(judgeObject==null) return;
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObject.getId());
        if (schemeJudgeObject == null) return;
        List<DataSetUseField> setUseFieldList = getFieldList(judgeObject.getSetUse());
        if(CollectionUtils.isEmpty(setUseFieldList)) return;
        MdMarketCompare mdMarketCompare = new MdMarketCompare();
        mdMarketCompare.setName(String.format("%s号委估对象", schemeJudgeObject.getNumber()));
        mdMarketCompare.setCreator(commonService.thisUserAccount());
        mdMarketCompareDao.addMarketCompare(mdMarketCompare);

        MdMarketCompareItem mdMarketCompareItem = new MdMarketCompareItem();
        mdMarketCompareItem.setMcId(mdMarketCompare.getId());
        mdMarketCompareItem.setName("");
        mdMarketCompareItem.setType(ExamineTypeEnum.EXPLORE.getId());
        mdMarketCompareItem.setCreator(commonService.thisUserAccount());

        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.SCENE_EXPLORE_EXAMINE);
        ProjectPlanDetails planDetails = projectPlanDetailsService.getProjectPlanDetails(schemeJudgeObject.getDeclareRecordId(), projectPhase.getId());
        mdMarketCompareItem.setJsonContent(mdMarketCompareFieldService.getJsonContent(schemeJudgeObject.getDeclareRecordId(), planDetails.getId(), setUseFieldList));
        mdMarketCompareItemDao.addMarketCompareItem(mdMarketCompareItem);
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
}
