package com.copower.pmcc.assess.controller.project.scheme;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePriceFactor;
import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePriceItem;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeSurePriceApplyDto;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.scheme.SchemeSurePriceFactorService;
import com.copower.pmcc.assess.service.project.scheme.SchemeSurePriceService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by kings on 2018-10-15.
 */
@Controller
@RequestMapping("/schemeSurePrice")
public class SchemeSurePriceController {
    @Autowired
    private SchemeSurePriceService schemeSurePriceService;
    @Autowired
    private SchemeSurePriceFactorService schemeSurePriceFactorService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;

    @GetMapping(value = "/getSchemeSurePriceItemList", name = "获取确定单价明细数据信息")
    @ResponseBody
    public HttpResult getSchemeSurePriceItemList(Integer judgeObjectId,Boolean isUpdatePrice) {
        try {
            List<SchemeSurePriceItem> schemeSurePriceList = schemeSurePriceService.getSchemeSurePriceItemList(judgeObjectId, isUpdatePrice);
            return HttpResult.newCorrectResult(schemeSurePriceList);
        } catch (BusinessException ex) {
            return HttpResult.newErrorResult(ex.getMessage());
        } catch (Exception e) {
            return HttpResult.newErrorResult("获取数据异常");
        }
    }


    @GetMapping(value = "/getSurePriceFactors", name = "获取调整单价系数")
    @ResponseBody
    public HttpResult getSurePriceFactors(Integer declareId) {
        try {
            List<SchemeSurePriceFactor> certAdjustmentFactors = schemeSurePriceFactorService.getSurePriceFactors(declareId);
            return HttpResult.newCorrectResult(certAdjustmentFactors);
        } catch (Exception e) {
            return HttpResult.newErrorResult("获取数据异常");
        }
    }


    @PostMapping(value = "/saveSurePriceFactor", name = "保存调整系数及价格")
    @ResponseBody
    public HttpResult saveSurePriceFactor(Integer judgeObjectId, BigDecimal price, String formData) {
        try {
            List<SchemeSurePriceFactor> factorList = JSON.parseArray(formData, SchemeSurePriceFactor.class);
            return HttpResult.newCorrectResult(schemeSurePriceFactorService.saveSurePriceFactor(judgeObjectId, price, factorList));
        } catch (Exception e) {
            return HttpResult.newErrorResult("获取数据异常");
        }
    }

    @PostMapping(value = "/copySurePriceFactor", name = "复制调整系数")
    @ResponseBody
    public HttpResult copySurePriceFactor(Integer beCopyJudgeObjectId, Integer judgeObjectId) {
        try {
            schemeSurePriceFactorService.copySurePriceFactor(beCopyJudgeObjectId, Lists.newArrayList(judgeObjectId));
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            return HttpResult.newErrorResult("复制调整系数异常");
        }
    }

    @PostMapping(value = "/copySurePriceFactorBatch", name = "批量复制调整系数")
    @ResponseBody
    public HttpResult copySurePriceFactorBatch(Integer beCopyJudgeObjectId, String judgeObjectIds) {
        try {
            List<Integer> integers = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(judgeObjectIds));
            schemeSurePriceFactorService.copySurePriceFactor(beCopyJudgeObjectId, integers);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            return HttpResult.newErrorResult("批量复制调整系数异常");
        }
    }

    @PostMapping(value = "/updateCalculationSchemeSurePrice", name = "更新计算的估价对象单价")
    @ResponseBody
    public HttpResult updateCalculationSchemeSurePrice(String fomData,Integer planDetailsId) {
        try {
            schemeSurePriceService.submitSurePrice(JSONObject.parseObject(fomData,SchemeSurePriceApplyDto.class),
                    projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId),null);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            return HttpResult.newErrorResult("更新计算的估价对象单价异常");
        }
    }
}
