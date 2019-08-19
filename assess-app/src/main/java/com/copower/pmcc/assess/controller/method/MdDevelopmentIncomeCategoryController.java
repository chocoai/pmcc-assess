package com.copower.pmcc.assess.controller.method;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentIncomeCategory;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.method.MdDevelopmentIncomeCategoryService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zch on 2019/8/15.
 * 假设开发法 收入类
 */
@RequestMapping(value = "/mdDevelopmentIncomeCategory")
@RestController
public class MdDevelopmentIncomeCategoryController {
    @Autowired
    private MdDevelopmentIncomeCategoryService service;
    @Autowired
    private BaseService baseService;

    @PostMapping(value = "/saveMdDevelopmentIncomeCategory")
    public HttpResult save(String fomData) {
        try {
            MdDevelopmentIncomeCategory oo = JSONObject.parseObject(fomData, MdDevelopmentIncomeCategory.class);
            service.saveMdDevelopmentIncomeCategory(oo);
            return HttpResult.newCorrectResult(200, oo);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @RequestMapping(value = "/getBootstrapTableVo", name = "获取", method = RequestMethod.GET)
    public BootstrapTableVo getBootstrapTableVo(MdDevelopmentIncomeCategory oo) {
        BootstrapTableVo vo = service.getBootstrapTableVo(oo);
        return vo;
    }

    @PostMapping(value = "/deleteMdDevelopmentIncomeCategory")
    public HttpResult delete(String ids){
        try {
            List<Integer> integerList = FormatUtils.transformString2Integer(ids);
            if (CollectionUtils.isNotEmpty(integerList)){
                for (Integer id:integerList){
                    service.deleteMdDevelopmentIncomeCategoryById(id) ;
                }
            }
            return HttpResult.newCorrectResult(200, "success");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }
}
