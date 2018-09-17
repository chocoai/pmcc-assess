package com.copower.pmcc.assess.controller.project.survey;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRight;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryRightService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zly on 2018/6/11.
 */
@Controller
@RequestMapping(value = "/surveyAssetInventoryRight")
public class SurveyAssetInventoryRightController {

    @Autowired
    private SurveyAssetInventoryRightService surveyAssetInventoryRightService;

    @ResponseBody
    @RequestMapping(value = "/getListByPlanDetailsId", name = "取得他项权利列表", method = RequestMethod.GET)
    public BootstrapTableVo list(Integer planDetailsId) {
        BootstrapTableVo vo = surveyAssetInventoryRightService.getListByPlanDetailsId(planDetailsId);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/getListByProjectId", name = "取得他项权利列表", method = RequestMethod.GET)
    public BootstrapTableVo get(Integer projectId) {
        BootstrapTableVo vo = surveyAssetInventoryRightService.getListByProjectId(projectId);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/save", name = "新增和修改他项权利", method = RequestMethod.POST)
    public HttpResult save(String formData) {
        try {
            SurveyAssetInventoryRight surveyAssetInventoryRight = JSON.parseObject(formData, SurveyAssetInventoryRight.class);
            surveyAssetInventoryRightService.save(surveyAssetInventoryRight);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/delete", name = "删除他项权利", method = RequestMethod.POST)
    public HttpResult delete(@RequestParam(value = "id") Integer id) {
        try {
            surveyAssetInventoryRightService.delete(id);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

}
