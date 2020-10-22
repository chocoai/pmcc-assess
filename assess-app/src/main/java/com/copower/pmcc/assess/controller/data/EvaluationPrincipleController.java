package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.common.enums.SchemeSupportTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataReportTemplateItemService;
import com.copower.pmcc.assess.service.data.EvaluationPrincipleService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 评估原则
 * Created by 13426 on 2018/4/28.
 */
@RequestMapping(value = "/evaluationPrinciple", name = "评估原则")
@Controller
public class EvaluationPrincipleController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private EvaluationPrincipleService evaluationPrincipleService;
    @Autowired
    private DataReportTemplateItemService dataReportTemplateItemService;

    @RequestMapping(value = "/view", name = "转到index页面")
    public ModelAndView index() {
        List<KeyValueDto> types = SchemeSupportTypeEnum.getSchemeSupportTypeEnumList();
        List<BaseDataDic> methodDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_EVALUATION_METHOD);
        List<BaseDataDic> purposeDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE);
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/data/evaluationPrincipleView");
        modelAndView.addObject("methodDicList", methodDicList);
        modelAndView.addObject("purposeDicList", purposeDicList);
        modelAndView.addObject("types", types);
        dataReportTemplateItemService.initClean();
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/list", name = "显示列表", method = RequestMethod.GET)
    public BootstrapTableVo list(String name) {
        return evaluationPrincipleService.getPrincipleList(name);
    }

    @ResponseBody
    @RequestMapping(value = "/get", name = "获取", method = {RequestMethod.GET})
    public HttpResult get(@RequestParam(value = "id") Integer id) {
        try {
            return HttpResult.newCorrectResult(evaluationPrincipleService.getPrinciple(id));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = {RequestMethod.POST}, name = "增加与修改")
    public HttpResult save(String formData) {
        try {
            evaluationPrincipleService.saveAndUpdate(formData);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/delete", name = "删除", method = RequestMethod.POST)
    public HttpResult delete(@RequestParam(value = "id") Integer id) {
        try {
            evaluationPrincipleService.removePrinciple(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

}
