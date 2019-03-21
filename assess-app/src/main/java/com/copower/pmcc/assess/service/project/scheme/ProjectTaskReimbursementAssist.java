package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeReimbursementItemVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryRightRecordCenterService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
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
        try {
            writeSchemeReimbursement(projectPlanDetails, schemeAreaGroupService.get(projectPlanDetails.getAreaId()),modelAndView);
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
        try {
            writeSchemeReimbursement(projectPlanDetails, schemeAreaGroupService.get(projectPlanDetails.getAreaId()),modelAndView);
        } catch (Exception e) {
            logger.error("生成法定优先受偿款初始数据失败!", e);
        }
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
    private void writeSchemeReimbursement(ProjectPlanDetails projectPlanDetails, SchemeAreaGroup schemeAreaGroup,ModelAndView modelAndView) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(8);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.OTHER_RIGHT, projectInfo.getProjectCategoryId());
        SchemeReimbursement select = new SchemeReimbursement();
        select.setPlanDetailsId(projectPlanDetails.getId());
        select.setProjectId(projectPlanDetails.getProjectId());
        select.setAreaId(schemeAreaGroup.getId());
        select.setProcessInsId(StringUtils.isNotBlank(projectPlanDetails.getProcessInsId())?projectPlanDetails.getProcessInsId():"0");
        List<SchemeReimbursement> schemeReimbursementList = schemeReimbursementService.getObjectList(select);
        //当查询不到主法定优先受偿款时则生成一个主数据
        if (CollectionUtils.isEmpty(schemeReimbursementList)){
            select.setStatus(ProcessStatusEnum.RUN.getValue());
            schemeReimbursementService.saveSchemeReimbursement(select);
            schemeReimbursementList = Lists.newArrayList(select);
        }
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(schemeAreaGroup.getId());
        if (projectPlanDetails != null && CollectionUtils.isNotEmpty(schemeJudgeObjectList) && projectPhase != null && CollectionUtils.isNotEmpty(schemeReimbursementList)) {
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
                    //他项权利组(多对) 每组有一个或者多个他项权力   他项权利组对应一个SchemeReimbursementItem
                    List<SurveyAssetInventoryRightRecord> rightRecordList = surveyAssetInventoryRightRecordCenterService.getSurveyAssetInventoryRightRecordList(centerList.get(0).getId(), centerList.get(0).getProjectId(), centerList.get(0).getPlanDetailsId());
                    if (CollectionUtils.isNotEmpty(rightRecordList)) {
                        for (SurveyAssetInventoryRightRecord surveyAssetInventoryRightRecord : rightRecordList) {
                            //取 生成多少条数据,1,2号
                            if (StringUtils.isNotBlank(surveyAssetInventoryRightRecord.getRecordIds())) {
                                List<DeclareRecord> declareRecordList = surveyAssetInventoryRightRecordCenterService.getDeclareRecordList(surveyAssetInventoryRightRecord.getRecordIds().split(","));
                                if (CollectionUtils.isNotEmpty(declareRecordList)){
                                    Map<SchemeJudgeObject,DeclareRecord> schemeJudgeObjectDeclareRecordMap = surveyAssetInventoryRightRecordCenterService.getDeclareRecordJudgeObjectMap(declareRecordList,schemeJudgeObjectList);
                                    if (!schemeJudgeObjectDeclareRecordMap.isEmpty()){
                                        List<SchemeJudgeObject> judgeObjectList = Lists.newArrayList();
                                        for (Map.Entry<SchemeJudgeObject,DeclareRecord> recordEntry:schemeJudgeObjectDeclareRecordMap.entrySet()){
                                            judgeObjectList.add(recordEntry.getKey());
                                        }
                                        //生成name
                                        List<String> stringList = Lists.newArrayList();
                                        judgeObjectList.stream().forEach(schemeJudgeObject -> stringList.add(schemeJudgeObject.getNumber()));
                                        //排一次序
                                        Ordering<String> ordering = Ordering.from((o1, o2) -> {
                                            int x = Integer.parseInt(o1.substring(0, 1));
                                            int y = Integer.parseInt(o2.substring(0, 1));
                                            return (x > y) ? -1 : ((x == y) ? 0 : 1);
                                        });
                                        stringList.sort(ordering.reverse());
                                        for (int i = 0; i < stringList.size(); i++) {
                                            if ( i == stringList.size()-1){
                                                stringBuilder.append(stringList.get(i));
                                            }else {
                                                stringBuilder.append(stringList.get(i));
                                                if (stringList.size() != 1){
                                                    stringBuilder.append(",");
                                                }
                                            }
                                        }
                                        SchemeReimbursementItem queryItem = new SchemeReimbursementItem();
                                        queryItem.setMasterId(schemeReimbursementList.get(0).getId());
                                        queryItem.setName(String.format("%s%s",stringBuilder.toString(),"号"));
                                        queryItem.setProjectId(schemeReimbursementList.get(0).getProjectId());
                                        queryItem.setPlanDetailsId(schemeReimbursementList.get(0).getPlanDetailsId());
                                        stringBuilder.delete(0,stringBuilder.toString().length());
                                        List<SchemeReimbursementItemVo> schemeReimbursementItemVoList = schemeReimbursementService.findQueryBySchemeReimbursementItem(queryItem);
                                        //当查询不到则生成
                                        if (CollectionUtils.isEmpty(schemeReimbursementItemVoList)){
                                            queryItem.setJudgeObjectId(judgeObjectList.get(0).getId());
                                            schemeReimbursementService.saveAndUpdateSchemeReimbursementItem(queryItem);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            modelAndView.addObject("master", schemeReimbursementList.get(0));
            modelAndView.addObject("areaGroup", schemeAreaGroup);
        }
    }
}
