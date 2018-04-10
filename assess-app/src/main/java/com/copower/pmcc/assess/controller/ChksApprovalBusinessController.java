package com.copower.pmcc.assess.controller;

import com.copower.pmcc.assess.dto.output.ChksApprovalBusinessVo;
import com.copower.pmcc.assess.dto.output.ChksApprovalInfoVo;
import com.copower.pmcc.assess.service.ChksApprovalBusinessService;
import com.copower.pmcc.assess.service.ChksApprovalService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/3/28
 * @time: 15:53
 */
@Controller
@RequestMapping(value = "/ChksApprovalBusiness")
public class ChksApprovalBusinessController {
    @Autowired
    private ControllerComponent controllerComponent;
    @Autowired
    private ChksApprovalService chksApprovalService;
    @Autowired
    private ChksApprovalBusinessService chksApprovalBusinessService;

    @RequestMapping(value = "/chksApprovalIndex", method = RequestMethod.GET)
    public ModelAndView chksApprovalIndex() {
        ModelAndView modelAndView = controllerComponent.baseModelAndView("/chksApproval/chksApprovalIndex");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getChksApprovalBusinessList", name = "取得考核信息", method = RequestMethod.GET)
    public BootstrapTableVo getChksApprovalBusinessList(Integer bisCheck) {
        Boolean check = null;
        if (bisCheck >= 0) {
            check = bisCheck > 0;
        }
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ChksApprovalBusinessVo> chksApprovalBusinessList = chksApprovalBusinessService.getChksApprovalBusinessList(check, requestBaseParam.getSearch());
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(chksApprovalBusinessList) ? new ArrayList<ChksApprovalBusinessVo>() : chksApprovalBusinessList);
        return bootstrapTableVo;
    }

    @ResponseBody
    @RequestMapping(value = "/getChksApprovalInfoList", name = "取得考核信息", method = RequestMethod.GET)
    public BootstrapTableVo getChksApprovalInfoList(String processInsId) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ChksApprovalInfoVo> chksApprovalInfoVoList = chksApprovalService.getChksApprovalInfoVoList(processInsId);
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(chksApprovalInfoVoList) ? new ArrayList<ChksApprovalInfoVo>() : chksApprovalInfoVoList);
        return bootstrapTableVo;
    }
}
