package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicUnitStairs;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnitStairsExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicUnitStairsMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2020-4-15.
 */
@Repository
public class BasicUnitStairsDao {

    @Autowired
    private BasicUnitStairsMapper basicUnitStairsMapper;

    public BasicUnitStairs getBasicUnitStairsById(Integer id) {
        return basicUnitStairsMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicUnitStairs(BasicUnitStairs basicUnitStairs) {
        basicUnitStairsMapper.insertSelective(basicUnitStairs);
        return basicUnitStairs.getId();
    }

    public boolean updateBasicUnitStairs(BasicUnitStairs basicUnitStairs, boolean updateNull) {
        return updateNull ? basicUnitStairsMapper.updateByPrimaryKey(basicUnitStairs) == 1 : basicUnitStairsMapper.updateByPrimaryKeySelective(basicUnitStairs) == 1;
    }

    public boolean deleteBasicUnitStairs(Integer id) {
        BasicUnitStairs basicUnitStairs = getBasicUnitStairsById(id);
        if (basicUnitStairs == null) return false;
        basicUnitStairs.setBisDelete(true);
        return basicUnitStairsMapper.updateByPrimaryKeySelective(basicUnitStairs) == 1;
    }

    public List<BasicUnitStairs> basicUnitStairsList(BasicUnitStairs basicUnitStairs) {
        BasicUnitStairsExample example = new BasicUnitStairsExample();
        BasicUnitStairsExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicUnitStairs, criteria);
        return basicUnitStairsMapper.selectByExample(example);
    }
    
}
