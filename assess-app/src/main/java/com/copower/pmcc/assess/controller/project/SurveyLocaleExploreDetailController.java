package com.copower.pmcc.assess.controller.project;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.controller.ControllerComponent;
import com.copower.pmcc.assess.dal.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.entity.SurveyLocaleExploreDetail;
import com.copower.pmcc.assess.service.project.*;
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

import java.util.List;

/**
 * Created by zly on 2018/5/15.
 */
@Controller
@RequestMapping(value = "/surveyLocale")
public class SurveyLocaleExploreDetailController {

    @Autowired
    private ControllerComponent controllerComponent;
    @Autowired
    private SurveyLocaleExploreDetailService surveyLocaleExploreDetailService;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private DeclareRecordService declareRecordService;


    @RequestMapping(value = "/index", name = "现场查勘新增页面", method = RequestMethod.GET)
    public ModelAndView index(Integer responsibilityId) {
        ModelAndView modelAndView = controllerComponent.baseModelAndView("/task/explore/addIndex");
        ProjectResponsibilityDto projectPlanResponsibility = bpmRpcProjectTaskService.getProjectTaskById(responsibilityId);
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(projectPlanResponsibility.getPlanDetailsId());
//        Integer planId = projectPlanDetails.getPlanId();
        Integer projectId = projectPlanDetails.getProjectId();
        String processInsId = projectPlanDetails.getProcessInsId();
        List<DeclareRecord> declareRecords = declareRecordService.getDeclareRecordByProjectId(projectId);
//        List<SurveyLocaleExplore> surveyLocaleExplore = surveyLocaleExploreService.getSurveyLocaleExplore(processInsId);
//        SurveyLocaleExplore surveyLocaleExplore1 = surveyLocaleExplore.get(0);
        modelAndView.addObject("declareRecords",declareRecords);
        modelAndView.addObject("projectPlanDetails",projectPlanDetails);
//        modelAndView.addObject("surveyLocaleExplore",surveyLocaleExplore1);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/list", name = "取得现场查勘详情", method = RequestMethod.GET)
    public BootstrapTableVo list(Integer mainId) {
        BootstrapTableVo vo = surveyLocaleExploreDetailService.getList(mainId);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/save", name = "新增和修改现场查勘", method = RequestMethod.POST)
    public HttpResult save(String formData) {
        try {
            SurveyLocaleExploreDetail surveyLocaleExploreDetail = JSON.parseObject(formData, SurveyLocaleExploreDetail.class);
            surveyLocaleExploreDetailService.save(surveyLocaleExploreDetail);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/delete", name = "删除现场查勘", method = RequestMethod.POST)
    public HttpResult delete(@RequestParam(value = "id") Integer id) {
        try {
            surveyLocaleExploreDetailService.delete(id);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

}