package com.copower.pmcc.assess.controller.data;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetailAchievement;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.data.DataLandLevelDetailAchievementService;
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
    public HttpResult delete(@PathVariable String id) {
        try {
            List<Integer> integerList = FormatUtils.transformString2Integer(id);
            for (Integer integer:integerList){
                LandLevelDetailAchievementService.deleteDataLandLevelDetailAchievement(integer) ;
            }
            return HttpResult.newCorrectResult(true);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/getAchievementByParam", name = "获取调整因素")
    public HttpResult getAchievementByParam(Integer levelDetailId,Integer type,String classification,String category,Integer grade) {
        try {
            DataLandLevelDetailAchievement achievement = LandLevelDetailAchievementService.getAchievementByParam(levelDetailId,type,classification,category,grade);
            return HttpResult.newCorrectResult(achievement);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @PostMapping(value = "/saveAchievement", name = "restful post")
    public HttpResult saveAchievement(String formData) {
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
    public HttpResult landLevelFilter(Integer levelDetailId) {
        try {
            return HttpResult.newCorrectResult(LandLevelDetailAchievementService.landLevelFilter(levelDetailId));
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
    public HttpResult downloadDataLandDetailAchievementFile(String id){
        try {
            return HttpResult.newCorrectResult(LandLevelDetailAchievementService.getLandLevelDetailTree(id));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

}
