package com.copower.pmcc.assess.service.project.taks.assist;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.input.DeclareInfoDto;
import com.copower.pmcc.assess.dto.output.BaseProcessFormVo;
import com.copower.pmcc.assess.dto.output.FormConfigureFieldVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.*;
import com.copower.pmcc.assess.service.event.project.DeclareRecordEvent;
import com.copower.pmcc.assess.service.project.DeclareInfoService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
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
@WorkFlowAnnotation(desc = "资产申报成果")
public class ProjectTaskDeclareAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BaseFormService baseFormService;
    @Autowired
    private FormConfigureService formConfigureService;
    @Autowired
    private BaseProcessService baseProcessService;
    @Autowired
    private DeclareInfoService declareInfoService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/declare/taskIndex", "", 0, "0", "");
        //根据申报类型 确定申报表单
        //先读使用分类表 如果没有数据 则检查计划是否设置了范围 如果设置到了范围 则初始化数据

        List<DeclareUseClassify> declareUseClassifyList = null;
        if(CollectionUtils.isEmpty(declareUseClassifyList)){
            Integer declareFormId = projectPlanDetails.getDeclareFormId();
            if (declareFormId != null && declareFormId > 0) {
                BaseProjectClassify baseProjectClassify = baseProjectClassifyService.getCacheProjectClassifyById(declareFormId);
                if (baseProjectClassify != null && baseProjectClassify.getFormModuleId() != null) {
                    BaseFormModule baseFormModule = baseFormService.getBaseFormModule(baseProjectClassify.getFormModuleId());
                    modelAndView.addObject("baseProjectClassify", baseProjectClassify);
                    modelAndView.addObject("baseFormModule", baseFormModule);
                    List<FormConfigureFieldVo> fieldVos = formConfigureService.getListFieldsShow(baseFormModule.getId());
                    modelAndView.addObject("jsonValue", JSON.toJSONString(fieldVos));
                    modelAndView.addObject("fieldList", JSON.toJSONString(fieldVos));
                }
            }
        }

        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/declare/taskApproval", processInsId, boxId, taskId, agentUserAccount);

        Integer declareFormId = projectPlanDetails.getDeclareFormId();
        BaseDataDic baseDataDic = baseDataDicService.getDataDicById(declareFormId);
        BaseProcess baseProcess = baseProcessService.getProcessByName(baseDataDic.getItemKey());
        List<BaseProcessFormVo> hrProcessForms = baseProcessService.getProcessFormVos(baseDataDic.getItemKey());
        modelAndView.addObject("hrProcessForms", hrProcessForms);
        Map<String, Object> map = formConfigureService.getObjectSingle(baseProcess.getTableName(), processInsId);

        Map<String, Object> primaryData = Maps.newHashMap();
        primaryData.put("primaryId", map.get("id"));
        primaryData.put("primaryTableName", baseProcess.getTableName());
        modelAndView.addObject("primaryData", primaryData);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/declare/taskIndex", processInsId, boxId, taskId, agentUserAccount);
        Integer declareFormId = projectPlanDetails.getDeclareFormId();
        BaseDataDic baseDataDic = baseDataDicService.getDataDicById(declareFormId);
        BaseProcess baseProcess = baseProcessService.getProcessByName(baseDataDic.getItemKey());
        List<BaseProcessFormVo> hrProcessForms = baseProcessService.getProcessFormVos(baseDataDic.getItemKey());
        modelAndView.addObject("hrProcessForms", hrProcessForms);
        Map<String, Object> map = formConfigureService.getObjectSingle(baseProcess.getTableName(), processInsId);

        Map<String, Object> primaryData = Maps.newHashMap();
        primaryData.put("primaryId", map.get("id"));
        primaryData.put("primaryTableName", baseProcess.getTableName());
        modelAndView.addObject("primaryData", primaryData);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/declare/taskApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException, BpmException {
        DeclareInfoDto declareInfoDto = JSON.parseObject(formData, DeclareInfoDto.class);
        declareInfoDto.setPlanDetailId(projectPlanDetails.getId());
        declareInfoDto.setProcessInsId(processInsId);
        declareInfoDto.setProjectId(projectPlanDetails.getProjectId());
        declareInfoService.saveDeclareInfo(declareInfoDto);
        bpmRpcActivitiProcessManageService.setProcessEventExecutor(processInsId, DeclareRecordEvent.class.getSimpleName());//修改监听器
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }
}
