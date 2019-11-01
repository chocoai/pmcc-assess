package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicUnitDecorate;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnitDecorateExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicUnitDecorateMapper;
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
public class BasicUnitDecorateDao {
    @Autowired
    private BasicUnitDecorateMapper basicUnitDecorateMapper;

    public BasicUnitDecorate getBasicUnitDecorateById(Integer id) {
        return basicUnitDecorateMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicUnitDecorate(BasicUnitDecorate basicUnitDecorate) {
        basicUnitDecorateMapper.insertSelective(basicUnitDecorate);
        return basicUnitDecorate.getId();
    }

    public boolean updateBasicUnitDecorate(BasicUnitDecorate basicUnitDecorate, boolean updateNull) {
        return updateNull ? basicUnitDecorateMapper.updateByPrimaryKey(basicUnitDecorate) == 1 : basicUnitDecorateMapper.updateByPrimaryKeySelective(basicUnitDecorate) == 1;
    }

    public boolean deleteBasicUnitDecorate(Integer id) {
        BasicUnitDecorate basicUnitDecorate = getBasicUnitDecorateById(id);
        if (basicUnitDecorate == null) return false;
        basicUnitDecorate.setBisDelete(true);
        return basicUnitDecorateMapper.updateByPrimaryKeySelective(basicUnitDecorate) == 1;
    }

    public List<BasicUnitDecorate> basicUnitDecorateList(BasicUnitDecorate basicUnitDecorate) {
        BasicUnitDecorateExample example = new BasicUnitDecorateExample();
        BasicUnitDecorateExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicUnitDecorate, criteria);
        return basicUnitDecorateMapper.selectByExample(example);
    }
}
