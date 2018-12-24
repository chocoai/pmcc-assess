package com.copower.pmcc.assess.dal.basic.dao;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouseEquipment;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouseEquipmentExample;
import com.copower.pmcc.assess.dal.basic.mapper.BasicHouseEquipmentMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:29
 * @Description:
 */
@Repository
public class BasicHouseEquipmentDao {

    @Autowired
    private BasicHouseEquipmentMapper basicHouseEquipmentMapper;

    public BasicHouseEquipment getBasicHouseEquipmentById(Integer id) throws SQLException {
        return basicHouseEquipmentMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicHouseEquipment(BasicHouseEquipment basicHouseEquipment) throws SQLException {
        basicHouseEquipmentMapper.insertSelective(basicHouseEquipment);
        return basicHouseEquipment.getId();
    }

    public boolean updateBasicHouseEquipment(BasicHouseEquipment basicHouseEquipment) throws SQLException {
        return basicHouseEquipmentMapper.updateByPrimaryKeySelective(basicHouseEquipment) == 1;
    }

    public boolean deleteBasicHouseEquipment(BasicHouseEquipment basicHouseEquipment) throws SQLException {
        BasicHouseEquipmentExample example = new BasicHouseEquipmentExample();
        MybatisUtils.convertObj2Example(basicHouseEquipment, example);
        return basicHouseEquipmentMapper.deleteByExample(example) > 0;
    }

    public boolean deleteBasicHouseEquipment(Integer id) throws SQLException {
        return basicHouseEquipmentMapper.deleteByPrimaryKey(id) == 1;
    }

    public List<BasicHouseEquipment> basicHouseEquipmentList(BasicHouseEquipment basicHouseEquipment) throws SQLException {
        BasicHouseEquipmentExample example = new BasicHouseEquipmentExample();
        MybatisUtils.convertObj2Example(basicHouseEquipment, example);
        return basicHouseEquipmentMapper.selectByExample(example);
    }

}
