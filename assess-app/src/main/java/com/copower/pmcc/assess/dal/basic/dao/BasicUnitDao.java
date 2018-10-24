package com.copower.pmcc.assess.dal.basic.dao;

import com.copower.pmcc.assess.dal.basic.entity.BasicUnit;
import com.copower.pmcc.assess.dal.basic.entity.BasicUnitExample;
import com.copower.pmcc.assess.dal.basic.mapper.BasicUnitMapper;
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
public class BasicUnitDao {

    @Autowired
    private BasicUnitMapper basicUnitMapper;

    public BasicUnit getBasicUnitById(Integer id)throws SQLException{
        return basicUnitMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicUnit(BasicUnit basicUnit)throws SQLException{
        basicUnitMapper.insertSelective(basicUnit);
        return basicUnit.getId();
    }

    public boolean updateBasicUnit(BasicUnit basicUnit)throws SQLException{
        return basicUnitMapper.updateByPrimaryKeySelective(basicUnit)==1;
    }

    public boolean deleteBasicUnit(Integer id)throws SQLException{
        return  basicUnitMapper.deleteByPrimaryKey(id)==1;
    }

    public List<BasicUnit> basicUnitList(BasicUnit basicUnit)throws SQLException{
        BasicUnitExample example = new BasicUnitExample();
        MybatisUtils.convertObj2Example(basicUnit, example);
        return basicUnitMapper.selectByExample(example);
    }
}
