package com.copower.pmcc.assess.controller.project.csr;

import com.copower.pmcc.assess.dal.basis.entity.CsrContract;
import com.copower.pmcc.assess.dto.output.project.csr.CsrContractVo;
import com.copower.pmcc.assess.service.project.csr.CsrContractService;
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
@RequestMapping(value = "/csrContract", name = "借款合同基础情况")
public class CsrContractController {
    @Autowired
    private CsrContractService csrContractService;

    @ResponseBody
    @RequestMapping(value = "/saveLoanContract", name = "保存借款合同基础数据", method = RequestMethod.POST)
    public HttpResult saveLoanContract(CsrContract csrContract,Integer detailsId,String taskRemarks, String actualHours) {
        try {
            csrContract = csrContractService.saveCsrContract(csrContract,detailsId,taskRemarks,actualHours);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult(csrContract.getId());
    }

    @ResponseBody
    @RequestMapping(value = "/loadLoanContractAssist", name = "根据借款人信息取得相应的合同信息", method = RequestMethod.GET)
    public HttpResult loadLoanContractAssist(String borrowerId,Integer detailsId) {
        try {
            CsrContractVo csrContractVo = csrContractService.loadCsrContract(borrowerId, detailsId);
            return HttpResult.newCorrectResult(csrContractVo);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }


}
