package com.copower.pmcc.assess.controller.project.survey;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.controller.ControllerComponent;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.input.project.SurveyLocaleExploreDetailDto;
import com.copower.pmcc.assess.dto.output.report.SurveyCorrelationCardVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.FormConfigureService;
import com.copower.pmcc.assess.service.project.*;
import com.copower.pmcc.bpm.api.dto.ProjectResponsibilityDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private BaseDataDicService baseDataDicService;
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
        ModelAndView modelAndView = controllerComponent.baseModelAndView("/task/explore/localeExploreManage");
        SurveyLocaleExploreDetail surveyLocaleExploreDetail = null;
        if (id != null && id > 0) {
            surveyLocaleExploreDetail = surveyLocaleExploreDetailService.getSingelDetail(id);
            String moduleJsonString = formConfigureService.getModuleJsonString(surveyLocaleExploreDetail.getDynamicFormId(), surveyLocaleExploreDetail.getDynamicTableId());
            modelAndView.addObject("JsonValue", moduleJsonString);
        } else {
            surveyLocaleExploreDetail = new SurveyLocaleExploreDetail();
            surveyLocaleExploreDetail.setId(0);
        }
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
        List<DeclareRecord> declareRecords = declareRecordService.getDeclareRecordByProjectId(projectId);
        if (CollectionUtils.isNotEmpty(declareRecords)) {
            List<SurveyCorrelationCardVo> surveyCorrelationCardVos = Lists.newArrayList();
            List<Integer> correlationCardIds = Lists.newArrayList();
            if (StringUtils.isNotBlank(surveyLocaleExploreDetail.getCorrelationCard())) {
                List<String> list = FormatUtils.transformString2List(surveyLocaleExploreDetail.getCorrelationCard());
                correlationCardIds = FormatUtils.ListStringToListInteger(list);
            }
            for (DeclareRecord declareRecord : declareRecords) {
                if (declareRecord.getId().equals(projectPlanDetails.getDeclareRecordId())) continue;
                SurveyCorrelationCardVo surveyCorrelationCardVo = new SurveyCorrelationCardVo();
                surveyCorrelationCardVo.setId(declareRecord.getId());
                surveyCorrelationCardVo.setName(declareRecord.getName());
                surveyCorrelationCardVo.setBisChecked(correlationCardIds.contains(declareRecord.getId()));
                surveyCorrelationCardVos.add(surveyCorrelationCardVo);
            }
            modelAndView.addObject("surveyCorrelationCardVos", surveyCorrelationCardVos);
        }

        modelAndView.addObject("projectPlanDetails", projectPlanDetails);
        modelAndView.addObject("surveyLocaleExploreDetail", surveyLocaleExploreDetail);
        return modelAndView;
    }

    @RequestMapping(value = "/detailsIndex", name = "详情页面", method = RequestMethod.GET)
    public ModelAndView detailIndex(Integer id) {
        ModelAndView modelAndView = controllerComponent.baseModelAndView("/task/explore/localeExploreDetails");
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