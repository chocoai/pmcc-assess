package com.copower.pmcc.assess.controller.project.declare;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecordGroup;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordGroupService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zch on 2019-10-14.
 * 资产申报 分组
 */
@RequestMapping(value = "/declareRecordGroup")
@RestController
public class DeclareRecordGroupController {
    private final String errorName = "资产申报 分组" ;

    @Autowired
    private DeclareRecordGroupService declareRecordGroupService;
    @Autowired
    private BaseService baseService;
   
    @GetMapping(value = "/getBootstrapTableVo")
    public BootstrapTableVo getBootstrapTableVo(DeclareRecordGroup oo){
        return declareRecordGroupService.getBootstrapTableVo(oo);
    }

    @PostMapping(value = "/saveDeclareRecordGroup")
    public HttpResult saveDeclareRecordGroup(String formData) {
        try {
            DeclareRecordGroup declareRecordGroup = JSONObject.parseObject(formData, DeclareRecordGroup.class);
            declareRecordGroupService.saveDeclareRecordGroup(declareRecordGroup);
            return HttpResult.newCorrectResult(200, declareRecordGroup);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,errorName);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }


    @GetMapping(value = "/getDeclareRecordGroupById")
    public HttpResult getDeclareRecordGroup(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, declareRecordGroupService.getDeclareRecordGroupById(id));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,errorName);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @DeleteMapping(value = "/deleteDeclareRecordGroupById/{id}")
    public HttpResult deleteDeclareRecordGroupById(@PathVariable String id) {
        try {
            declareRecordGroupService.deleteDeclareRecordGroup(id) ;
            return HttpResult.newCorrectResult(200);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,errorName);
            return HttpResult.newErrorResult(500, e);
        }
    }

    private void settingParams(ModelAndView modelAndView){

    }

}
