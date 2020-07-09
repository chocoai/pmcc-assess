package com.copower.pmcc.assess.controller;

import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.finance.api.dto.FinancialBillMakeOutDto;
import com.copower.pmcc.finance.api.provider.FinanceRpcToolService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/1/23
 * @time: 10:16
 */
@Controller
@RequestMapping(value = "/rpcFinanceService", name = "Finance接口类")
public class RpcFinanceController {
    @Autowired
    private FinanceRpcToolService financeRpcToolService;

    @ResponseBody
    @RequestMapping(value = "/getFinancialBillMakeOutList", name = "取得开票信息", method = RequestMethod.GET)
    public BootstrapTableVo getFinancialBillMakeOutList(Integer publicProjectId) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        List<FinancialBillMakeOutDto> projectBillMakeOutList = financeRpcToolService.getFinancialBillMakeOutListAll(publicProjectId, null);
        if (CollectionUtils.isEmpty(projectBillMakeOutList)) return bootstrapTableVo;
        bootstrapTableVo.setTotal((long) projectBillMakeOutList.size());
        bootstrapTableVo.setRows(projectBillMakeOutList);
        return bootstrapTableVo;
    }
}
