package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryRightRecordCenterService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:15
 */
@Component
@WorkFlowAnnotation(desc = "法定优先受偿款")
public class ProjectTaskReimbursementAssist implements ProjectTaskInterface {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SurveyAssetInventoryRightRecordCenterService surveyAssetInventoryRightRecordCenterService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private SchemeReimbursementService schemeReimbursementService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskReimbursementIndex", "", 0, "0", "");
        SchemeReimbursement master = schemeReimbursementService.applyInit(projectPlanDetails);
        modelAndView.addObject("master", master);
        modelAndView.addObject("areaGroup", schemeAreaGroupService.get(projectPlanDetails.getAreaId()));
        try {
            writeSchemeReimbursement(projectPlanDetails, schemeAreaGroupService.get(projectPlanDetails.getAreaId()));
        } catch (Exception e) {
            logger.error("生成法定优先受偿款初始数据失败!", e);
        }
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
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskReimbursementApproval", processInsId, boxId, taskId, agentUserAccount);
        SchemeReimbursement schemeReimbursement = schemeReimbursementService.getDataByProcessInsId(projectPlanDetails.getProcessInsId());
        modelAndView.addObject("master", schemeReimbursement);
        modelAndView.addObject("areaGroup", schemeAreaGroupService.get(projectPlanDetails.getAreaId()));
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
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskReimbursementIndex", processInsId, boxId, taskId, agentUserAccount);
        SchemeReimbursement schemeReimbursement = schemeReimbursementService.getDataByProcessInsId(projectPlanDetails.getProcessInsId());
        modelAndView.addObject("master", schemeReimbursement == null ? new SchemeReimbursement() : schemeReimbursement);
        modelAndView.addObject("areaGroup", schemeAreaGroupService.get(projectPlanDetails.getAreaId()));
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/taskReimbursementApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        SchemeReimbursement schemeReimbursement = schemeReimbursementService.getDataByProcessInsId(projectPlanDetails.getProcessInsId());
        modelAndView.addObject("master", schemeReimbursement);
        modelAndView.addObject("areaGroup", schemeAreaGroupService.get(projectPlanDetails.getAreaId()));
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        schemeReimbursementService.applyCommit(formData, processInsId);
    }

    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {

    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        schemeReimbursementService.applyCommit(formData, processInsId);
    }

    /**
     * 生成法定优先受偿款初始数据
     *
     * @param projectPlanDetails
     * @param schemeAreaGroup
     * @throws Exception
     */
    private void writeSchemeReimbursement(ProjectPlanDetails projectPlanDetails, SchemeAreaGroup schemeAreaGroup) throws Exception {
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.OTHER_RIGHT, projectInfo.getProjectCategoryId());
        if (projectPlanDetails != null && schemeAreaGroup != null && projectPhase != null) {
            ProjectPlanDetails query = new ProjectPlanDetails();
            query.setProjectId(projectPlanDetails.getProjectId());
            query.setProjectPhaseId(projectPhase.getId());
            List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsService.getProjectDetails(query);
            if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                //SurveyAssetInventoryRightRecordCenter只会有一个
                SurveyAssetInventoryRightRecordCenter selectRightRecordCenter = new SurveyAssetInventoryRightRecordCenter();
                selectRightRecordCenter.setProjectId(projectPlanDetailsList.get(0).getProjectId());
                selectRightRecordCenter.setPlanDetailsId(projectPlanDetailsList.get(0).getId());
                selectRightRecordCenter.setProcessInsId(projectPlanDetailsList.get(0).getProcessInsId());
                List<SurveyAssetInventoryRightRecordCenter> centerList = surveyAssetInventoryRightRecordCenterService.getSurveyAssetInventoryRightRecordCenterList(selectRightRecordCenter);
                if (CollectionUtils.isNotEmpty(centerList)) {
                    //他项权利组(多对) 每组有一个或者多个他项权力
                    List<SurveyAssetInventoryRightRecord> rightRecordList = surveyAssetInventoryRightRecordCenterService.getSurveyAssetInventoryRightRecordList(centerList.get(0).getId(), centerList.get(0).getProjectId(), centerList.get(0).getPlanDetailsId());
                    if (CollectionUtils.isNotEmpty(rightRecordList)) {
                        for (SurveyAssetInventoryRightRecord surveyAssetInventoryRightRecord : rightRecordList) {
                            //取 生成多少条数据,1,2号
                            if (StringUtils.isNotBlank(surveyAssetInventoryRightRecord.getRecordIds())) {
                                List<DeclareRecord> declareRecordList = surveyAssetInventoryRightRecordCenterService.getDeclareRecordList(surveyAssetInventoryRightRecord.getRecordIds().split(","));
                                if (CollectionUtils.isNotEmpty(declareRecordList)){
                                    List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(schemeAreaGroup.getId());
                                    if (CollectionUtils.isEmpty(schemeJudgeObjectList)){
                                        continue;
                                    }
                                    Map<SchemeJudgeObject,DeclareRecord> schemeJudgeObjectDeclareRecordMap = surveyAssetInventoryRightRecordCenterService.getDeclareRecordJudgeObjectMap(declareRecordList,schemeJudgeObjectList);
                                    if (schemeJudgeObjectDeclareRecordMap.isEmpty()){

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
