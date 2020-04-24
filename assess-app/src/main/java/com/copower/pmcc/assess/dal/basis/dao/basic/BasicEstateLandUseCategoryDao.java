package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateLandUseCategory;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateLandUseCategoryExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicEstateLandUseCategoryMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 10:46
 * @Description:
 */
@Repository
public class BasicEstateLandUseCategoryDao {
    @Autowired
    private BasicEstateLandUseCategoryMapper basicEstateLandUseCategoryMapper;

    public BasicEstateLandUseCategory getBasicEstateLandUseCategoryById(Integer id) {
        return basicEstateLandUseCategoryMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicEstateLandUseCategory(BasicEstateLandUseCategory basicEstateLandUseCategory) {
        basicEstateLandUseCategoryMapper.insertSelective(basicEstateLandUseCategory);
        return basicEstateLandUseCategory.getId();
    }

    public boolean updateBasicEstateLandUseCategory(BasicEstateLandUseCategory basicEstateLandUseCategory, boolean updateNull) {
        return updateNull ? basicEstateLandUseCategoryMapper.updateByPrimaryKey(basicEstateLandUseCategory) == 1 : basicEstateLandUseCategoryMapper.updateByPrimaryKeySelective(basicEstateLandUseCategory) == 1;
    }

    public boolean deleteBasicEstateLandUseCategory(Integer id) {
        BasicEstateLandUseCategory basicEstateLandUseCategory = getBasicEstateLandUseCategoryById(id);
        if (basicEstateLandUseCategory == null) return false;
        basicEstateLandUseCategory.setBisDelete(true);
        return basicEstateLandUseCategoryMapper.updateByPrimaryKeySelective(basicEstateLandUseCategory) == 1;
    }

    public List<BasicEstateLandUseCategory> basicEstateLandUseCategoryList(BasicEstateLandUseCategory basicEstateLandUseCategory) {
        BasicEstateLandUseCategoryExample example = new BasicEstateLandUseCategoryExample();
        BasicEstateLandUseCategoryExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicEstateLandUseCategory, criteria);
        return basicEstateLandUseCategoryMapper.selectByExample(example);
    }
}
