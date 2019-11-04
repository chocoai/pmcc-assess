package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicBuildingFunction;
import com.copower.pmcc.assess.dal.basis.entity.BasicBuildingFunctionExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicBuildingFunctionMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/30 11:24
 * @Description:
 */
@Repository
public class BasicBuildingFunctionDao {
    @Autowired
    private BasicBuildingFunctionMapper basicBuildingFunctionMapper;

    public BasicBuildingFunction getBasicBuildingFunctionById(Integer id) {
        return basicBuildingFunctionMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicBuildingFunction(BasicBuildingFunction basicBuildingFunction) {
        basicBuildingFunctionMapper.insertSelective(basicBuildingFunction);
        return basicBuildingFunction.getId();
    }

    public boolean updateBasicBuildingFunction(BasicBuildingFunction basicBuildingFunction, boolean updateNull) {
        basicBuildingFunction.setBisDelete(false);
        return updateNull ? basicBuildingFunctionMapper.updateByPrimaryKey(basicBuildingFunction) == 1 : basicBuildingFunctionMapper.updateByPrimaryKeySelective(basicBuildingFunction) == 1;
    }

    public void removeBasicBuildingFunction(BasicBuildingFunction basicBuildingFunction) {
        BasicBuildingFunctionExample example = new BasicBuildingFunctionExample();
        BasicBuildingFunctionExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicBuildingFunction, criteria);
        basicBuildingFunctionMapper.deleteByExample(example);
    }

    public boolean deleteBasicBuildingFunction(Integer id) {
        BasicBuildingFunction buildingFunction = getBasicBuildingFunctionById(id);
        if (buildingFunction == null) return false;
        return basicBuildingFunctionMapper.updateByPrimaryKeySelective(buildingFunction) == 1;
    }

    public List<BasicBuildingFunction> basicBuildingFunctionList(BasicBuildingFunction basicBuildingFunction) {
        BasicBuildingFunctionExample example = new BasicBuildingFunctionExample();
        BasicBuildingFunctionExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicBuildingFunction, criteria);
        return basicBuildingFunctionMapper.selectByExample(example);
    }
}
