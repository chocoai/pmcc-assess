package com.copower.pmcc.assess.controller.data;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.DataLandApproximationMethodSetting;
import com.copower.pmcc.assess.service.data.DataLandApproximationMethodSettingService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: zch
 * @date: 2019/5/5 16:53
 * @description:土地逼近法补偿配置
 */
@RequestMapping(value = "/landApproximationMethodSetting")
@RestController
public class DataLandApproximationMethodSettingController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DataLandApproximationMethodSettingService landDetailAchievementService;

    @RequestMapping(value = "/index", name = "土地逼近法补偿配置")
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/data/dataLandApproximationMethodSettingView");
        return modelAndView;
    }
    @GetMapping(value = "/getBootstrapTableVo")
    public BootstrapTableVo getBootstrapTableVo(DataLandApproximationMethodSetting oo) {
        BootstrapTableVo bootstrapTableVo = landDetailAchievementService.getBootstrapTableVo(oo);
        return bootstrapTableVo;
    }

    @DeleteMapping(value = "/delete/{id}",name = "restful delete")
    public HttpResult delete(@PathVariable Integer id){
        try {
            return HttpResult.newCorrectResult(landDetailAchievementService.deleteDataLandApproximationMethodSetting(id));
        } catch (Exception e) {
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/get/{id}",name = "restful get")
    public HttpResult get(@PathVariable Integer id){
        try {
            return HttpResult.newCorrectResult(landDetailAchievementService.getDataLandApproximationMethodSettingById(id));
        } catch (Exception e) {
            return HttpResult.newErrorResult(500, e);
        }
    }

    @PostMapping(value = "/save",name = "restful post")
    public HttpResult save(String formData){
        try {
            DataLandApproximationMethodSetting oo = JSON.parseObject(formData,DataLandApproximationMethodSetting.class);
            return HttpResult.newCorrectResult(landDetailAchievementService.saveDataLandApproximationMethodSetting(oo));
        } catch (Exception e) {
            return HttpResult.newErrorResult(500, e);
        }
    }

    
}
