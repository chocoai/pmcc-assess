package com.copower.pmcc.assess.dal.basic.dao;

import com.copower.pmcc.assess.dal.basic.entity.BasicBuildingFunction;
import com.copower.pmcc.assess.dal.basic.entity.BasicBuildingFunctionExample;
import com.copower.pmcc.assess.dal.basic.mapper.BasicBuildingFunctionMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/30 11:24
 * @Description:
 */
@Repository
public class BasicBuildingFunctionDao {
    @Autowired
    private BasicBuildingFunctionMapper basicBuildingFunctionMapper;

    public BasicBuildingFunction getBasicBuildingFunctionById(Integer id)throws SQLException {
        return basicBuildingFunctionMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicBuildingFunction(BasicBuildingFunction basicBuildingFunction)throws SQLException{
        basicBuildingFunctionMapper.insertSelective(basicBuildingFunction);
        return basicBuildingFunction.getId();
    }

    public boolean updateBasicBuildingFunction(BasicBuildingFunction basicBuildingFunction)throws SQLException{
        return basicBuildingFunctionMapper.updateByPrimaryKeySelective(basicBuildingFunction)==1;
    }

    public void removeBasicBuildingFunction(BasicBuildingFunction basicBuildingFunction)throws SQLException{
        BasicBuildingFunctionExample example = new BasicBuildingFunctionExample();
        MybatisUtils.convertObj2Example(basicBuildingFunction, example);
        basicBuildingFunctionMapper.deleteByExample(example);
    }

    public boolean deleteBasicBuildingFunction(Integer id)throws SQLException{
        return  basicBuildingFunctionMapper.deleteByPrimaryKey(id)==1;
    }

    public List<BasicBuildingFunction> basicBuildingFunctionList(BasicBuildingFunction basicBuildingFunction){
        BasicBuildingFunctionExample example = new BasicBuildingFunctionExample();
        MybatisUtils.convertObj2Example(basicBuildingFunction, example);
        return basicBuildingFunctionMapper.selectByExample(example);
    }
}
