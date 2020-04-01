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
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private BaseService baseService;

    private final String applyViewName = "/project/stageSurvey/inventory/surveyAssetInventoryIndex";
    private final String detailView = "/project/stageSurvey/inventory/surveyAssetInventoryDetail";

    @GetMapping(value = "/view/{projectId}/{planDetailId}/{inventoryId}/{declareId}/{masterName}", name = "申请或者修改")
    public ModelAndView applyView(@PathVariable(name = "projectId", required = true) Integer projectId, @PathVariable(name = "planDetailId", required = true) Integer planDetailId, @PathVariable(name = "inventoryId") Integer inventoryId, @PathVariable(name = "declareId", required = true) Integer declareId,@PathVariable(name = "masterName")String masterName) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(applyViewName);
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(declareId);
        if (inventoryId != null && inventoryId != 0) {
            //修改情况
            setModelViewParam(inventoryId, declareRecord, modelAndView);
        } else {
            //初始状况
            modelAndView.addObject("declareRecord", declareRecord);
            List<SurveyAssetInventoryContent> list = surveyAssetInventoryContentService.initAssetInventoryContentNew(0, projectId, inventoryId, declareRecord);
            modelAndView.addObject("surveyAssetInventory", new SurveyAssetInventoryVo());
            List<SurveyAssetInventoryContentVo> surveyAssetInventoryContentVos = surveyAssetInventoryContentService.getVoList(list);
            SysUserDto thisUserInfo = processControllerComponent.getThisUserInfo();
            modelAndView.addObject("thisUserInfo", thisUserInfo);    //当前操作用户信息
            modelAndView.addObject("surveyAssetInventoryContentVos", surveyAssetInventoryContentVos);
            //土地类型
            BaseProjectClassify houseLand = baseProjectClassifyService.getCacheProjectClassifyByFieldName(AssessProjectClassifyConstant.SINGLE_HOUSE_LAND_CERTIFICATE_TYPE_SIMPLE);
            modelAndView.addObject("houseLand", houseLand.getId());
        }
        modelAndView.addObject("projectPlanDetails", projectPlanDetailsService.getProjectPlanDetailsById(planDetailId));
        modelAndView.addObject("projectInfo", projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(projectId)));
        modelAndView.addObject("masterName", masterName);
        return modelAndView;
    }

    @GetMapping(value = "/detailView/{projectId}/{planDetailId}/{inventoryId}/{declareId}/{masterName}", name = "详情")
    public ModelAndView detailsView(@PathVariable(name = "projectId", required = true) Integer projectId, @PathVariable(name = "planDetailId", required = true) Integer planDetailId, @PathVariable(name = "inventoryId") Integer inventoryId, @PathVariable(name = "declareId", required = true) Integer declareId , @PathVariable(name = "masterName")String masterName) {
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
            surveyAssetInventoryService.save(0, 0, 0, "0", surveyAssetCommonDataDto);
            return HttpResult.newCorrectResult(200, surveyAssetCommonDataDto);
        } catch (BusinessException e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @PostMapping(value = "/parseSurveyAssetInventory/{inventoryId}/{type}/{masterId}", name = "数据粘贴")
    public HttpResult parseSurveyAssetInventory(@PathVariable(name = "inventoryId") Integer inventoryId, @PathVariable(name = "type") String type, @PathVariable(name = "masterId") String masterId) {
        try {
            surveyAssetInventoryService.parseSurveyAssetInventory(inventoryId, type, masterId);
            return HttpResult.newCorrectResult(200);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @GetMapping(value = "/getSurveyAssetInventoryById")
    public HttpResult getSurveyAssetInventoryById(Integer id) {
        try {
            SurveyAssetInventoryVo surveyAssetInventoryVo = surveyAssetInventoryService.getSurveyAssetInventoryVo(surveyAssetInventoryService.getSurveyAssetInventoryById(id));
            return HttpResult.newCorrectResult(200, surveyAssetInventoryVo);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }


    private void setModelViewParam(Integer inventoryId, DeclareRecord declareRecord, ModelAndView modelAndView) {
        modelAndView.addObject("thisUserInfo", processControllerComponent.getThisUserInfo());    //当前操作用户信息
        modelAndView.addObject("declareRecord", declareRecord);
        SurveyAssetInventoryVo surveyAssetInventoryVo = surveyAssetInventoryService.getSurveyAssetInventoryVo(surveyAssetInventoryService.getSurveyAssetInventoryById(inventoryId));
        modelAndView.addObject("surveyAssetInventory", surveyAssetInventoryVo);

        List<SurveyAssetInventoryContentVo> surveyAssetInventoryContentVos = surveyAssetInventoryContentService.getVoList(surveyAssetInventoryContentService.getSurveyAssetInventoryContentListByMasterId(inventoryId));
        modelAndView.addObject("surveyAssetInventoryContentVos", surveyAssetInventoryContentVos);

        //土地类型
        BaseProjectClassify houseLand = baseProjectClassifyService.getCacheProjectClassifyByFieldName(AssessProjectClassifyConstant.SINGLE_HOUSE_LAND_CERTIFICATE_TYPE_SIMPLE);
        modelAndView.addObject("houseLand", houseLand.getId());
    }


}
