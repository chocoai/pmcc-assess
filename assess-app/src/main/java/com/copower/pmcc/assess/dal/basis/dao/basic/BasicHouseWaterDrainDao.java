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

    public BasicHouseWaterDrain getBasicHouseWaterDrainById(Integer id) throws SQLException {
        return basicHouseWaterDrainMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicHouseWaterDrain(BasicHouseWaterDrain basicHouseWaterDrain) throws SQLException {
        basicHouseWaterDrainMapper.insertSelective(basicHouseWaterDrain);
        return basicHouseWaterDrain.getId();
    }

    public boolean updateBasicHouseWaterDrain(BasicHouseWaterDrain basicHouseWaterDrain, boolean updateNull) throws SQLException {
        return updateNull ? basicHouseWaterDrainMapper.updateByPrimaryKey(basicHouseWaterDrain) == 1 : basicHouseWaterDrainMapper.updateByPrimaryKeySelective(basicHouseWaterDrain) == 1;
    }

    public boolean deleteBasicHouseWaterDrain(BasicHouseWaterDrain basicHouseWaterDrain) throws SQLException {
        BasicHouseWaterDrainExample example = new BasicHouseWaterDrainExample();
        MybatisUtils.convertObj2Example(basicHouseWaterDrain, example);
        return basicHouseWaterDrainMapper.deleteByExample(example) > 0;
    }

    public boolean deleteBasicHouseWaterDrain(Integer id) throws SQLException {
        return basicHouseWaterDrainMapper.deleteByPrimaryKey(id) == 1;
    }

    public List<BasicHouseWaterDrain> basicHouseWaterDrainList(BasicHouseWaterDrain basicHouseWaterDrain) throws SQLException {
        BasicHouseWaterDrainExample example = new BasicHouseWaterDrainExample();
        MybatisUtils.convertObj2Example(basicHouseWaterDrain, example);
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
        example.createCriteria().andHouseIdEqualTo(houseId);
        return basicHouseWaterDrainMapper.countByExample(example);
    }
}
