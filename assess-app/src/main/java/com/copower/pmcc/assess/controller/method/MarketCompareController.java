package com.copower.pmcc.assess.controller.method;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.basic.MethodCompareFieldEnum;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.controller.BaseController;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.method.MarketCompareItemDto;
import com.copower.pmcc.assess.dto.input.method.MarketCompareResultDto;
import com.copower.pmcc.assess.dto.output.basic.BasicApplyVo;
import com.copower.pmcc.assess.dto.output.method.MdCompareInitParamVo;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.BasicEstateLandCategoryInfoService;
import com.copower.pmcc.assess.service.method.MdMarketCompareService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by kings on 2018-7-23.
 */
@Controller
@RequestMapping("/marketCompare")
public class MarketCompareController extends BaseController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private MdMarketCompareService mdMarketCompareService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicEstateLandCategoryInfoService basicEstateLandCategoryInfoService;

    @RequestMapping(value = "/index", name = "市场比较法测算")
    public ModelAndView index(Integer mcId, Integer judgeObjectId, Boolean isLand) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/method/marketCompareIndex");
        isLand = isLand == null ? false : isLand;
        SchemeJudgeObject judgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
        if (judgeObject.getBisMerge() == Boolean.TRUE) {
            SchemeJudgeObject standardJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObject.getStandardJudgeId());
            modelAndView.addObject("standardJudgeObject", standardJudgeObject);
        }
        MdMarketCompare marketCompare = null;
        if (mcId != null) {
            marketCompare = mdMarketCompareService.getMdMarketCompare(mcId);
        }
        if (marketCompare == null) {
            marketCompare = mdMarketCompareService.initExplore(judgeObject, isLand);
        }
        if(isLand){
            modelAndView.addObject("fieldsJSON", JSON.toJSONString(mdMarketCompareService.getLandFieldListByApplyId(judgeObject.getBasicApplyId())));
        }else{
            modelAndView.addObject("fieldsJSON", JSON.toJSONString(mdMarketCompareService.getSetUseFieldList(BaseConstant.ASSESS_DATA_SET_USE_FIELD_HOUSE,null,null)));
        }
        MdMarketCompareItem evaluationObject = mdMarketCompareService.getEvaluationByMcId(marketCompare.getId());
        List<BasicApply> standardJudgeList = mdMarketCompareService.getStandardJudgeList(judgeObject);
        modelAndView.addObject("standardJudgesJSON", JSON.toJSONString(CollectionUtils.isEmpty(standardJudgeList) ? Lists.newArrayList() : standardJudgeList));
        modelAndView.addObject("marketCompareJSON", JSON.toJSONString(marketCompare));

        modelAndView.addObject("evaluationJSON", JSON.toJSONString(evaluationObject));
        modelAndView.addObject("casesJSON", JSON.toJSONString(mdMarketCompareService.getCaseListByMcId(marketCompare.getId())));
        modelAndView.addObject("mcId", marketCompare.getId());
        modelAndView.addObject("judgeObject", judgeObject);
        modelAndView.addObject("isLand", isLand);
        return modelAndView;
    }

    @RequestMapping(value = "/detail", name = "市场比较法显示")
    public ModelAndView detail() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/method/marketCompareDetail");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/saveResult", name = "保存市场比较法结果", method = RequestMethod.POST)
    public HttpResult saveResult(String formData) {
        try {
            MarketCompareResultDto marketCompareResultDto = JSON.parseObject(formData, MarketCompareResultDto.class);
            MdMarketCompare marketCompare = mdMarketCompareService.saveResult(marketCompareResultDto);
            return HttpResult.newCorrectResult(marketCompare);
        } catch (Exception e) {
            return HttpResult.newErrorResult("保存失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getCasesAll", name = "获取所有案例", method = RequestMethod.GET)
    public BootstrapTableVo getCasesAll(Integer projectId, String projectPhaseName) {
        BootstrapTableVo casesAll = mdMarketCompareService.getCasesAll(projectId, projectPhaseName);
        return casesAll;
    }

    @ResponseBody
    @RequestMapping(value = "/selectCase", name = "选择案例", method = RequestMethod.POST)
    public HttpResult selectCase(Integer mcId, String planDetailsIdList, Integer judgeObjectId, Boolean isLand, String jsonData) {
        try {
            MdCompareInitParamVo mdCompareInitParamVo = mdMarketCompareService.selectCase(mcId, planDetailsIdList, judgeObjectId, isLand, jsonData);
            return HttpResult.newCorrectResult(mdCompareInitParamVo);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return HttpResult.newErrorResult("保存失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/selectJudge", name = "选择估价对象", method = RequestMethod.POST)
    public HttpResult selectJudge(Integer mcId, Integer applyId, Integer judgeObjectId, Boolean isLand) {
        try {
            MdCompareInitParamVo mdCompareInitParamVo = mdMarketCompareService.selectJudge(judgeObjectId, applyId, mcId, isLand);
            return HttpResult.newCorrectResult(mdCompareInitParamVo);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return HttpResult.newErrorResult("保存失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getMarketCompareById", name = "获取数据", method = RequestMethod.POST)
    public HttpResult getMarketCompareById(Integer id) {
        try {
            return HttpResult.newCorrectResult(mdMarketCompareService.getMdMarketCompare(id));
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return HttpResult.newErrorResult("获取失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getMarketCompareItemById", name = "获取明细项数据", method = RequestMethod.POST)
    public HttpResult getMarketCompareItemById(Integer id) {
        try {
            return HttpResult.newCorrectResult(mdMarketCompareService.getMarketCompareItemById(id));
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return HttpResult.newErrorResult("获取失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveMarketCompareItem", name = "保存明细项数据", method = RequestMethod.POST)
    public HttpResult saveMarketCompareItem(MdMarketCompareItem mdMarketCompareItem) {
        try {
            mdMarketCompareService.saveMarketCompareItem(mdMarketCompareItem);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return HttpResult.newErrorResult("保存失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/updateAnnualCoefficient", name = "更新年期修正系数", method = RequestMethod.POST)
    public HttpResult updateAnnualCoefficient(Integer judgeObjectId, Integer mcId, Integer rewardRateId, BigDecimal rewardRate) {
        try {
            return HttpResult.newCorrectResult(mdMarketCompareService.updateAnnualCoefficient(judgeObjectId, mcId, rewardRateId, rewardRate));
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return HttpResult.newErrorResult("更新失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/refreshData", name = "刷新", method = RequestMethod.POST)
    public HttpResult refreshData(Integer mcId, Integer judgeObjectId, Boolean isLand) {
        try {
            MdCompareInitParamVo mdCompareInitParamVo = mdMarketCompareService.refreshData(mcId, judgeObjectId, isLand);
            return HttpResult.newCorrectResult(mdCompareInitParamVo);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return HttpResult.newErrorResult("保存失败");
        }
    }
}
