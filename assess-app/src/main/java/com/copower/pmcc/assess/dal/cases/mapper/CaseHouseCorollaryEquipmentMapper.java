package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseCorollaryEquipment;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseCorollaryEquipmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseHouseCorollaryEquipmentMapper {
    int countByExample(CaseHouseCorollaryEquipmentExample example);

    int deleteByExample(CaseHouseCorollaryEquipmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseHouseCorollaryEquipment record);

    int insertSelective(CaseHouseCorollaryEquipment record);

    List<CaseHouseCorollaryEquipment> selectByExample(CaseHouseCorollaryEquipmentExample example);

    CaseHouseCorollaryEquipment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseHouseCorollaryEquipment record, @Param("example") CaseHouseCorollaryEquipmentExample example);

    int updateByExample(@Param("record") CaseHouseCorollaryEquipment record, @Param("example") CaseHouseCorollaryEquipmentExample example);

    int updateByPrimaryKeySelective(CaseHouseCorollaryEquipment record);

    int updateByPrimaryKey(CaseHouseCorollaryEquipment record);
}