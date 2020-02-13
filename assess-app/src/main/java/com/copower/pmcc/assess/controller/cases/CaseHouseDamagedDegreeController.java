package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.controller.BaseController;
import com.copower.pmcc.assess.service.cases.CaseHouseDamagedDegreeService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by kings on 2018-12-25.
 */
@Controller
@RequestMapping("/caseHouseDamagedDegree")
public class CaseHouseDamagedDegreeController extends BaseController {
    @Autowired
    private CaseHouseDamagedDegreeService caseHouseDamagedDegreeService;


    @ResponseBody
    @RequestMapping(value = "/getDamagedDegreeList", name = "获取完损度数据", method = {RequestMethod.GET})
    public HttpResult getDamagedDegreeList(Integer houseId) {
        try {
            return HttpResult.newCorrectResult(caseHouseDamagedDegreeService.getDamagedDegreeVoList(houseId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getDamagedDegreeDetailList", method = {RequestMethod.GET})
    public BootstrapTableVo getDamagedDegreeDetailList(Integer damagedDegreeId) {
        return caseHouseDamagedDegreeService.getDamagedDegreeDetailList(damagedDegreeId);
    }
}
