package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseEquipment;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseEquipmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineHouseEquipmentMapper {
    int countByExample(ExamineHouseEquipmentExample example);

    int deleteByExample(ExamineHouseEquipmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineHouseEquipment record);

    int insertSelective(ExamineHouseEquipment record);

    List<ExamineHouseEquipment> selectByExample(ExamineHouseEquipmentExample example);

    ExamineHouseEquipment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineHouseEquipment record, @Param("example") ExamineHouseEquipmentExample example);

    int updateByExample(@Param("record") ExamineHouseEquipment record, @Param("example") ExamineHouseEquipmentExample example);

    int updateByPrimaryKeySelective(ExamineHouseEquipment record);

    int updateByPrimaryKey(ExamineHouseEquipment record);
}