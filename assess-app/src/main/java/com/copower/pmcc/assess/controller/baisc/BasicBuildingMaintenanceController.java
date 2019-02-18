package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.entity.BasicBuildingMaintenance;
import com.copower.pmcc.assess.service.basic.BasicBuildingMaintenanceService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: zch
 * @Date: 2018/10/30 14:24
 * @Description:围护结构
 */
@RequestMapping(value = "/basicBuildingMaintenance")
@Controller
public class BasicBuildingMaintenanceController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicBuildingMaintenanceService basicBuildingMaintenanceService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicBuildingMaintenanceById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicBuildingMaintenanceById(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicBuildingMaintenanceService.getBasicBuildingMaintenanceById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicBuildingMaintenance", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicBuildingMaintenance(BasicBuildingMaintenance basicBuildingMaintenance){
        try {
            return HttpResult.newCorrectResult(200,basicBuildingMaintenanceService.saveAndUpdateBasicBuildingMaintenance(basicBuildingMaintenance));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicBuildingMaintenance", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicBuildingMaintenance(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicBuildingMaintenanceService.deleteBasicBuildingMaintenance(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicBuildingMaintenance basicBuildingMaintenance, @RequestParam(required = true, name = "approval", defaultValue = "false") Boolean approval){
        try {
            if (basicBuildingMaintenance != null){
                if (!approval) {
                    basicBuildingMaintenance.setCreator(commonService.thisUserAccount());
                }
            }
            return basicBuildingMaintenanceService.getBootstrapTableVo(basicBuildingMaintenance);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicBuildingMaintenanceList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicBuildingMaintenanceList(BasicBuildingMaintenance basicBuildingMaintenance){
        try {
            return HttpResult.newCorrectResult(200,basicBuildingMaintenanceService.basicBuildingMaintenanceList(basicBuildingMaintenance));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }
    
}
