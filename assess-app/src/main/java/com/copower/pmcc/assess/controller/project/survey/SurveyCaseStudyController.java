package com.copower.pmcc.assess.controller.project.survey;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.service.project.survey.SurveyCaseStudyService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
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
    @PostMapping(name = "保存案例任务", value = "/saveCaseTask")
    public HttpResult saveCaseTask(String formData,Integer planDetailsId) {
        try {
            ProjectPlanDetails projectPlanDetails= JSON.parseObject(formData,ProjectPlanDetails.class);
            surveyCaseStudyService.saveCaseTask(projectPlanDetails,planDetailsId);
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
            return HttpResult.newErrorResult("删除案例任务");
        }
    }

    @ResponseBody
    @PostMapping(name = "复制案例", value = "/copyCaseStudy")
    public HttpResult copyCaseStudy(Integer planDetailsId) {
        try {
            surveyCaseStudyService.copyCaseStudy(planDetailsId);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error("复制案例", e);
            return HttpResult.newErrorResult("复制案例异常");
        }
    }

}
