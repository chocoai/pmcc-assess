package com.copower.pmcc.assess.controller.project.survey;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.controller.ControllerComponent;
import com.copower.pmcc.assess.dal.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.entity.SurveyLocaleExploreDetail;
import com.copower.pmcc.assess.dto.input.project.SurveyLocaleExploreDetailDto;
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
public class SurveyLocaleExploreController {

    @Autowired
    private ControllerComponent controllerComponent;
    @Autowired
    private SurveyLocaleExploreDetailService surveyLocaleExploreDetailService;
    @Autowired
    private DeclareRecordService declareRecordService;

    @ResponseBody
    @RequestMapping(value = "/list", name = "取得现场查勘详情", method = RequestMethod.GET)
    public BootstrapTableVo list(Integer planDetailsId) {
        BootstrapTableVo vo = surveyLocaleExploreDetailService.getList(planDetailsId);
        return vo;
    }

    @RequestMapping(value = "/index", name = "现场查勘管理页面", method = RequestMethod.GET)
    public ModelAndView index(Integer id, Integer planDetailsId, Integer projectId) {
        ModelAndView modelAndView = controllerComponent.baseModelAndView("/task/explore/localeExploreManage");
        SurveyLocaleExploreDetail surveyLocaleExploreDetail = null;
        if (id != null && id > 0) {
            surveyLocaleExploreDetail = surveyLocaleExploreDetailService.getSingelDetail(id);
        } else {
            surveyLocaleExploreDetail = new SurveyLocaleExploreDetail();
            surveyLocaleExploreDetail.setId(0);
        }
        List<DeclareRecord> declareRecords = declareRecordService.getDeclareRecordByProjectId(projectId);
        modelAndView.addObject("declareRecords", declareRecords);
        modelAndView.addObject("planDetailsId", planDetailsId);
        modelAndView.addObject("surveyLocaleExploreDetail", surveyLocaleExploreDetail);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/save", name = "新增和修改现场查勘", method = RequestMethod.POST)
    public HttpResult save(String formData) {
        try {
            SurveyLocaleExploreDetailDto surveyLocaleExploreDetail = JSON.parseObject(formData, SurveyLocaleExploreDetailDto.class);
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