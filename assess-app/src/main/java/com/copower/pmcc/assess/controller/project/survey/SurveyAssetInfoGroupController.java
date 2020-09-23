package com.copower.pmcc.assess.controller.project.survey;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInfoGroup;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInfoItem;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventory;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInfoGroupService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


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
    private CommonService commonService;
    @Autowired
    private SurveyAssetInfoGroupService surveyAssetInfoGroupService;
    @Autowired
    private SurveyAssetInventoryService surveyAssetInventoryService;

    private static final String STRING = "资产清查组";

    @PostMapping(value = "/addSurveyAssetInfoGroup", name = "save")
    public HttpResult addSurveyAssetInfoGroup(Integer assetInfoId, String formType, String groupName) {
        try {
            surveyAssetInfoGroupService.addSurveyAssetInfoGroup(assetInfoId, formType, groupName);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, String.join("", STRING, e.getMessage()));
            return HttpResult.newErrorResult(e);
        }
    }

    @PostMapping(value = "/deleteSurveyAssetInfoGroupById", name = "delete")
    public HttpResult deleteSurveyAssetInfoGroupById(String id) {
        try {
            surveyAssetInfoGroupService.deleteSurveyAssetInfoGroupById(id);
            return HttpResult.newCorrectResult("success");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, String.join("", STRING, e.getMessage()));
            return HttpResult.newErrorResult(e.getMessage());
        }
    }


    @GetMapping(value = "/getSurveyAssetInfoGroupById", name = "get")
    public HttpResult getSurveyAssetInfoGroupById(Integer id) {
        try {
            return HttpResult.newCorrectResult(surveyAssetInfoGroupService.getSurveyAssetInfoGroupById(id));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, String.join("", STRING, e.getMessage()));
            return HttpResult.newErrorResult(e);
        }
    }

    @GetMapping(value = "/getSurveyAssetInfoGroupListByQuery", name = "get list")
    public HttpResult getSurveyAssetInfoGroupListByQuery(SurveyAssetInfoGroup oo) {
        try {
            return HttpResult.newCorrectResult(surveyAssetInfoGroupService.getSurveyAssetInfoGroupListByQuery(oo));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, String.join("", STRING, e.getMessage()));
            return HttpResult.newErrorResult(e);
        }
    }

    @GetMapping(value = "/getBootstrapTableVo", name = "get Pagination list")
    public BootstrapTableVo getBootstrapTableVo(SurveyAssetInfoGroup oo) {
        return surveyAssetInfoGroupService.getBootstrapTableVo(oo);
    }

    @PostMapping(value = "/selectClaimInfoItem", name = "认领的权证添加到分组中")
    public HttpResult selectClaimInfoItem(Integer groupId, String infoItems) {
        try {
            surveyAssetInfoGroupService.selectClaimInfoItem(groupId, infoItems);
            return HttpResult.newCorrectResult("success");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, String.join("", STRING, e.getMessage()));
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @PostMapping(value = "/removeGroupInfoItem", name = "移除分组中权证")
    public HttpResult removeGroupInfoItem(String infoItems) {
        try {
            surveyAssetInfoGroupService.removeGroupInfoItem(infoItems);
            return HttpResult.newCorrectResult("success");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, String.join("", STRING, e.getMessage()));
            return HttpResult.newErrorResult(e.getMessage());
        }
    }


    @GetMapping(value = "/view/{groupId}", name = "申请或者修改")
    public ModelAndView applyView(@PathVariable(name = "groupId", required = true) Integer groupId) {
        ModelAndView modelAndView = commonService.baseView("/project/stageSurvey/inventory/groupAssetInventoryIndex");
        SurveyAssetInfoGroup assetInfoGroup = surveyAssetInfoGroupService.getSurveyAssetInfoGroupById(groupId);
        if (assetInfoGroup != null) {
            modelAndView.addObject("viewSpiltEntity", surveyAssetInventoryService.getSurveyAssetInventoryById(assetInfoGroup.getViewSpiltId()));
            modelAndView.addObject("taxArrearsEntity", surveyAssetInventoryService.getSurveyAssetInventoryById(assetInfoGroup.getTaxArrearsId()));
            modelAndView.addObject("damageEntity", surveyAssetInventoryService.getSurveyAssetInventoryById(assetInfoGroup.getDamageId()));
            modelAndView.addObject("transferEntity", surveyAssetInventoryService.getSurveyAssetInventoryById(assetInfoGroup.getTransferId()));
        }
        return modelAndView;
    }

    @PostMapping(value = "/saveGroupInventoryData", name = "保存分组的清查信息")
    public HttpResult saveGroupInventoryData(String formData) {
        try {
            surveyAssetInfoGroupService.saveGroupInventoryData(formData);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, String.join("", STRING, e.getMessage()));
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
}
