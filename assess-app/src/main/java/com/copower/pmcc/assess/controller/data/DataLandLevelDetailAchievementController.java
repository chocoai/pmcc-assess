package com.copower.pmcc.assess.controller.data;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetailAchievement;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.data.DataLandLevelDetailAchievementService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * @author: zch
 * @date: 2019/5/5 10:52
 * @description:土地级别详情从表
 */
@RequestMapping(value = "/dataLandLevelDetailAchievement")
@RestController
public class DataLandLevelDetailAchievementController {
    @Autowired
    private BaseService baseService;
    @Autowired
    private DataLandLevelDetailAchievementService LandLevelDetailAchievementService;

    @GetMapping(value = "/getBootstrapTableVo")
    public BootstrapTableVo getBootstrapTableVo(DataLandLevelDetailAchievement oo) {
        BootstrapTableVo bootstrapTableVo = LandLevelDetailAchievementService.getBootstrapTableVo(oo);
        return bootstrapTableVo;
    }

    @DeleteMapping(value = "/delete/{id}", name = "restful delete")
    public HttpResult delete(@PathVariable Integer id) {
        try {
            return HttpResult.newCorrectResult(LandLevelDetailAchievementService.deleteDataLandLevelDetailAchievement(id));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/get/{id}", name = "restful get")
    public HttpResult get(@PathVariable Integer id) {
        try {
            return HttpResult.newCorrectResult(LandLevelDetailAchievementService.getDataLandLevelDetailAchievementById(id));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @PostMapping(value = "/save", name = "restful post")
    public HttpResult save(String formData) {
        try {
            DataLandLevelDetailAchievement oo = JSON.parseObject(formData, DataLandLevelDetailAchievement.class);
            return HttpResult.newCorrectResult(LandLevelDetailAchievementService.saveDataLandLevelDetailAchievement(oo));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/list", name = "list get")
    public HttpResult list(DataLandLevelDetailAchievement oo) {
        try {
            return HttpResult.newCorrectResult(LandLevelDetailAchievementService.getDataLandLevelDetailAchievementList(oo).stream().map(po -> LandLevelDetailAchievementService.getDataLandLevelDetailAchievementVo(po)).collect(Collectors.toList()));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }


    @GetMapping(value = "/landLevelFilter", name = "过滤 list get")
    public HttpResult landLevelFilter(DataLandLevelDetailAchievement oo) {
        try {
            return HttpResult.newCorrectResult(LandLevelDetailAchievementService.landLevelFilter(oo));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @RequestMapping(value = "/importDataLandDetailAchievement", name = "导入 (excel)", method = RequestMethod.POST)
    public HttpResult importDataLandLevelDetailAchievement(Integer landLevelId ,HttpServletRequest request) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
            if (multipartFile.isEmpty()) {
                return HttpResult.newErrorResult("上传的文件不能为空");
            }
            String resultString = LandLevelDetailAchievementService.importDataLandLevelDetailAchievementNew(multipartFile ,landLevelId);
            return HttpResult.newCorrectResult(200, resultString);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @PostMapping(value = "/downloadDataLandDetailAchievementFile")
    public HttpResult downloadDataLandDetailAchievementFile(Integer classify,String type){
        try {
            return HttpResult.newCorrectResult(LandLevelDetailAchievementService.downloadDataLandDetailAchievementFile(classify, type));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

}
