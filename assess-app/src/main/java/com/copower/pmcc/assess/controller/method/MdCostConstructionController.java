package com.copower.pmcc.assess.controller.method;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.entity.MdCostConstruction;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.method.MdCalculatingMethodEngineeringCostService;
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
    @Autowired
    private MdCalculatingMethodEngineeringCostService mdCalculatingMethodEngineeringCostService;

    @PostMapping(value = "/calculationNumeric", name = "成本法后台自动计算")
    public HttpResult calculationNumeric(String fomData) {
        try {
            MdCostConstruction target = JSONObject.parseObject(fomData, MdCostConstruction.class);
            mdMarketCostService.calculationNumeric(target);
            return HttpResult.newCorrectResult(200, target);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("请检查输入的数据");
        }
    }

    @PostMapping(value = "/setMdCalculatingMethodEngineeringCost", name = "设置工程费")
    public HttpResult setMdCalculatingMethodEngineeringCost(Integer planDetailsId, String type, boolean flag) {
        try {
            if (flag) {
                mdCalculatingMethodEngineeringCostService.setMdCalculatingMethodEngineeringCost(planDetailsId, type);
            }else {
                mdCalculatingMethodEngineeringCostService.setMethodTargetData(AssessReportFieldConstant.DEVELOPMENT,planDetailsId,type);
            }
            return HttpResult.newCorrectResult("success");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("设置工程费 error");
        }
    }

    @PostMapping(value = "/copyConstructionById", name = "拷贝数据")
    public HttpResult copyConstructionById(Integer copyId, Integer masterId) {
        StringBuilder stringBuilder = new StringBuilder(8);
        try {
            mdMarketCostService.copyConstructionById(copyId, masterId, stringBuilder);
            return HttpResult.newCorrectResult(stringBuilder.toString());
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "拷贝数据 error");
            return HttpResult.newErrorResult(stringBuilder.toString());
        }
    }


}
