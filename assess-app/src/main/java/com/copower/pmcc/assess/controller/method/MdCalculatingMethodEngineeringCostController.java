package com.copower.pmcc.assess.controller.method;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.MdCalculatingMethodEngineeringCost;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.method.MdCalculatingMethodEngineeringCostService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zch on 2019-8-28.
 */
@RequestMapping(value = "/mdCalculatingMethodEngineeringCost")
@RestController(value = "测算方法 工程费")
public class MdCalculatingMethodEngineeringCostController {

    @Autowired
    private MdCalculatingMethodEngineeringCostService mdCalculatingMethodEngineeringCostService;
    @Autowired
    private BaseService baseService;

    @GetMapping(value = "/getBootstrapTableVo")
    public BootstrapTableVo getBootstrapTableVo(MdCalculatingMethodEngineeringCost oo){
        return mdCalculatingMethodEngineeringCostService.getBootstrapTableVo(oo);
    }

    @PostMapping(value = "/saveMdCalculatingMethodEngineeringCost")
    public HttpResult saveMdCalculatingMethodEngineeringCost(String formData) {
        try {
            MdCalculatingMethodEngineeringCost mdCalculatingMethodEngineeringCost = JSONObject.parseObject(formData, MdCalculatingMethodEngineeringCost.class);
            mdCalculatingMethodEngineeringCostService.saveMdCalculatingMethodEngineeringCost(mdCalculatingMethodEngineeringCost);
            return HttpResult.newCorrectResult(200, mdCalculatingMethodEngineeringCost);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }


    @GetMapping(value = "/getMdCalculatingMethodEngineeringCostById")
    public HttpResult getMdCalculatingMethodEngineeringCost(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, mdCalculatingMethodEngineeringCostService.getMdCalculatingMethodEngineeringCostById(id));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @PostMapping(value = "/deleteMdCalculatingMethodEngineeringCostById")
    public HttpResult deleteMdCalculatingMethodEngineeringCostById(String id) {
        try {
            for (String s : id.split(",")) {
                mdCalculatingMethodEngineeringCostService.deleteMdCalculatingMethodEngineeringCostById(Integer.parseInt(s)) ;
            }
            return HttpResult.newCorrectResult(200);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/getMdCalculatingMethodEngineeringCostList")
    public HttpResult getMdCalculatingMethodEngineeringCostList(MdCalculatingMethodEngineeringCost mdCalculatingMethodEngineeringCost) {
        try {
            List<MdCalculatingMethodEngineeringCost> objs = mdCalculatingMethodEngineeringCostService.getMdCalculatingMethodEngineeringCostListByExample(mdCalculatingMethodEngineeringCost);
            return HttpResult.newCorrectResult(200, objs);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

}
