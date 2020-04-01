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
 * @Date: 2018/11/6 11:19
 * @Description:
 */
@Repository
public class BasicHouseHuxingPriceDao {
    @Autowired
    private BasicHouseHuxingPriceMapper basicHouseHuxingPriceMapper;

    public BasicHouseHuxingPrice getBasicHouseHuxingPriceById(Integer id) {
        return basicHouseHuxingPriceMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicHouseHuxingPrice(BasicHouseHuxingPrice basicHouseHuxingPrice) {
        basicHouseHuxingPriceMapper.insertSelective(basicHouseHuxingPrice);
        return basicHouseHuxingPrice.getId();
    }

    public boolean updateBasicHouseHuxingPrice(BasicHouseHuxingPrice basicHouseHuxingPrice, boolean updateNull) {
        return updateNull ? basicHouseHuxingPriceMapper.updateByPrimaryKey(basicHouseHuxingPrice) == 1 : basicHouseHuxingPriceMapper.updateByPrimaryKeySelective(basicHouseHuxingPrice) == 1;
    }

    public boolean deleteBasicHouseHuxingPrice(Integer id) {
        return basicHouseHuxingPriceMapper.deleteByPrimaryKey(id) == 1;
    }

    public List<BasicHouseHuxingPrice> basicHouseHuxingPriceList(BasicHouseHuxingPrice basicHouseHuxingPrice) {
        BasicHouseHuxingPriceExample example = new BasicHouseHuxingPriceExample();
        BasicHouseHuxingPriceExample.Criteria criteria = example.createCriteria();
        MybatisUtils.convertObj2Criteria(basicHouseHuxingPrice, criteria);
        return basicHouseHuxingPriceMapper.selectByExample(example);
    }

}
