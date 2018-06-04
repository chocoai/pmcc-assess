package com.copower.pmcc.assess.controller.csr.task;

import com.copower.pmcc.assess.dal.entity.CsrPrincipalInterest;
import com.copower.pmcc.assess.service.csr.CsrPrincipalInterestService;
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
@RequestMapping(value = "/csrPrincipalInterest", name = "本金利息情况")
public class CsrPrincipalInterestController {
    @Autowired
    private CsrPrincipalInterestService csrPrincipalInterestService;

    @ResponseBody
    @RequestMapping(value = "/saveLoanPrincipalInterest", name = "本金利息数据", method = RequestMethod.POST)
    public HttpResult saveLoanPrincipalInterest(CsrPrincipalInterest csrPrincipalInterest) {
        try {
            csrPrincipalInterest = csrPrincipalInterestService.saveCsrPrincipalInterest(csrPrincipalInterest);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult(csrPrincipalInterest.getId());
    }
}
