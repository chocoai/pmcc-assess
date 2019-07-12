package com.copower.pmcc.assess.controller;

import com.copower.pmcc.assess.dal.basis.dao.net.NetAuctionInfoDao;
import com.copower.pmcc.assess.dal.basis.entity.NetAuctionInfo;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
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
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/6/12
 * @time: 17:30
 */
@Controller
@RequestMapping(value = "/netAuctionInfoController")
public class NetAuctionInfoController {
    @Autowired
    private NetAuctionInfoDao netAuctionInfoDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView homeMain() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/net/netAuctionInfo");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getAuctionInfoList", name = "信息列表", method = RequestMethod.GET)
    public BootstrapTableVo getAuctionInfoList(String queryTitle) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<NetAuctionInfo> netAuctionInfos = netAuctionInfoDao.getNetAuctionInfoListByName(queryTitle);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(netAuctionInfos) ? new ArrayList<NetAuctionInfo>() : netAuctionInfos);
        return bootstrapTableVo;
    }

}
