package com.copower.pmcc.assess.controller.project.declare;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.DeclareApplyExtension;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.project.declare.DeclareApplyExtensionService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

/**
 * Created by zch on 2019-12-10.
 * 申报扩展自定义字段
 */
@RestController
@RequestMapping(value = "/declareApplyExtension")
public class DeclareApplyExtensionController {
    private static final String STRING = "申报扩展自定义字段" ;
    @Autowired
    private BaseService baseService;
    @Autowired
    private DeclareApplyExtensionService declareApplyExtensionService;

    @PostMapping(value = "/saveAndUpdateDeclareApplyExtensionAll",name = "save")
    public HttpResult saveAndUpdateDeclareApplyExtensionAll(String fomData,boolean updateNull){
        try {
            List<DeclareApplyExtension> declareApplyExtensionList = JSONObject.parseArray(fomData,DeclareApplyExtension.class) ;
            if (CollectionUtils.isNotEmpty(declareApplyExtensionList)){
                Iterator<DeclareApplyExtension> iterator = declareApplyExtensionList.iterator();
                while (iterator.hasNext()){
                    declareApplyExtensionService.saveAndUpdateDeclareApplyExtension(iterator.next(),updateNull);
                }
            }
            return HttpResult.newCorrectResult(200,declareApplyExtensionList) ;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,String.join("", STRING,e.getMessage()));
            return HttpResult.newErrorResult(500,e) ;
        }
    }

    @PostMapping(value = "/deleteDeclareApplyExtensionById",name = "delete")
    public HttpResult deleteDeclareApplyExtensionById(String id){
        try {
            declareApplyExtensionService.deleteDeclareApplyExtensionById(id);
            return HttpResult.newCorrectResult(200,"success") ;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,String.join("", STRING,e.getMessage()));
            return HttpResult.newErrorResult(500,e) ;
        }
    }


    @GetMapping(value = "/getDeclareApplyExtensionById",name = "get")
    public HttpResult getDeclareApplyExtensionById(String id){
        try {
            List<Integer> ids = FormatUtils.transformString2Integer(id);
            if (ids.size() == 1){
                return HttpResult.newCorrectResult(200,declareApplyExtensionService.getDeclareApplyExtensionById(ids.get(0))) ;
            }else {
                return HttpResult.newCorrectResult(200,declareApplyExtensionService.getDeclareApplyExtensionByIds(ids)) ;
            }
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,String.join("", STRING,e.getMessage()));
            return HttpResult.newErrorResult(500,e) ;
        }
    }

    @GetMapping(value = "/getDeclareApplyExtensionListByExample",name = "get list")
    public HttpResult getDeclareApplyExtensionListByExample(DeclareApplyExtension oo){
        try {
            return HttpResult.newCorrectResult(200,declareApplyExtensionService.getDeclareApplyExtensionListByExample(oo)) ;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,String.join("", STRING,e.getMessage()));
            return HttpResult.newErrorResult(500,e) ;
        }
    }

    @GetMapping(value = "/getBootstrapTableVo",name = "get Pagination list")
    public BootstrapTableVo getBootstrapTableVo(DeclareApplyExtension oo){
        return declareApplyExtensionService.getBootstrapTableVo(oo) ;
    }

}
