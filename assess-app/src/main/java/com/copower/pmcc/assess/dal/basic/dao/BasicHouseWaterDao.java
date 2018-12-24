package com.copower.pmcc.assess.dal.basic.dao;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouseWater;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouseWaterExample;
import com.copower.pmcc.assess.dal.basic.mapper.BasicHouseWaterMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:19
 * @Description:
 */
@Repository
public class BasicHouseWaterDao {

    @Autowired
    private BasicHouseWaterMapper basicHouseWaterMapper;

    public BasicHouseWater getBasicHouseWaterById(Integer id) throws SQLException {
        return basicHouseWaterMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicHouseWater(BasicHouseWater basicHouseWater) throws SQLException {
        basicHouseWaterMapper.insertSelective(basicHouseWater);
        return basicHouseWater.getId();
    }

    public boolean updateBasicHouseWater(BasicHouseWater basicHouseWater) throws SQLException {
        return basicHouseWaterMapper.updateByPrimaryKeySelective(basicHouseWater) == 1;
    }

    public boolean deleteBasicHouseWater(BasicHouseWater basicHouseWater) throws SQLException {
        BasicHouseWaterExample example = new BasicHouseWaterExample();
        MybatisUtils.convertObj2Example(basicHouseWater, example);
        return basicHouseWaterMapper.deleteByExample(example) > 0;
    }

    public boolean deleteBasicHouseWater(Integer id) throws SQLException {
        return basicHouseWaterMapper.deleteByPrimaryKey(id) == 1;
    }

    public List<BasicHouseWater> basicHouseWaterList(BasicHouseWater basicHouseWater) throws SQLException {
        BasicHouseWaterExample example = new BasicHouseWaterExample();
        MybatisUtils.convertObj2Example(basicHouseWater, example);
        return basicHouseWaterMapper.selectByExample(example);
    }

}
