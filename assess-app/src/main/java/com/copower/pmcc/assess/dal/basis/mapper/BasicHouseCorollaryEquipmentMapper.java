package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseCorollaryEquipment;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseCorollaryEquipmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicHouseCorollaryEquipmentMapper {
    long countByExample(BasicHouseCorollaryEquipmentExample example);

    int deleteByExample(BasicHouseCorollaryEquipmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicHouseCorollaryEquipment record);

    int insertSelective(@Param("record") BasicHouseCorollaryEquipment record, @Param("selective") BasicHouseCorollaryEquipment.Column ... selective);

    List<BasicHouseCorollaryEquipment> selectByExample(BasicHouseCorollaryEquipmentExample example);

    BasicHouseCorollaryEquipment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicHouseCorollaryEquipment record, @Param("example") BasicHouseCorollaryEquipmentExample example, @Param("selective") BasicHouseCorollaryEquipment.Column ... selective);

    int updateByExample(@Param("record") BasicHouseCorollaryEquipment record, @Param("example") BasicHouseCorollaryEquipmentExample example);

    int updateByPrimaryKeySelective(@Param("record") BasicHouseCorollaryEquipment record, @Param("selective") BasicHouseCorollaryEquipment.Column ... selective);

    int updateByPrimaryKey(BasicHouseCorollaryEquipment record);

    int batchInsert(@Param("list") List<BasicHouseCorollaryEquipment> list);

    int batchInsertSelective(@Param("list") List<BasicHouseCorollaryEquipment> list, @Param("selective") BasicHouseCorollaryEquipment.Column ... selective);
}