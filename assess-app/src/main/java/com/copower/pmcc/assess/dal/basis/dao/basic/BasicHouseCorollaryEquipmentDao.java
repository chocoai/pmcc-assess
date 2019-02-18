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

    public BasicHouseCorollaryEquipment getBasicHouseCorollaryEquipmentById(Integer id)throws SQLException {
        return basicHouseCorollaryEquipmentMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicHouseCorollaryEquipment(BasicHouseCorollaryEquipment basicHouseCorollaryEquipment)throws SQLException{
        basicHouseCorollaryEquipmentMapper.insertSelective(basicHouseCorollaryEquipment);
        return basicHouseCorollaryEquipment.getId();
    }

    public boolean updateBasicHouseCorollaryEquipment(BasicHouseCorollaryEquipment basicHouseCorollaryEquipment)throws SQLException{
        return basicHouseCorollaryEquipmentMapper.updateByPrimaryKeySelective(basicHouseCorollaryEquipment)==1;
    }

    public void removeBasicHouseCorollaryEquipment(BasicHouseCorollaryEquipment basicHouseCorollaryEquipment)throws SQLException{
        BasicHouseCorollaryEquipmentExample example = new BasicHouseCorollaryEquipmentExample();
        MybatisUtils.convertObj2Example(basicHouseCorollaryEquipment, example);
        basicHouseCorollaryEquipmentMapper.deleteByExample(example);
    }

    public boolean deleteBasicHouseCorollaryEquipment(Integer id)throws SQLException{
        return  basicHouseCorollaryEquipmentMapper.deleteByPrimaryKey(id)==1;
    }

    public List<BasicHouseCorollaryEquipment> basicHouseCorollaryEquipmentList(BasicHouseCorollaryEquipment basicHouseCorollaryEquipment){
        BasicHouseCorollaryEquipmentExample example = new BasicHouseCorollaryEquipmentExample();
        MybatisUtils.convertObj2Example(basicHouseCorollaryEquipment, example);
        return basicHouseCorollaryEquipmentMapper.selectByExample(example);
    }
    
}
