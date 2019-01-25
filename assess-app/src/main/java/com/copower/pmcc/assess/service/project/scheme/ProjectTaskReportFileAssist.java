package com.copower.pmcc.assess.service.project.scheme;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dal.basis.entity.SchemeReportFile;
import com.copower.pmcc.assess.dal.basis.entity.SchemeReportFileItem;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeReportFileDto;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
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
@WorkFlowAnnotation(desc = "报告附件")
public class ProjectTaskReportFileAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private SchemeReportFileService schemeReportFileService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskReportFileIndex", "", 0, "0", "");
        SchemeReportFile schemeReportFile = schemeReportFileService.getReportFileByPlanDetailsId(projectPlanDetails.getId());
        if (schemeReportFile == null) {
            schemeReportFile = new SchemeReportFile();
            schemeReportFile.setAreaId(projectPlanDetails.getAreaId());
            schemeReportFile.setPlanDetailsId(projectPlanDetails.getId());
            schemeReportFile.setProjectId(projectPlanDetails.getProjectId());
            schemeReportFileService.saveSchemeReportFile(schemeReportFile);
        }
        setParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    private void setParam(ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectListByAreaGroupId(projectPlanDetails.getAreaId());//该区域下的所有委估对象
        modelAndView.addObject("judgeObjectList", judgeObjectList);
        modelAndView.addObject("ownershipCertFileList", schemeReportFileService.getOwnershipCertFileList(projectPlanDetails.getAreaId()));
        modelAndView.addObject("inventoryAddressFileList", schemeReportFileService.getInventoryAddressFileList(projectPlanDetails.getAreaId()));
        modelAndView.addObject("reimbursementFileList", schemeReportFileService.getReimbursementFileList(projectPlanDetails.getAreaId()));
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
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskReportFileApproval", processInsId, boxId, taskId, agentUserAccount);
        setParam(projectPlanDetails, modelAndView);
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
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskReportFileIndex", processInsId, boxId, taskId, agentUserAccount);
        setParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskReportFileApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        setParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        SchemeReportFileDto customDto = JSON.parseObject(formData, SchemeReportFileDto.class);
        List<SchemeReportFileItem> reportFileItemList = customDto.getReportFileItemList();
        if (CollectionUtils.isNotEmpty(reportFileItemList)) {
            for (SchemeReportFileItem custom : reportFileItemList) {
                schemeReportFileService.updateReportFileItem(custom);
            }
        }
        SchemeReportFile schemeReportFile = schemeReportFileService.getReportFileByPlanDetailsId(projectPlanDetails.getId());
        schemeReportFile.setProcessInsId(processInsId);
        schemeReportFileService.saveSchemeReportFile(schemeReportFile);
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        SchemeReportFileDto customDto = JSON.parseObject(formData, SchemeReportFileDto.class);
        List<SchemeReportFileItem> reportFileItemList = customDto.getReportFileItemList();
        if (CollectionUtils.isNotEmpty(reportFileItemList)) {
            for (SchemeReportFileItem custom : reportFileItemList) {
                schemeReportFileService.updateReportFileItem(custom);
            }
        }
    }
}
