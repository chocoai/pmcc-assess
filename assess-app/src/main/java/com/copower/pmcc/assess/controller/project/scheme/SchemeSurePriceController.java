package com.copower.pmcc.assess.controller.project.scheme;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePriceFactor;
import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePriceItem;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeSurePriceApplyDto;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeJudgeObjectVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.scheme.SchemeSurePriceFactorService;
import com.copower.pmcc.assess.service.project.scheme.SchemeSurePriceService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by kings on 2018-10-15.
 */
@RestController
@RequestMapping("/schemeSurePrice")
public class SchemeSurePriceController {
    @Autowired
    private SchemeSurePriceService schemeSurePriceService;
    @Autowired
    private SchemeSurePriceFactorService schemeSurePriceFactorService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private BaseService baseService;
    private final String errorInfo = "确定单价异常";

    @GetMapping(value = "/getSchemeSurePriceItemList", name = "获取确定单价明细数据信息")
    public HttpResult getSchemeSurePriceItemList(Integer judgeObjectId, boolean isUpdatePrice) {
        try {
            List<SchemeSurePriceItem> schemeSurePriceList = schemeSurePriceService.getSchemeSurePriceItemList(judgeObjectId, isUpdatePrice);
            return HttpResult.newCorrectResult(200,schemeSurePriceList);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, errorInfo);
            return HttpResult.newErrorResult(500, "获取数据异常");
        }
    }


    @GetMapping(value = "/getAdjustObjectListByPid", name = "获取待调整价格的估价对象")
    public HttpResult getAdjustObjectListByPid(Integer judgeObjectId) {
        try {
            List<SchemeJudgeObjectVo> vos = schemeJudgeObjectService.getAdjustObjectListByPid(judgeObjectId);
            return HttpResult.newCorrectResult(200,vos);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, errorInfo);
            return HttpResult.newErrorResult(500, "获取数据异常");
        }
    }


    @GetMapping(value = "/getSurePriceFactors", name = "获取调整单价系数")
    public HttpResult getSurePriceFactors(Integer judgeObjectId) {
        try {
            List<SchemeSurePriceFactor> certAdjustmentFactors = schemeSurePriceFactorService.getSurePriceFactors(judgeObjectId);
            return HttpResult.newCorrectResult(certAdjustmentFactors);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, errorInfo);
            return HttpResult.newErrorResult("获取数据异常");
        }
    }


    @PostMapping(value = "/saveSurePriceFactor", name = "保存调整系数及价格")
    public HttpResult saveSurePriceFactor(Integer judgeObjectId, BigDecimal price, String formData) {
        try {
            List<SchemeSurePriceFactor> factorList = JSON.parseArray(formData, SchemeSurePriceFactor.class);
            return HttpResult.newCorrectResult(schemeSurePriceFactorService.saveSurePriceFactor(judgeObjectId, price, factorList));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, errorInfo);
            return HttpResult.newErrorResult("获取数据异常");
        }
    }

    @PostMapping(value = "/copySurePriceFactor", name = "复制调整系数")
    public HttpResult copySurePriceFactor(Integer beCopyJudgeObjectId, Integer judgeObjectId) {
        try {
            schemeSurePriceFactorService.copySurePriceFactor(beCopyJudgeObjectId, Lists.newArrayList(judgeObjectId));
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, errorInfo);
            return HttpResult.newErrorResult("复制调整系数异常");
        }
    }

    @PostMapping(value = "/copySurePriceFactorBatch", name = "批量复制调整系数")
    public HttpResult copySurePriceFactorBatch(Integer beCopyJudgeObjectId, String judgeObjectIds) {
        try {
            List<Integer> integers = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(judgeObjectIds));
            schemeSurePriceFactorService.copySurePriceFactor(beCopyJudgeObjectId, integers);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, errorInfo);
            return HttpResult.newErrorResult("批量复制调整系数异常");
        }
    }

    @PostMapping(value = "/updateCalculationSchemeSurePrice", name = "更新计算的估价对象单价")
    public HttpResult updateCalculationSchemeSurePrice(String fomData, Integer planDetailsId) {
        try {
            schemeSurePriceService.submitSurePrice(JSONObject.parseObject(fomData, SchemeSurePriceApplyDto.class),
                    projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId), null);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, errorInfo);
            return HttpResult.newErrorResult("更新计算的估价对象单价异常");
        }
    }
}
