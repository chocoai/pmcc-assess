package com.copower.pmcc.assess.controller.csr.task;

import com.copower.pmcc.assess.dal.entity.CsrBorrowerMortgage;
import com.copower.pmcc.assess.service.csr.CsrBorrowerMortgageService;
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
@RequestMapping(value = "/csrBorrowerMortgage", name = "测算情况")
public class CsrBorrowerMortgageController {
    @Autowired
    private CsrBorrowerMortgageService csrBorrowerMortgageService;

    @ResponseBody
    @RequestMapping(value = "/saveLoanBorrowerMortgage", name = "测算情况", method = RequestMethod.POST)
    public HttpResult saveLoanBorrowerMortgage(CsrBorrowerMortgage csrBorrowerMortgage) {
        try {
            csrBorrowerMortgage = csrBorrowerMortgageService.saveCsrBorrowerMortgage(csrBorrowerMortgage);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult(csrBorrowerMortgage.getId());
    }
}
