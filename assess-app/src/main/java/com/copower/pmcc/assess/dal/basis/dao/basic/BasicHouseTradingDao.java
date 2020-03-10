package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseTrading;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseTradingExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicHouseTradingMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:21
 * @Description:案例基础数据
 */
@Repository
public class BasicHouseTradingDao {

    @Autowired
    private BasicHouseTradingMapper basicHouseTradingMapper;

    public BasicHouseTrading getBasicHouseTradingById(Integer id)  {
        return basicHouseTradingMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicHouseTrading(BasicHouseTrading basicHouseTrading)  {
        basicHouseTradingMapper.insertSelective(basicHouseTrading);
        return basicHouseTrading.getId();
    }

    public boolean updateBasicHouseTrading(BasicHouseTrading basicHouseTrading, boolean updateNull)  {
        return updateNull ? basicHouseTradingMapper.updateByPrimaryKey(basicHouseTrading) == 1 : basicHouseTradingMapper.updateByPrimaryKeySelective(basicHouseTrading) == 1;
    }

    public boolean deleteBasicHouseTrading(Integer id)  {
        BasicHouseTrading basicHouseTrading = getBasicHouseTradingById(id);
        if(basicHouseTrading==null) return false;
        basicHouseTrading.setBisDelete(true);
        return basicHouseTradingMapper.updateByPrimaryKeySelective(basicHouseTrading) == 1;
    }

    public List<BasicHouseTrading> basicHouseTradingList(BasicHouseTrading basicHouseTrading)  {
        BasicHouseTradingExample example = new BasicHouseTradingExample();
        BasicHouseTradingExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicHouseTrading, criteria);
        return basicHouseTradingMapper.selectByExample(example);
    }
}
