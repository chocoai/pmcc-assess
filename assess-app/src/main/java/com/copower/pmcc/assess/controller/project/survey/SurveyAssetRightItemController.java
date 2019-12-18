package com.copower.pmcc.assess.controller.project.survey;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRightItem;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetRightItemService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zch on 2019-12-16.
 * 他项权利详情表
 */
@RestController
@RequestMapping(value = "/surveyAssetRightItem")
public class SurveyAssetRightItemController {
    @Autowired
    private BaseService baseService;
    @Autowired
    private SurveyAssetRightItemService surveyAssetRightItemService;
    private static final String STRING = "他项权利详情表";


    @PostMapping(value = "/saveAndUpdateSurveyAssetRightItemAll", name = "save")
    public HttpResult saveAndUpdateSurveyAssetRightItemAll(String fomData, boolean updateNull) {
        try {
            List<SurveyAssetRightItem> declareApplyExtensionList = JSONObject.parseArray(fomData, SurveyAssetRightItem.class);
            if (CollectionUtils.isNotEmpty(declareApplyExtensionList)) {
                Iterator<SurveyAssetRightItem> iterator = declareApplyExtensionList.iterator();
                while (iterator.hasNext()) {
                    surveyAssetRightItemService.saveAndUpdateSurveyAssetRightItem(iterator.next(), updateNull);
                }
            }
            return HttpResult.newCorrectResult(200, declareApplyExtensionList);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, String.join("", STRING, e.getMessage()));
            return HttpResult.newErrorResult(500, e);
        }
    }

    @PostMapping(value = "/deleteSurveyAssetRightItemById", name = "delete")
    public HttpResult deleteSurveyAssetRightItemById(String id) {
        try {
            surveyAssetRightItemService.deleteSurveyAssetRightItemById(id);
            return HttpResult.newCorrectResult(200, "success");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, String.join("", STRING, e.getMessage()));
            return HttpResult.newErrorResult(500, e);
        }
    }


    @GetMapping(value = "/getSurveyAssetRightItemById", name = "get")
    public HttpResult getSurveyAssetRightItemById(String id) {
        try {
            List<Integer> ids = FormatUtils.transformString2Integer(id);
            if (ids.size() == 1) {
                return HttpResult.newCorrectResult(200, surveyAssetRightItemService.getSurveyAssetRightItemById(ids.get(0)));
            } else {
                return HttpResult.newCorrectResult(200, surveyAssetRightItemService.getSurveyAssetRightItemByIds(ids));
            }
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, String.join("", STRING, e.getMessage()));
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/getSurveyAssetRightItemListByExample", name = "get list")
    public HttpResult getSurveyAssetRightItemListByExample(SurveyAssetRightItem oo) {
        try {
            return HttpResult.newCorrectResult(200, surveyAssetRightItemService.getSurveyAssetRightItemListByExample(oo));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, String.join("", STRING, e.getMessage()));
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/getBootstrapTableVo", name = "get Pagination list")
    public BootstrapTableVo getBootstrapTableVo(SurveyAssetRightItem oo) {
        return surveyAssetRightItemService.getBootstrapTableVo(oo);
    }

    @PostMapping(value = "/importData", name = "导入他项权利")
    public HttpResult importData(SurveyAssetRightItem oo, HttpServletRequest request) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
            if (multipartFile.isEmpty()) {
                return HttpResult.newErrorResult("上传的文件不能为空");
            }
            String s = surveyAssetRightItemService.importData(oo, multipartFile);
            return HttpResult.newCorrectResult(s);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, String.join("", STRING, e.getMessage()));
            return HttpResult.newErrorResult(500, e);
        }
    }

}
