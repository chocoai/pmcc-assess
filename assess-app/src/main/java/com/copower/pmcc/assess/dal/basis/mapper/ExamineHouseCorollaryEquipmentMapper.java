package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseCorollaryEquipment;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseCorollaryEquipmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineHouseCorollaryEquipmentMapper {
    int countByExample(ExamineHouseCorollaryEquipmentExample example);

    int deleteByExample(ExamineHouseCorollaryEquipmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineHouseCorollaryEquipment record);

    int insertSelective(ExamineHouseCorollaryEquipment record);

    List<ExamineHouseCorollaryEquipment> selectByExample(ExamineHouseCorollaryEquipmentExample example);

    ExamineHouseCorollaryEquipment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineHouseCorollaryEquipment record, @Param("example") ExamineHouseCorollaryEquipmentExample example);

    int updateByExample(@Param("record") ExamineHouseCorollaryEquipment record, @Param("example") ExamineHouseCorollaryEquipmentExample example);

    int updateByPrimaryKeySelective(ExamineHouseCorollaryEquipment record);

    int updateByPrimaryKey(ExamineHouseCorollaryEquipment record);
}