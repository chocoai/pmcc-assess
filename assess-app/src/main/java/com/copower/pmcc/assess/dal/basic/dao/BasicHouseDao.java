package com.copower.pmcc.assess.dal.basic.dao;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouse;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouseExample;
import com.copower.pmcc.assess.dal.basic.mapper.BasicHouseMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang.StringUtils;
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
public class BasicHouseDao {

    @Autowired
    private BasicHouseMapper basicHouseMapper;

    public BasicHouse getBasicHouseById(Integer id)throws SQLException{
        return basicHouseMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicHouse(BasicHouse basicHouse)throws SQLException{
        basicHouseMapper.insertSelective(basicHouse);
        return basicHouse.getId();
    }

    public boolean updateBasicHouse(BasicHouse basicHouse)throws SQLException{
        return basicHouseMapper.updateByPrimaryKeySelective(basicHouse)==1;
    }

    public boolean deleteBasicHouse(Integer id)throws SQLException{
        return  basicHouseMapper.deleteByPrimaryKey(id)==1;
    }

    public List<BasicHouse> basicHouseList(BasicHouse basicHouse)throws SQLException{
        BasicHouseExample example = new BasicHouseExample();
        MybatisUtils.convertObj2Example(basicHouse, example);
        return basicHouseMapper.selectByExample(example);
    }

    public List<BasicHouse> autoComplete(BasicHouse basicHouse)throws SQLException{
        BasicHouseExample example = new BasicHouseExample();
        BasicHouseExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (basicHouse.getUnitId() != null){
            criteria.andUnitIdEqualTo(basicHouse.getUnitId());
        }
        if (StringUtils.isNotBlank(basicHouse.getHouseNumber())){
            criteria.andHouseNumberLike(new StringBuilder("%").append(basicHouse.getHouseNumber()).append("%").toString());
        }
        return basicHouseMapper.selectByExample(example);
    }
}
