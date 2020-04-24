package com.copower.pmcc.assess.controller.method;

import com.copower.pmcc.assess.dal.basis.entity.SchemeInfo;
import com.copower.pmcc.assess.service.project.scheme.SchemeInfoService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zch on 2019-11-11.
 */
@RestController
@RequestMapping(value = "/schemeInfo",name = "方案 方法")
public class SchemeInfoController {

    @Autowired
    private SchemeInfoService schemeInfoService;

    @GetMapping(value = "/getBootstrapTableVo")
    public BootstrapTableVo getBootstrapTableVo(Integer methodType,Integer methodDataId,Integer projectId){
        return schemeInfoService.getBootstrapTableVo(methodType, methodDataId, projectId) ;
    }


    @GetMapping(value = "/getSchemeIncomeVo")
    public BootstrapTableVo getSchemeIncomeVo(Integer methodType,Integer methodDataId,Integer projectId){
        return schemeInfoService.getSchemeIncomeVo(methodType, methodDataId, projectId) ;
    }
}
