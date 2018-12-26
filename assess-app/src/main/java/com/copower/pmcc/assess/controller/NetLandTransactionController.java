package com.copower.pmcc.assess.controller;

import com.copower.pmcc.assess.dal.basis.dao.net.NetLandTransactionDao;
import com.copower.pmcc.assess.dal.basis.entity.NetLandTransaction;
import com.copower.pmcc.assess.service.NetLandTransactionService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ResponseBody
    @RequestMapping(value = "/updateLandTransaction", name = "更新信息", method = RequestMethod.POST)
    public HttpResult updateLandTransaction() {
        netLandTransactionService.getNetLandTransactionList(new Date());

        NetLandTransaction netLandTransaction = new NetLandTransaction();
        netLandTransaction.setBisEdit(false);
        List<NetLandTransaction> landTransactionList = netLandTransactionDao.getNetLandTransactionList(netLandTransaction);
        for (NetLandTransaction item : landTransactionList) {
            item.setBisEdit(true);
            netLandTransactionDao.editNetLandTransaction(item);
            if (item.getId() >= 1) {
                netLandTransactionService.getNetLandTransactionDetail(item.getContent(), item.getDetailLink(), item.getId());
            }
        }
        return HttpResult.newCorrectResult();
    }

}
