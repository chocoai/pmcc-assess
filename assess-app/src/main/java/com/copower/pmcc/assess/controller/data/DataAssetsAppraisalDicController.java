package com.copower.pmcc.assess.controller.data;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.DataAssetsAppraisalTypeEnum;
import com.copower.pmcc.assess.dal.basis.entity.DataAssetsAppraisalDic;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.data.DataAssetsAppraisalDicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zch on 2019-9-18.
 * 资产评估 文件和字段配置
 */
@RestController
@RequestMapping(value = "/dataAssetsAppraisalDic")
public class DataAssetsAppraisalDicController {
    private final String errorName = "资产评估 文件和字段配置" ;

    @Autowired
    private  DataAssetsAppraisalDicService dataAssetsAppraisalDicService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @RequestMapping(value = "/dataAssetsAppraisalDicView")
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/data/dataAssetsAppraisalDicView");
        settingParams(modelAndView) ;
        return modelAndView;
    }

    @GetMapping(value = "/getBootstrapTableVo")
    public BootstrapTableVo getBootstrapTableVo(DataAssetsAppraisalDic oo){
        return dataAssetsAppraisalDicService.getBootstrapTableVo(oo);
    }

    @PostMapping(value = "/saveDataAssetsAppraisalDic")
    public HttpResult saveDataAssetsAppraisalDic(String formData) {
        try {
            DataAssetsAppraisalDic dataAssetsAppraisalDic = JSONObject.parseObject(formData, DataAssetsAppraisalDic.class);
            dataAssetsAppraisalDicService.saveDataAssetsAppraisalDic(dataAssetsAppraisalDic);
            return HttpResult.newCorrectResult(200, dataAssetsAppraisalDic);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,errorName);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }


    @GetMapping(value = "/getDataAssetsAppraisalDicById")
    public HttpResult getDataAssetsAppraisalDic(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, dataAssetsAppraisalDicService.getDataAssetsAppraisalDicById(id));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,errorName);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @DeleteMapping(value = "/deleteDataAssetsAppraisalDicById/{id}")
    public HttpResult deleteDataAssetsAppraisalDicById(@PathVariable String id) {
        try {
            dataAssetsAppraisalDicService.deleteDataAssetsAppraisalDic(id) ;
            return HttpResult.newCorrectResult(200);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,errorName);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/getDataAssetsAppraisalDicList")
    public HttpResult getDataAssetsAppraisalDicList(DataAssetsAppraisalDic dataAssetsAppraisalDic) {
        try {
            List<DataAssetsAppraisalDic> objs = dataAssetsAppraisalDicService.getDataAssetsAppraisalDicListByExample(dataAssetsAppraisalDic);
            return HttpResult.newCorrectResult(200, objs);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,errorName);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/getDataAssetsAppraisalDicListByTypeAndPlanDetailsId")
    public HttpResult getDataAssetsAppraisalDicListByTypeAndPlanDetailsId(Integer planDetailsId,String type) {
        try {
            List<DataAssetsAppraisalDic> objs = dataAssetsAppraisalDicService.getDataAssetsAppraisalDicListByTypeAndPlanDetailsId(planDetailsId, type);
            return HttpResult.newCorrectResult(200, objs);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,errorName);
            return HttpResult.newErrorResult(500, e);
        }
    }


    @GetMapping(value = "/getDataAssetsAppraisalDicListByType")
    public HttpResult getDataAssetsAppraisalDicListByType(String type) {
        try {
            List<DataAssetsAppraisalDic> objs = dataAssetsAppraisalDicService.getDataAssetsAppraisalDicListByType(type);
            return HttpResult.newCorrectResult(200, objs);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,errorName);
            return HttpResult.newErrorResult(500, e);
        }
    }

    private void settingParams(ModelAndView modelAndView){
        List<KeyValueDto> keyValueDtoList = new ArrayList<>(4) ;
        for (DataAssetsAppraisalTypeEnum typeEnum: DataAssetsAppraisalTypeEnum.values()){
            keyValueDtoList.add(new KeyValueDto(typeEnum.getKey(),typeEnum.getDec()));
        }
        modelAndView.addObject(StringUtils.uncapitalize(DataAssetsAppraisalTypeEnum.class.getSimpleName()),JSONObject.toJSONString(keyValueDtoList)) ;
    }
}
