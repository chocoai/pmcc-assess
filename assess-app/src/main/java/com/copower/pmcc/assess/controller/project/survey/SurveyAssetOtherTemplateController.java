package com.copower.pmcc.assess.controller.project.survey;

import com.copower.pmcc.assess.dto.input.project.survey.SurveyAssetOtherTemplateDto;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetOtherTemplateService;
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
@RequestMapping(value="/surveyAssetOtherTemplate")
public class SurveyAssetOtherTemplateController {

    @Autowired
    private SurveyAssetOtherTemplateService surveyAssetOtherTemplateService;

    @ResponseBody
    @RequestMapping(value = "/list", name = "取得他项权利详情", method = RequestMethod.GET)
    public BootstrapTableVo list(Integer pid) {
        BootstrapTableVo vo = surveyAssetOtherTemplateService.getList(pid);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/save", name = "新增和修改他项权利", method = RequestMethod.POST)
    public HttpResult save(SurveyAssetOtherTemplateDto surveyAssetOtherTemplateDto, Integer pid) {
        try {
            surveyAssetOtherTemplateService.save(surveyAssetOtherTemplateDto,pid);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/delete", name = "删除他项权利", method = RequestMethod.POST)
    public HttpResult delete(@RequestParam(value = "id") Integer id) {
        try {
            surveyAssetOtherTemplateService.delete(id);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

}
