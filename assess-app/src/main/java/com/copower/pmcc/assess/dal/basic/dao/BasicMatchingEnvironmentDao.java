package com.copower.pmcc.assess.dal.basic.dao;

import com.copower.pmcc.assess.dal.basic.entity.BasicMatchingEnvironment;
import com.copower.pmcc.assess.dal.basic.entity.BasicMatchingEnvironmentExample;
import com.copower.pmcc.assess.dal.basic.mapper.BasicMatchingEnvironmentMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 10:59
 * @Description:
 */
@Repository
public class BasicMatchingEnvironmentDao {

    @Autowired
    private BasicMatchingEnvironmentMapper basicMatchingEnvironmentMapper;

    public BasicMatchingEnvironment getBasicMatchingEnvironmentById(Integer id)throws SQLException {
        return basicMatchingEnvironmentMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicMatchingEnvironment(BasicMatchingEnvironment basicMatchingEnvironment)throws SQLException{
        basicMatchingEnvironmentMapper.insertSelective(basicMatchingEnvironment);
        return basicMatchingEnvironment.getId();
    }

    public boolean updateBasicMatchingEnvironment(BasicMatchingEnvironment basicMatchingEnvironment)throws SQLException{
        return basicMatchingEnvironmentMapper.updateByPrimaryKeySelective(basicMatchingEnvironment)==1;
    }

    public void removeBasicMatchingEnvironment(BasicMatchingEnvironment basicMatchingEnvironment)throws SQLException{
        BasicMatchingEnvironmentExample example = new BasicMatchingEnvironmentExample();
        MybatisUtils.convertObj2Example(basicMatchingEnvironment, example);
        basicMatchingEnvironmentMapper.deleteByExample(example);
    }

    public boolean deleteBasicMatchingEnvironment(Integer id)throws SQLException{
        return  basicMatchingEnvironmentMapper.deleteByPrimaryKey(id)==1;
    }

    public List<BasicMatchingEnvironment> basicMatchingEnvironmentList(BasicMatchingEnvironment basicMatchingEnvironment){
        BasicMatchingEnvironmentExample example = new BasicMatchingEnvironmentExample();
        MybatisUtils.convertObj2Example(basicMatchingEnvironment, example);
        return basicMatchingEnvironmentMapper.selectByExample(example);
    }
    
}
