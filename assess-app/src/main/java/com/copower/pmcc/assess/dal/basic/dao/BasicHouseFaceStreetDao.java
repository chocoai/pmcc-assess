package com.copower.pmcc.assess.dal.basic.dao;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouseFaceStreet;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouseFaceStreetExample;
import com.copower.pmcc.assess.dal.basic.mapper.BasicHouseFaceStreetMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:26
 * @Description:
 */
@Repository
public class BasicHouseFaceStreetDao {

    @Autowired
    private BasicHouseFaceStreetMapper basicHouseFaceStreetMapper;

    public BasicHouseFaceStreet getBasicHouseFaceStreetById(Integer id) throws SQLException {
        return basicHouseFaceStreetMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicHouseFaceStreet(BasicHouseFaceStreet basicHouseFaceStreet) throws SQLException {
        basicHouseFaceStreetMapper.insertSelective(basicHouseFaceStreet);
        return basicHouseFaceStreet.getId();
    }

    public boolean updateBasicHouseFaceStreet(BasicHouseFaceStreet basicHouseFaceStreet) throws SQLException {
        return basicHouseFaceStreetMapper.updateByPrimaryKeySelective(basicHouseFaceStreet) == 1;
    }

    public boolean deleteBasicHouseFaceStreet(BasicHouseFaceStreet basicHouseFaceStreet) throws SQLException {
        BasicHouseFaceStreetExample example = new BasicHouseFaceStreetExample();
        MybatisUtils.convertObj2Example(basicHouseFaceStreet, example);
        return basicHouseFaceStreetMapper.deleteByExample(example) > 0;
    }

    public boolean deleteBasicHouseFaceStreet(Integer id) throws SQLException {
        return basicHouseFaceStreetMapper.deleteByPrimaryKey(id) == 1;
    }

    public List<BasicHouseFaceStreet> basicHouseFaceStreetList(BasicHouseFaceStreet basicHouseFaceStreet) throws SQLException {
        BasicHouseFaceStreetExample example = new BasicHouseFaceStreetExample();
        MybatisUtils.convertObj2Example(basicHouseFaceStreet, example);
        return basicHouseFaceStreetMapper.selectByExample(example);
    }

}
