package com.copower.pmcc.assess.dal.basic.dao;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouseIntelligent;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouseIntelligentExample;
import com.copower.pmcc.assess.dal.basic.mapper.BasicHouseIntelligentMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:22
 * @Description:
 */
@Repository
public class BasicHouseIntelligentDao {

    @Autowired
    private BasicHouseIntelligentMapper basicHouseIntelligentMapper;

    public BasicHouseIntelligent getBasicHouseIntelligentById(Integer id)throws SQLException {
        return basicHouseIntelligentMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicHouseIntelligent(BasicHouseIntelligent basicHouseIntelligent)throws SQLException{
        basicHouseIntelligentMapper.insertSelective(basicHouseIntelligent);
        return basicHouseIntelligent.getId();
    }

    public boolean updateBasicHouseIntelligent(BasicHouseIntelligent basicHouseIntelligent)throws SQLException{
        return basicHouseIntelligentMapper.updateByPrimaryKeySelective(basicHouseIntelligent)==1;
    }

    public void removeBasicHouseIntelligent(BasicHouseIntelligent basicHouseIntelligent)throws SQLException{
        BasicHouseIntelligentExample example = new BasicHouseIntelligentExample();
        MybatisUtils.convertObj2Example(basicHouseIntelligent, example);
        basicHouseIntelligentMapper.deleteByExample(example);
    }

    public boolean deleteBasicHouseIntelligent(Integer id)throws SQLException{
        return  basicHouseIntelligentMapper.deleteByPrimaryKey(id)==1;
    }

    public List<BasicHouseIntelligent> basicHouseIntelligentList(BasicHouseIntelligent basicHouseIntelligent)throws SQLException{
        BasicHouseIntelligentExample example = new BasicHouseIntelligentExample();
        MybatisUtils.convertObj2Example(basicHouseIntelligent, example);
        return basicHouseIntelligentMapper.selectByExample(example);
    }
    
}
