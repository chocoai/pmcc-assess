package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateLandUseType;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateLandUseTypeExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicEstateLandUseTypeMapper;
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
public class BasicEstateLandUseTypeDao {
    @Autowired
    private BasicEstateLandUseTypeMapper basicEstateLandUseTypeMapper;

    public BasicEstateLandUseType getBasicEstateLandUseTypeById(Integer id) {
        return basicEstateLandUseTypeMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicEstateLandUseType(BasicEstateLandUseType basicEstateLandUseType) {
        basicEstateLandUseTypeMapper.insertSelective(basicEstateLandUseType);
        return basicEstateLandUseType.getId();
    }

    public boolean updateBasicEstateLandUseType(BasicEstateLandUseType basicEstateLandUseType, boolean updateNull) {
        return updateNull ? basicEstateLandUseTypeMapper.updateByPrimaryKey(basicEstateLandUseType) == 1 : basicEstateLandUseTypeMapper.updateByPrimaryKeySelective(basicEstateLandUseType) == 1;
    }

    public boolean deleteBasicEstateLandUseType(Integer id) {
        BasicEstateLandUseType basicEstateLandUseType = getBasicEstateLandUseTypeById(id);
        if (basicEstateLandUseType == null) return false;
        basicEstateLandUseType.setBisDelete(true);
        return basicEstateLandUseTypeMapper.updateByPrimaryKeySelective(basicEstateLandUseType) == 1;
    }

    public List<BasicEstateLandUseType> basicEstateLandUseTypeList(BasicEstateLandUseType basicEstateLandUseType) {
        BasicEstateLandUseTypeExample example = new BasicEstateLandUseTypeExample();
        BasicEstateLandUseTypeExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicEstateLandUseType, criteria);
        return basicEstateLandUseTypeMapper.selectByExample(example);
    }
}
