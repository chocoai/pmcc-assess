package com.copower.pmcc.assess.controller.project.survey;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInfoGroup;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInfoItem;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventory;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryContent;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInfoItemService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryContentService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


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
    private CommonService commonService;
    @Autowired
    private SurveyAssetInfoItemService surveyAssetInfoItemService;
    @Autowired
    private SurveyAssetInventoryContentService surveyAssetInventoryContentService;
    private static final String STRING = "资产清查";

    @PostMapping(value = "/saveAndUpdateSurveyAssetInfoItem", name = "单个添加")
    public HttpResult saveAndUpdateSurveyAssetInfoItem(String formData, @RequestParam(defaultValue = "false") boolean updateNull) {
        try {
            SurveyAssetInfoItem surveyAssetInfoItem = JSONObject.parseObject(formData, SurveyAssetInfoItem.class);
            surveyAssetInfoItemService.saveAndUpdateSurveyAssetInfoItem(surveyAssetInfoItem, updateNull);
            return HttpResult.newCorrectResult(surveyAssetInfoItem);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, String.join("", STRING, e.getMessage()));
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @PostMapping(value = "/addSurveyAssetInfoItem", name = "多个添加")
    public HttpResult addSurveyAssetInfoItem(String formData, @RequestParam(defaultValue = "false") boolean updateNull) {
        try {
            List<SurveyAssetInfoItem> surveyAssetInfoItems = JSONObject.parseArray(formData, SurveyAssetInfoItem.class);
            for (SurveyAssetInfoItem surveyAssetInfoItem : surveyAssetInfoItems) {
                surveyAssetInfoItemService.saveAndUpdateSurveyAssetInfoItem(surveyAssetInfoItem, updateNull);
            }
            return HttpResult.newCorrectResult(surveyAssetInfoItems);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, String.join("", STRING, e.getMessage()));
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @PostMapping(value = "/addSurveyAssetInfoItemRecordData", name = "多个添加 并且会更新权证")
    public HttpResult addSurveyAssetInfoItemRecordData(String formData) {
        try {
            return HttpResult.newCorrectResult(surveyAssetInfoItemService.addSurveyAssetInfoItemRecordData(formData));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, String.join("", STRING, e.getMessage()));
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @PostMapping(value = "/deleteSurveyAssetInfoItemById", name = "delete")
    public HttpResult deleteSurveyAssetInfoItemById(String id) {
        try {
            surveyAssetInfoItemService.deleteSurveyAssetInfoItemById(id);
            return HttpResult.newCorrectResult("success");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, String.join("", STRING, e.getMessage()));
            return HttpResult.newErrorResult(e.getMessage());
        }
    }


    @GetMapping(value = "/getSurveyAssetInfoItemById", name = "get")
    public HttpResult getSurveyAssetInfoItemById(Integer id) {
        try {
            return HttpResult.newCorrectResult(surveyAssetInfoItemService.getSurveyAssetInfoItemById(id));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, String.join("", STRING, e.getMessage()));
            return HttpResult.newErrorResult(e);
        }
    }


    @GetMapping(value = "/getSurveyAssetInfoItemListByQuery", name = "get list")
    public HttpResult getSurveyAssetInfoItemListByQuery(SurveyAssetInfoItem oo) {
        try {
            return HttpResult.newCorrectResult(surveyAssetInfoItemService.getSurveyAssetInfoItemListByQuery(oo));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, String.join("", STRING, e.getMessage()));
            return HttpResult.newErrorResult(e);
        }
    }

    @GetMapping(value = "/getSurveyAssetInfoItemIdsByGroupId", name = "get list by groupId")
    public HttpResult getSurveyAssetInfoItemIdsByGroupId(Integer groupId) {
        try {
            return HttpResult.newCorrectResult(surveyAssetInfoItemService.getSurveyAssetInfoItemsByGroupId(groupId));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, String.join("", STRING, e.getMessage()));
            return HttpResult.newErrorResult(e);
        }
    }

    @GetMapping(value = "/getBootstrapTableVo", name = "get Pagination list")
    public BootstrapTableVo getBootstrapTableVo(SurveyAssetInfoItem oo, Integer eatate, Integer building, Integer unit) {
        return surveyAssetInfoItemService.getBootstrapTableVo(oo, eatate, building, unit);
    }

    @PostMapping(value = "/damageSurveyBatch", name = "批量处理损坏调查")
    public HttpResult damageSurveyBatch(String assetInfoItemIds, String formData) {
        try {
            SurveyAssetInventory inventory = JSON.parseObject(formData, SurveyAssetInventory.class);
            surveyAssetInfoItemService.damageSurveyBatch(assetInfoItemIds, inventory);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @PostMapping(value = "/checkUniformityBatch", name = "批量一致性清查")
    public HttpResult checkUniformityBatch(String assetInfoItemIds) {
        try {
            surveyAssetInfoItemService.checkUniformityBatch(assetInfoItemIds);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @GetMapping(value = "/inventory/{itemId}", name = "一致性清查")
    public ModelAndView inventory(@PathVariable(name = "itemId", required = true) Integer itemId) throws BusinessException {
        ModelAndView modelAndView = commonService.baseView("/project/stageSurvey/inventory/uniformityAssetInventoryIndex");
        List<SurveyAssetInventoryContent> inventoryContents = surveyAssetInventoryContentService.getInventoryContentListByItemId(itemId);
        SurveyAssetInfoItem assetInfoItem = surveyAssetInfoItemService.getSurveyAssetInfoItemById(itemId);
        if (CollectionUtils.isEmpty(inventoryContents)) {
            inventoryContents = surveyAssetInventoryContentService.initContentByInfoItem(assetInfoItem);
        }
        modelAndView.addObject("assetInfoItem", assetInfoItem);
        modelAndView.addObject("surveyAssetInventoryContentVos", surveyAssetInventoryContentService.getVoList(inventoryContents));
        return modelAndView;
    }

    @GetMapping(value = "/inventoryDetail/{itemId}", name = "一致性清查")
    public ModelAndView inventoryDetail(@PathVariable(name = "itemId", required = true) Integer itemId) throws BusinessException {
        ModelAndView modelAndView = commonService.baseView("/project/stageSurvey/inventory/uniformityAssetInventoryDetail");
        List<SurveyAssetInventoryContent> inventoryContents = surveyAssetInventoryContentService.getInventoryContentListByItemId(itemId);
        modelAndView.addObject("surveyAssetInventoryContentVos", surveyAssetInventoryContentService.getVoList(inventoryContents));
        return modelAndView;
    }

    @PostMapping(value = "/saveInventoryContent", name = "保存一致性清查")
    public HttpResult saveInventoryContent(String formData) {
        try {
            List<SurveyAssetInventoryContent> contentList = JSON.parseArray(formData, SurveyAssetInventoryContent.class);
            if (CollectionUtils.isNotEmpty(contentList)) {
                for (SurveyAssetInventoryContent content : contentList) {
                    surveyAssetInventoryContentService.saveAssetInventoryContent(content);
                }
                SurveyAssetInfoItem assetInfoItem = surveyAssetInfoItemService.getSurveyAssetInfoItemById(contentList.get(0).getInfoItemId());
                if (assetInfoItem != null) {
                    assetInfoItem.setBisFinishUniformity(true);
                    surveyAssetInfoItemService.updateSurveyAssetInfoItem(assetInfoItem, false);
                }
            }
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @PostMapping(value = "/reinitializeContentList", name = "保存一致性清查")
    public HttpResult reinitializeContentList(Integer itemId) {
        try {
            if (itemId != null) {
                SurveyAssetInfoItem assetInfoItem = surveyAssetInfoItemService.getSurveyAssetInfoItemById(itemId);
                surveyAssetInventoryContentService.deleteInventoryContentByItemId(itemId);
                surveyAssetInventoryContentService.initContentByInfoItem(assetInfoItem);
            }
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
}
