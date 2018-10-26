package com.copower.pmcc.assess.controller.project.scheme;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.SchemeCertAdjustmentFactor;
import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePriceItem;
import com.copower.pmcc.assess.service.project.scheme.SchemeCertAdjustmentFactorService;
import com.copower.pmcc.assess.service.project.scheme.SchemeSurePriceService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private SchemeCertAdjustmentFactorService schemeCertAdjustmentFactorService;

    @GetMapping(value = "/getSchemeSurePriceItemList", name = "获取确定单价明细数据信息")
    @ResponseBody
    public HttpResult getSchemeSurePriceItemList(Integer judgeObjectId) {
        try {
            List<SchemeSurePriceItem> schemeSurePriceList = schemeSurePriceService.getSchemeSurePriceItemList(judgeObjectId,true);
            return HttpResult.newCorrectResult(schemeSurePriceList);
        } catch (Exception e) {
            return HttpResult.newErrorResult("获取数据异常");
        }
    }


    @GetMapping(value = "/getCertAdjustmentFactors", name = "获取调整单价系数")
    @ResponseBody
    public HttpResult getCertAdjustmentFactors(Integer declareId) {
        try {
            List<SchemeCertAdjustmentFactor> certAdjustmentFactors = schemeCertAdjustmentFactorService.getCertAdjustmentFactors(declareId);
            return HttpResult.newCorrectResult(certAdjustmentFactors);
        } catch (Exception e) {
            return HttpResult.newErrorResult("获取数据异常");
        }
    }


    @PostMapping(value = "/saveCertAdjustmentFactor", name = "保存调整系数及价格")
    @ResponseBody
    public HttpResult saveCertAdjustmentFactor(Integer judgeObjectId, String formData) {
        try {
            List<SchemeCertAdjustmentFactor> factorList = JSON.parseArray(formData, SchemeCertAdjustmentFactor.class);
            schemeCertAdjustmentFactorService.saveCertAdjustmentFactor(judgeObjectId, factorList);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            return HttpResult.newErrorResult("获取数据异常");
        }
    }
}
