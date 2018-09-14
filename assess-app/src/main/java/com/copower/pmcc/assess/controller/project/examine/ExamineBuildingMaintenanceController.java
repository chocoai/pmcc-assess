package com.copower.pmcc.assess.controller.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineBuildingMaintenance;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.examine.ExamineBuildingMaintenanceService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/8/2 15:44
 * @Description:维护结构
 */

@RequestMapping(value = "/examineBuildingMaintenance")
@Controller
public class ExamineBuildingMaintenanceController {


    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineBuildingMaintenanceService examineBuildingMaintenanceService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    @ResponseBody
    @RequestMapping(value = "/getExamineBuildingMaintenanceById", method = {RequestMethod.GET}, name = "获取维护结构")
    public HttpResult getById(Integer id) {
        ExamineBuildingMaintenance examineBuildingMaintenance = null;
        try {
            if (id != null) {
                examineBuildingMaintenance = examineBuildingMaintenanceService.getExamineBuildingMaintenanceById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(examineBuildingMaintenance);
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineBuildingMaintenanceList", method = {RequestMethod.GET}, name = "维护结构列表")
    public BootstrapTableVo getExamineBuildingMaintenanceList(Integer examineType, Integer declareId,Integer planDetailsId, Integer buildingId,String buildNumber) {
        BootstrapTableVo vo = null;
        try {
            ExamineBuildingMaintenance examineBuildingMaintenance = new ExamineBuildingMaintenance();
            if (!ObjectUtils.isEmpty(examineType)) {
                examineBuildingMaintenance.setExamineType(examineType);
            }
            if (declareId != null ) {
                examineBuildingMaintenance.setDeclareId(declareId);
            }
            if (planDetailsId != null ) {
                examineBuildingMaintenance.setPlanDetailsId(planDetailsId);
            }
            if (buildingId != null ) {
                examineBuildingMaintenance.setBuildingId(buildingId);
            }
            if (!StringUtils.isEmpty(buildNumber)){
                examineBuildingMaintenance.setBuildNumber(buildNumber);
            }
            vo = examineBuildingMaintenanceService.getExamineBuildingMaintenanceLists(examineBuildingMaintenance);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteExamineBuildingMaintenanceById", method = {RequestMethod.POST}, name = "删除维护结构")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                return HttpResult.newCorrectResult(examineBuildingMaintenanceService.deleteExamineBuildingMaintenance(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateExamineBuildingMaintenance", method = {RequestMethod.POST}, name = "更新维护结构")
    public HttpResult save(ExamineBuildingMaintenance examineBuildingMaintenance) {
        try {
            if (examineBuildingMaintenance.getId() == null || examineBuildingMaintenance.getId().equals(0)) {
                examineBuildingMaintenanceService.addExamineBuildingMaintenance(examineBuildingMaintenance);
            } else {
                examineBuildingMaintenanceService.updateExamineBuildingMaintenance(examineBuildingMaintenance);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_building_maintenance_category", method = {RequestMethod.GET}, name = "维护结构分类")
    public HttpResult environment_type() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_BUILDING_MAINTENANCE_CATEGORY);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_building_materialquality", method = {RequestMethod.GET}, name = "维护结构材质")
    public HttpResult environment_category() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_BUILDING_MATERIALQUALITY);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }
}
