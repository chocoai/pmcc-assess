package com.copower.pmcc.assess.service.project.scheme;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.AssessProjectTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeJudgeObjectDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeProgrammeDto;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeAreaGroupVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.data.DataBestUseDescriptionService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.data.EvaluationMethodService;
import com.copower.pmcc.assess.service.data.EvaluationThinkingService;
import com.copower.pmcc.assess.service.event.project.ProgrammeProcessEvent;
import com.copower.pmcc.assess.service.method.MdCommonService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
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
@WorkFlowAnnotation(desc = "评估方案编制")
public class ProjectTaskSchemeProgrammeAssist implements ProjectTaskInterface {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DataBestUseDescriptionService dataBestUseDescriptionService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private EvaluationMethodService evaluationMethodService;
    @Autowired
    private EvaluationThinkingService evaluationThinkingService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private DataSetUseFieldService dataSetUseFieldService;
    @Autowired
    private MdCommonService mdCommonService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private SchemeJudgeObjectDao schemeJudgeObjectDao;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;

    @Override
    public ModelAndView applyView(ProjectPlanDetails projectPlanDetails) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/schemeProgrammeIndex", "", 0, "0", "");
        setEditParams(modelAndView, projectPlanDetails.getProjectId(), projectPlanDetails.getPlanId(), projectPlanDetails.getProcessInsId());
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
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/schemeProgrammeApproval", processInsId, boxId, taskId, agentUserAccount);
        List<SchemeAreaGroupVo> areaGroups = schemeAreaGroupService.getSchemeAreaGroupVos(projectPlanDetails.getProjectId());//获取分组信息
        ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId()));
        modelAndView.addObject("projectInfo", projectInfoVo);
        modelAndView.addObject("areaGroups", areaGroups);
        //土地
        AssessProjectTypeEnum assessProjectType = projectInfoService.getAssessProjectType(projectInfoVo.getProjectCategoryId());
        if(AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_LAND.getKey().equals(assessProjectType.getKey())){
            modelAndView.addObject("projectCategory", "land");
        }
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
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/schemeProgrammeIndex", processInsId, boxId, taskId, agentUserAccount);
        setEditParams(modelAndView, projectPlanDetails.getProjectId(), projectPlanDetails.getPlanId(), projectPlanDetails.getProcessInsId());
        return modelAndView;
    }

    @Override
    public void saveDraft(ProjectPlanDetails projectPlanDetails, String formData) throws BusinessException {

    }

    @Override
    public ModelAndView detailsView(ProjectPlanDetails projectPlanDetails, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/project/stageScheme/schemeProgrammeApproval", projectPlanDetails.getProcessInsId(), boxId, "-1", "");
        List<SchemeAreaGroupVo> areaGroups = schemeAreaGroupService.getSchemeAreaGroupVos(projectPlanDetails.getProjectId());//获取分组信息
        ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId()));
        modelAndView.addObject("projectInfo", projectInfoVo);
        modelAndView.addObject("areaGroups", areaGroups);
        //土地
        AssessProjectTypeEnum assessProjectType = projectInfoService.getAssessProjectType(projectInfoVo.getProjectCategoryId());
        if(AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_LAND.getKey().equals(assessProjectType.getKey())){
            modelAndView.addObject("projectCategory", "land");
        }
        return modelAndView;
    }

    @Override
    public void applyCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        List<SchemeProgrammeDto> applyDto = JSON.parseArray(formData, SchemeProgrammeDto.class);
        schemeJudgeObjectService.saveProgrammeAll(applyDto);
        if (StringUtils.isBlank(processInsId)) {
            ProjectPlan projectPlan = projectPlanService.getProjectplanById(projectPlanDetails.getPlanId());
            if (projectPlan == null){
                return;
            }
            ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlan.getProjectId());
            ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlan.getWorkStageId());
            long count = schemeJudgeObjectDao.getNotSetFunctionCount(projectInfo.getId());
            if (count > 0) {
                throw new BusinessException("还有委估对象未设置评估方法请检查");
            }
            try {
                schemeJudgeObjectService.submitProgrammeHandle(projectInfo,projectPlan,projectWorkStage) ;
            } catch (BpmException e) {
                baseService.writeExceptionInfo(e,"评估方案编制阶段任务发起失败");
                throw  new BusinessException("评估方案编制阶段任务发起失败") ;
            }
        } else {
            //修改监听器
            try {
                publicService.updateProcessEventExecutor(processInsId, ProgrammeProcessEvent.class.getSimpleName());
            } catch (BpmException e) {
                baseService.writeExceptionInfo(e,"评估方案编制修改监听器失败");
                throw  new BusinessException("评估方案编制修改监听器失败") ;
            }
        }
    }


    @Override
    public void approvalCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
    }

    @Override
    public void returnEditCommit(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws BusinessException {
        List<SchemeProgrammeDto> applyDto = JSON.parseArray(formData, SchemeProgrammeDto.class);
        schemeJudgeObjectService.saveProgrammeAll(applyDto);
    }


    /**
     * 编辑或者申请 参数
     *
     * @param modelAndView
     * @param projectId
     * @param planId
     */
    private void setEditParams(ModelAndView modelAndView, Integer projectId, Integer planId, String processInsId) {
        List<SchemeAreaGroupVo> areaGroups = schemeAreaGroupService.getSchemeAreaGroupVos(projectId);//获取分组信息
        ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(projectId));
        modelAndView.addObject("projectInfo", projectInfoVo);
        modelAndView.addObject("areaGroups", areaGroups);
        modelAndView.addObject("bestUseList", dataBestUseDescriptionService.dataBestUseDescriptionList(projectInfoVo.getProjectTypeId(), projectInfoVo.getProjectCategoryId()));
        AssessProjectTypeEnum assessProjectType = projectInfoService.getAssessProjectType(projectInfoVo.getProjectCategoryId());
        if(AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_HOUSE.getKey().equals(assessProjectType.getKey())){
            modelAndView.addObject("setUseList", dataSetUseFieldService.getCacheSetUseFieldsByType(assessProjectType.getKey()));
        }else  if(AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_LAND.getKey().equals(assessProjectType.getKey())){
            modelAndView.addObject("setUseList", baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_LAND_USE));
            modelAndView.addObject("projectCategory", "land");
        }

        modelAndView.addObject("dataDicMethodList", baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_EVALUATION_METHOD));
        modelAndView.addObject("valueTypes", baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.VALUE_TYPE));//价值类型
        modelAndView.addObject("entrustmentPurposes", baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE));//委托目的
        modelAndView.addObject("valueConnotations", baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROGRAMME_VALUE_CONNOTATION));//价值内涵
        modelAndView.addObject("baseMethodList", mdCommonService.getBaseMethodList(projectInfoVo.getProjectCategoryId()));//基本方法
        modelAndView.addObject("otherMethodList", mdCommonService.getOtherMethodList(projectInfoVo.getProjectCategoryId()));//其它方法
        modelAndView.addObject("evaluationMethodMap", evaluationMethodService.getEvaluationMethodMap());
        modelAndView.addObject("evaluationThinkingMap", evaluationThinkingService.getEvaluationThinkingMap());
        modelAndView.addObject("planId", planId);
        BaseDataDic entrustPurposeData = baseDataDicService.getDataDicById(projectInfoVo.getEntrustPurpose());
        String valueDateExplain = baseDataDicService.getValueByKey("valueDateExplain", entrustPurposeData);
        modelAndView.addObject("valueDateExplain", valueDateExplain);
        if (StringUtils.isNotBlank(processInsId)) {
            modelAndView.addObject("processInsId", processInsId);
        }
    }


}
