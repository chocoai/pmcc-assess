package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicUnitCommonPart;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnitCommonPartExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicUnitCommonPartMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/5 16:20
 * @Description:
 */
@Repository
public class BasicUnitCommonPartDao {
    @Autowired
    private BasicUnitCommonPartMapper basicUnitCommonPartMapper;

    public BasicUnitCommonPart getBasicUnitCommonPartById(Integer id) {
        return basicUnitCommonPartMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicUnitCommonPart(BasicUnitCommonPart basicUnitCommonPart) {
        basicUnitCommonPartMapper.insertSelective(basicUnitCommonPart);
        return basicUnitCommonPart.getId();
    }

    public boolean updateBasicUnitCommonPart(BasicUnitCommonPart basicUnitCommonPart, boolean updateNull) {
        return updateNull ? basicUnitCommonPartMapper.updateByPrimaryKey(basicUnitCommonPart) == 1 : basicUnitCommonPartMapper.updateByPrimaryKeySelective(basicUnitCommonPart) == 1;
    }

    public boolean deleteBasicUnitCommonPart(Integer id) {
        BasicUnitCommonPart basicUnitCommonPart = getBasicUnitCommonPartById(id);
        if (basicUnitCommonPart == null) return false;
        basicUnitCommonPart.setBisDelete(true);
        return basicUnitCommonPartMapper.updateByPrimaryKeySelective(basicUnitCommonPart) == 1;
    }

    public List<BasicUnitCommonPart> basicUnitCommonPartList(BasicUnitCommonPart basicUnitCommonPart) {
        BasicUnitCommonPartExample example = new BasicUnitCommonPartExample();
        BasicUnitCommonPartExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicUnitCommonPart, criteria);
        return basicUnitCommonPartMapper.selectByExample(example);
    }
}
