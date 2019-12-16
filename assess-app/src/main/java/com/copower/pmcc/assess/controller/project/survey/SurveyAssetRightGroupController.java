package com.copower.pmcc.assess.controller.project.survey;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRightGroup;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetRightGroupService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

/**
 * Created by zch on 2019-12-16.
 * 他项权利申报表(他权组)
 */
@RestController
@RequestMapping(value = "/surveyAssetRightGroup")
public class SurveyAssetRightGroupController {
    @Autowired
    private BaseService baseService;
    @Autowired
    private SurveyAssetRightGroupService surveyAssetRightGroupService;
    private static final String STRING = "他项权利申报表(他权组)" ;

    @PostMapping(value = "/saveAndUpdateSurveyAssetRightGroupAll",name = "save")
    public HttpResult saveAndUpdateSurveyAssetRightGroupAll(String fomData, boolean updateNull){
        try {
            List<SurveyAssetRightGroup> declareApplyExtensionList = JSONObject.parseArray(fomData,SurveyAssetRightGroup.class) ;
            if (CollectionUtils.isNotEmpty(declareApplyExtensionList)){
                Iterator<SurveyAssetRightGroup> iterator = declareApplyExtensionList.iterator();
                while (iterator.hasNext()){
                    surveyAssetRightGroupService.saveAndUpdateSurveyAssetRightGroup(iterator.next(),updateNull);
                }
            }
            return HttpResult.newCorrectResult(200,declareApplyExtensionList) ;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,String.join("", STRING,e.getMessage()));
            return HttpResult.newErrorResult(500,e) ;
        }
    }

    @PostMapping(value = "/deleteSurveyAssetRightGroupById",name = "delete")
    public HttpResult deleteSurveyAssetRightGroupById(String id){
        try {
            surveyAssetRightGroupService.deleteSurveyAssetRightGroupById(id);
            return HttpResult.newCorrectResult(200,"success") ;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,String.join("", STRING,e.getMessage()));
            return HttpResult.newErrorResult(500,e) ;
        }
    }


    @GetMapping(value = "/getSurveyAssetRightGroupById",name = "get")
    public HttpResult getSurveyAssetRightGroupById(String id){
        try {
            List<Integer> ids = FormatUtils.transformString2Integer(id);
            if (ids.size() == 1){
                return HttpResult.newCorrectResult(200,surveyAssetRightGroupService.getSurveyAssetRightGroupById(ids.get(0))) ;
            }else {
                return HttpResult.newCorrectResult(200,surveyAssetRightGroupService.getSurveyAssetRightGroupByIds(ids)) ;
            }
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,String.join("", STRING,e.getMessage()));
            return HttpResult.newErrorResult(500,e) ;
        }
    }

    @GetMapping(value = "/getSurveyAssetRightGroupListByExample",name = "get list")
    public HttpResult getSurveyAssetRightGroupListByExample(SurveyAssetRightGroup oo){
        try {
            return HttpResult.newCorrectResult(200,surveyAssetRightGroupService.getSurveyAssetRightGroupListByExample(oo)) ;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,String.join("", STRING,e.getMessage()));
            return HttpResult.newErrorResult(500,e) ;
        }
    }

    @GetMapping(value = "/getBootstrapTableVo",name = "get Pagination list")
    public BootstrapTableVo getBootstrapTableVo(SurveyAssetRightGroup oo){
        return surveyAssetRightGroupService.getBootstrapTableVo(oo) ;
    }

}
