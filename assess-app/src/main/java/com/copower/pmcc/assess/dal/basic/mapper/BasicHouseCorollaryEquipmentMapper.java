package com.copower.pmcc.assess.dal.basic.mapper;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouseCorollaryEquipment;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouseCorollaryEquipmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicHouseCorollaryEquipmentMapper {
    int countByExample(BasicHouseCorollaryEquipmentExample example);

    int deleteByExample(BasicHouseCorollaryEquipmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicHouseCorollaryEquipment record);

    int insertSelective(BasicHouseCorollaryEquipment record);

    List<BasicHouseCorollaryEquipment> selectByExample(BasicHouseCorollaryEquipmentExample example);

    BasicHouseCorollaryEquipment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicHouseCorollaryEquipment record, @Param("example") BasicHouseCorollaryEquipmentExample example);

    int updateByExample(@Param("record") BasicHouseCorollaryEquipment record, @Param("example") BasicHouseCorollaryEquipmentExample example);

    int updateByPrimaryKeySelective(BasicHouseCorollaryEquipment record);

    int updateByPrimaryKey(BasicHouseCorollaryEquipment record);
}