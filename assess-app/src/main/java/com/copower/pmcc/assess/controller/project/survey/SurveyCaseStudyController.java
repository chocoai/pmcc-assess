package com.copower.pmcc.assess.controller.project.survey;

import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.service.project.survey.SurveyCaseStudyService;
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
@RequestMapping("/surveyCaseStudy")
public class SurveyCaseStudyController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private SurveyCaseStudyService surveyCaseStudyService;
    @Autowired
    private SurveyExamineTaskService surveyExamineTaskService;


    @ResponseBody
    @GetMapping(name = "获取计划相关调查任务", value = "/getPlanTaskExamineList")
    public BootstrapTableVo getPlanTaskExamineList(Integer planDetailsId) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        List<ProjectPlanDetailsVo> caseTaskList = surveyCommonService.getPlanTaskExamineList(planDetailsId);
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(caseTaskList) ? Lists.newArrayList() : caseTaskList);
        bootstrapTableVo.setTotal((long) caseTaskList.size());
        return bootstrapTableVo;
    }

    @ResponseBody
    @PostMapping(name = "保存案例任务,并且分派", value = "/saveCaseTask")
    public HttpResult saveCaseTask(Integer planDetailsId,String projectPhaseName, Integer transactionType) {
        try {
            surveyCaseStudyService.saveCaseTaskExtend(planDetailsId,projectPhaseName,transactionType);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("保存案例任务", e);
            return HttpResult.newErrorResult("保存案例任务异常");
        }
    }

    @ResponseBody
    @PostMapping(name = "删除案例任务", value = "/deleteCaseTask")
    public HttpResult deleteCaseTask(Integer id) {
        try {
            surveyCaseStudyService.deleteCaseTask(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("删除案例任务", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @PostMapping(name = "检查是否添加任务", value = "/checkAssignmentTask")
    public HttpResult checkAssignmentTask(Integer planDetailsId) {
        try {
            return HttpResult.newCorrectResult(surveyExamineTaskService.checkAssignmentTask(planDetailsId));
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("操作异常");
        }
    }
}
