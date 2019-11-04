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

    public BasicMatchingEducation getBasicMatchingEducationById(Integer id) {
        return basicMatchingEducationMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicMatchingEducation(BasicMatchingEducation basicMatchingEducation) {
        basicMatchingEducationMapper.insertSelective(basicMatchingEducation);
        return basicMatchingEducation.getId();
    }

    public boolean updateBasicMatchingEducation(BasicMatchingEducation basicMatchingEducation, boolean updateNull) {
        basicMatchingEducation.setBisDelete(false);
        return updateNull ? basicMatchingEducationMapper.updateByPrimaryKey(basicMatchingEducation) == 1 : basicMatchingEducationMapper.updateByPrimaryKeySelective(basicMatchingEducation) == 1;
    }

    public boolean deleteBasicMatchingEducation(Integer id) {
        BasicMatchingEducation basicMatchingEducation = getBasicMatchingEducationById(id);
        if (basicMatchingEducation == null) return false;
        basicMatchingEducation.setBisDelete(true);
        return basicMatchingEducationMapper.updateByPrimaryKeySelective(basicMatchingEducation) == 1;
    }

    public List<BasicMatchingEducation> basicMatchingEducationList(BasicMatchingEducation basicMatchingEducation) {
        BasicMatchingEducationExample example = new BasicMatchingEducationExample();
        BasicMatchingEducationExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicMatchingEducation, criteria);
        return basicMatchingEducationMapper.selectByExample(example);
    }

    public void removeIds(List<Integer> ids) {
        BasicMatchingEducationExample example = new BasicMatchingEducationExample();
        BasicMatchingEducationExample.Criteria criteria = example.createCriteria();
        criteria.andBisDeleteEqualTo(false).andIdIn(ids);

        BasicMatchingEducation item=new BasicMatchingEducation();
        item.setBisDelete(true);
        basicMatchingEducationMapper.updateByExampleSelective(item,example);
    }

}
