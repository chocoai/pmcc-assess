package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.controller.ControllerComponent;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dto.input.data.EvaluationThinkingDto;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.EvaluationThinkingService;
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
 * 评估技术思路
 * Created by 13426 on 2018/4/26.
 */
@RequestMapping(value = "/evaluationThinking",name = "评估技术思路")
@Controller
public class EvaluationThinkingController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ControllerComponent controllerComponent;

    @Autowired
    private EvaluationThinkingService service;

    @Autowired
    private BaseDataDicService baseDataDicService;

    @RequestMapping(value = "/index", name = "转到index页面")
    public ModelAndView index() {
        List<BaseDataDic> baseDataDics = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.EVALUATION_METHOD);
        ModelAndView modelAndView = controllerComponent.baseModelAndView("/data/evaluationThinkingView");
        modelAndView.addObject("useList", baseDataDics);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/list", name = "显示列表", method = {RequestMethod.GET,RequestMethod.POST})
    public BootstrapTableVo list(String methodStr) {
        BootstrapTableVo vo = null;
        if (methodStr == null || methodStr == "") {//查询所有
            vo = service.listVo(null);
        } else {
            vo = service.listVo(methodStr);//关键字查询
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/get", name = "获取",method = {RequestMethod.GET})
    public Object get(@RequestParam(value = "id") Integer id) {
        EvaluationThinkingDto evaluationThinkingDto = null;
        try {
            evaluationThinkingDto = service.get(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return evaluationThinkingDto;
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = {RequestMethod.POST, RequestMethod.GET}, name = "增加与修改")
    public HttpResult add(EvaluationThinkingDto evaluationThinkingDto) {
        try {
            if (evaluationThinkingDto.getId() != null && evaluationThinkingDto.getId() != 0) {//不再使用专门的 update controller
                service.update(evaluationThinkingDto);
            } else {
                service.add(evaluationThinkingDto);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/delete", name = "删除",method = RequestMethod.POST)
    public HttpResult delete(@RequestParam(value = "id") Integer id) {
        try {
            service.remove(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

}
