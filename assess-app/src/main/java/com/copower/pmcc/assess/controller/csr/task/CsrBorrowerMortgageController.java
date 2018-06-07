package com.copower.pmcc.assess.controller.csr.task;

import com.copower.pmcc.assess.dal.entity.CsrBorrower;
import com.copower.pmcc.assess.dal.entity.CsrBorrowerMortgage;
import com.copower.pmcc.assess.dto.output.project.csr.CsrBorrowerMortgageVo;
import com.copower.pmcc.assess.service.csr.CsrBorrowerMortgageService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/6/1
 * @time: 17:08
 */
@Controller
@RequestMapping(value = "/csrBorrowerMortgage", name = "借款人资产抵押情况")
public class CsrBorrowerMortgageController {
    @Autowired
    private CsrBorrowerMortgageService csrBorrowerMortgageService;

    @ResponseBody
    @RequestMapping(value = "/saveLoanBorrowerMortgage", name = "保存借款人资产抵押", method = RequestMethod.POST)
    public HttpResult saveLoanBorrowerMortgage(CsrBorrowerMortgage csrBorrowerMortgage, Integer detailsId, String taskRemarks, String actualHours) {
        try {
            csrBorrowerMortgage = csrBorrowerMortgageService.saveCsrBorrowerMortgage(csrBorrowerMortgage, detailsId, taskRemarks, actualHours);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult(csrBorrowerMortgage.getId());
    }

    //取得资产抵押情况列表
    @ResponseBody
    @RequestMapping(value = "/getCsrBorrowerMortgage", name = "取得借款人资产抵押情况", method = RequestMethod.GET)
    public BootstrapTableVo getCsrBorrowerMortgage(Integer borrowerId, Integer detailsId) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CsrBorrowerMortgageVo> csrBorrowerMortgage = csrBorrowerMortgageService.getCsrBorrowerMortgage(borrowerId, detailsId);
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(csrBorrowerMortgage) ? new ArrayList<CsrBorrowerMortgageVo>() : csrBorrowerMortgage);
        bootstrapTableVo.setTotal(page.getTotal());
        return bootstrapTableVo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCsrBorrowerMortgage", name = "删除记录", method = RequestMethod.POST)
    public HttpResult deleteCsrBorrowerMortgage(Integer id) {
        try {
            csrBorrowerMortgageService.deleteCsrBorrowerMortgage(id);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}
