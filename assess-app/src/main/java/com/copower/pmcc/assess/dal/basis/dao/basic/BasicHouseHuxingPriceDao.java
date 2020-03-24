package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseHuxingPrice;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseHuxingPriceExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicHouseHuxingPriceMapper;
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
public class BasicHouseHuxingPriceDao {
    @Autowired
    private BasicHouseHuxingPriceMapper basicHouseHuxingPriceMapper;

    public BasicHouseHuxingPrice getBasicHouseHuxingPriceById(Integer id) {
        return basicHouseHuxingPriceMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicHouseHuxingPrice(BasicHouseHuxingPrice basicHouseHuxing) {
        basicHouseHuxingPriceMapper.insertSelective(basicHouseHuxing);
        return basicHouseHuxing.getId();
    }

    public boolean updateBasicHouseHuxingPrice(BasicHouseHuxingPrice basicHouseHuxing, boolean updateNull) {
        return updateNull ? basicHouseHuxingPriceMapper.updateByPrimaryKey(basicHouseHuxing) == 1 : basicHouseHuxingPriceMapper.updateByPrimaryKeySelective(basicHouseHuxing) == 1;
    }

    public boolean deleteBasicHouseHuxingPrice(Integer id) {
        return basicHouseHuxingPriceMapper.deleteByPrimaryKey(id) == 1;
    }

    public List<BasicHouseHuxingPrice> basicHouseHuxingList(BasicHouseHuxingPrice basicHouseHuxing) {
        BasicHouseHuxingPriceExample example = new BasicHouseHuxingPriceExample();
        BasicHouseHuxingPriceExample.Criteria criteria = example.createCriteria();
        MybatisUtils.convertObj2Criteria(basicHouseHuxing, criteria);
        return basicHouseHuxingPriceMapper.selectByExample(example);
    }

}
