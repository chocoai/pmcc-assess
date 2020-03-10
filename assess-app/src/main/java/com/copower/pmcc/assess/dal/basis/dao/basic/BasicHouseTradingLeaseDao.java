package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseTradingLease;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseTradingLeaseExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicHouseTradingLeaseMapper;
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

    public BasicHouseTradingLease getBasicHouseTradingLeaseById(Integer id)  {
        return basicHouseTradingLeaseMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicHouseTradingLease(BasicHouseTradingLease basicHouseTradingLease)  {
        basicHouseTradingLeaseMapper.insertSelective(basicHouseTradingLease);
        return basicHouseTradingLease.getId();
    }

    public boolean updateBasicHouseTradingLease(BasicHouseTradingLease basicHouseTradingLease, boolean updateNull)  {
        return updateNull ? basicHouseTradingLeaseMapper.updateByPrimaryKey(basicHouseTradingLease) == 1 : basicHouseTradingLeaseMapper.updateByPrimaryKeySelective(basicHouseTradingLease) == 1;
    }

    public boolean deleteBasicHouseTradingLease(Integer id)  {
        BasicHouseTradingLease basicHouseTradingLease = getBasicHouseTradingLeaseById(id);
        if(basicHouseTradingLease==null) return false;
        basicHouseTradingLease.setBisDelete(true);
        return basicHouseTradingLeaseMapper.updateByPrimaryKeySelective(basicHouseTradingLease) == 1;
    }

    public List<BasicHouseTradingLease> basicHouseTradingLeaseList(BasicHouseTradingLease basicHouseTradingLease)  {
        BasicHouseTradingLeaseExample example = new BasicHouseTradingLeaseExample();
        BasicHouseTradingLeaseExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicHouseTradingLease, criteria);
        return basicHouseTradingLeaseMapper.selectByExample(example);
    }
}
