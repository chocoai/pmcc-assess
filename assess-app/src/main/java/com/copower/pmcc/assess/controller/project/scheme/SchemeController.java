package com.copower.pmcc.assess.controller.project.scheme;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeFunction;
import com.copower.pmcc.assess.dal.basis.entity.SchemeReportFileCustom;
import com.copower.pmcc.assess.dal.basis.entity.SchemeReportFileItem;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeFunctionService;
import com.copower.pmcc.assess.service.project.SchemeReportFileService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by kings on 2018-10-25.
 */
@Controller
@RequestMapping("/scheme")
public class SchemeController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SchemeJudgeFunctionService schemeJudgeFunctionService;
    @Autowired
    private SchemeReportFileService schemeReportFileService;


    @ResponseBody
    @RequestMapping(value = "/changeFunctionContent", name = "评估方法原因及思路更新 ", method = RequestMethod.POST)
    public HttpResult changeFunctionContent(String formData) {
        try {
            List<SchemeJudgeFunction> judgeFunctionList = JSON.parseArray(formData, SchemeJudgeFunction.class);
            schemeJudgeFunctionService.changeFunctionContent(judgeFunctionList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/getLiveSituationAll", name = "获取委估对象下所有的实况图片 ", method = RequestMethod.POST)
    public HttpResult getLiveSituationAll(Integer judgeObjectId,Integer projectId) {
        try {
            return HttpResult.newCorrectResult(schemeReportFileService.getLiveSituationAll(judgeObjectId,projectId));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getLiveSituationSelect", name = "获取选择的实况图片 ", method = RequestMethod.POST)
    public HttpResult getLiveSituationSelect(Integer judgeObjectId) {
        try {
            return HttpResult.newCorrectResult(schemeReportFileService.getLiveSituationSelect(judgeObjectId));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/selectLiveSituation", name = "选择实况图片 ", method = RequestMethod.POST)
    public HttpResult selectLiveSituation(SchemeReportFileItem schemeReportFileItem) {
        try {
            return HttpResult.newCorrectResult(schemeReportFileService.selectLiveSituation(schemeReportFileItem));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/removeLiveSituation", name = "移除实况图片 ", method = RequestMethod.POST)
    public HttpResult removeLiveSituation(Integer id) {
        try {
            schemeReportFileService.removeLiveSituation(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getReportFileCustomList", name = "获取自定义块 ", method = RequestMethod.POST)
    public HttpResult getReportFileCustomList(Integer areaId) {
        try {
            return HttpResult.newCorrectResult(schemeReportFileService.getReportFileCustomList(areaId));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/addReportFileCustom", name = "新增自定义块 ", method = RequestMethod.POST)
    public HttpResult addReportFileCustom(SchemeReportFileCustom schemeReportFileCustom) {
        try {
            return HttpResult.newCorrectResult(schemeReportFileService.addReportFileCustom(schemeReportFileCustom));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteReportFileCustom", name = "删除自定义块 ", method = RequestMethod.POST)
    public HttpResult deleteReportFileCustom(Integer id) {
        try {
            schemeReportFileService.deleteReportFileCustom(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
}
