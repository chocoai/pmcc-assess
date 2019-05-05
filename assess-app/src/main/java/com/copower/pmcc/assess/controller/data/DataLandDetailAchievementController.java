package com.copower.pmcc.assess.controller.data;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.DataLandDetailAchievement;
import com.copower.pmcc.assess.service.data.DataLandDetailAchievementService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zch
 * @date: 2019/5/5 10:52
 * @description:土地级别详情从表
 */
@RequestMapping(value = "/dataLandDetailAchievement")
@RestController
public class DataLandDetailAchievementController {

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
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/get/{id}",name = "restful get")
    public HttpResult get(@PathVariable Integer id){
        try {
            return HttpResult.newCorrectResult(landDetailAchievementService.getDataLandDetailAchievementById(id));
        } catch (Exception e) {
            return HttpResult.newErrorResult(500, e);
        }
    }

    @PostMapping(value = "/save/{formData}",name = "restful post")
    public HttpResult save(@PathVariable(name = "formData") String formData){
        try {
            DataLandDetailAchievement oo = JSON.parseObject(formData,DataLandDetailAchievement.class);
            return HttpResult.newCorrectResult(landDetailAchievementService.saveDataLandDetailAchievement(oo));
        } catch (Exception e) {
            return HttpResult.newErrorResult(500, e);
        }
    }

    @PutMapping(value = "/edit/{formData}",name = "restful put")
    public HttpResult edit(@PathVariable(name = "formData") String formData){
        try {
            DataLandDetailAchievement oo = JSON.parseObject(formData,DataLandDetailAchievement.class);
            return HttpResult.newCorrectResult(landDetailAchievementService.saveDataLandDetailAchievement(oo));
        } catch (Exception e) {
            return HttpResult.newErrorResult(500, e);
        }
    }

}
