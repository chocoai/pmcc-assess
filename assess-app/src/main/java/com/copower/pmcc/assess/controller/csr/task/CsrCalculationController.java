package com.copower.pmcc.assess.controller.csr.task;

import com.copower.pmcc.assess.dal.entity.CsrCalculation;
import com.copower.pmcc.assess.service.csr.CsrCalculationService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/6/1
 * @time: 17:08
 */
@Controller
@RequestMapping(value = "/csrCalculation", name = "测算情况")
public class CsrCalculationController {
    @Autowired
    private CsrCalculationService csrCalculationService;

    @ResponseBody
    @RequestMapping(value = "/saveLoanCalculation", name = "测算情况", method = RequestMethod.POST)
    public HttpResult saveLoanCalculation(CsrCalculation csrCalculation) {
        try {
            csrCalculation = csrCalculationService.saveCsrCalculation(csrCalculation);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult(csrCalculation.getId());
    }
}
