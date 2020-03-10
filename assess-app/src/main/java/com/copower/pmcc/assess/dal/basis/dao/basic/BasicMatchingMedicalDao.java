package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingMedical;
import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingMedicalExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicMatchingMedicalMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:13
 * @Description:
 */
@Repository
public class BasicMatchingMedicalDao {

    @Autowired
    private BasicMatchingMedicalMapper basicMatchingMedicalMapper;

    public BasicMatchingMedical getBasicMatchingMedicalById(Integer id) {
        return basicMatchingMedicalMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicMatchingMedical(BasicMatchingMedical basicMatchingMedical) {
        basicMatchingMedicalMapper.insertSelective(basicMatchingMedical);
        return basicMatchingMedical.getId();
    }

    public boolean updateBasicMatchingMedical(BasicMatchingMedical basicMatchingMedical, boolean updateNull) {
        return updateNull ? basicMatchingMedicalMapper.updateByPrimaryKey(basicMatchingMedical) == 1 : basicMatchingMedicalMapper.updateByPrimaryKeySelective(basicMatchingMedical) == 1;
    }

    public boolean deleteBasicMatchingMedical(Integer id) {
        BasicMatchingMedical basicMatchingMedical = getBasicMatchingMedicalById(id);
        if (basicMatchingMedical == null) return false;
        basicMatchingMedical.setBisDelete(true);
        return basicMatchingMedicalMapper.updateByPrimaryKeySelective(basicMatchingMedical) == 1;
    }

    public List<BasicMatchingMedical> basicMatchingMedicalList(BasicMatchingMedical basicMatchingMedical) {
        BasicMatchingMedicalExample example = new BasicMatchingMedicalExample();
        BasicMatchingMedicalExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicMatchingMedical, criteria);
        example.setOrderByClause("id");
        return basicMatchingMedicalMapper.selectByExample(example);
    }

    public void removeIds(List<Integer> ids) {
        BasicMatchingMedicalExample example = new BasicMatchingMedicalExample();
        example.createCriteria().andBisDeleteEqualTo(false).andIdIn(ids);

        BasicMatchingMedical item = new BasicMatchingMedical();
        item.setBisDelete(true);
        basicMatchingMedicalMapper.updateByExampleSelective(item, example);
    }

}
