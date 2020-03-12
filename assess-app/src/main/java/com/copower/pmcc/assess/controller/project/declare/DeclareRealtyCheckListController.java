package com.copower.pmcc.assess.controller.project.declare;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyCheckList;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyCheckListService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zch on 2020-3-12.
 * 不动产清单
 */
@RequestMapping(value = "/declareRealtyCheckList")
@RestController
public class DeclareRealtyCheckListController {

    private static final String STRING = "不动产清单" ;
    @Autowired
    private BaseService baseService;
    @Autowired
    private DeclareRealtyCheckListService declareRealtyCheckListService;

    @PostMapping(value = "/saveAndUpdateDeclareRealtyCheckList")
    public HttpResult saveAndUpdateDeclareRealtyCheckList(String formData,@RequestParam(required = false,defaultValue = "false") boolean updateNull){
        try {
            DeclareRealtyCheckList oo = JSONObject.parseObject(formData,DeclareRealtyCheckList.class) ;
            declareRealtyCheckListService.saveAndUpdateDeclareRealtyCheckList(oo,updateNull);
            return HttpResult.newCorrectResult(200,oo) ;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,String.join("", STRING,e.getMessage()));
            return HttpResult.newErrorResult(500,e) ;
        }
    }



    @PostMapping(value = "/deleteDeclareRealtyCheckListById",name = "delete")
    public HttpResult deleteDeclareRealtyCheckListById(String id){
        try {
            declareRealtyCheckListService.deleteDeclareRealtyCheckListById(id);
            return HttpResult.newCorrectResult(200,"success") ;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,String.join("", STRING,e.getMessage()));
            return HttpResult.newErrorResult(500,e) ;
        }
    }


    @GetMapping(value = "/getDeclareRealtyCheckListById",name = "get")
    public HttpResult getDeclareRealtyCheckListById(String id){
        try {
            List<Integer> ids = FormatUtils.transformString2Integer(id);
            if (ids.size() == 1){
                return HttpResult.newCorrectResult(200, declareRealtyCheckListService.getDeclareRealtyCheckListById(ids.get(0))) ;
            }else {
                return HttpResult.newCorrectResult(200, declareRealtyCheckListService.getDeclareRealtyCheckListByIds(ids)) ;
            }
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,String.join("", STRING,e.getMessage()));
            return HttpResult.newErrorResult(500,e) ;
        }
    }

    @GetMapping(value = "/getDeclareRealtyCheckListListByExample",name = "get list")
    public HttpResult getDeclareRealtyCheckListListByExample(DeclareRealtyCheckList oo){
        try {
            return HttpResult.newCorrectResult(200, declareRealtyCheckListService.getDeclareRealtyCheckListListByExample(oo)) ;
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,String.join("", STRING,e.getMessage()));
            return HttpResult.newErrorResult(500,e) ;
        }
    }

    @GetMapping(value = "/getBootstrapTableVo",name = "get Pagination list")
    public BootstrapTableVo<DeclareRealtyCheckList> getBootstrapTableVo(DeclareRealtyCheckList oo){
        return declareRealtyCheckListService.getBootstrapTableVo(oo) ;
    }

    @PostMapping(value = "/importData")
    public HttpResult importData(DeclareRealtyCheckList oo,HttpServletRequest request){
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
            if (multipartFile.isEmpty()) {
                return HttpResult.newErrorResult(500,"上传的文件不能为空");
            }
            return HttpResult.newCorrectResult(200,declareRealtyCheckListService.importData(oo,multipartFile));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,String.join("", STRING,e.getMessage()));
            return HttpResult.newErrorResult(500,e) ;
        }
    }
    
}
