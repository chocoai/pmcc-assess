package com.copower.pmcc.assess.controller.project.scheme;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeFunction;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeProgrammeDto;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataBestUseDescriptionService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.data.EvaluationMethodService;
import com.copower.pmcc.assess.service.data.EvaluationThinkingService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeFunctionService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by kings on 2018-9-12.
 */
@Controller
@RequestMapping("/schemeProgramme")
public class SchemeProgrammeController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DeclareRecordService declareRecordService;
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

    @RequestMapping(value = "/index", name = "方案设置视图", method = {RequestMethod.GET})
    public ModelAndView index(Integer projectId, Integer planId) {
        String view = "/project/stageScheme/programmeIndex";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        List<SchemeAreaGroup> areaGroups = declareRecordService.getSchemeAreaGroup(projectId);//获取分组信息
        ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(projectId));
        modelAndView.addObject("projectInfo", projectInfoVo);
        modelAndView.addObject("areaGroups", areaGroups);
        modelAndView.addObject("bestUseList", dataBestUseDescriptionService.dataBestUseDescriptionList(projectInfoVo.getProjectTypeId(),projectInfoVo.getProjectCategoryId()));
        modelAndView.addObject("setUseList", dataSetUseFieldService.getCacheSetUseFieldListByPid(0));
        modelAndView.addObject("dataDicMethodList", baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_EVALUATION_METHOD));
        modelAndView.addObject("valueTypes",baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.VALUE_TYPE));//价值类型
        modelAndView.addObject("entrustmentPurposes",baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE));//委托目的
        modelAndView.addObject("valueConnotations",baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROGRAMME_VALUE_CONNOTATION));//价值内涵

        modelAndView.addObject("evaluationMethodMap", evaluationMethodService.getEvaluationMethodMap());
        modelAndView.addObject("evaluationThinkingMap", evaluationThinkingService.getEvaluationThinkingMap());
        modelAndView.addObject("inventoryRightTypeList", baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.INVENTORY_RIGHT_TYPE));

        modelAndView.addObject("planId", planId);
        return modelAndView;
    }

    @RequestMapping(value = "/view", name = "方案设置视图", method = {RequestMethod.GET})
    public ModelAndView view(Integer projectId, Integer planId) {
        String view = "/project/stageScheme/programmeView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        List<SchemeAreaGroup> areaGroups = declareRecordService.getSchemeAreaGroup(projectId);//获取分组信息
        ProjectInfoVo projectInfoVo = projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(projectId));
        modelAndView.addObject("projectInfo", projectInfoVo);
        modelAndView.addObject("areaGroups", areaGroups);
        modelAndView.addObject("bestUseList", dataBestUseDescriptionService.dataBestUseDescriptionList(projectInfoVo.getProjectTypeId(),projectInfoVo.getProjectCategoryId()));
        modelAndView.addObject("setUseList", baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_PRACTICAL_USE));
        modelAndView.addObject("dataDicMethodList", baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_EVALUATION_METHOD));

        modelAndView.addObject("planId", planId);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getSchemeJudgeObjectList", name = "区域下的委估对象数据列表 ", method = RequestMethod.POST)
    public HttpResult getSchemeJudgeObjectList(Integer areaGroupId) {
        try {
            return HttpResult.newCorrectResult(schemeJudgeObjectService.getSchemeJudgeObjectList(areaGroupId));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
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

    @ResponseBody
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

    @ResponseBody
    @PostMapping(name = "委估对象拆分", value = "/splitJudge")
    public HttpResult splitJudge(Integer projectId, Integer areaGroupId, Integer id) {
        try {
            schemeJudgeObjectService.splitJudge(projectId, areaGroupId, id);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("委估对象拆分", e);
            return HttpResult.newErrorResult("委估对象拆分异常");
        }
    }

    @ResponseBody
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

    @ResponseBody
    @PostMapping(name = "委估对象合并", value = "/mergeJudge")
    public HttpResult mergeJudge(String ids) {
        try {
            schemeJudgeObjectService.mergeJudge(ids);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e) {
            logger.error("委估对象合并", e);
            return HttpResult.newErrorResult("委估对象合并异常");
        }
    }

    @ResponseBody
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

    @ResponseBody
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

    @ResponseBody
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

    @ResponseBody
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

    @ResponseBody
    @RequestMapping(value = "/saveJudgeFunction", name = "评估方法保存 ", method = RequestMethod.POST)
    public HttpResult saveJudgeFunction(String formData) {
        try {
            List<SchemeJudgeFunction> judgeFunctionList = JSON.parseArray(formData, SchemeJudgeFunction.class);
            schemeJudgeFunctionService.saveJudgeFunction(judgeFunctionList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/submitProgramme", name = "工作方案提交", method = RequestMethod.POST)
    public HttpResult submitProgramme(Integer projectId, Integer planId, String formData) {
        try {
            List<SchemeProgrammeDto> applyDto = JSON.parseArray(formData, SchemeProgrammeDto.class);
            schemeJudgeObjectService.submitProgramme(projectId, planId, applyDto);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e) {
            logger.error("工作方案提交", e);
            return HttpResult.newErrorResult("工作方案提交异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getJudgeObjectListByPid", name = "获取合并的委估对象明细", method = RequestMethod.GET)
    public BootstrapTableVo getJudgeObjectListByPid(Integer pid) {
        return schemeJudgeObjectService.getJudgeObjectListByPid(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/getPlanDetailsByDeclareId", name = "获取权证调查信息", method = RequestMethod.GET)
    public BootstrapTableVo getPlanDetailsByDeclareId(Integer declareId) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        List<ProjectPlanDetailsVo> caseTaskList = projectPlanDetailsService.getPlanDetailsByDeclareId(declareId);
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(caseTaskList) ? Lists.newArrayList() : caseTaskList);
        bootstrapTableVo.setTotal((long) caseTaskList.size());
        return bootstrapTableVo;
    }

    @ResponseBody
    @PostMapping(name = "更新出租占用情况", value = "/updateRentalPossessionDesc")
    public HttpResult updateRentalPossessionDesc(Integer id,String rentalPossessionDesc) {
        try {
            schemeJudgeObjectService.updateRentalPossessionDesc(id,rentalPossessionDesc);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("更新出租占用情况", e);
            return HttpResult.newErrorResult("更新出租占用情况异常");
        }
    }
}
