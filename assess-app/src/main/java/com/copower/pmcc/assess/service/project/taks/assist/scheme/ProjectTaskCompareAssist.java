package com.copower.pmcc.assess.service.project.taks.assist.scheme;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.method.MdMarketCompareService;
import com.copower.pmcc.assess.service.project.TaskCompareService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:15
 */
@Component
@WorkFlowAnnotation(desc = "市场比较法成果")
public class ProjectTaskCompareAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private TaskCompareService taskCompareService;
    @Autowired
    private MdMarketCompareService mdMarketCompareService;
    @Autowired
    private BaseDataDicService baseDataDicService;


    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/scheme/taskMarketCompareIndex", "", 0, "0", "");
        setViewParam(modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/scheme/taskMarketCompareApproval", processInsId, boxId, taskId, agentUserAccount);
        setViewParam(modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/scheme/taskMarketCompareIndex", processInsId, boxId, taskId, agentUserAccount);
        setViewParam(modelAndView);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/scheme/taskMarketCompareApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        setViewParam(modelAndView);
        return modelAndView;
    }

    /**
     * 给modelview设置显示参数
     * @param modelAndView
     */
    private void setViewParam(ModelAndView modelAndView) {
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_MARKET_COMPARE);
        modelAndView.addObject("method", baseDataDic.getId());
        modelAndView.addObject("entrustPurpose","123");
        MdMarketCompare marketCompare = mdMarketCompareService.getMdMarketCompare(201);
        List<MdMarketCompareField> fieldList = mdMarketCompareService.getFieldListByMcId(marketCompare.getId());
        MdMarketCompareItem evaluationObject = mdMarketCompareService.getEvaluationListByMcId(marketCompare.getId());
        List<MdMarketCompareItem> caseList = mdMarketCompareService.getCaseListByMcId(marketCompare.getId());
        modelAndView.addObject("marketCompareJSON", JSON.toJSONString(marketCompare) );
        modelAndView.addObject("fieldsJSON",JSON.toJSONString(fieldList));
        modelAndView.addObject("evaluationJSON",JSON.toJSONString(evaluationObject));
        modelAndView.addObject("casesJSON",JSON.toJSONString(caseList));
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        taskCompareService.saveData(projectPlanDetails,processInsId,formData);

    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        //返回修改提交走这里
        taskCompareService.editData(projectPlanDetails,processInsId,formData);
    }
}
