package com.copower.pmcc.assess.controller.project.survey;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyAssetCommonDataDto;
import com.copower.pmcc.assess.dto.output.basic.SurveyAssetInventoryVo;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyAssetInventoryContentVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
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
    private BaseDataDicService baseDataDicService;
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

    private final String applyViewName = "/project/stageSurvey/surveyAssetInventoryIndex";
    private final String detailView = "/project/stageSurvey/surveyAssetInventoryDetail";

    @GetMapping(value = "/view/{projectId}/{planDetailId}/{inventoryId}/{declareId}", name = "申请或者修改")
    public ModelAndView applyView(@PathVariable(name = "projectId", required = true) Integer projectId, @PathVariable(name = "planDetailId", required = true) Integer planDetailId, @PathVariable(name = "inventoryId") Integer inventoryId, @PathVariable(name = "declareId", required = true) Integer declareId) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(applyViewName);
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(declareId);
        if (inventoryId != null && inventoryId != 0) {
            //修改情况
            setModelViewParam(inventoryId, declareRecord, modelAndView);
            //房产证类型
            List<BaseDataDic> types = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_DECLARE_HOUSE_CERTIFICATE_TYPE);
            modelAndView.addObject("types", types);
            //是否办证
            List<BaseDataDic> certificateTypes = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.CERTIFICATE_HANDLING_TYPE);
            modelAndView.addObject("certificateTypes", certificateTypes);
        } else {
            //初始状况
            modelAndView.addObject("declareRecord", declareRecord);
            List<SurveyAssetInventoryContent> list = surveyAssetInventoryContentService.initAssetInventoryContentNew(0, projectId, inventoryId, declareRecord);
            modelAndView.addObject("surveyAssetInventory", new SurveyAssetInventoryVo());
            List<SurveyAssetInventoryContentVo> surveyAssetInventoryContentVos = surveyAssetInventoryContentService.getVoList(list);
            SysUserDto thisUserInfo = processControllerComponent.getThisUserInfo();
            modelAndView.addObject("thisUserInfo", thisUserInfo);    //当前操作用户信息
            modelAndView.addObject("surveyAssetInventoryContentVos", surveyAssetInventoryContentVos);
            //证载用途
            List<BaseDataDic> types = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_LOAD_UTILITY);
            modelAndView.addObject("types", types);
            //是否办证
            List<BaseDataDic> certificateTypes = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.CERTIFICATE_HANDLING_TYPE);
            modelAndView.addObject("certificateTypes", certificateTypes);
            //土地类型
            BaseProjectClassify houseLand = baseProjectClassifyService.getCacheProjectClassifyByFieldName(AssessProjectClassifyConstant.SINGLE_HOUSE_LAND_CERTIFICATE_TYPE_SIMPLE);
            modelAndView.addObject("houseLand", houseLand.getId());
        }
        modelAndView.addObject("projectPlanDetails", projectPlanDetailsService.getProjectPlanDetailsById(planDetailId));
        modelAndView.addObject("projectInfo", projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(projectId)));
        return modelAndView;
    }

    @GetMapping(value = "/detailView/{projectId}/{planDetailId}/{inventoryId}/{declareId}", name = "详情")
    public ModelAndView detailsView(@PathVariable(name = "projectId", required = true) Integer projectId, @PathVariable(name = "planDetailId", required = true) Integer planDetailId, @PathVariable(name = "inventoryId") Integer inventoryId, @PathVariable(name = "declareId", required = true) Integer declareId){
        ModelAndView modelAndView =  processControllerComponent.baseModelAndView(detailView);
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(declareId);
        setModelViewParam(inventoryId, declareRecord, modelAndView);
        return modelAndView;
    }

    @PostMapping(value = "/saveSurveyAssetInventory",name = "具体的数据保存")
    public HttpResult saveSurveyAssetInventory(String formData) {
        try {
            SurveyAssetCommonDataDto surveyAssetCommonDataDto = surveyAssetInventoryService.format(formData);
            surveyAssetInventoryService.save(0, 0, 0, "0", surveyAssetCommonDataDto);
            return HttpResult.newCorrectResult(200, surveyAssetCommonDataDto);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(500, e);
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
