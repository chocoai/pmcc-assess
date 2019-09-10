package com.copower.pmcc.assess.controller.method;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.MdDevelopment;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.method.MdDevelopmentService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zch on 2019-9-6.
 */
@RestController
@RequestMapping(value = "/mdDevelopment")
public class MdDevelopmentController {

    @Autowired
    private BaseService baseService;
    @Autowired
    private MdDevelopmentService mdDevelopmentService;

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

}
