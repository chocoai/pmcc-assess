package com.copower.pmcc.assess.controller.project;

import com.copower.pmcc.assess.controller.ControllerComponent;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.input.project.SurveyLocaleExploreDto;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.SurveyLocaleExploreService;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zly on 2018/5/15.
 */
@Controller
@RequestMapping(value="/surveyLocale")
public class SurveyLocaleExploreController {

    @Autowired
    private ControllerComponent controllerComponent;

    @Autowired
    private SurveyLocaleExploreService surveyLocaleExploreService;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;

    @RequestMapping(value="/index", name="现场查勘新增页面" ,method = RequestMethod.GET)
    public ModelAndView index(Integer responsibilityId){
        ModelAndView modelAndView = controllerComponent.baseModelAndView("/task/explore/addIndex");
        ProjectResponsibilityDto projectPlanResponsibility = bpmRpcProjectTaskService.getProjectTaskById(responsibilityId);
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(projectPlanResponsibility.getPlanDetailsId());
        Integer planId = projectPlanDetails.getPlanId();
        Integer projectId = projectPlanDetails.getProjectId();
        String processInsId = projectPlanDetails.getProcessInsId();

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/list", name = "取得清查模板详情", method = RequestMethod.GET)
    public BootstrapTableVo list(String processInsId) {
        BootstrapTableVo vo = surveyLocaleExploreService.getList(processInsId);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/save", name = "新增和修改现场查勘", method = RequestMethod.POST)
    public HttpResult save(SurveyLocaleExploreDto surveyLocaleExploreDto, Integer pid) {
        try {
            surveyLocaleExploreService.save(surveyLocaleExploreDto,pid);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/delete", name = "删除现场查勘", method = RequestMethod.POST)
    public HttpResult delete(@RequestParam(value = "id") Integer id) {
        try {
            surveyLocaleExploreService.delete(id);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

}