package com.copower.pmcc.assess.service.project.taks.assist.scheme;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.method.MdMarketCostDto;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.method.MdMarketCostService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.scheme.SchemeInfoService;
import com.copower.pmcc.assess.service.project.scheme.SchemeSupportInfoService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
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
@WorkFlowAnnotation(desc = "成本法成果")
public class ProjectTaskCostAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private SchemeInfoService schemeInfoService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private MdMarketCostService mdMarketCostService;
    @Autowired
    private SchemeSupportInfoService schemeSupportInfoService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/scheme/taskCostIndex", "", 0, "0", "");
        //初始化支撑数据
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        schemeSupportInfoService.initSupportInfo(projectPlanDetails.getId(), projectInfo.getEntrustPurpose(), AssessDataDicKeyConstant.MD_MARKET_COMPARE);
        setViewParam(projectPlanDetails, modelAndView);
        return modelAndView;
    }

    @Override
    public ModelAndView approvalView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/scheme/taskCostApproval", processInsId, boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @Override
    public ModelAndView returnEditView(String processInsId, String taskId, Integer boxId, ProjectPlanDetails projectPlanDetails, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/scheme/taskCostIndex", processInsId, boxId, taskId, agentUserAccount);
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/task/scheme/taskCostApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        MdMarketCostDto mdMarketCostDto = JSON.parseObject(formData, MdMarketCostDto.class);
        if (CollectionUtils.isNotEmpty(mdMarketCostDto.getSupportInfoList())) {
            for (SchemeSupportInfo schemeSupportInfo : mdMarketCostDto.getSupportInfoList()) {
                schemeSupportInfoService.saveSupportInfo(schemeSupportInfo);
            }
        }
        if (!ObjectUtils.isEmpty(mdMarketCostDto.getMdCostBuilding())) {//评估单价 (建筑物)
            MdCostBuilding mdCostBuilding = mdMarketCostDto.getMdCostBuilding();
            mdMarketCostService.addEstateNetwork(mdCostBuilding);
            mdMarketCostDto.setId(mdCostBuilding.getId());
        }
        if (!ObjectUtils.isEmpty(mdMarketCostDto.getMdCostConstruction())){//在建工程
            MdCostConstruction mdCostConstruction = mdMarketCostDto.getMdCostConstruction();
            mdMarketCostService.addEstateNetwork(mdCostConstruction);
            mdMarketCostDto.setId(mdMarketCostDto.getMdCostConstruction().getId());
        }
        SchemeInfo schemeInfo = new SchemeInfo();
        schemeInfo.setProjectId(projectPlanDetails.getProjectId());
        schemeInfo.setPlanDetailsId(projectPlanDetails.getId());
        schemeInfo.setProcessInsId(processInsId);
        schemeInfo.setMethodType(AssessDataDicKeyConstant.MD_COST);
        schemeInfo.setMethodDataId(mdMarketCostDto.getId());
        schemeInfoService.saveSchemeInfo(schemeInfo);
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    /**
     * 给modelview设置显示参数
     *
     * @param modelAndView
     */
    private void setViewParam(ProjectPlanDetails projectPlanDetails, ModelAndView modelAndView) {
        //评估支持数据
        List<SchemeSupportInfo> supportInfoList = schemeSupportInfoService.getSupportInfoList(projectPlanDetails.getId());
        modelAndView.addObject("supportInfosJSON", JSON.toJSONString(supportInfoList));
    }
}
