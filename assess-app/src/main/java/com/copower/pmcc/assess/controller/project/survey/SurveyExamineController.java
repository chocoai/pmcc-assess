package com.copower.pmcc.assess.controller.project.survey;

import com.copower.pmcc.assess.common.enums.ExamineTypeEnum;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyExamineTaskDto;
import com.copower.pmcc.assess.dto.output.project.ProjectInfoVo;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyExamineTaskVo;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.assess.service.project.survey.SurveyExamineInfoService;
import com.copower.pmcc.assess.service.project.survey.SurveyExamineTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by kings on 2018-7-6.
 */
@Controller
@RequestMapping("/surveyExamine")
public class SurveyExamineController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private SurveyExamineTaskService surveyExamineTaskService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;
    @Autowired
    private SurveyExamineInfoService surveyExamineInfoService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private ProjectInfoService projectInfoService;

    @RequestMapping(value = "/assignment", name = "任务分派视图", method = {RequestMethod.GET})
    public ModelAndView assignment(Integer planDetailsId) {
        String view = "/task/survey/taskAssignmentIndex";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsDao.getProjectPlanDetailsItemById(planDetailsId);
        Integer id = projectPlanDetails.getPid();
        ProjectPlanDetails parentPlan = projectPlanDetailsDao.getProjectPlanDetailsItemById(id);
        modelAndView.addObject("parentPlan", parentPlan);


        //确认调查类型 查勘或案例
        Integer examineType = ExamineTypeEnum.EXPLORE.getId();
        ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseById(projectPlanDetails.getProjectPhaseId());
        if (StringUtils.equals(projectPhase.getPhaseKey(), AssessPhaseKeyConstant.CASE_STUDY)){
            examineType = ExamineTypeEnum.CASE.getId();
        }
        modelAndView.addObject("examineType", examineType);
        modelAndView.addObject("examineFormTypeList", surveyCommonService.getExamineFormTypeList());
        SurveyExamineInfo surveyExamineInfo = surveyExamineInfoService.getExploreByPlanDetailsId(planDetailsId);
        if(surveyExamineInfo==null){
            surveyExamineTaskService.deleteTaskByPlanDetailsId(planDetailsId);//清空数据
        }
        modelAndView.addObject("surveyExamineInfo", surveyExamineInfo);
        modelAndView.addObject("projectPlanDetails",projectPlanDetails);
        ProjectInfoVo projectInfoVo = projectInfoService.getProjectInfoVoView(projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId()));
        modelAndView.addObject("projectInfo", projectInfoVo);
        return modelAndView;
    }

    @ResponseBody
    @PostMapping(name = "初始化该调查表下的所有任务", value = "/initExamineTask")
    public HttpResult initExamineTask(SurveyExamineTaskDto surveyExamineTaskDto) {
        try {
            surveyExamineTaskService.initExamineTask(surveyExamineTaskDto);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("初始化该调查表下的所有任务", e);
            return HttpResult.newErrorResult("初始化异常");
        }
    }

    @ResponseBody
    @GetMapping(name = "获取调查任务", value = "/getExamineTaskList")
    public BootstrapTableVo getExamineTaskList(Integer planDetailsId) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        List<SurveyExamineTask> examineTaskList = surveyExamineTaskService.getTaskListByPlanDetailsId(planDetailsId);
        List<SurveyExamineTaskVo> taskVos = surveyExamineTaskService.getSurveyExamineTaskVos(examineTaskList, false);
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(taskVos) ? Lists.newArrayList() : taskVos);
        bootstrapTableVo.setTotal((long) examineTaskList.size());
        return bootstrapTableVo;
    }

    @ResponseBody
    @PostMapping(name = "删除调查任务", value = "/deleteSurveyExamineTask")
    public HttpResult deleteSurveyExamineTask(Integer id) {
        try {
            surveyExamineTaskService.deleteSurveyExamineTask(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("删除调查任务", e);
            return HttpResult.newErrorResult("删除任务异常");
        }
    }

    @ResponseBody
    @PostMapping(name = "保存批量设置", value = "/saveFastSet")
    public HttpResult saveFastSet(String ids, String userAccount) {
        try {
            surveyExamineTaskService.saveFastSet(ids, userAccount);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("保存批量设置", e);
            return HttpResult.newErrorResult("保存批量设置异常");
        }
    }

    @ResponseBody
    @GetMapping(name = "获取可添加的任务", value = "/getCanAppendTaskList")
    public HttpResult getCanAppendTaskList(Integer dataTaskId, Integer pid, Integer planDetailsId) {
        try {
            List<DataExamineTask> examineTaskList = surveyExamineTaskService.getCanAppendTaskList(dataTaskId, pid, planDetailsId);
            return HttpResult.newCorrectResult(examineTaskList);
        } catch (Exception e) {
            logger.error("获取可添加的任务", e);
            return HttpResult.newErrorResult("获取可添加的任务异常");
        }
    }

    @ResponseBody
    @PostMapping(name = "保存任务信息", value = "/saveSurveyExamineTask")
    public HttpResult saveSurveyExamineTask(SurveyExamineTask surveyExamineTask) {
        try {
            surveyExamineTaskService.saveSurveyExamineTask(surveyExamineTask);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("保存任务信息", e);
            return HttpResult.newErrorResult("保存任务信息异常");
        }
    }

    @ResponseBody
    @PostMapping(name = "保存选择的调查任务", value = "/saveSelectExamineTask")
    public HttpResult saveSelectExamineTask(SurveyExamineTaskDto surveyExamineTaskDto) {
        try {
            surveyExamineTaskService.saveSelectExamineTask(surveyExamineTaskDto);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("保存选择的调查任务", e);
            return HttpResult.newErrorResult("保存选择的调查任务异常");
        }
    }

    @ResponseBody
    @PostMapping(name = "确认分派", value = "/confirmAssignment")
    public HttpResult confirmAssignment(SurveyExamineTaskDto surveyExamineTaskDto) {
        try {
            surveyExamineTaskService.confirmAssignment(surveyExamineTaskDto);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("确认分派", e);
            return HttpResult.newErrorResult("确认分派异常");
        }
    }



}
