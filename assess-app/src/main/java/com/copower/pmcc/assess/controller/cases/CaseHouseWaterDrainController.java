package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseWaterDrain;
import com.copower.pmcc.assess.service.cases.CaseHouseWaterDrainService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: zch
 * @Date: 2018/12/11 14:50
 * @Description:供排水情况
 */
@RequestMapping(value = "/caseHouseWaterDrain")
@RestController
public class CaseHouseWaterDrainController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private CaseHouseWaterDrainService caseHouseWaterDrainService;

    @GetMapping(value = "/get",name = "get")
    public HttpResult get(Integer id){
        try {
            return HttpResult.newCorrectResult(200, caseHouseWaterDrainService.getCaseHouseWaterDrainById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @PostMapping(value = "/saveAndUpdate",name = "saveAndUpdate")
    public HttpResult saveAndUpdate(CaseHouseWaterDrain caseHouseWaterDrain){
        try {
            return HttpResult.newCorrectResult(200, caseHouseWaterDrainService.saveAndUpdateCaseHouseWaterDrain(caseHouseWaterDrain));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @GetMapping(value = "/getBootstrapTableVo", name = "getBootstrapTableVo")
    public BootstrapTableVo getBootstrapTableVo(CaseHouseWaterDrain caseHouseWaterDrain) {
        try {
            return caseHouseWaterDrainService.getCaseHouseWaterDrainListVos(caseHouseWaterDrain);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return null;
        }
    }

    @PostMapping(value = "/delete",name = "delete")
    public HttpResult delete(CaseHouseWaterDrain caseHouseWaterDrain){
        try {
            return HttpResult.newCorrectResult(200, caseHouseWaterDrainService.removeCaseHouseWaterDrain(caseHouseWaterDrain));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }
}
