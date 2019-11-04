package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingEnvironment;
import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingEnvironmentExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicMatchingEnvironmentMapper;
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

    public BasicMatchingEnvironment getBasicMatchingEnvironmentById(Integer id)  {
        return basicMatchingEnvironmentMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicMatchingEnvironment(BasicMatchingEnvironment basicMatchingEnvironment)  {
        basicMatchingEnvironmentMapper.insertSelective(basicMatchingEnvironment);
        return basicMatchingEnvironment.getId();
    }

    public boolean updateBasicMatchingEnvironment(BasicMatchingEnvironment basicMatchingEnvironment, boolean updateNull)  {
        basicMatchingEnvironment.setBisDelete(false);
        return updateNull ? basicMatchingEnvironmentMapper.updateByPrimaryKey(basicMatchingEnvironment) == 1 : basicMatchingEnvironmentMapper.updateByPrimaryKeySelective(basicMatchingEnvironment) == 1;
    }

    public boolean deleteBasicMatchingEnvironment(Integer id)  {
        BasicMatchingEnvironment basicMatchingEnvironment = getBasicMatchingEnvironmentById(id);
        if(basicMatchingEnvironment==null) return false;
        basicMatchingEnvironment.setBisDelete(true);
        return basicMatchingEnvironmentMapper.updateByPrimaryKeySelective(basicMatchingEnvironment) == 1;
    }

    public List<BasicMatchingEnvironment> basicMatchingEnvironmentList(BasicMatchingEnvironment basicMatchingEnvironment) {
        BasicMatchingEnvironmentExample example = new BasicMatchingEnvironmentExample();
        BasicMatchingEnvironmentExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicMatchingEnvironment, criteria);
        return basicMatchingEnvironmentMapper.selectByExample(example);
    }

}
