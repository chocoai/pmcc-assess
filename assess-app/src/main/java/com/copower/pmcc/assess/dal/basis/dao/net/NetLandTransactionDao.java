package com.copower.pmcc.assess.dal.basis.dao.net;

import com.copower.pmcc.assess.dal.basis.entity.NetLandTransaction;
import com.copower.pmcc.assess.dal.basis.entity.NetLandTransactionExample;
import com.copower.pmcc.assess.dal.basis.mapper.NetLandTransactionMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/6/12
 * @time: 15:04
 */
@Repository
public class NetLandTransactionDao {
    @Autowired
    private NetLandTransactionMapper netLandTransactionMapper;

    public NetLandTransaction getNetLandTransaction(Integer id) {
        return netLandTransactionMapper.selectByPrimaryKey(id);
    }

    public List<NetLandTransaction> getNetLandTransactionList(String content) {
        NetLandTransactionExample example = new NetLandTransactionExample();
        NetLandTransactionExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(content)) {
            criteria.andContentLike("%" + content + "%");
        }
        List<NetLandTransaction> netLandTransactions = netLandTransactionMapper.selectByExample(example);
        return netLandTransactions;
    }

    public List<NetLandTransaction> getObjectList(NetLandTransaction netLandTransaction) {
        NetLandTransactionExample example = new NetLandTransactionExample();
        MybatisUtils.convertObj2Example(netLandTransaction, example);
        return netLandTransactionMapper.selectByExample(example);
    }

    public boolean addNetLandTransaction(NetLandTransaction netLandTransaction) {
        int i = netLandTransactionMapper.insert(netLandTransaction);
        return i > 0;
    }

    public boolean editNetLandTransaction(NetLandTransaction netLandTransaction) {
        int i = netLandTransactionMapper.updateByPrimaryKeySelective(netLandTransaction);
        return i > 0;
    }

    public boolean deleteNetLandTransaction(Integer id) {
        int i = netLandTransactionMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
