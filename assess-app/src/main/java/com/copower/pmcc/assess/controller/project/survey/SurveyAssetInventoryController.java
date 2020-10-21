package com.copower.pmcc.assess.controller.project.survey;

import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyAssetCommonDataDto;
import com.copower.pmcc.assess.dto.output.basic.SurveyAssetInventoryVo;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyAssetInventoryContentVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInfoItemService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryContentService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by zch on 2020-3-24.
 * 资产清查
 */
@RestController
@RequestMapping(value = "/surveyAssetInventory")
public class SurveyAssetInventoryController {

    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private SurveyAssetInventoryService surveyAssetInventoryService;
    @Autowired
    private SurveyAssetInventoryContentService surveyAssetInventoryContentService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private SurveyAssetInfoItemService surveyAssetInfoItemService;
    @Autowired
    private ProjectInfoService projectInfoService;

    private final String applyViewName = "/project/stageSurvey/inventory/surveyAssetInventoryIndex";
    private final String detailView = "/project/stageSurvey/inventory/surveyAssetInventoryDetail";

    @GetMapping(value = "/view/{assetInfoItemId}", name = "申请或者修改")
    public ModelAndView applyView(@PathVariable(name = "assetInfoItemId", required = true) Integer assetInfoItemId) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(applyViewName);
        SurveyAssetInfoItem assetInfoItem = surveyAssetInfoItemService.getSurveyAssetInfoItemById(assetInfoItemId);
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(assetInfoItem.getDeclareId());
        SurveyAssetInventory assetInventory = null;
        if (assetInfoItem.getInventoryId() == null || assetInfoItem.getInventoryId() == 0) {
            try {
                assetInventory = surveyAssetInventoryService.initSurveyAssetInventory(assetInfoItemId);
            } catch (BusinessException e) {
                baseService.writeExceptionInfo(e);
            }
        }
        setModelViewParam(assetInventory != null ? assetInventory.getId() : assetInfoItem.getInventoryId(), declareRecord, modelAndView);
        modelAndView.addObject("assetInfoItem", assetInfoItem);
        modelAndView.addObject("masterName", assetInfoItem.getName());
        return modelAndView;
    }

    @GetMapping(value = "/detailView/{projectId}/{planDetailId}/{inventoryId}/{declareId}/{masterName}", name = "详情")
    public ModelAndView detailsView(@PathVariable(name = "projectId", required = true) Integer projectId, @PathVariable(name = "planDetailId", required = true) Integer planDetailId, @PathVariable(name = "inventoryId") Integer inventoryId, @PathVariable(name = "declareId", required = true) Integer declareId, @PathVariable(name = "masterName") String masterName) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(detailView);
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(declareId);
        setModelViewParam(inventoryId, declareRecord, modelAndView);
        modelAndView.addObject("masterName", masterName);
        return modelAndView;
    }

    @PostMapping(value = "/saveSurveyAssetInventory", name = "具体的数据保存")
    public HttpResult saveSurveyAssetInventory(String formData) {
        try {
            SurveyAssetCommonDataDto surveyAssetCommonDataDto = surveyAssetInventoryService.format(formData);
            surveyAssetInventoryService.saveSurveyAssetInventory(surveyAssetCommonDataDto);
            return HttpResult.newCorrectResult(surveyAssetCommonDataDto);
        } catch (BusinessException e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @PostMapping(value = "/parseSurveyAssetInventory/{inventoryId}/{type}/{masterId}", name = "数据粘贴")
    public HttpResult parseSurveyAssetInventory(@PathVariable(name = "inventoryId") Integer inventoryId, @PathVariable(name = "type") String type, @PathVariable(name = "masterId") String masterId) {
        try {
            surveyAssetInventoryService.parseSurveyAssetInventory(inventoryId, type, masterId);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @GetMapping(value = "/getSurveyAssetInventoryById")
    public HttpResult getSurveyAssetInventoryById(Integer id) {
        try {
            SurveyAssetInventoryVo surveyAssetInventoryVo = surveyAssetInventoryService.getSurveyAssetInventoryVo(surveyAssetInventoryService.getSurveyAssetInventoryById(id));
            return HttpResult.newCorrectResult(surveyAssetInventoryVo);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @PostMapping(value = "/canInventory")
    public HttpResult canInventory(Integer assetInfoItemId) {
        try {
            Boolean canInventory = surveyAssetInventoryService.canInventory(assetInfoItemId);
            return HttpResult.newCorrectResult(canInventory);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }


    private void setModelViewParam(Integer inventoryId, DeclareRecord declareRecord, ModelAndView modelAndView) {
        modelAndView.addObject("thisUserInfo", processControllerComponent.getThisUserInfo());    //当前操作用户信息
        modelAndView.addObject("declareRecord", declareRecord);
        SurveyAssetInventoryVo surveyAssetInventoryVo = surveyAssetInventoryService.getSurveyAssetInventoryVo(surveyAssetInventoryService.getSurveyAssetInventoryById(inventoryId));
        modelAndView.addObject("surveyAssetInventory", surveyAssetInventoryVo);

        List<SurveyAssetInventoryContentVo> surveyAssetInventoryContentVos = surveyAssetInventoryContentService.getVoList(surveyAssetInventoryContentService.getSurveyAssetInventoryContentListByMasterId(inventoryId));
        modelAndView.addObject("surveyAssetInventoryContentVos", surveyAssetInventoryContentVos);

        modelAndView.addObject("projectInfo",projectInfoService.getProjectInfoById(declareRecord.getProjectId()));
        //土地类型
        BaseProjectClassify landClassify = baseProjectClassifyService.getCacheProjectClassifyByFieldName(AssessProjectClassifyConstant.SINGLE_HOUSE_LAND_CERTIFICATE_TYPE_SIMPLE);
        modelAndView.addObject("landCategoryId", landClassify.getId());
    }


}
