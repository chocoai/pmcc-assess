package com.copower.pmcc.assess.service.project.taks.assist;

import com.copower.pmcc.assess.controller.ControllerComponent;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.output.BaseProcessFormVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseFormService;
import com.copower.pmcc.assess.service.base.BaseProcessService;
import com.copower.pmcc.assess.service.base.FormConfigureService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:15
 */
@Component
@WorkFlowAnnotation(desc = "提交资产申报成果")
public class ProjectDeclareTaskAssist implements ProjectTaskInterface {
    @Autowired
    private ControllerComponent serviceComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BaseFormService baseFormService;
    @Autowired
    private FormConfigureService formConfigureService;
    @Autowired
    private BaseProcessService baseProcessService;


    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/task/declare/taskIndex", "", 0, "0", "");
        //根据申报类型 确定申报表单
        Integer declareFormId = projectPlanDetails.getDeclareFormId();
        BaseDataDic baseDataDic = baseDataDicService.getDataDicById(declareFormId);
        BaseProcess baseProcess = baseProcessService.getProcessByName(baseDataDic.getItemKey());
        List<BaseProcessFormVo> hrProcessForms = baseProcessService.getProcessFormVos(baseDataDic.getItemKey());
        modelAndView.addObject("hrProcessForms", hrProcessForms);
        Integer tableId = 0;

        Map<String, Object> primaryData = Maps.newHashMap();
        primaryData.put("primaryId", tableId);
        primaryData.put("primaryTableName", baseProcess.getTableName());
        modelAndView.addObject("primaryData", primaryData);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/task/declare/taskApproval", processInsId, boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/task/declare/taskIndex", processInsId, boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = serviceComponent.baseFormModelAndView("/task/declare/taskApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }
}
