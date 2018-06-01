package com.copower.pmcc.assess.controller.csr.task;

import com.copower.pmcc.assess.dal.entity.CsrGuarantor;
import com.copower.pmcc.assess.service.csr.CsrGuarantorService;
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
@RequestMapping(value = "/csrGuarantor", name = "借款合同基础情况")
public class CsrGuarantorController {
    @Autowired
    private CsrGuarantorService csrGuarantorService;

    @ResponseBody
    @RequestMapping(value = "/saveLoanGuarantor", name = "保存借款合同基础数据", method = RequestMethod.POST)
    public HttpResult saveLoanGuarantor(CsrGuarantor csrGuarantor) {
        try {
            csrGuarantor = csrGuarantorService.saveCsrGuarantor(csrGuarantor);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult(csrGuarantor.getId());
    }
}
