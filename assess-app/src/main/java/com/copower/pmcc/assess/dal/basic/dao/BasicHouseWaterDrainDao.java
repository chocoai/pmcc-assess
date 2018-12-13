package com.copower.pmcc.assess.dal.basic.dao;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouseWaterDrain;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouseWaterDrainExample;
import com.copower.pmcc.assess.dal.basic.mapper.BasicHouseWaterDrainMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/12/11 11:45
 * @Description:
 */
@Repository
public class BasicHouseWaterDrainDao {

    @Autowired
    private BasicHouseWaterDrainMapper basicHouseWaterDrainMapper;

    public BasicHouseWaterDrain getBasicHouseWaterDrainById(Integer id)throws SQLException {
        return basicHouseWaterDrainMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicHouseWaterDrain(BasicHouseWaterDrain basicHouseWaterDrain)throws SQLException{
        basicHouseWaterDrainMapper.insertSelective(basicHouseWaterDrain);
        return basicHouseWaterDrain.getId();
    }

    public boolean updateBasicHouseWaterDrain(BasicHouseWaterDrain basicHouseWaterDrain)throws SQLException{
        return basicHouseWaterDrainMapper.updateByPrimaryKeySelective(basicHouseWaterDrain)==1;
    }

    public void removeBasicHouseWaterDrain(BasicHouseWaterDrain basicHouseWaterDrain)throws SQLException{
        BasicHouseWaterDrainExample example = new BasicHouseWaterDrainExample();
        MybatisUtils.convertObj2Example(basicHouseWaterDrain, example);
        basicHouseWaterDrainMapper.deleteByExample(example);
    }

    public boolean deleteBasicHouseWaterDrain(Integer id)throws SQLException{
        return  basicHouseWaterDrainMapper.deleteByPrimaryKey(id)==1;
    }

    public List<BasicHouseWaterDrain> basicHouseWaterDrainList(BasicHouseWaterDrain basicHouseWaterDrain)throws SQLException{
        BasicHouseWaterDrainExample example = new BasicHouseWaterDrainExample();
        MybatisUtils.convertObj2Example(basicHouseWaterDrain, example);
        return basicHouseWaterDrainMapper.selectByExample(example);
    }
    
}