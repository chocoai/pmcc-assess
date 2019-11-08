package com.copower.pmcc.assess.controller.method;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.MdArchitecturalObj;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.method.MdArchitecturalObjService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zch on 2019/7/8.
 * 建筑安装工程费
 */
@RequestMapping(value = "/mdArchitecturalObj")
@RestController
public class MdArchitecturalObjController {
    @Autowired
    private MdArchitecturalObjService mdArchitecturalObjService;
    @Autowired
    private BaseService baseService;

    @PostMapping(value = "/saveMdArchitecturalObj")
    public HttpResult saveMdArchitecturalObj(String forData,String jsonString) {
        try {
            MdArchitecturalObj mdArchitecturalObj = JSONObject.parseObject(forData,MdArchitecturalObj.class) ;
            mdArchitecturalObj.setJsonContent(jsonString);
            mdArchitecturalObjService.saveMdArchitecturalObj(mdArchitecturalObj);
            return HttpResult.newCorrectResult(200, mdArchitecturalObj);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }


    @GetMapping(value = "/getMdArchitecturalObjById")
    public HttpResult getMdArchitecturalObj(Integer id){
        try {
            return HttpResult.newCorrectResult(200, mdArchitecturalObjService.getMdArchitecturalObjById(id));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @PostMapping(value = "/deleteMdArchitecturalObjById")
    public HttpResult deleteMdArchitecturalObjById(String id){
        try {
            mdArchitecturalObjService.deleteMdArchitecturalObjById(id) ;
            return HttpResult.newCorrectResult(200, "success");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }


    @PostMapping(value = "/removeMdArchitecturalObj")
    public HttpResult removeMdArchitecturalObj(String type, String databaseName, Integer pid, Integer planDetailsId) {
        try {
            mdArchitecturalObjService.removeMdArchitecturalObj(type, pid, databaseName, planDetailsId);
            return HttpResult.newCorrectResult(200, "success");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/getMdArchitecturalObjList")
    public HttpResult getMdArchitecturalObjList(MdArchitecturalObj mdArchitecturalObj) {
        try {
            List<MdArchitecturalObj> objs = mdArchitecturalObjService.getMdArchitecturalObjListByExample(mdArchitecturalObj);
            return HttpResult.newCorrectResult(200, objs);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }


}
