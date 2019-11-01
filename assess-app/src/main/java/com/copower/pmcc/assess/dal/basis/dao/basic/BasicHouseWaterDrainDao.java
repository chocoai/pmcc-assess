package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseWaterDrain;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseWaterDrainExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicHouseWaterDrainMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/12/11 11:45
 * @Description:
 */
@Repository
public class BasicHouseWaterDrainDao {

    @Autowired
    private BasicHouseWaterDrainMapper basicHouseWaterDrainMapper;

    public BasicHouseWaterDrain getBasicHouseWaterDrainById(Integer id) {
        return basicHouseWaterDrainMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicHouseWaterDrain(BasicHouseWaterDrain basicHouseWaterDrain) {
        basicHouseWaterDrainMapper.insertSelective(basicHouseWaterDrain);
        return basicHouseWaterDrain.getId();
    }

    public boolean updateBasicHouseWaterDrain(BasicHouseWaterDrain basicHouseWaterDrain, boolean updateNull) {
        return updateNull ? basicHouseWaterDrainMapper.updateByPrimaryKey(basicHouseWaterDrain) == 1 : basicHouseWaterDrainMapper.updateByPrimaryKeySelective(basicHouseWaterDrain) == 1;
    }

    public boolean deleteBasicHouseWaterDrain(Integer id) {
        BasicHouseWaterDrain basicHouseWaterDrain = getBasicHouseWaterDrainById(id);
        if (basicHouseWaterDrain == null) return false;
        basicHouseWaterDrain.setBisDelete(true);
        return basicHouseWaterDrainMapper.updateByPrimaryKeySelective(basicHouseWaterDrain) == 1;
    }

    public List<BasicHouseWaterDrain> basicHouseWaterDrainList(BasicHouseWaterDrain basicHouseWaterDrain) {
        BasicHouseWaterDrainExample example = new BasicHouseWaterDrainExample();
        BasicHouseWaterDrainExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicHouseWaterDrain, criteria);
        return basicHouseWaterDrainMapper.selectByExample(example);
    }

    /**
     * 获取数据条数
     *
     * @param houseId
     * @return
     */
    public int countByHouseId(Integer houseId) {
        BasicHouseWaterDrainExample example = new BasicHouseWaterDrainExample();
        example.createCriteria().andBisDeleteEqualTo(false).andHouseIdEqualTo(houseId);
        return basicHouseWaterDrainMapper.countByExample(example);
    }
}
