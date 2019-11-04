package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingFinance;
import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingFinanceExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicMatchingFinanceMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:03
 * @Description:
 */
@Repository
public class BasicMatchingFinanceDao {

    @Autowired
    private BasicMatchingFinanceMapper basicMatchingFinanceMapper;

    public BasicMatchingFinance getBasicMatchingFinanceById(Integer id) {
        return basicMatchingFinanceMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicMatchingFinance(BasicMatchingFinance basicMatchingFinance) {
        basicMatchingFinanceMapper.insertSelective(basicMatchingFinance);
        return basicMatchingFinance.getId();
    }

    public boolean updateBasicMatchingFinance(BasicMatchingFinance basicMatchingFinance, boolean updateNull) {
        basicMatchingFinance.setBisDelete(false);
        return updateNull ? basicMatchingFinanceMapper.updateByPrimaryKey(basicMatchingFinance) == 1 : basicMatchingFinanceMapper.updateByPrimaryKeySelective(basicMatchingFinance) == 1;
    }

    public boolean deleteBasicMatchingFinance(Integer id) {
        BasicMatchingFinance basicMatchingFinance = getBasicMatchingFinanceById(id);
        if (basicMatchingFinance == null) return false;
        basicMatchingFinance.setBisDelete(true);
        return basicMatchingFinanceMapper.updateByPrimaryKeySelective(basicMatchingFinance) == 1;
    }

    public List<BasicMatchingFinance> basicMatchingFinanceList(BasicMatchingFinance basicMatchingFinance) {
        BasicMatchingFinanceExample example = new BasicMatchingFinanceExample();
        BasicMatchingFinanceExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicMatchingFinance, criteria);
        example.setOrderByClause("id");
        return basicMatchingFinanceMapper.selectByExample(example);
    }

    public void removeIds(List<Integer> ids) {
        BasicMatchingFinanceExample example = new BasicMatchingFinanceExample();
        BasicMatchingFinanceExample.Criteria criteria = example.createCriteria();
        criteria.andBisDeleteEqualTo(false).andIdIn(ids);

        BasicMatchingFinance item = new BasicMatchingFinance();
        item.setBisDelete(true);
        basicMatchingFinanceMapper.updateByExampleSelective(item, example);
    }

}
