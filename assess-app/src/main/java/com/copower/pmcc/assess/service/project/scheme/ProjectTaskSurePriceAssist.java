package com.copower.pmcc.assess.service.project.scheme;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePrice;
import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePriceItem;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeSurePriceApplyDto;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.event.project.SchemeSurePriceEvent;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:15
 */
@Component
@WorkFlowAnnotation(desc = "确定单价")
public class ProjectTaskSurePriceAssist implements ProjectTaskInterface {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private SchemeSurePriceService schemeSurePriceService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskSurePriceIndex", "", 0, "0", "");
        SchemeSurePrice schemeSurePrice = schemeSurePriceService.getSurePriceByPlanDetailsId(projectPlanDetails.getId());
        if (schemeSurePrice == null) {
            schemeSurePrice = new SchemeSurePrice();
            schemeSurePrice.setProjectId(projectPlanDetails.getProjectId());
            schemeSurePrice.setJudgeObjectId(projectPlanDetails.getJudgeObjectId());
            schemeSurePrice.setPlanDetailsId(projectPlanDetails.getId());
            schemeSurePriceService.saveSchemeSurePrice(schemeSurePrice);
        }
        modelAndView.addObject("schemeSurePrice", schemeSurePrice);
        modelAndView.addObject("judgeObject", schemeJudgeObjectService.getSchemeJudgeObject(projectPlanDetails.getJudgeObjectId()));
        modelAndView.addObject("subJudgeObjectList", schemeJudgeObjectService.getVoListByPid(projectPlanDetails.getJudgeObjectId()));
        return modelAndView;
    }

    /**
     * 详情处理
     *
     * @param processInsId
     * @param taskId
     * @param boxId
     * @param projectPlanDetails
     * @param agentUserAccount
     * @return
     */
    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskSurePriceApproval", processInsId, boxId, taskId, agentUserAccount);
        modelAndView.addObject("schemeSurePrice", schemeSurePriceService.getSurePriceByPlanDetailsId(projectPlanDetails.getId()));
        modelAndView.addObject("judgeObject", schemeJudgeObjectService.getSchemeJudgeObject(projectPlanDetails.getJudgeObjectId()));
        List<SchemeSurePriceItem> surePriceItemList = null;
        try {
            surePriceItemList = schemeSurePriceService.getSchemeSurePriceItemList(projectPlanDetails.getJudgeObjectId(), false);
            modelAndView.addObject("surePriceItemList", surePriceItemList);
        } catch (BusinessException e) {
            logger.error(e.getMessage(),e);
        }
        modelAndView.addObject("subJudgeObjectList", schemeJudgeObjectService.getVoListByPid(projectPlanDetails.getJudgeObjectId()));
        return modelAndView;
    }

    /**
     * 返回修改处理
     *
     * @param processInsId
     * @param taskId
     * @param boxId
     * @param projectPlanDetails
     * @param agentUserAccount
     * @return
     */
    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskSurePriceIndex", processInsId, boxId, taskId, agentUserAccount);
        SchemeSurePrice schemeSurePrice = schemeSurePriceService.getSurePriceByPlanDetailsId(projectPlanDetails.getId());
        modelAndView.addObject("schemeSurePrice", schemeSurePrice == null ? new SchemeSurePrice() : schemeSurePrice);
        modelAndView.addObject("judgeObject", schemeJudgeObjectService.getSchemeJudgeObject(projectPlanDetails.getJudgeObjectId()));
        modelAndView.addObject("subJudgeObjectList", schemeJudgeObjectService.getVoListByPid(projectPlanDetails.getJudgeObjectId()));
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskSurePriceApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        modelAndView.addObject("schemeSurePrice", schemeSurePriceService.getSurePriceByPlanDetailsId(projectPlanDetails.getId()));
        modelAndView.addObject("judgeObject", schemeJudgeObjectService.getSchemeJudgeObject(projectPlanDetails.getJudgeObjectId()));
        List<SchemeSurePriceItem> surePriceItemList = null;
        try {
            surePriceItemList = schemeSurePriceService.getSchemeSurePriceItemList(projectPlanDetails.getJudgeObjectId(), false);
            modelAndView.addObject("surePriceItemList", surePriceItemList);
        } catch (BusinessException e) {
            logger.error(e.getMessage(),e);
        }
        modelAndView.addObject("subJudgeObjectList", schemeJudgeObjectService.getVoListByPid(projectPlanDetails.getJudgeObjectId()));
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        SchemeSurePrice schemeSurePrice = JSON.parseObject(formData, SchemeSurePrice.class);
        schemeSurePrice.setProcessInsId(processInsId);
        schemeSurePriceService.saveSchemeSurePrice(schemeSurePrice);
        if (!StringUtils.isBlank(processInsId)) {
            try {
                bpmRpcActivitiProcessManageService.setProcessEventExecutor(processInsId, SchemeSurePriceEvent.class.getSimpleName()); //修改监听器
            } catch (BpmException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        SchemeSurePrice schemeSurePrice = JSON.parseObject(formData, SchemeSurePrice.class);
        schemeSurePrice.setProcessInsId(processInsId);
        schemeSurePriceService.saveSchemeSurePrice(schemeSurePrice);
        if (!StringUtils.isBlank(processInsId)) {
            try {
                bpmRpcActivitiProcessManageService.setProcessEventExecutor(processInsId, SchemeSurePriceEvent.class.getSimpleName()); //修改监听器
            } catch (BpmException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
}
