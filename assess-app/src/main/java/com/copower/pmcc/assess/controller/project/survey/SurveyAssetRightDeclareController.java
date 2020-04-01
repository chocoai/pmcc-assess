package com.copower.pmcc.assess.controller.project.survey;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRightDeclare;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetRightDeclareService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Iterator;
import java.util.List;

/**
 * Created by zch on 2019-12-16.
 * 他项权利 申报记录
 */
@RestController
@RequestMapping(value = "/surveyAssetRightDeclare")
public class SurveyAssetRightDeclareController {

    @Autowired
    private SurveyAssetRightDeclareService surveyAssetRightDeclareService;
    @Autowired
    private BaseService baseService;
    private static final String STRING = "他项权利 申报记录" ;

    @Autowired
    private ProcessControllerComponent processControllerComponent;


    @RequestMapping(value = "/taskRightDetail/{projectId}", name = "转到详情页面 ", method = {RequestMethod.GET})
    public ModelAndView index(@PathVariable(name = "projectId",required = true) Integer projectId) {
        String view = "/project/stageSurvey/right/taskRightDetail";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        modelAndView.addObject(StringUtils.uncapitalize(ProjectPlanDetails.class.getSimpleName()),surveyAssetRightDeclareService.getProjectPlanDetailsByQuery(projectId)) ;
        return modelAndView;
    }


    @PostMapping(value = "/saveAndUpdateSurveyAssetRightDeclareAll",name = "save")
    public HttpResult saveAndUpdateSurveyAssetRightDeclareAll(String fomData, boolean updateNull){
        try {
            List<SurveyAssetRightDeclare> declareApplyExtensionList = JSONObject.parseArray(fomData,SurveyAssetRightDeclare.class) ;
            if (CollectionUtils.isNotEmpty(declareApplyExtensionList)){
                Iterator<SurveyAssetRightDeclare> iterator = declareApplyExtensionList.iterator();
                while (iterator.hasNext()){
                    surveyAssetRightDeclareService.saveAndUpdateSurveyAssetRightDeclare(iterator.next(),updateNull);
                }
            }
            return HttpResult.newCorrectResult(200,declareApplyExtensionList) ;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,String.join("", STRING,e.getMessage()));
            return HttpResult.newErrorResult(500,e) ;
        }
    }

    @PostMapping(value = "/deleteSurveyAssetRightDeclareById",name = "delete")
    public HttpResult deleteSurveyAssetRightDeclareById(String id){
        try {
            surveyAssetRightDeclareService.deleteSurveyAssetRightDeclareById(id);
            return HttpResult.newCorrectResult(200,"success") ;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,String.join("", STRING,e.getMessage()));
            return HttpResult.newErrorResult(500,e) ;
        }
    }


    @GetMapping(value = "/getSurveyAssetRightDeclareById",name = "get")
    public HttpResult getSurveyAssetRightDeclareById(String id){
        try {
            List<Integer> ids = FormatUtils.transformString2Integer(id);
            if (ids.size() == 1){
                return HttpResult.newCorrectResult(200,surveyAssetRightDeclareService.getSurveyAssetRightDeclareById(ids.get(0))) ;
            }else {
                return HttpResult.newCorrectResult(200,surveyAssetRightDeclareService.getSurveyAssetRightDeclareByIds(ids)) ;
            }
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,String.join("", STRING,e.getMessage()));
            return HttpResult.newErrorResult(500,e) ;
        }
    }

    @GetMapping(value = "/getSurveyAssetRightDeclareListByExample",name = "get list")
    public HttpResult getSurveyAssetRightDeclareListByExample(SurveyAssetRightDeclare oo){
        try {
            return HttpResult.newCorrectResult(200,surveyAssetRightDeclareService.getSurveyAssetRightDeclareListByExample(oo)) ;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,String.join("", STRING,e.getMessage()));
            return HttpResult.newErrorResult(500,e) ;
        }
    }

    @GetMapping(value = "/getBootstrapTableVo",name = "get Pagination list")
    public BootstrapTableVo getBootstrapTableVo(SurveyAssetRightDeclare oo){
        return surveyAssetRightDeclareService.getBootstrapTableVo(oo) ;
    }


}
