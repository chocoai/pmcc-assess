package com.copower.pmcc.assess.controller.data;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.DataLandDetailAchievement;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.data.DataLandDetailAchievementService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * @author: zch
 * @date: 2019/5/5 10:52
 * @description:土地级别详情从表
 */
@RequestMapping(value = "/dataLandDetailAchievement")
@RestController
public class DataLandDetailAchievementController {
    @Autowired
    private BaseService baseService;
    @Autowired
    private DataLandDetailAchievementService landDetailAchievementService;

    @GetMapping(value = "/getBootstrapTableVo")
    public BootstrapTableVo getBootstrapTableVo(DataLandDetailAchievement oo) {
        BootstrapTableVo bootstrapTableVo = landDetailAchievementService.getBootstrapTableVo(oo);
        return bootstrapTableVo;
    }

    @DeleteMapping(value = "/delete/{id}",name = "restful delete")
    public HttpResult delete(@PathVariable Integer id){
        try {
            return HttpResult.newCorrectResult(landDetailAchievementService.deleteDataLandDetailAchievement(id));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/get/{id}",name = "restful get")
    public HttpResult get(@PathVariable Integer id){
        try {
            return HttpResult.newCorrectResult(landDetailAchievementService.getDataLandDetailAchievementById(id));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @PostMapping(value = "/save",name = "restful post")
    public HttpResult save( String formData){
        try {
            DataLandDetailAchievement oo = JSON.parseObject(formData,DataLandDetailAchievement.class);
            return HttpResult.newCorrectResult(landDetailAchievementService.saveDataLandDetailAchievement(oo));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/list",name = "list get")
    public HttpResult list(DataLandDetailAchievement oo){
        try {
            return HttpResult.newCorrectResult(landDetailAchievementService.getDataLandDetailAchievementList(oo).stream().map( po -> landDetailAchievementService.getDataLandDetailAchievementVo(po)).collect(Collectors.toList()));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }


    @GetMapping(value = "/landLevelFilter",name = "过滤 list get")
    public HttpResult landLevelFilter(DataLandDetailAchievement oo){
        try {
            return HttpResult.newCorrectResult(landDetailAchievementService.landLevelFilter(oo));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

}
