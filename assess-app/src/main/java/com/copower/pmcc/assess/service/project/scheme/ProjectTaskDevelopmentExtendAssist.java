package com.copower.pmcc.assess.service.project.scheme;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.method.MdDevelopmentService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:15
 */
@Component
@WorkFlowAnnotation(desc = "假设开发法成果扩展")
public class ProjectTaskDevelopmentExtendAssist implements ProjectTaskInterface {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private SchemeInfoService schemeInfoService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private MdDevelopmentService mdDevelopmentService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskDevelopmentExtendIndex", "", 0, "0", "");
        SchemeInfo info = schemeInfoService.getSchemeInfo(projectPlanDetails.getId());
        SchemeJudgeObject judgeObject = schemeJudgeObjectService.getSchemeJudgeObject(projectPlanDetails.getJudgeObjectId());
        if (info == null) {
            MdDevelopment mdDevelopment = mdDevelopmentService.initExplore(judgeObject);
            if (mdDevelopment != null) {
                SchemeInfo schemeInfo = new SchemeInfo();
                schemeInfo.setProjectId(projectPlanDetails.getProjectId());
                schemeInfo.setPlanDetailsId(projectPlanDetails.getId());
                schemeInfo.setJudgeObjectId(projectPlanDetails.getJudgeObjectId());
                schemeInfo.setMethodType(baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.MD_DEVELOPMENT).getId());
                schemeInfo.setMethodDataId(mdDevelopment.getId());
                try {
                    schemeInfoService.saveSchemeInfo(schemeInfo);
                } catch (BusinessException e) {
                    logger.error(e.getMessage(), e);
                }
                modelAndView.addObject("mdDevelopment", mdDevelopment);
            }
        }
        return modelAndView;
    }

    private void setViewParam(ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) {
        SchemeInfo info = schemeInfoService.getSchemeInfo(projectPlanDetails.getId());
        MdDevelopment mdDevelopment = mdDevelopmentService.getMdDevelopmentById(info.getMethodDataId());
        if (mdDevelopment != null)
            modelAndView.addObject("mdDevelopment", mdDevelopment);
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskDevelopmentExtendApproval", processInsId, boxId, taskId, agentUserAccount);
        setViewParam(projectPlanDetails,modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskDevelopmentExtendIndex", processInsId, boxId, taskId, agentUserAccount);
        setViewParam(projectPlanDetails,modelAndView);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskDevelopmentExtendApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        setViewParam(projectPlanDetails,modelAndView);
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        MdDevelopment mdDevelopment = JSON.parseObject(formData, MdDevelopment.class);
        mdDevelopmentService.saveAndUpdateMdDevelopment(mdDevelopment);

        SchemeInfo schemeInfo = schemeInfoService.getSchemeInfo(projectPlanDetails.getId());
        schemeInfo.setProcessInsId(processInsId);
        schemeInfoService.saveSchemeInfo(schemeInfo);
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        MdDevelopment mdDevelopment = JSON.parseObject(formData, MdDevelopment.class);
        mdDevelopmentService.saveAndUpdateMdDevelopment(mdDevelopment);
    }
}
