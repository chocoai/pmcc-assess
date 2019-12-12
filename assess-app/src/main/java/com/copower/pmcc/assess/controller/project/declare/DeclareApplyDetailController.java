package com.copower.pmcc.assess.controller.project.declare;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.DeclareApplyDetail;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.project.declare.DeclareApplyDetailService;
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
public class DeclareApplyDetailController {
    private static final String STRING = "申报扩展自定义字段" ;
    @Autowired
    private BaseService baseService;
    @Autowired
    private DeclareApplyDetailService declareApplyExtensionService;

    @PostMapping(value = "/saveAndUpdateDeclareApplyExtensionAll",name = "save")
    public HttpResult saveAndUpdateDeclareApplyExtensionAll(String fomData,boolean updateNull){
        try {
            List<DeclareApplyDetail> declareApplyExtensionList = JSONObject.parseArray(fomData,DeclareApplyDetail.class) ;
            if (CollectionUtils.isNotEmpty(declareApplyExtensionList)){
                Iterator<DeclareApplyDetail> iterator = declareApplyExtensionList.iterator();
                while (iterator.hasNext()){
                    declareApplyExtensionService.saveAndUpdateDeclareApplyDetail(iterator.next(),updateNull);
                }
            }
            return HttpResult.newCorrectResult(200,declareApplyExtensionList) ;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,String.join("", STRING,e.getMessage()));
            return HttpResult.newErrorResult(500,e) ;
        }
    }

    @PostMapping(value = "/deleteDeclareApplyExtensionById",name = "delete")
    public HttpResult deleteDeclareApplyDetailById(String id){
        try {
            declareApplyExtensionService.deleteDeclareApplyDetailById(id);
            return HttpResult.newCorrectResult(200,"success") ;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,String.join("", STRING,e.getMessage()));
            return HttpResult.newErrorResult(500,e) ;
        }
    }


    @GetMapping(value = "/getDeclareApplyExtensionById",name = "get")
    public HttpResult getDeclareApplyDetailById(String id){
        try {
            List<Integer> ids = FormatUtils.transformString2Integer(id);
            if (ids.size() == 1){
                return HttpResult.newCorrectResult(200,declareApplyExtensionService.getDeclareApplyDetailById(ids.get(0))) ;
            }else {
                return HttpResult.newCorrectResult(200,declareApplyExtensionService.getDeclareApplyDetailByIds(ids)) ;
            }
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,String.join("", STRING,e.getMessage()));
            return HttpResult.newErrorResult(500,e) ;
        }
    }

    @GetMapping(value = "/getDeclareApplyExtensionListByExample",name = "get list")
    public HttpResult getDeclareApplyDetailListByExample(DeclareApplyDetail oo){
        try {
            return HttpResult.newCorrectResult(200,declareApplyExtensionService.getDeclareApplyDetailListByExample(oo)) ;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,String.join("", STRING,e.getMessage()));
            return HttpResult.newErrorResult(500,e) ;
        }
    }

    @GetMapping(value = "/getBootstrapTableVo",name = "get Pagination list")
    public BootstrapTableVo getBootstrapTableVo(DeclareApplyDetail oo){
        return declareApplyExtensionService.getBootstrapTableVo(oo) ;
    }

}
