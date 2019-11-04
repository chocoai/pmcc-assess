package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseCorollaryEquipment;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseCorollaryEquipmentExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicHouseCorollaryEquipmentMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:32
 * @Description:
 */
@Repository
public class BasicHouseCorollaryEquipmentDao {

    @Autowired
    private BasicHouseCorollaryEquipmentMapper basicHouseCorollaryEquipmentMapper;

    public BasicHouseCorollaryEquipment getBasicHouseCorollaryEquipmentById(Integer id) {
        return basicHouseCorollaryEquipmentMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicHouseCorollaryEquipment(BasicHouseCorollaryEquipment basicHouseCorollaryEquipment) {
        basicHouseCorollaryEquipmentMapper.insertSelective(basicHouseCorollaryEquipment);
        return basicHouseCorollaryEquipment.getId();
    }

    public boolean updateBasicHouseCorollaryEquipment(BasicHouseCorollaryEquipment basicHouseCorollaryEquipment, boolean updateNull) {
        basicHouseCorollaryEquipment.setBisDelete(false);
        return updateNull ? basicHouseCorollaryEquipmentMapper.updateByPrimaryKey(basicHouseCorollaryEquipment) == 1 : basicHouseCorollaryEquipmentMapper.updateByPrimaryKeySelective(basicHouseCorollaryEquipment) == 1;
    }

    public boolean deleteBasicHouseCorollaryEquipment(Integer id) {
        BasicHouseCorollaryEquipment basicHouseCorollaryEquipment = getBasicHouseCorollaryEquipmentById(id);
        if (basicHouseCorollaryEquipment == null) return false;
        basicHouseCorollaryEquipment.setBisDelete(true);
        return basicHouseCorollaryEquipmentMapper.updateByPrimaryKeySelective(basicHouseCorollaryEquipment) == 1;
    }

    public List<BasicHouseCorollaryEquipment> basicHouseCorollaryEquipmentList(BasicHouseCorollaryEquipment basicHouseCorollaryEquipment) {
        BasicHouseCorollaryEquipmentExample example = new BasicHouseCorollaryEquipmentExample();
        BasicHouseCorollaryEquipmentExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicHouseCorollaryEquipment, criteria);
        return basicHouseCorollaryEquipmentMapper.selectByExample(example);
    }

}
