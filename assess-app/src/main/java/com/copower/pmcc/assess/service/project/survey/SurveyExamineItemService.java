package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyExamineItemDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.event.project.SurveyExamineTaskEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectTaskAllService;
import com.copower.pmcc.assess.service.project.manage.ProjectWorkStageService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by zly on 2018/5/15.
 */
@Service
public class SurveyExamineItemService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private SurveyExamineItemDao surveyExamineItemDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectTaskAllService projectTaskAllService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private SurveyExamineTaskService surveyExamineTaskService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private SurveyExamineItemService surveyExamineItemService;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private ProjectInfoService projectInfoService;

    public boolean save(SurveyExamineItem surveyExamineItem) throws BusinessException {
        if(surveyExamineItem == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if(surveyExamineItem.getId() != null && surveyExamineItem.getId() > 0){
            return surveyExamineItemDao.update(surveyExamineItem);
        }else{
            surveyExamineItem.setCreator(processControllerComponent.getThisUser());
            return surveyExamineItemDao.save(surveyExamineItem);
        }
    }

    public boolean delete(Integer id) throws BusinessException {
        if(id ==null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());;
        return surveyExamineItemDao.delete(id);

    }


    public SurveyExamineItem getExamineItemByProcessInsId(String processInsId) {
        SurveyExamineItem surveyExamineItem= surveyExamineItemDao.getSurveyExamineItem(processInsId);
        return surveyExamineItem;
    }

    public SurveyExamineItem getExamineItemByPlanDetailsId(Integer planDetailsId) {
        SurveyExamineItem surveyExamineItem= surveyExamineItemDao.getExamineItemByPlanDetailsId(planDetailsId);
        return surveyExamineItem;
    }



    /**
     * 提交调查信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void submitExamineDataInfo(String formData, Integer planDetailsId,Integer responsibilityId) throws BusinessException {
        //1.保存信息
        //2.先检查是否需要发流程 如果不需要则更新任务状态 如果需要发流程则发起流程，写写入主表，更新任务状态为运行中，
        //3.检查是否所有任务都已提交完成 如果都已完成则更新planDetails的状态 并确认是否走下一个阶段任务
        surveyExamineTaskService.saveExamineDataInfo(formData);
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
        //取到该事项所配置的流程
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseById(projectPlanDetails.getProjectPhaseId());
        if (StringUtils.isBlank(projectPhase.getBoxName())) {//不走流程
            surveyCommonService.updateExamineTaskStatus(planDetailsId, commonService.thisUserAccount(), ProjectStatusEnum.FINISH);
            if (surveyCommonService.isAllTaskFinish(planDetailsId)) {
                projectPlanDetails.setStatus(ProjectStatusEnum.FINISH.getKey());
                projectPlanDetailsService.updateProjectPlanDetails(projectPlanDetails);
                if (projectPlanDetailsService.isAllPlanDetailsFinish(projectPlanDetails.getPlanId())) {
                    projectTaskAllService.startTaskAllApproval(projectPlanDetails.getPlanId());
                }
            }
        } else {//提交流程
            //先保存流程主表
            SurveyExamineItem surveyExamineItem=new SurveyExamineItem();
            surveyExamineItem.setProjectId(projectPlanDetails.getProjectId());
            surveyExamineItem.setPlanDetailsId(projectPlanDetails.getId());
            surveyExamineItem.setDeclareRecordId(projectPlanDetails.getDeclareRecordId());
            surveyExamineItem.setCreator(commonService.thisUserAccount());
            surveyExamineItemService.save(surveyExamineItem);

            ProcessUserDto processUserDto = null;
            //发起相应的流程
            ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
            String folio = String.format("%s【成果提交】%s", projectPhase.getProjectPhaseName(), projectInfo.getProjectName());
            Integer boxIdByBoxName = bpmRpcBoxService.getBoxIdByBoxName(projectPhase.getBoxName());
            BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxIdByBoxName);
            ProcessInfo processInfo = new ProcessInfo();
            processInfo.setStartUser(commonService.thisUserAccount());
            processInfo.setProjectId(projectPlanDetails.getProjectId());
            processInfo.setProcessName(boxReDto.getProcessName());
            processInfo.setGroupName(boxReDto.getGroupName());
            processInfo.setFolio(folio);//流程描述
            processInfo.setTableName(FormatUtils.entityNameConvertToTableName(SurveyExamineItem.class));
            processInfo.setTableId(surveyExamineItem.getId());
            processInfo.setBoxId(boxReDto.getId());
            processInfo.setWorkStage(projectWorkStageService.cacheProjectWorkStage(projectPlanDetails.getProjectWorkStageId()).getWorkStageName());
            processInfo.setProcessEventExecutorName(SurveyExamineTaskEvent.class.getSimpleName());
            processInfo.setWorkStageId(projectPlanDetails.getProjectWorkStageId());
            processInfo.setAppKey(applicationConstant.getAppKey());

            try {
                processUserDto = processControllerComponent.processStart(processInfo, commonService.thisUserAccount(), false);
            } catch (BpmException e) {
                logger.info("提交调查信息,发起流程异常",e);
                throw new BusinessException(e.getMessage());
            }
            surveyCommonService.updateExamineTaskStatus(planDetailsId, commonService.thisUserAccount(), ProjectStatusEnum.RUNING);
            surveyExamineItem.setStatus(ProcessStatusEnum.RUN.getValue());
            surveyExamineItem.setProcessInsId(processUserDto.getProcessInsId());
            surveyExamineItemService.save(surveyExamineItem);
        }
        //关掉bpm的任务
        bpmRpcProjectTaskService.deleteProjectTask(responsibilityId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void submitEditExamineDataInfo(String formData, ApprovalModelDto approvalModelDto) throws BusinessException {
        surveyExamineTaskService.saveExamineDataInfo(formData);
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(publicService.getEditApprovalModel(approvalModelDto), false);
        } catch (BpmException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * 任务审批
     * @param approvalModelDto
     * @throws BusinessException
     */
    public void taskApproval(ApprovalModelDto approvalModelDto) throws BusinessException {
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (BpmException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
