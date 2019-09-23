package com.copower.pmcc.assess.controller.data;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.assets.DataAssetsAppraisalTypeEnum;
import com.copower.pmcc.assess.dal.basis.entity.DataAssetsAppraisalDic;
import com.copower.pmcc.assess.dto.output.data.DataAssetsAppraisalDicVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.data.DataAssetsAppraisalDicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
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

    @GetMapping(value = "/getDataAssetsAppraisalDicListVo")
    public HttpResult getDataAssetsAppraisalDicList(DataAssetsAppraisalDic dataAssetsAppraisalDic) {
        try {
            List<DataAssetsAppraisalDic> objs = dataAssetsAppraisalDicService.getDataAssetsAppraisalDicListByExample(dataAssetsAppraisalDic);
            List<DataAssetsAppraisalDicVo> voList = Lists.newArrayList();
            if (CollectionUtils.isNotEmpty(objs)){
                objs.forEach(oo -> {
                    voList.add(dataAssetsAppraisalDicService.getDataAssetsAppraisalDicVo(oo)) ;
                });
            }
            return HttpResult.newCorrectResult(200, voList);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,errorName);
            return HttpResult.newErrorResult(500, e);
        }
    }


    @GetMapping("/getBootstrapTableVoByPid")
    public BootstrapTableVo getBootstrapTableVoByPid(Integer pid) {
        return dataAssetsAppraisalDicService.getDataAssetsAppraisalDicByPid(pid);
    }

    @PostMapping(value = "/getDataAssetsAppraisalDicLevel", name = "获取层级")
    public HttpResult getReportFieldLevel(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, dataAssetsAppraisalDicService.getDataAssetsAppraisalDicLevel(id));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e.getMessage());
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
