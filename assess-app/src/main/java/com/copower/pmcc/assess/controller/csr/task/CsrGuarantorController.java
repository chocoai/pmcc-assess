package com.copower.pmcc.assess.controller.csr.task;

import com.copower.pmcc.assess.dal.entity.CsrBorrowerMortgage;
import com.copower.pmcc.assess.dal.entity.CsrGuarantor;
import com.copower.pmcc.assess.dto.output.project.csr.CsrGuarantorVo;
import com.copower.pmcc.assess.service.csr.CsrGuarantorService;
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
@RequestMapping(value = "/csrGuarantor", name = "保证人情况")
public class CsrGuarantorController {
    @Autowired
    private CsrGuarantorService csrGuarantorService;

    @ResponseBody
    @RequestMapping(value = "/saveLoanGuarantor", name = "保存保证人数据", method = RequestMethod.POST)
    public HttpResult saveLoanGuarantor(CsrGuarantor csrGuarantor, Integer detailsId, String taskRemarks, String actualHours) {
        try {
            csrGuarantor = csrGuarantorService.saveCsrGuarantor(csrGuarantor,detailsId,taskRemarks,actualHours);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult(csrGuarantor.getId());
    }

    //取得保证人情况
    @ResponseBody
    @RequestMapping(value = "/getCsrGuarantor", name = "取得保证人情况", method = RequestMethod.GET)
    public BootstrapTableVo getCsrGuarantor(String borrowerId, Integer detailsId) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CsrGuarantorVo> csrGuarantors = csrGuarantorService.getCsrGuarantor(borrowerId,detailsId);
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(csrGuarantors) ? new ArrayList<CsrGuarantorVo>() : csrGuarantors);
        bootstrapTableVo.setTotal(page.getTotal());
        return bootstrapTableVo;
    }


    @ResponseBody
    @RequestMapping(value = "/deleteCsrGuarantor", name = "删除记录", method = RequestMethod.POST)
    public HttpResult deleteCsrGuarantor(Integer id) {
        try {
            csrGuarantorService.deleteCsrGuarantor(id);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}
