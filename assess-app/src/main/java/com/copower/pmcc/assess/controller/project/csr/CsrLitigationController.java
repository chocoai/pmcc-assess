package com.copower.pmcc.assess.controller.project.csr;

import com.copower.pmcc.assess.dal.basis.entity.CsrLitigation;
import com.copower.pmcc.assess.dto.output.project.csr.CsrLitigationVo;
import com.copower.pmcc.assess.service.project.csr.CsrLitigationService;
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
@RequestMapping(value = "/csrLitigation", name = "诉讼保全情况")
public class CsrLitigationController {
    @Autowired
    private CsrLitigationService csrLitigationService;

    @ResponseBody
    @RequestMapping(value = "/saveLoanLitigation", name = "保存诉讼保全数据", method = RequestMethod.POST)
    public HttpResult saveLoanLitigation(CsrLitigation csrLitigation,Integer detailsId,String taskRemarks, String actualHours) {
        try {
            csrLitigation = csrLitigationService.saveCsrLitigation(csrLitigation,detailsId,taskRemarks,actualHours);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult(csrLitigation.getId());
    }

    @ResponseBody
    @RequestMapping(value = "/loadLoanLitigation", name = "根据借款人信息取得诉讼保全数据", method = RequestMethod.GET)
    public HttpResult loadLoanLitigation(String borrowerId,Integer detailsId) {
        try {
            CsrLitigationVo csrLitigationVo = csrLitigationService.loadLoanLitigation(borrowerId, detailsId);
            return HttpResult.newCorrectResult(csrLitigationVo);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
}
