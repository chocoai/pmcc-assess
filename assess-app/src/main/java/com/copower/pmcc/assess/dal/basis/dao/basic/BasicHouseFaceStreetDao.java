package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseFaceStreet;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseFaceStreetExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicHouseFaceStreetMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:26
 * @Description:
 */
@Repository
public class BasicHouseFaceStreetDao {

    @Autowired
    private BasicHouseFaceStreetMapper basicHouseFaceStreetMapper;

    public BasicHouseFaceStreet getBasicHouseFaceStreetById(Integer id) {
        return basicHouseFaceStreetMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicHouseFaceStreet(BasicHouseFaceStreet basicHouseFaceStreet) {
        basicHouseFaceStreetMapper.insertSelective(basicHouseFaceStreet);
        return basicHouseFaceStreet.getId();
    }

    public boolean updateBasicHouseFaceStreet(BasicHouseFaceStreet basicHouseFaceStreet, boolean updateNull) {
        return updateNull ? basicHouseFaceStreetMapper.updateByPrimaryKey(basicHouseFaceStreet) == 1 : basicHouseFaceStreetMapper.updateByPrimaryKeySelective(basicHouseFaceStreet) == 1;
    }

    public boolean deleteBasicHouseFaceStreet(Integer id) {
        BasicHouseFaceStreet basicHouseFaceStreet = getBasicHouseFaceStreetById(id);
        if (basicHouseFaceStreet == null) return false;
        basicHouseFaceStreet.setBisDelete(true);
        return basicHouseFaceStreetMapper.updateByPrimaryKeySelective(basicHouseFaceStreet) == 1;
    }

    public List<BasicHouseFaceStreet> basicHouseFaceStreetList(BasicHouseFaceStreet basicHouseFaceStreet) {
        BasicHouseFaceStreetExample example = new BasicHouseFaceStreetExample();
        BasicHouseFaceStreetExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicHouseFaceStreet, criteria);
        return basicHouseFaceStreetMapper.selectByExample(example);
    }

    /**
     * 获取数据条数
     *
     * @param houseId
     * @return
     */
    public int countByHouseId(Integer houseId) {
        BasicHouseFaceStreetExample example = new BasicHouseFaceStreetExample();
        example.createCriteria().andBisDeleteEqualTo(false).andHouseIdEqualTo(houseId);
        return (int)basicHouseFaceStreetMapper.countByExample(example);
    }
}
