package com.copower.pmcc.assess.controller.csr.task;

import com.copower.pmcc.assess.dal.entity.CsrBorrower;
import com.copower.pmcc.assess.service.csr.CsrBorrowerService;
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
@RequestMapping(value = "/csrBorrower", name = "测算情况")
public class CsrBorrowerController {
    @Autowired
    private CsrBorrowerService csrBorrowerService;

    @ResponseBody
    @RequestMapping(value = "/saveLoanBorrower", name = "测算情况", method = RequestMethod.POST)
    public HttpResult saveLoanBorrower(CsrBorrower csrBorrower) {
        try {
            csrBorrower = csrBorrowerService.saveCsrBorrower(csrBorrower);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult(csrBorrower.getId());
    }
}
