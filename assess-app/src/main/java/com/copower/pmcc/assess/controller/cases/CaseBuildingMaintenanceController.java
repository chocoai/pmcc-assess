package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingMaintenance;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.cases.CaseBuildingMaintenanceService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
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
 * @Date: 2018/9/18 16:08
 * @Description:
 */
@RequestMapping(value = "/caseBuildingMaintenance")
@Controller
public class CaseBuildingMaintenanceController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseBuildingMaintenanceService caseBuildingMaintenanceService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;

    @ResponseBody
    @RequestMapping(value = "/getCaseBuildingMaintenanceById", method = {RequestMethod.GET}, name = "获取围护结构")
    public HttpResult getById(Integer id) {
        CaseBuildingMaintenance caseBuildingMaintenance = null;
        try {
            if (id != null) {
                caseBuildingMaintenance = caseBuildingMaintenanceService.getCaseBuildingMaintenanceById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseBuildingMaintenance);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseBuildingMaintenanceList", method = {RequestMethod.GET}, name = "围护结构列表")
    public BootstrapTableVo getCaseBuildingMaintenanceList( Integer buildingId, String buildNumber) {
        BootstrapTableVo vo = null;
        try {
            CaseBuildingMaintenance caseBuildingMaintenance = new CaseBuildingMaintenance();
            if (buildingId != null ) {
                caseBuildingMaintenance.setBuildingId(buildingId);
            }
            if (!StringUtils.isEmpty(buildNumber)){
                caseBuildingMaintenance.setBuildNumber(buildNumber);
            }
            vo = caseBuildingMaintenanceService.getCaseBuildingMaintenanceLists(caseBuildingMaintenance);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseBuildingMaintenanceById", method = {RequestMethod.POST}, name = "删除围护结构")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                return HttpResult.newCorrectResult(caseBuildingMaintenanceService.deleteCaseBuildingMaintenance(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseBuildingMaintenance", method = {RequestMethod.POST}, name = "更新围护结构")
    public HttpResult save(CaseBuildingMaintenance caseBuildingMaintenance) {
        try {
            if (caseBuildingMaintenance.getId() == null || caseBuildingMaintenance.getId().equals(0)) {
                caseBuildingMaintenanceService.addCaseBuildingMaintenance(caseBuildingMaintenance);
            } else {
                caseBuildingMaintenanceService.updateCaseBuildingMaintenance(caseBuildingMaintenance);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }


}
