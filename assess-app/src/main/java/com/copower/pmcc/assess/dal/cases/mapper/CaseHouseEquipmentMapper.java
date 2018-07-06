package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseEquipment;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseEquipmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseHouseEquipmentMapper {
    int countByExample(CaseHouseEquipmentExample example);

    int deleteByExample(CaseHouseEquipmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseHouseEquipment record);

    int insertSelective(CaseHouseEquipment record);

    List<CaseHouseEquipment> selectByExample(CaseHouseEquipmentExample example);

    CaseHouseEquipment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseHouseEquipment record, @Param("example") CaseHouseEquipmentExample example);

    int updateByExample(@Param("record") CaseHouseEquipment record, @Param("example") CaseHouseEquipmentExample example);

    int updateByPrimaryKeySelective(CaseHouseEquipment record);

    int updateByPrimaryKey(CaseHouseEquipment record);
}