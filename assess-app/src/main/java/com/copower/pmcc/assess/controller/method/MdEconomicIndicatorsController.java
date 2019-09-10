package com.copower.pmcc.assess.controller.method;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dto.input.method.MdEconomicIndicatorsApplyDto;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.method.MdEconomicIndicatorsService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangpc on 2019-9-6.
 */
@RestController
@RequestMapping(value = "/mdEconomicIndicators")
public class MdEconomicIndicatorsController {
    @Autowired
    private BaseService baseService;
    @Autowired
    private MdEconomicIndicatorsService mdEconomicIndicatorsService;
    @PostMapping(value = "/save")
    public HttpResult save(String formData) {
        try {
            MdEconomicIndicatorsApplyDto mdEconomicIndicatorsApplyDto = JSONObject.parseObject(formData, MdEconomicIndicatorsApplyDto.class);
            return HttpResult.newCorrectResult(mdEconomicIndicatorsService.saveEconomicIndicators(mdEconomicIndicatorsApplyDto));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("保存经济指标异常");
        }
    }

    @PostMapping(value = "/deleteById",name = "删除数据")
    public HttpResult deleteById(Integer id){
        try {
            mdEconomicIndicatorsService.deleteById(id);
            return HttpResult.newCorrectResult() ;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500,"删除经济指标异常");
        }
    }

    @PostMapping(value = "/getEconomicIndicatorsInfo")
    public HttpResult getEconomicIndicatorsInfo(Integer economicId) {
        try {
            return HttpResult.newCorrectResult(mdEconomicIndicatorsService.getEconomicIndicatorsInfo(economicId));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("获取经济指标异常");
        }
    }
}
