package com.copower.pmcc.assess.controller.project.assets;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.AssetsCustomizeDataField;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.project.assets.AssetsCustomizeDataFieldService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zch on 2019-9-23.
 * 资产评估 阶段 自定义名称表
 */
@RestController
@RequestMapping(value = "/assetsCustomizeDataField")
public class AssetsCustomizeDataFieldController {
    private final String errorName = "资产评估 阶段 自定义名称" ;

    @Autowired
    private AssetsCustomizeDataFieldService dataAssetsAppraisalDicService;
    @Autowired
    private BaseService baseService;



    @GetMapping(value = "/getBootstrapTableVo")
    public BootstrapTableVo getBootstrapTableVo(AssetsCustomizeDataField oo){
        return dataAssetsAppraisalDicService.getBootstrapTableVo(oo);
    }

    @PostMapping(value = "/saveAssetsCustomizeDataField")
    public HttpResult saveAssetsCustomizeDataField(String formData) {
        try {
            AssetsCustomizeDataField assetsCustomizeDataField = JSONObject.parseObject(formData, AssetsCustomizeDataField.class);
            dataAssetsAppraisalDicService.saveDataAssetsAppraisalDic(assetsCustomizeDataField);
            return HttpResult.newCorrectResult(200, assetsCustomizeDataField);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,errorName);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }


    @GetMapping(value = "/getAssetsCustomizeDataFieldById")
    public HttpResult getAssetsCustomizeDataField(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, dataAssetsAppraisalDicService.getDataAssetsAppraisalDicById(id));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,errorName);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @DeleteMapping(value = "/deleteAssetsCustomizeDataFieldById/{id}")
    public HttpResult deleteAssetsCustomizeDataFieldById(@PathVariable String id) {
        try {
            dataAssetsAppraisalDicService.deleteDataAssetsAppraisalDic(id); ;
            return HttpResult.newCorrectResult(200);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,errorName);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/getAssetsCustomizeDataFieldList")
    public HttpResult getAssetsCustomizeDataFieldList(AssetsCustomizeDataField assetsCustomizeDataField) {
        try {
            List<AssetsCustomizeDataField> objs = dataAssetsAppraisalDicService.getDataAssetsAppraisalDicListByExample(assetsCustomizeDataField);
            return HttpResult.newCorrectResult(200, objs);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,errorName);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/getAssetsCustomizeDataFieldListByTypeAndPlanDetailsId")
    public HttpResult getAssetsCustomizeDataFieldListByTypeAndPlanDetailsId(Integer planDetailsId,String type) {
        try {
            List<AssetsCustomizeDataField> objs = dataAssetsAppraisalDicService.getDataAssetsAppraisalDicListByTypeAndPlanDetailsId(planDetailsId, type);
            return HttpResult.newCorrectResult(200, objs);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,errorName);
            return HttpResult.newErrorResult(500, e);
        }
    }


    @GetMapping(value = "/getAssetsCustomizeDataFieldListByType")
    public HttpResult getAssetsCustomizeDataFieldListByType(String type) {
        try {
            List<AssetsCustomizeDataField> objs = dataAssetsAppraisalDicService.getDataAssetsAppraisalDicListByType(type);
            return HttpResult.newCorrectResult(200, objs);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,errorName);
            return HttpResult.newErrorResult(500, e);
        }
    }


}
