package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseWaterDrain;
import com.copower.pmcc.assess.service.basic.BasicHouseWaterDrainService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: zch
 * @Date: 2018/12/11 11:51
 * @Description:供排水情况
 */
@RequestMapping(value = "/basicHouseWaterDrain")
@RestController
public class BasicHouseWaterDrainController {

    @Autowired
    private CommonService commonService;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BasicHouseWaterDrainService basicHouseWaterDrainService;

    @GetMapping(value = "/get",name = "get")
    public HttpResult get(Integer id){
        try {
            return HttpResult.newCorrectResult(200, basicHouseWaterDrainService.getBasicHouseWaterDrainById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @PostMapping(value = "/saveAndUpdate",name = "saveAndUpdate")
    public HttpResult saveAndUpdate(BasicHouseWaterDrain basicHouseWaterDrain){
        try {
            return HttpResult.newCorrectResult(200, basicHouseWaterDrainService.saveAndUpdateBasicHouseWaterDrain(basicHouseWaterDrain));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @GetMapping(value = "/getBootstrapTableVo", name = "getBootstrapTableVo")
    public BootstrapTableVo getBootstrapTableVo(BasicHouseWaterDrain basicHouseWaterDrain, @RequestParam(required = true, name = "approval", defaultValue = "false") Boolean approval) {
        try {
            if (basicHouseWaterDrain != null) {
                if (!approval) {
                    basicHouseWaterDrain.setCreator(commonService.thisUserAccount());
                }
            }
            return basicHouseWaterDrainService.getBootstrapTableVo(basicHouseWaterDrain);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return null;
        }
    }

    @PostMapping(value = "/delete",name = "delete")
    public HttpResult delete(BasicHouseWaterDrain basicHouseWaterDrain){
        try {
            return HttpResult.newCorrectResult(200, basicHouseWaterDrainService.deleteBasicHouseWaterDrain(basicHouseWaterDrain.getId()));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }
}
