package com.copower.pmcc.assess.controller.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEngineeringAndEquipmentCenter;
import com.copower.pmcc.assess.service.project.declare.DeclareBuildEngineeringAndEquipmentCenterService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Auther: zch
 * @Date: 2018/11/22 17:39
 * @Description:申报中间表
 */
@RequestMapping(value = "/declareBuildEngineeringAndEquipmentCenter")
@RestController
public class DeclareBuildEngineeringAndEquipmentCenterController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

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
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(declareBuildEngineeringAndEquipmentCenter);
    }

    @RequestMapping(value = "/saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter", method = {RequestMethod.POST}, name = "更新在建工程中间表")
    public HttpResult saveAndUpdate(DeclareBuildEngineeringAndEquipmentCenter declareBuildEngineeringAndEquipmentCenter) {
        try {
            Integer id = declareBuildEngineeringAndEquipmentCenterService.saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(declareBuildEngineeringAndEquipmentCenter);
            return HttpResult.newCorrectResult(id);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
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
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("异常");
        }
    }

    @RequestMapping(value = "/copyDeclareBuildEngineeringAndEquipmentCenter", method = {RequestMethod.POST}, name = "在建工程中间表 copy")
    public HttpResult copy(Integer copyId, String type, String ids) {
        try {
            if (StringUtils.isNotEmpty(type)) {
                declareBuildEngineeringAndEquipmentCenterService.copy(ids, copyId, type);
            }else {
                declareBuildEngineeringAndEquipmentCenterService.copy(ids, copyId);
            }
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("异常");
        }
    }

    @PostMapping(value = "/deleteDeclareBuildEngineeringAndEquipmentCenterById", name = "删除")
    public HttpResult deleteById(String id) {
        try {
            declareBuildEngineeringAndEquipmentCenterService.deleteIds(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("异常");
        }
    }

    @PostMapping(value = "/deleteByType", name = "根据type删除子项id")
    public HttpResult deleteByType(String type, Integer centerId) {
        try {
            declareBuildEngineeringAndEquipmentCenterService.deleteByType(type, centerId);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("异常");
        }
    }

}
