package com.copower.pmcc.assess.controller;

import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.hr.api.provider.HrRpcToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/23
 * @time: 10:16
 */
@Controller
@RequestMapping(value = "/rpcHrService", name = "Hr接口类")
public class RpcHrController {
    @Autowired
    private HrRpcToolService hrRpcToolService;

    @ResponseBody
    @RequestMapping(value = "/getHrLegworkList", name = "取得外勤信息", method = RequestMethod.GET)
    public BootstrapTableVo getHrLegworkList(Integer publicProjectId) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = hrRpcToolService.getHrLegworkList(publicProjectId, requestBaseParam.getOffset(), requestBaseParam.getLimit(), requestBaseParam.getSearch());
        return bootstrapTableVo;
    }
}
