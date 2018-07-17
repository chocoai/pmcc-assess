package com.copower.pmcc.assess.controller.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.DataExamineTask;
import com.copower.pmcc.assess.dal.basis.entity.SurveyExamineTask;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyExamineTaskDto;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyExamineTaskVo;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.assess.service.project.survey.SurveyExamineTaskService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ResponseBody
    @PostMapping(name = "初始化该调查表下的所有任务", value = "/initExamineTask")
    public HttpResult initExamineTask(SurveyExamineTaskDto surveyExamineTaskDto) {
        try {
            surveyCommonService.initExamineTask(surveyExamineTaskDto);
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
        List<SurveyExamineTaskVo> taskVos = surveyExamineTaskService.getSurveyExamineTaskVos(examineTaskList);
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
    public HttpResult saveFastSet(String ids,String userAccount) {
        try {
            surveyExamineTaskService.saveFastSet(ids,userAccount);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("保存批量设置", e);
            return HttpResult.newErrorResult("保存批量设置异常");
        }
    }

    @ResponseBody
    @GetMapping(name = "获取可添加的任务", value = "/getCanAppendTaskList")
    public HttpResult getCanAppendTaskList(Integer dataTaskId,Integer pid,Integer planDetailsId) {
        try {
            List<DataExamineTask> examineTaskList = surveyExamineTaskService.getCanAppendTaskList(dataTaskId, pid, planDetailsId);
            return HttpResult.newCorrectResult(examineTaskList);
        } catch (Exception e) {
            logger.error("获取可添加的任务",e);
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
    public HttpResult confirmAssignment(Integer planDetailsId) {
        try {
            surveyExamineTaskService.confirmAssignment(planDetailsId);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("确认分派", e);
            return HttpResult.newErrorResult("确认分派异常");
        }
    }

    @ResponseBody
    @GetMapping(name = "获取版块信息", value = "/getBlockById")
    public HttpResult getBlockById() {

        return null;
    }


}
