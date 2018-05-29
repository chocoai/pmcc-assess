package com.copower.pmcc.assess.controller.project.survey;

import com.copower.pmcc.assess.dto.input.project.SurveyAssetTemplateDto;
import com.copower.pmcc.assess.service.project.SurveyAssetTemplateService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/surveyAssetTemplate")
@Controller
public class SurveyAssetTemplateController {
    @Autowired
    private SurveyAssetTemplateService surveyAssetTemplateService;

    @ResponseBody
    @RequestMapping(value = "/list", name = "取得清查模板详情", method = RequestMethod.GET)
    public BootstrapTableVo list(Integer pid) {
        BootstrapTableVo vo = surveyAssetTemplateService.getList(pid);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/save", name = "新增和修改清查模板", method = RequestMethod.POST)
    public HttpResult save(SurveyAssetTemplateDto surveyAssetTemplateDto,Integer pid) {
        try {
            surveyAssetTemplateService.save(surveyAssetTemplateDto,pid);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/delete", name = "删除清查模板", method = RequestMethod.POST)
    public HttpResult delete(@RequestParam(value = "id") Integer id) {
        try {
            surveyAssetTemplateService.delete(id);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}
