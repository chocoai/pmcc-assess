package com.copower.pmcc.assess.controller.project.plan;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.EvaluationMethodField;
import com.copower.pmcc.assess.dal.basis.entity.EvaluationThinkingField;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeFunction;
import com.copower.pmcc.assess.dto.input.data.EvaluationThinkingDto;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeJudgeFunctionApplyDto;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeJudgeObjectApplyDto;
import com.copower.pmcc.assess.dto.output.data.EvaluationMethodVo;
import com.copower.pmcc.assess.service.project.scheme.SchemeAssistService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeFunctionService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * ProjectPlanSchemeAssist class 引用的Controller
 * Created by 13426 on 2018/5/17.
 */
@RequestMapping(value = "/projectplanschemeassist")
@Controller
public class ProjectPlanSchemeAssistController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SchemeAssistService schemeAssistService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private SchemeJudgeFunctionService schemeJudgeFunctionService;

    @ResponseBody
    @RequestMapping(value = "/evaluationThink/think", name = "评估工作方案阶段工作计划 评估技术思路 获取,以及字段引用", method = RequestMethod.POST)
    public Object evaluationThinkGet(@RequestParam(value = "id") Integer id, Integer type, Integer flag) {
        try {
            if (id != null) {
                if (flag.equals(2)) {
                    List<EvaluationThinkingField> fields = schemeAssistService.schemeassistserviceThinkFilds(id, type);
                    if (fields != null) return fields;
                } else if (flag.equals(1)) {
                    EvaluationThinkingDto evaluationThinkingDto = schemeAssistService.get(id);
                    if (evaluationThinkingDto != null) return evaluationThinkingDto;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/evaluationmethod/fieldList", name = "评估工作方案阶段工作计划 评估方法 字段引用", method = RequestMethod.POST)
    public Object evaluationMethodField(@RequestParam(value = "id") Integer id, @RequestParam(value = "type") Integer type) {
        try {
            if (id != null && type != null) {
                List<EvaluationMethodField> fields = schemeAssistService.list(id, type);
                if (fields != null) return fields;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }


    @ResponseBody
    @RequestMapping(value = "/evaluationmethod/getList", name = "评估工作方案阶段工作计划 评估方法 ", method = RequestMethod.POST)
    public Object evaluationMethod(@RequestParam(value = "id") Integer id) {
        try {
            if (id != null) {
                List<EvaluationMethodVo> vos = schemeAssistService.listMethod(id);
                if (vos != null) return vos;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/schemeAreaGroupVoList", name = "评估工作方案阶段工作计划 区域分组数据 ", method = RequestMethod.POST)
    public HttpResult schemeAreaGroupVoList(@RequestParam(value = "areaGroupId") Integer areaGroupId) {
        try {
            return HttpResult.newCorrectResult(schemeJudgeObjectService.getSchemeJudgeObjectList(areaGroupId));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getSchemeJudgeFunctions", name = "获取估价对象设置的评估方法 ", method = RequestMethod.GET)
    public Object getSchemeJudgeFunctions(Integer areaGroupId,Integer groupNumber) {
        try {
            List<SchemeJudgeFunction> judgeFunctions = schemeJudgeFunctionService.getSchemeJudgeFunctions(areaGroupId,groupNumber);
            return HttpResult.newCorrectResult(judgeFunctions);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveJudgeFunction", name = "评估方法 保存 ", method = RequestMethod.POST)
    public HttpResult saveJudgeFunction(String formData) {
        try {
            SchemeJudgeFunctionApplyDto schemeJudgeFunctionApplyDto=JSON.parseObject(formData,SchemeJudgeFunctionApplyDto.class);
            schemeAssistService.saveJudgeFunction(schemeJudgeFunctionApplyDto);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }


    @ResponseBody
    @RequestMapping(value = "/saveEvaluationObject", name = "保存估价对象 ", method = RequestMethod.POST)
    public HttpResult saveEvaluationObject(String formData) {
        try {
            if (StringUtils.isNotBlank(formData)) {
                SchemeJudgeObjectApplyDto applyDto = JSON.parseObject(formData, SchemeJudgeObjectApplyDto.class);
                schemeJudgeObjectService.saveEvaluationObject(applyDto);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}
