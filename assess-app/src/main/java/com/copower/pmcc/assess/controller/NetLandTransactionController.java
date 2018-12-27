package com.copower.pmcc.assess.controller;

import com.copower.pmcc.assess.dal.basis.dao.net.*;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.NetLandTransactionService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
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

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/6/12
 * @time: 17:30
 */
@Controller
@RequestMapping(value = "/netLandTransaction")
public class NetLandTransactionController {
    @Autowired
    NetLandTransactionService netLandTransactionService;
    @Autowired
    private NetLandTransactionDao netLandTransactionDao;
    @Autowired
    private NetResultAnnouncementDao netResultAnnouncementDao;
    @Autowired
    private NetHangTagAnnouncementDao netHangTagAnnouncementDao;
    @Autowired
    private NetAuctionAnnouncementDao netAuctionAnnouncementDao;
    @Autowired
    private NetBeforehandAnnouncementDao netBeforehandAnnouncementDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView homeMain() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/net/netLandTransaction");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getLandTransactionList", name = "土地交易信息列表", method = RequestMethod.GET)
    public BootstrapTableVo getLandTransactionList() {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        HttpServletRequest request = requestBaseParam.getRequest();
        String content =  request.getParameter("content");
        NetLandTransaction netLandTransaction = new NetLandTransaction();
        List<NetLandTransaction> netLandTransactions = netLandTransactionDao.getNetLandTransactionList(content);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(netLandTransactions) ? new ArrayList<NetLandTransaction>() : netLandTransactions);
        return bootstrapTableVo;
    }


    @ResponseBody
    @RequestMapping(value = "/updateLandTransaction", name = "更新信息", method = RequestMethod.POST)
    public HttpResult updateLandTransaction() {
        netLandTransactionService.getNetLandTransactionList(new Date());

        NetLandTransaction netLandTransaction = new NetLandTransaction();
        netLandTransaction.setBisEdit(false);
        List<NetLandTransaction> landTransactionList = netLandTransactionDao.getObjectList(netLandTransaction);
        for (NetLandTransaction item : landTransactionList) {
            item.setBisEdit(true);
            netLandTransactionDao.editNetLandTransaction(item);
            if (item.getId() >= 1) {
                netLandTransactionService.getNetLandTransactionDetail(item.getContent(), item.getDetailLink(), item.getId());
            }
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/getResultList", name = "结果公告信息列表", method = RequestMethod.GET)
    public BootstrapTableVo getResultList(Integer mainId) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<NetResultAnnouncement> list = netResultAnnouncementDao.getNetResultAnnouncementList(mainId);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(list) ? new ArrayList<NetResultAnnouncement>() : list);
        return bootstrapTableVo;
    }


    @ResponseBody
    @RequestMapping(value = "/getHangTagList", name = "挂牌公告信息列表", method = RequestMethod.GET)
    public BootstrapTableVo getHangTagList(Integer mainId) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<NetHangTagAnnouncement> list = netHangTagAnnouncementDao.getNetHangTagAnnouncementList(mainId);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(list) ? new ArrayList<NetHangTagAnnouncement>() : list);
        return bootstrapTableVo;
    }

    @ResponseBody
    @RequestMapping(value = "/getAuctionList", name = "拍卖公告信息列表", method = RequestMethod.GET)
    public BootstrapTableVo getAuctionList(Integer mainId) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<NetAuctionAnnouncement> list = netAuctionAnnouncementDao.getNetAuctionAnnouncementList(mainId);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(list) ? new ArrayList<NetAuctionAnnouncement>() : list);
        return bootstrapTableVo;
    }

    @ResponseBody
    @RequestMapping(value = "/getBeforehandList", name = "土地预公告信息列表", method = RequestMethod.GET)
    public BootstrapTableVo getBeforehandList(Integer mainId) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<NetBeforehandAnnouncement> list = netBeforehandAnnouncementDao.getNetBeforehandAnnouncementList(mainId);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(list) ? new ArrayList<NetBeforehandAnnouncement>() : list);
        return bootstrapTableVo;
    }
}
