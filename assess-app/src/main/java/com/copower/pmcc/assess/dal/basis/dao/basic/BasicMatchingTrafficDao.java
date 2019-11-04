package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingTraffic;
import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingTrafficExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicMatchingTrafficMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:16
 * @Description:
 */
@Repository
public class BasicMatchingTrafficDao {

    @Autowired
    private BasicMatchingTrafficMapper basicMatchingTrafficMapper;

    public BasicMatchingTraffic getBasicMatchingTrafficById(Integer id) {
        return basicMatchingTrafficMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicMatchingTraffic(BasicMatchingTraffic basicMatchingTraffic) {
        basicMatchingTrafficMapper.insertSelective(basicMatchingTraffic);
        return basicMatchingTraffic.getId();
    }

    public boolean updateBasicMatchingTraffic(BasicMatchingTraffic basicMatchingTraffic, boolean updateNull) {
        basicMatchingTraffic.setBisDelete(false);
        return updateNull ? basicMatchingTrafficMapper.updateByPrimaryKey(basicMatchingTraffic) == 1 : basicMatchingTrafficMapper.updateByPrimaryKeySelective(basicMatchingTraffic) == 1;
    }

    public boolean deleteBasicMatchingTraffic(Integer id) {
        BasicMatchingTraffic basicMatchingTraffic = getBasicMatchingTrafficById(id);
        if (basicMatchingTraffic == null) return false;
        basicMatchingTraffic.setBisDelete(true);
        return basicMatchingTrafficMapper.updateByPrimaryKeySelective(basicMatchingTraffic) == 1;
    }

    public List<BasicMatchingTraffic> basicMatchingTrafficList(BasicMatchingTraffic basicMatchingTraffic) {
        BasicMatchingTrafficExample example = new BasicMatchingTrafficExample();
        BasicMatchingTrafficExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicMatchingTraffic, criteria);
        example.setOrderByClause("id");
        return basicMatchingTrafficMapper.selectByExample(example);
    }

    public void remove(List<Integer> ids) {
        BasicMatchingTrafficExample example = new BasicMatchingTrafficExample();
        BasicMatchingTrafficExample.Criteria criteria = example.createCriteria();
        criteria.andBisDeleteEqualTo(false).andIdIn(ids);

        BasicMatchingTraffic item = new BasicMatchingTraffic();
        item.setBisDelete(true);
        basicMatchingTrafficMapper.updateByExampleSelective(item, example);
    }

}
