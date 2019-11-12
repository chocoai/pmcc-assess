package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicUnitHuxingPrice;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnitHuxingPriceExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicUnitHuxingPriceMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/7 09:52
 * @Description:
 */
@Repository
public class BasicUnitHuxingPriceDao {
    @Autowired
    private BasicUnitHuxingPriceMapper basicUnitHuxingPriceMapper;

    public BasicUnitHuxingPrice getBasicUnitHuxingPriceById(Integer id) {
        return basicUnitHuxingPriceMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicUnitHuxingPrice(BasicUnitHuxingPrice basicUnitHuxing) {
        basicUnitHuxingPriceMapper.insertSelective(basicUnitHuxing);
        return basicUnitHuxing.getId();
    }

    public boolean updateBasicUnitHuxingPrice(BasicUnitHuxingPrice basicUnitHuxing, boolean updateNull) {
        return updateNull ? basicUnitHuxingPriceMapper.updateByPrimaryKey(basicUnitHuxing) == 1 : basicUnitHuxingPriceMapper.updateByPrimaryKeySelective(basicUnitHuxing) == 1;
    }

    public boolean deleteBasicUnitHuxingPrice(Integer id) {
        return basicUnitHuxingPriceMapper.deleteByPrimaryKey(id) == 1;
    }

    public List<BasicUnitHuxingPrice> basicUnitHuxingList(BasicUnitHuxingPrice basicUnitHuxing) {
        BasicUnitHuxingPriceExample example = new BasicUnitHuxingPriceExample();
        BasicUnitHuxingPriceExample.Criteria criteria = example.createCriteria();
        MybatisUtils.convertObj2Criteria(basicUnitHuxing, criteria);
        return basicUnitHuxingPriceMapper.selectByExample(example);
    }

}
