package com.copower.pmcc.assess.controller.project.survey;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.entity.SurveyLocaleExploreDetail;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyLocaleExploreDetailDto;
import com.copower.pmcc.assess.dto.output.report.SurveyCorrelationCardVo;
import com.copower.pmcc.assess.service.base.FormConfigureService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.assess.service.project.survey.SurveyLocaleExploreDetailService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.collections.CollectionUtils;
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
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private SurveyLocaleExploreDetailService surveyLocaleExploreDetailService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private FormConfigureService formConfigureService;

    @ResponseBody
    @RequestMapping(value = "/list", name = "取得现场查勘详情", method = RequestMethod.GET)
    public BootstrapTableVo list(Integer planDetailsId) {
        BootstrapTableVo vo = surveyLocaleExploreDetailService.getList(planDetailsId);
        return vo;
    }

    @RequestMapping(value = "/index", name = "现场查勘管理页面", method = RequestMethod.GET)
    public ModelAndView index(Integer id, Integer planDetailsId, Integer projectId) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/task/explore/localeExploreManage");
        SurveyLocaleExploreDetail surveyLocaleExploreDetail = null;
        if (id != null && id > 0) {
            surveyLocaleExploreDetail = surveyLocaleExploreDetailService.getSingelDetail(id);
            if(surveyLocaleExploreDetail.getDynamicFormId()==null) surveyLocaleExploreDetail.setDynamicFormId(0);
            String moduleJsonString = formConfigureService.getModuleJsonString(surveyLocaleExploreDetail.getDynamicFormId(), surveyLocaleExploreDetail.getDynamicTableId());
            modelAndView.addObject("JsonValue", moduleJsonString);
        } else {
            surveyLocaleExploreDetail = new SurveyLocaleExploreDetail();
            surveyLocaleExploreDetail.setId(0);
        }
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
        List<DeclareRecord> declareRecords = declareRecordService.getDeclareRecordByProjectId(projectId);
        if (CollectionUtils.isNotEmpty(declareRecords)) {
            List<SurveyCorrelationCardVo> surveyCorrelationCardVos = surveyCommonService.getSurveyCorrelationCardVos(surveyLocaleExploreDetail.getCorrelationCard(), projectPlanDetails, declareRecords);
            modelAndView.addObject("surveyCorrelationCardVos", surveyCorrelationCardVos);
        }
        modelAndView.addObject("projectPlanDetails", projectPlanDetails);
        modelAndView.addObject("surveyLocaleExploreDetail", surveyLocaleExploreDetail);
        return modelAndView;
    }



    @RequestMapping(value = "/detailsIndex", name = "详情页面", method = RequestMethod.GET)
    public ModelAndView detailIndex(Integer id) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/task/explore/localeExploreDetails");
        SurveyLocaleExploreDetail surveyLocaleExploreDetail = null;
        if (id != null && id > 0) {
            surveyLocaleExploreDetail = surveyLocaleExploreDetailService.getSingelDetail(id);
            String moduleJsonString = formConfigureService.getModuleJsonString(surveyLocaleExploreDetail.getDynamicFormId(), surveyLocaleExploreDetail.getDynamicTableId());
            modelAndView.addObject("JsonValue", moduleJsonString);
        }
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