package com.copower.pmcc.assess.dal.basic.dao;

import com.copower.pmcc.assess.dal.basic.entity.BasicEstateLandState;
import com.copower.pmcc.assess.dal.basic.entity.BasicEstateLandStateExample;
import com.copower.pmcc.assess.dal.basic.mapper.BasicEstateLandStateMapper;
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
public class BasicEstateLandStateDao {

    @Autowired
    private BasicEstateLandStateMapper basicEstateLandStateMapper;

    public BasicEstateLandState getBasicEstateLandStateById(Integer id)throws SQLException{
        return basicEstateLandStateMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicEstateLandState(BasicEstateLandState basicEstateLandState)throws SQLException{
        basicEstateLandStateMapper.insertSelective(basicEstateLandState);
        return basicEstateLandState.getId();
    }

    public boolean updateBasicEstateLandState(BasicEstateLandState basicEstateLandState)throws SQLException{
        return basicEstateLandStateMapper.updateByPrimaryKeySelective(basicEstateLandState)==1;
    }

    public boolean deleteBasicEstateLandState(Integer id)throws SQLException{
        return  basicEstateLandStateMapper.deleteByPrimaryKey(id)==1;
    }

    public List<BasicEstateLandState> basicEstateLandStateList(BasicEstateLandState basicEstateLandState)throws SQLException{
        BasicEstateLandStateExample example = new BasicEstateLandStateExample();
        MybatisUtils.convertObj2Example(basicEstateLandState, example);
        return basicEstateLandStateMapper.selectByExample(example);
    }
}
