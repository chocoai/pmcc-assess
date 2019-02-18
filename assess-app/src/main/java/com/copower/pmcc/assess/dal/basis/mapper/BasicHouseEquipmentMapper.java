package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseEquipment;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseEquipmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicHouseEquipmentMapper {
    int countByExample(BasicHouseEquipmentExample example);

    int deleteByExample(BasicHouseEquipmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicHouseEquipment record);

    int insertSelective(BasicHouseEquipment record);

    List<BasicHouseEquipment> selectByExample(BasicHouseEquipmentExample example);

    BasicHouseEquipment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicHouseEquipment record, @Param("example") BasicHouseEquipmentExample example);

    int updateByExample(@Param("record") BasicHouseEquipment record, @Param("example") BasicHouseEquipmentExample example);

    int updateByPrimaryKeySelective(BasicHouseEquipment record);

    int updateByPrimaryKey(BasicHouseEquipment record);
}