package com.copower.pmcc.assess.controller.project.scheme;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeJudgeFunctionApplyDto;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeProgrammeDto;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeAreaGroupVo;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeJudgeObjectVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataBestUseDescriptionService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.data.EvaluationMethodService;
import com.copower.pmcc.assess.service.data.EvaluationThinkingService;
import com.copower.pmcc.assess.service.method.MdCommonService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeFunctionService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by kings on 2018-9-12.
 * 方案
 */
@RestController
@RequestMapping("/schemeProgramme")
public class SchemeProgrammeController {
    private Logger logger = LoggerFactory.getLogger(getClass());
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
    private SchemeJudgeFunctionService schemeJudgeFunctionService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private DataSetUseFieldService dataSetUseFieldService;
    @Autowired
    private MdCommonService mdCommonService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private SurveyCommonService surveyCommonService;

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
        modelAndView.addObject("setUseList", dataSetUseFieldService.getCacheSetUseFieldsByType(projectInfoService.getAssessProjectType(projectInfoVo.getProjectCategoryId()).getKey()));
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
        String valueDateExplain = baseDataDicService.getValueByKey(AssessDataDicKeyConstant.EXTEND_PROP_VALUEDATEEXPLAIN, entrustPurposeData);
        modelAndView.addObject("valueDateExplain", valueDateExplain);
        if (StringUtils.isNotBlank(processInsId)) {
            modelAndView.addObject("processInsId", processInsId);
        }
    }

    /**
     * 详情 参数
     *
     * @param modelAndView
     * @param projectId
     * @param planId
     */
    private void setDetailParams(ModelAndView modelAndView, Integer projectId, Integer planId, String processInsId) {
        List<SchemeAreaGroupVo> areaGroups = schemeAreaGroupService.getSchemeAreaGroupVos(projectId);//获取分组信息
        ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(projectId));
        modelAndView.addObject("projectInfo", projectInfoVo);
        modelAndView.addObject("areaGroups", areaGroups);
        modelAndView.addObject("bestUseList", dataBestUseDescriptionService.dataBestUseDescriptionList(projectInfoVo.getProjectTypeId(), projectInfoVo.getProjectCategoryId()));
        modelAndView.addObject("setUseList", baseDataDicService.getCacheDataDicList(projectInfoService.getAssessProjectType(projectInfoVo.getProjectCategoryId()).getKey()));
        modelAndView.addObject("dataDicMethodList", baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_EVALUATION_METHOD));
        modelAndView.addObject("planId", planId);
        if (StringUtils.isNotBlank(processInsId)) {
            modelAndView.addObject("processInsId", processInsId);
        }
    }

    @RequestMapping(value = "/index", name = "方案设置视图", method = {RequestMethod.GET})
    public ModelAndView index(Integer projectId, Integer planId, String processInsId) {
        String view = "/project/stageScheme/schemeProgrammeIndex";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        setEditParams(modelAndView, projectId, planId, processInsId);
        return modelAndView;
    }

    @RequestMapping(value = "/view", name = "方案设置视图 (实际相当于详情)", method = {RequestMethod.GET})
    public ModelAndView view(Integer projectId, Integer planId, String processInsId) {
        String view = "/project/stageScheme/schemeProgrammeApproval";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        setDetailParams(modelAndView, projectId, planId, processInsId);
        return modelAndView;
    }

    @RequestMapping(value = "/schemeProgrammeApprovalDetails", name = "方案流程详情", method = {RequestMethod.GET})
    public ModelAndView schemeProgrammeApprovalDetails(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsByProcessInsId(processInsId);
        ProjectPlan projectPlan = projectPlanService.getProjectplanById(projectPlanDetails.getPlanId());
        return view(projectPlan.getProjectId(), projectPlan.getId(), null);
    }

    @RequestMapping(value = "/schemeProgrammeEdit", name = "方案流程编辑", method = {RequestMethod.GET})
    public ModelAndView schemeProgrammeEdit(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        String view = "/project/stageScheme/schemeProgrammeIndex";
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView(view, processInsId, boxId, taskId, agentUserAccount);
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsByProcessInsId(processInsId);
        ProjectPlan projectPlan = projectPlanService.getProjectplanById(projectPlanDetails.getPlanId());
        setEditParams(modelAndView, projectPlan.getProjectId(), projectPlan.getId(), processInsId);
        return modelAndView;
    }

    @RequestMapping(value = "/schemeProgrammeApproval", name = "方案流程 审批", method = {RequestMethod.GET})
    public ModelAndView schemeProgrammeApproval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsByProcessInsId(processInsId);
        ProjectPlan projectPlan = projectPlanService.getProjectplanById(projectPlanDetails.getPlanId());
        String view = "/project/stageScheme/schemeProgrammeApproval";
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView(view, processInsId, boxId, taskId, agentUserAccount);
        setDetailParams(modelAndView, projectPlan.getProjectId(), projectPlan.getId(), processInsId);
        return modelAndView;
    }


    @RequestMapping(value = "/submitProgrammeApproval", name = "审批任务提交 ", method = RequestMethod.POST)
    public HttpResult submitProgrammeApproval(ApprovalModelDto approvalModelDto, String blockName, Boolean writeBackBlockFlag) {
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
            return HttpResult.newCorrectResult();
        } catch (BpmException e) {
            baseService.writeExceptionInfo(e, "方案 审批任务提交");
            return HttpResult.newErrorResult("异常");
        }
    }

    @RequestMapping(value = "/submitProgrammeEdit", name = "编辑任务提交 ", method = RequestMethod.POST)
    public HttpResult submitProgrammeEdit(ApprovalModelDto approvalModelDto, String blockName, Boolean writeBackBlockFlag) {
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(publicService.getEditApprovalModel(approvalModelDto), false);
            return HttpResult.newCorrectResult();
        } catch (BpmException e) {
            baseService.writeExceptionInfo(e, "方案 编辑任务提交");
            return HttpResult.newErrorResult("异常");
        }
    }

    @RequestMapping(value = "/getSceneExploreBasicApplyList", name = "权证查勘数据列表 ", method = RequestMethod.POST)
    public HttpResult getSceneExploreBasicApplyList(Integer declareId) {
        try {
            List<BasicApply> basicApplyList = surveyCommonService.getSceneExploreBasicApplyList(declareId);
            return HttpResult.newCorrectResult(basicApplyList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = "/getSchemeJudgeObjectList", name = "区域下的委估对象数据列表 ", method = RequestMethod.POST)
    public HttpResult getSchemeJudgeObjectList(Integer areaGroupId, String number, String ownership, String seat) {
        try {
            List<SchemeJudgeObjectVo> schemeJudgeObjectList = schemeJudgeObjectService.getSchemeJudgeObjectList(areaGroupId, number, ownership, seat);
            return HttpResult.newCorrectResult(schemeJudgeObjectList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @PostMapping(name = "生成方案数据", value = "/generatorAreaGroup")
    public HttpResult generatorAreaGroup(Integer projectId) {
        try {
            schemeAreaGroupService.generatorAreaGroup(projectId);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("生成方案数据", e);
            return HttpResult.newErrorResult("生成方案数据异常");
        }
    }

    @PostMapping(name = "区域合并", value = "/areaGroupMerge")
    public HttpResult areaGroupMerge(Integer projectId, String areaGroupIds) {
        try {
            schemeAreaGroupService.areaGroupMerge(projectId, areaGroupIds);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e) {
            logger.error("区域合并", e);
            return HttpResult.newErrorResult("区域合并异常");
        }
    }

    @PostMapping(name = "取消合并的区域", value = "/areaGroupMergeCancel")
    public HttpResult areaGroupMergeCancel(Integer id) {
        try {
            schemeAreaGroupService.areaGroupMergeCancel(id);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e) {
            logger.error("取消合并的区域", e);
            return HttpResult.newErrorResult("取消合并的区域异常");
        }
    }

    @PostMapping(name = "区域拆分", value = "/areaGroupSplit")
    public HttpResult areaGroupSplit(Integer planId, Integer areaGroupId, String judgeObjectIds) {
        try {
            List<Integer> list = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(judgeObjectIds));
            schemeAreaGroupService.areaGroupSplit(planId, areaGroupId, list);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e) {
            logger.error("区域拆分", e);
            return HttpResult.newErrorResult("区域拆分异常");
        }
    }

    @PostMapping(name = "移除拆分区域", value = "/areaGroupSplitRemove")
    public HttpResult areaGroupSplitRemove(Integer areaGroupId) {
        try {
            schemeAreaGroupService.areaGroupSplitRemove(areaGroupId);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("移除拆分区域", e);
            return HttpResult.newErrorResult("移除拆分区域异常");
        }
    }

    @PostMapping(name = "委估对象拆分", value = "/splitJudge")
    public HttpResult splitJudge(Integer projectId, Integer areaGroupId, Integer id, Integer splitCount) {
        try {
            schemeJudgeObjectService.splitJudge(projectId, areaGroupId, id, splitCount);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("委估对象拆分", e);
            return HttpResult.newErrorResult("委估对象拆分异常");
        }
    }

    @PostMapping(name = "删除拆分出来的委估对象", value = "/delSplitJudge")
    public HttpResult delSplitJudge(Integer projectId, Integer areaGroupId, Integer id) {
        try {
            schemeJudgeObjectService.delSplitJudge(projectId, areaGroupId, id);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("删除拆分出来的委估对象", e);
            return HttpResult.newErrorResult("删除拆分出来的委估对象异常");
        }
    }

    @PostMapping(name = "委估对象合并", value = "/mergeJudge")
    public HttpResult mergeJudge(String ids, Integer standardJudgeId) {
        try {
            schemeJudgeObjectService.mergeJudge(ids, standardJudgeId);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e) {
            logger.error("委估对象合并", e);
            return HttpResult.newErrorResult("委估对象合并异常");
        }
    }

    @PostMapping(name = "取消合并的委估对象", value = "/mergeJudgeCancel")
    public HttpResult mergeJudgeCancel(Integer id) {
        try {
            schemeJudgeObjectService.mergeJudgeCancel(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("取消合并的委估对象", e);
            return HttpResult.newErrorResult("取消合并的委估对象异常");
        }
    }

    @PostMapping(name = "调整合并的委估对象", value = "/mergeJudgeAdjust")
    public HttpResult mergeJudgeAdjust(Integer id, String removeIds, String addIds) {
        try {
            schemeJudgeObjectService.mergeJudgeAdjust(id, removeIds, addIds);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("调整合并的委估对象", e);
            return HttpResult.newErrorResult("调整合并的委估对象异常");
        }
    }

    @RequestMapping(value = "/saveProgrammeArea", name = "保存区域下方案 ", method = RequestMethod.POST)
    public HttpResult saveProgrammeArea(String formData) {
        try {
            if (StringUtils.isNotBlank(formData)) {
                SchemeProgrammeDto applyDto = JSON.parseObject(formData, SchemeProgrammeDto.class);
                schemeJudgeObjectService.saveProgrammeArea(applyDto);
            }
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e) {
            logger.error("保存区域下方案", e);
            return HttpResult.newErrorResult("保存区域下方案异常");
        }
    }

    @RequestMapping(value = "/saveProgrammeJudge", name = "保存估价对象", method = RequestMethod.POST)
    public HttpResult saveProgrammeJudge(String formData) {
        try {
            if (StringUtils.isNotBlank(formData)) {
                SchemeJudgeObject schemeJudgeObject = JSON.parseObject(formData, SchemeJudgeObject.class);
                schemeJudgeObjectService.updateSchemeJudgeObject(schemeJudgeObject);
            }
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("保存估价对象", e);
            return HttpResult.newErrorResult("保存估价对象异常");
        }
    }

    @RequestMapping(value = "/saveProgrammeAll", name = "保存方案所有内容 ", method = RequestMethod.POST)
    public HttpResult saveProgrammeAll(String formData) {
        try {
            if (StringUtils.isNotBlank(formData)) {
                List<SchemeProgrammeDto> applyDto = JSON.parseArray(formData, SchemeProgrammeDto.class);
                schemeJudgeObjectService.saveProgrammeAll(applyDto);
            }
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e) {
            logger.error("保存方案所有内容", e);
            return HttpResult.newErrorResult("保存方案所有内容异常");
        }
    }

    @RequestMapping(value = "/getSchemeJudgeFunctions", name = "获取估价对象设置的评估方法 ", method = RequestMethod.GET)
    public HttpResult getSchemeJudgeFunctions(Integer judgeObjectId) {
        try {
            List<SchemeJudgeFunction> judgeFunctions = schemeJudgeFunctionService.getSchemeJudgeFunctions(judgeObjectId);
            return HttpResult.newCorrectResult(judgeFunctions);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = "/getJudgeFunction", name = "评估方法获取 ", method = RequestMethod.POST)
    public HttpResult getJudgeFunction(Integer judgeObjectId) {
        try {
            return HttpResult.newCorrectResult(schemeJudgeFunctionService.getJudgeFunction(judgeObjectId));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = "/saveJudgeFunction", name = "评估方法保存 ", method = RequestMethod.POST)
    public HttpResult saveJudgeFunction(String formData) {
        try {
            SchemeJudgeFunctionApplyDto schemeJudgeFunctionApplyDto = JSON.parseObject(formData, SchemeJudgeFunctionApplyDto.class);
            schemeJudgeFunctionService.saveJudgeFunction(schemeJudgeFunctionApplyDto);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/submitProgramme", name = "工作方案提交", method = RequestMethod.POST)
    public HttpResult submitProgramme(Integer projectId, Integer planId, String formData, @RequestParam(defaultValue = "false") boolean mustUseBox) {
        try {
            List<SchemeProgrammeDto> applyDto = JSON.parseArray(formData, SchemeProgrammeDto.class);
            schemeJudgeObjectService.submitProgramme(projectId, planId, applyDto, mustUseBox);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e) {
            logger.error("工作方案提交", e);
            return HttpResult.newErrorResult("工作方案提交异常");
        }
    }

    @RequestMapping(value = "/getJudgeObjectListByPid", name = "获取合并的委估对象明细", method = RequestMethod.GET)
    public BootstrapTableVo getJudgeObjectListByPid(Integer pid) {
        return schemeJudgeObjectService.getBootstrapTableVoByPid(pid);
    }

    @RequestMapping(value = "/getPlanDetailsByDeclareId", name = "获取权证调查信息", method = RequestMethod.GET)
    public BootstrapTableVo getPlanDetailsByDeclareId(Integer declareId) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        List<ProjectPlanDetailsVo> caseTaskList = projectPlanDetailsService.getPlanDetailsByDeclareId(declareId);
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(caseTaskList) ? Lists.newArrayList() : caseTaskList);
        bootstrapTableVo.setTotal((long) caseTaskList.size());
        return bootstrapTableVo;
    }

    @RequestMapping(value = "/getJugdeObjectById", name = "获取估价对象信息", method = RequestMethod.GET)
    public HttpResult getJugdeObjectById(Integer judgeObjectId) {
        try {
            return HttpResult.newCorrectResult(schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("获取估价对象信息异常");
        }
    }

    @PostMapping(name = "更新估价对象信息", value = "/updateSchemeJudgeObject")
    public HttpResult updateSchemeJudgeObject(SchemeJudgeObject schemeJudgeObject) {
        try {
            schemeJudgeObjectService.updateSchemeJudgeObject(schemeJudgeObject);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("更新估价对象信息", e);
            return HttpResult.newErrorResult("更新估价对象信息异常");
        }
    }
}
