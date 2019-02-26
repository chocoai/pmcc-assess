package com.copower.pmcc.assess.controller.project;

import com.copower.pmcc.assess.controller.BaseController;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.survey.SurveyExamineTaskService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/30
 * @time: 14:06
 */
@Controller
@RequestMapping(value = "/ProjectTaskExamine")
public class ProjectTaskExamineController extends BaseController {
    @Autowired
    private SurveyExamineTaskService surveyExamineTaskService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;

    @ResponseBody
    @RequestMapping(value = "/saveData", name = "保存数据", method = RequestMethod.POST)
    public HttpResult submitTask(Integer projectDetailsId, String formData) {
        try {
            ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(projectDetailsId);
            surveyExamineTaskService.saveExamineDataInfo(formData, projectPlanDetails);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return HttpResult.newErrorResult("保存数据异常");
        }
        return HttpResult.newCorrectResult();
    }

}
