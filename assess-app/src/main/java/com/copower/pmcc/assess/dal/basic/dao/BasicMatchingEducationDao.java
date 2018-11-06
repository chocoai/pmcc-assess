package com.copower.pmcc.assess.dal.basic.dao;

import com.copower.pmcc.assess.dal.basic.entity.BasicMatchingEducation;
import com.copower.pmcc.assess.dal.basic.entity.BasicMatchingEducationExample;
import com.copower.pmcc.assess.dal.basic.mapper.BasicMatchingEducationMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 10:56
 * @Description:
 */
@Repository
public class BasicMatchingEducationDao {

    @Autowired
    private BasicMatchingEducationMapper basicMatchingEducationMapper;

    public BasicMatchingEducation getBasicMatchingEducationById(Integer id)throws SQLException {
        return basicMatchingEducationMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicMatchingEducation(BasicMatchingEducation basicMatchingEducation)throws SQLException{
        basicMatchingEducationMapper.insertSelective(basicMatchingEducation);
        return basicMatchingEducation.getId();
    }

    public boolean updateBasicMatchingEducation(BasicMatchingEducation basicMatchingEducation)throws SQLException{
        return basicMatchingEducationMapper.updateByPrimaryKeySelective(basicMatchingEducation)==1;
    }

    public void removeBasicMatchingEducation(BasicMatchingEducation basicMatchingEducation)throws SQLException{
        BasicMatchingEducationExample example = new BasicMatchingEducationExample();
        MybatisUtils.convertObj2Example(basicMatchingEducation, example);
        basicMatchingEducationMapper.deleteByExample(example);
    }

    public boolean deleteBasicMatchingEducation(Integer id)throws SQLException{
        return  basicMatchingEducationMapper.deleteByPrimaryKey(id)==1;
    }

    public List<BasicMatchingEducation> basicMatchingEducationList(BasicMatchingEducation basicMatchingEducation)throws SQLException{
        BasicMatchingEducationExample example = new BasicMatchingEducationExample();
        MybatisUtils.convertObj2Example(basicMatchingEducation, example);
        return basicMatchingEducationMapper.selectByExample(example);
    }
    
}
