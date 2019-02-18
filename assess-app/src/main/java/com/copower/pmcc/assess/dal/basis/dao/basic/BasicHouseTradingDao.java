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

    public BasicHouseTrading getBasicHouseTradingById(Integer id)throws SQLException{
        return basicHouseTradingMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicHouseTrading(BasicHouseTrading basicHouseTrading)throws SQLException{
        basicHouseTradingMapper.insertSelective(basicHouseTrading);
        return basicHouseTrading.getId();
    }

    public boolean updateBasicHouseTrading(BasicHouseTrading basicHouseTrading)throws SQLException{
        return basicHouseTradingMapper.updateByPrimaryKeySelective(basicHouseTrading)==1;
    }

    public boolean deleteBasicHouseTrading(Integer id)throws SQLException{
        return  basicHouseTradingMapper.deleteByPrimaryKey(id)==1;
    }

    public List<BasicHouseTrading> basicHouseTradingList(BasicHouseTrading basicHouseTrading)throws SQLException{
        BasicHouseTradingExample example = new BasicHouseTradingExample();
        MybatisUtils.convertObj2Example(basicHouseTrading, example);
        return basicHouseTradingMapper.selectByExample(example);
    }
}
