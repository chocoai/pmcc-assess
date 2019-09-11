package com.copower.pmcc.assess.controller.method;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.MdDevelopment;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.method.MdCalculatingMethodEngineeringCostService;
import com.copower.pmcc.assess.service.method.MdDevelopmentService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zch on 2019-9-6.
 * 假设开发法
 */
@RestController
@RequestMapping(value = "/mdDevelopment")
public class MdDevelopmentController {

    @Autowired
    private BaseService baseService;
    @Autowired
    private MdDevelopmentService mdDevelopmentService;
    @Autowired
    private MdCalculatingMethodEngineeringCostService mdCalculatingMethodEngineeringCostService;

    @PostMapping(value = "/calculationNumeric", name = "假设开发法后台自动计算")
    public HttpResult calculationNumeric(String fomData) {
        try {
            MdDevelopment target = JSONObject.parseObject(fomData,MdDevelopment.class) ;
            mdDevelopmentService.calculationNumeric(target) ;
            return HttpResult.newCorrectResult(200, target);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            String error = e.getMessage();
            e.printStackTrace();
            return HttpResult.newErrorResult("请检查输入的数据");
        }
    }

    @PostMapping(value = "/setMdCalculatingMethodEngineeringCost", name = "设置工程费")
    public HttpResult setMdCalculatingMethodEngineeringCost(Integer planDetailsId){
        try {
            mdCalculatingMethodEngineeringCostService.setMdCalculatingMethodEngineeringCost(planDetailsId, FormatUtils.entityNameConvertToTableName(MdDevelopment.class));
            return HttpResult.newCorrectResult("success");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("设置工程费 error");
        }
    }

}
