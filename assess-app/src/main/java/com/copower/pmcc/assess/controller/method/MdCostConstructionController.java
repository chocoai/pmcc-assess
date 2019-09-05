package com.copower.pmcc.assess.controller.method;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.MdCostConstruction;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.method.MdMarketCostService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zch on 2019-9-5.
 * 成本法
 */
@RestController
@RequestMapping(value = "/mdCostConstruction")
public class MdCostConstructionController {

    @Autowired
    private BaseService baseService;
    @Autowired
    private MdMarketCostService mdMarketCostService;

    @PostMapping(value = "/calculationNumeric", name = "成本法后台自动计算")
    public HttpResult calculationNumeric(String fomData) {
        try {
            MdCostConstruction target = JSONObject.parseObject(fomData, MdCostConstruction.class);
            mdMarketCostService.calculationNumeric(target);
            return HttpResult.newCorrectResult(200, target);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(200, e);
        }
    }

}
