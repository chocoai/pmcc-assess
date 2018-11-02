package com.copower.pmcc.assess.dal.basic.dao;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouseTradingLease;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouseTradingLeaseExample;
import com.copower.pmcc.assess.dal.basic.mapper.BasicHouseTradingLeaseMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/2 09:56
 * @Description:
 */
@Repository
public class BasicHouseTradingLeaseDao {
    @Autowired
    private BasicHouseTradingLeaseMapper basicHouseTradingLeaseMapper;

    public BasicHouseTradingLease getBasicHouseTradingLeaseById(Integer id) throws SQLException {
        return basicHouseTradingLeaseMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicHouseTradingLease(BasicHouseTradingLease basicHouseTradingLease) throws SQLException {
        basicHouseTradingLeaseMapper.insertSelective(basicHouseTradingLease);
        return basicHouseTradingLease.getId();
    }

    public boolean updateBasicHouseTradingLease(BasicHouseTradingLease basicHouseTradingLease) throws SQLException {
        return basicHouseTradingLeaseMapper.updateByPrimaryKeySelective(basicHouseTradingLease) == 1;
    }

    public boolean deleteBasicHouseTradingLease(Integer id) throws SQLException {
        return basicHouseTradingLeaseMapper.deleteByPrimaryKey(id) == 1;
    }


    public List<BasicHouseTradingLease> basicHouseTradingLeaseList(BasicHouseTradingLease basicHouseTradingLease) throws SQLException {
        BasicHouseTradingLeaseExample example = new BasicHouseTradingLeaseExample();
        MybatisUtils.convertObj2Example(basicHouseTradingLease, example);
        return basicHouseTradingLeaseMapper.selectByExample(example);
    }
}
