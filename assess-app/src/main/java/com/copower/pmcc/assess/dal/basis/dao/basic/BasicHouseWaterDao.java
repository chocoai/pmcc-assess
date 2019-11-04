package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseWater;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseWaterExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicHouseWaterMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:19
 * @Description:
 */
@Repository
public class BasicHouseWaterDao {

    @Autowired
    private BasicHouseWaterMapper basicHouseWaterMapper;

    public BasicHouseWater getBasicHouseWaterById(Integer id) {
        return basicHouseWaterMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicHouseWater(BasicHouseWater basicHouseWater) {
        basicHouseWaterMapper.insertSelective(basicHouseWater);
        return basicHouseWater.getId();
    }

    public boolean updateBasicHouseWater(BasicHouseWater basicHouseWater, boolean updateNull) {
        basicHouseWater.setBisDelete(false);
        return updateNull ? basicHouseWaterMapper.updateByPrimaryKey(basicHouseWater) == 1 : basicHouseWaterMapper.updateByPrimaryKeySelective(basicHouseWater) == 1;
    }

    public boolean deleteBasicHouseWater(Integer id) {
        BasicHouseWater basicHouseWater = getBasicHouseWaterById(id);
        if (basicHouseWater == null) return false;
        basicHouseWater.setBisDelete(true);
        return basicHouseWaterMapper.updateByPrimaryKeySelective(basicHouseWater) == 1;
    }

    public List<BasicHouseWater> basicHouseWaterList(BasicHouseWater basicHouseWater) {
        BasicHouseWaterExample example = new BasicHouseWaterExample();
        BasicHouseWaterExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicHouseWater, criteria);
        return basicHouseWaterMapper.selectByExample(example);
    }

    /**
     * 获取数据条数
     *
     * @param houseId
     * @return
     */
    public int countByHouseId(Integer houseId) {
        BasicHouseWaterExample example = new BasicHouseWaterExample();
        example.createCriteria().andBisDeleteEqualTo(false).andHouseIdEqualTo(houseId);
        return basicHouseWaterMapper.countByExample(example);
    }
}
