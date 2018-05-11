package com.copower.pmcc.assess.controller.project;

import com.copower.pmcc.assess.dto.input.project.SurveyAssetInventoryDto;
import com.copower.pmcc.assess.service.project.SurveyAssetInventoryService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zly on 2018/5/10.
 */

@RequestMapping(value="surveyAssetInventory")
@Controller
public class SurveyAssetInventoryController {

    @Autowired
    private SurveyAssetInventoryService surveyAssetInventoryService;

    @ResponseBody
    @RequestMapping(value = "/inventorySubmit", name = "新增和修改资产清查", method = RequestMethod.POST)
    public HttpResult save(String formData) {
        try {
//            surveyAssetInventoryService.save(surveyAssetInventoryService.format(formData));
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}
