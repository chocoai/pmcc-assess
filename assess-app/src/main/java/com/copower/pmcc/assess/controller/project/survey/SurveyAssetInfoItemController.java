package com.copower.pmcc.assess.controller.project.survey;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInfoItem;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInfoItemService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by zch on 2019-12-16.
 * 资产清查
 */
@RestController
@RequestMapping(value = "/surveyAssetInfoItem")
public class SurveyAssetInfoItemController {
    @Autowired
    private BaseService baseService;
    @Autowired
    private SurveyAssetInfoItemService surveyAssetInfoItemService;
    private static final String STRING = "资产清查" ;

    @PostMapping(value = "/saveAndUpdateSurveyAssetInfoItem",name = "save")
    public HttpResult saveAndUpdateSurveyAssetInfoItem(String formData, @RequestParam(defaultValue = "false") boolean updateNull){
        try {
            SurveyAssetInfoItem surveyAssetInfoItem = JSONObject.parseObject(formData,SurveyAssetInfoItem.class) ;
            surveyAssetInfoItemService.saveAndUpdateSurveyAssetInfoItem(surveyAssetInfoItem,updateNull);
            return HttpResult.newCorrectResult(200,surveyAssetInfoItem) ;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,String.join("", STRING,e.getMessage()));
            return HttpResult.newErrorResult(500,e) ;
        }
    }

    @PostMapping(value = "/deleteSurveyAssetInfoItemById",name = "delete")
    public HttpResult deleteSurveyAssetInfoItemById(String id){
        try {
            surveyAssetInfoItemService.deleteSurveyAssetInfoItemById(id);
            return HttpResult.newCorrectResult(200,"success") ;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,String.join("", STRING,e.getMessage()));
            return HttpResult.newErrorResult(500,e.getMessage()) ;
        }
    }


    @GetMapping(value = "/getSurveyAssetInfoItemById",name = "get")
    public HttpResult getSurveyAssetInfoItemById(Integer id){
        try {
            return HttpResult.newCorrectResult(200,surveyAssetInfoItemService.getSurveyAssetInfoItemById(id)) ;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,String.join("", STRING,e.getMessage()));
            return HttpResult.newErrorResult(500,e) ;
        }
    }



    @GetMapping(value = "/getSurveyAssetInfoItemListByQuery",name = "get list")
    public HttpResult getSurveyAssetInfoItemListByQuery(SurveyAssetInfoItem oo){
        try {
            return HttpResult.newCorrectResult(200,surveyAssetInfoItemService.getSurveyAssetInfoItemListByQuery(oo)) ;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,String.join("", STRING,e.getMessage()));
            return HttpResult.newErrorResult(500,e) ;
        }
    }

    @GetMapping(value = "/getBootstrapTableVo",name = "get Pagination list")
    public BootstrapTableVo getBootstrapTableVo(SurveyAssetInfoItem oo){
        return surveyAssetInfoItemService.getBootstrapTableVo(oo) ;
    }

}
