package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicUnitHuxing;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnitHuxingExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicUnitHuxingMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/5 16:11
 * @Description:
 */
@Repository
public class BasicUnitHuxingDao {
    @Autowired
    private BasicUnitHuxingMapper basicUnitHuxingMapper;

    public BasicUnitHuxing getBasicUnitHuxingById(Integer id) {
        return basicUnitHuxingMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicUnitHuxing(BasicUnitHuxing basicUnitHuxing) {
        basicUnitHuxingMapper.insertSelective(basicUnitHuxing);
        return basicUnitHuxing.getId();
    }

    public boolean updateBasicUnitHuxing(BasicUnitHuxing basicUnitHuxing, boolean updateNull) {
        return updateNull ? basicUnitHuxingMapper.updateByPrimaryKey(basicUnitHuxing) == 1 : basicUnitHuxingMapper.updateByPrimaryKeySelective(basicUnitHuxing) == 1;
    }

    public boolean deleteBasicUnitHuxing(Integer id) {
        BasicUnitHuxing basicUnitHuxing = getBasicUnitHuxingById(id);
        if (basicUnitHuxing == null) return false;
        basicUnitHuxing.setBisDelete(true);
        return basicUnitHuxingMapper.updateByPrimaryKeySelective(basicUnitHuxing) == 1;
    }

    public List<BasicUnitHuxing> basicUnitHuxingList(BasicUnitHuxing basicUnitHuxing) {
        BasicUnitHuxingExample example = new BasicUnitHuxingExample();
        BasicUnitHuxingExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicUnitHuxing, criteria);
        return basicUnitHuxingMapper.selectByExample(example);
    }

    public List<BasicUnitHuxing> getHuxingList(Integer applyBatchId, String name) {
        BasicUnitHuxingExample example = new BasicUnitHuxingExample();
        BasicUnitHuxingExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        criteria.andApplyBatchIdEqualTo(applyBatchId);
        if (StringUtils.isNotBlank(name))
            criteria.andNameLike(String.format("%%%s%%", name));
        return basicUnitHuxingMapper.selectByExample(example);
    }
}
