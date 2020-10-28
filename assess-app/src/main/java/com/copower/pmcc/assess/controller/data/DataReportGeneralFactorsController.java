package com.copower.pmcc.assess.controller.data;


import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.DataReportGeneralFactors;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.data.DataReportGeneralFactorsService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(value = "/dataReportGeneralFactors")
public class DataReportGeneralFactorsController {
    private final String errorName = "报告一般因素配置" ;
    @Autowired
    private DataReportGeneralFactorsService reportGeneralFactorsService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @RequestMapping(value = "/dataReportGeneralFactorsView")
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/data/dataReportGeneralFactorsView");
        return modelAndView;
    }

    @GetMapping(value = "/getBootstrapTableVo")
    public BootstrapTableVo getBootstrapTableVo( String type,String province, String city, String district, String name, String fieldName, Boolean bisEnable, Integer pid){
        return reportGeneralFactorsService.getBootstrapTableVo( type,province, city, district, name, fieldName, bisEnable, pid);
    }

    @PostMapping(value = "/saveDataReportGeneralFactors")
    public HttpResult saveDataReportGeneralFactors(String formData) {
        try {
            DataReportGeneralFactors DataReportGeneralFactors = JSONObject.parseObject(formData, DataReportGeneralFactors.class);
            reportGeneralFactorsService.saveAndUpdateDataReportGeneralFactors(DataReportGeneralFactors);
            return HttpResult.newCorrectResult(200, DataReportGeneralFactors);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,errorName);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }


    @GetMapping(value = "/getDataReportGeneralFactorsById")
    public HttpResult getDataReportGeneralFactors(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, reportGeneralFactorsService.getDataReportGeneralFactorsById(id));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,errorName);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @DeleteMapping(value = "/deleteDataReportGeneralFactorsById/{id}")
    public HttpResult deleteDataReportGeneralFactorsById(@PathVariable String id) {
        try {
            reportGeneralFactorsService.deleteByIds(FormatUtils.transformString2Integer(id)) ;
            return HttpResult.newCorrectResult(200,"success");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,errorName);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/getDataReportGeneralFactorsList")
    public HttpResult getDataReportGeneralFactorsList(DataReportGeneralFactors DataReportGeneralFactors) {
        try {
            List<DataReportGeneralFactors> objs = reportGeneralFactorsService.getDataReportGeneralFactorsList(DataReportGeneralFactors);
            return HttpResult.newCorrectResult(200, objs);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,errorName);
            return HttpResult.newErrorResult(500, e);
        }
    }


    @GetMapping("/getBootstrapTableVoByPid")
    public BootstrapTableVo getBootstrapTableVoByPid(Integer pid) {
        return reportGeneralFactorsService.getDataReportGeneralFactorsByPid(pid);
    }

    @PostMapping(value = "/getDataReportGeneralFactorsLevel", name = "获取层级")
    public HttpResult getReportFieldLevel(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, reportGeneralFactorsService.getDataAssetsAppraisalDicLevel(id));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }


    
}
