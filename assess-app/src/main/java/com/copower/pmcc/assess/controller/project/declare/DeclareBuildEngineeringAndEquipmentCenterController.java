package com.copower.pmcc.assess.controller.project.declare;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEngineeringAndEquipmentCenter;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.project.declare.DeclareBuildEngineeringAndEquipmentCenterService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Auther: zch
 * @Date: 2018/11/22 17:39
 * @Description:申报中间表
 */
@RequestMapping(value = "/declareBuildEngineeringAndEquipmentCenter")
@RestController
public class DeclareBuildEngineeringAndEquipmentCenterController {
    @Autowired
    private BaseService baseService;

    @Autowired
    private DeclareBuildEngineeringAndEquipmentCenterService declareBuildEngineeringAndEquipmentCenterService;

    @RequestMapping(value = "/getDeclareBuildEngineeringAndEquipmentCenterById", method = {RequestMethod.GET}, name = "获取在建工程中间表")
    public HttpResult getById(Integer id) {
        DeclareBuildEngineeringAndEquipmentCenter declareBuildEngineeringAndEquipmentCenter = null;
        try {
            if (id != null) {
                declareBuildEngineeringAndEquipmentCenter = declareBuildEngineeringAndEquipmentCenterService.getDeclareBuildEngineeringAndEquipmentCenterById(id);
            }
        } catch (Exception e1) {
            baseService.writeExceptionInfo(e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(declareBuildEngineeringAndEquipmentCenter);
    }

    @RequestMapping(value = "/saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter", method = {RequestMethod.POST}, name = "更新在建工程中间表")
    public HttpResult saveAndUpdate(DeclareBuildEngineeringAndEquipmentCenter declareBuildEngineeringAndEquipmentCenter, @RequestParam(defaultValue = "false") boolean updateNull) {
        try {
            Integer id = declareBuildEngineeringAndEquipmentCenterService.saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(declareBuildEngineeringAndEquipmentCenter, updateNull);
            return HttpResult.newCorrectResult(id);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @RequestMapping(value = "/saveDeclareBuildEngineeringAndEquipmentCenter", method = {RequestMethod.POST}, name = "更新在建工程中间表")
    public HttpResult saveDeclareBuildEngineeringAndEquipmentCenter(String formData, @RequestParam(defaultValue = "false") boolean updateNull) {
        try {
            DeclareBuildEngineeringAndEquipmentCenter target = JSONObject.parseObject(formData, DeclareBuildEngineeringAndEquipmentCenter.class);
            Integer id = declareBuildEngineeringAndEquipmentCenterService.saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(target, updateNull);
            return HttpResult.newCorrectResult(id);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @RequestMapping(value = "/listDeclareBuildEngineeringAndEquipmentCenter", method = {RequestMethod.GET}, name = "在建工程中间表 list")
    public HttpResult list(DeclareBuildEngineeringAndEquipmentCenter declareBuildEngineeringAndEquipmentCenter) {
        try {
            if (declareBuildEngineeringAndEquipmentCenter != null) {
                return HttpResult.newCorrectResult(declareBuildEngineeringAndEquipmentCenterService.declareBuildEngineeringAndEquipmentCenterList(declareBuildEngineeringAndEquipmentCenter));
            } else {
                return HttpResult.newErrorResult("异常");
            }
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("异常");
        }
    }

    @RequestMapping(value = "/copyDeclareBuildEngineeringAndEquipmentCenter", method = {RequestMethod.POST}, name = "在建工程中间表 copy")
    public HttpResult copy(Integer copyId, String type, String ids) {
        try {
            if (StringUtils.isNotEmpty(type)) {
                declareBuildEngineeringAndEquipmentCenterService.copy(ids, copyId, type);
            } else {
                declareBuildEngineeringAndEquipmentCenterService.copy(ids, copyId);
            }
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("异常");
        }
    }

    @PostMapping(value = "/deleteDeclareBuildEngineeringAndEquipmentCenterById", name = "删除")
    public HttpResult deleteById(String id) {
        try {
            declareBuildEngineeringAndEquipmentCenterService.deleteIds(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("异常");
        }
    }

    @PostMapping(value = "/deleteByType", name = "根据type删除子项id")
    public HttpResult deleteByType(String type, Integer centerId) {
        try {
            declareBuildEngineeringAndEquipmentCenterService.deleteByType(type, centerId);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("异常");
        }
    }

    @RequestMapping(value = "/getDataByDeclareRecord", method = {RequestMethod.GET}, name = "获取中间表")
    public HttpResult getDataByDeclareRecord(Integer declareRecordId) {
        DeclareBuildEngineeringAndEquipmentCenter declareBuildEngineeringAndEquipmentCenter = null;
        try {
            if (declareRecordId != null) {
                declareBuildEngineeringAndEquipmentCenter = declareBuildEngineeringAndEquipmentCenterService.getDataByDeclareRecord(declareRecordId);
            }
        } catch (Exception e1) {
            baseService.writeExceptionInfo(e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(declareBuildEngineeringAndEquipmentCenter);
    }
}
