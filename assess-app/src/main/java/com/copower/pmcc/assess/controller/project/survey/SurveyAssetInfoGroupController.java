package com.copower.pmcc.assess.controller.project.survey;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInfoGroup;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInfoGroupService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by zch on 2019-12-16.
 * 资产清查组
 */
@RestController
@RequestMapping(value = "/surveyAssetInfoGroup")
public class SurveyAssetInfoGroupController {
    @Autowired
    private BaseService baseService;
    @Autowired
    private SurveyAssetInfoGroupService surveyAssetInfoGroupService;
    private static final String STRING = "资产清查组" ;

    @PostMapping(value = "/saveAndUpdateSurveyAssetInfoGroup",name = "save")
    public HttpResult saveAndUpdateSurveyAssetInfoGroup(String formData, @RequestParam(defaultValue = "false") boolean updateNull){
        try {
            SurveyAssetInfoGroup surveyAssetInfoGroup = JSONObject.parseObject(formData,SurveyAssetInfoGroup.class) ;
            surveyAssetInfoGroupService.saveAndUpdateSurveyAssetInfoGroup(surveyAssetInfoGroup,updateNull);
            return HttpResult.newCorrectResult(200,surveyAssetInfoGroup) ;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,String.join("", STRING,e.getMessage()));
            return HttpResult.newErrorResult(500,e) ;
        }
    }

    @PostMapping(value = "/deleteSurveyAssetInfoGroupById",name = "delete")
    public HttpResult deleteSurveyAssetInfoGroupById(String id){
        try {
            surveyAssetInfoGroupService.deleteSurveyAssetInfoGroupById(id);
            return HttpResult.newCorrectResult(200,"success") ;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,String.join("", STRING,e.getMessage()));
            return HttpResult.newErrorResult(500,e.getMessage()) ;
        }
    }


    @GetMapping(value = "/getSurveyAssetInfoGroupById",name = "get")
    public HttpResult getSurveyAssetInfoGroupById(Integer id){
        try {
            return HttpResult.newCorrectResult(200,surveyAssetInfoGroupService.getSurveyAssetInfoGroupById(id)) ;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,String.join("", STRING,e.getMessage()));
            return HttpResult.newErrorResult(500,e) ;
        }
    }

    @GetMapping(value = "/getSurveyAssetInfoGroupListByQuery",name = "get list")
    public HttpResult getSurveyAssetInfoGroupListByQuery(SurveyAssetInfoGroup oo){
        try {
            return HttpResult.newCorrectResult(200,surveyAssetInfoGroupService.getSurveyAssetInfoGroupListByQuery(oo)) ;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,String.join("", STRING,e.getMessage()));
            return HttpResult.newErrorResult(500,e) ;
        }
    }

    @GetMapping(value = "/getBootstrapTableVo",name = "get Pagination list")
    public BootstrapTableVo getBootstrapTableVo(SurveyAssetInfoGroup oo){
        return surveyAssetInfoGroupService.getBootstrapTableVo(oo) ;
    }

}
