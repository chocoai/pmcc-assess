package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingEducation;
import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingEducationExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicMatchingEducationMapper;
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

    public BasicMatchingEducation getBasicMatchingEducationById(Integer id) throws SQLException {
        return basicMatchingEducationMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicMatchingEducation(BasicMatchingEducation basicMatchingEducation) throws SQLException {
        basicMatchingEducationMapper.insertSelective(basicMatchingEducation);
        return basicMatchingEducation.getId();
    }

    public boolean updateBasicMatchingEducation(BasicMatchingEducation basicMatchingEducation, boolean updateNull) throws SQLException {
        return updateNull ? basicMatchingEducationMapper.updateByPrimaryKey(basicMatchingEducation) == 1 : basicMatchingEducationMapper.updateByPrimaryKeySelective(basicMatchingEducation) == 1;
    }

    public void removeBasicMatchingEducation(BasicMatchingEducation basicMatchingEducation) throws SQLException {
        BasicMatchingEducationExample example = new BasicMatchingEducationExample();
        MybatisUtils.convertObj2Example(basicMatchingEducation, example);
        basicMatchingEducationMapper.deleteByExample(example);
    }

    public boolean deleteBasicMatchingEducation(Integer id) throws SQLException {
        return basicMatchingEducationMapper.deleteByPrimaryKey(id) == 1;
    }

    public List<BasicMatchingEducation> basicMatchingEducationList(BasicMatchingEducation basicMatchingEducation) {
        BasicMatchingEducationExample example = new BasicMatchingEducationExample();
        MybatisUtils.convertObj2Example(basicMatchingEducation, example);
        return basicMatchingEducationMapper.selectByExample(example);
    }

    public void removeIds(List<Integer> ids) {
        BasicMatchingEducationExample example = new BasicMatchingEducationExample();
        BasicMatchingEducationExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        basicMatchingEducationMapper.deleteByExample(example);
    }

}
