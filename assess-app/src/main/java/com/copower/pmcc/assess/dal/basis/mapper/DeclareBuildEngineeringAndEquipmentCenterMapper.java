package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEngineeringAndEquipmentCenter;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEngineeringAndEquipmentCenterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareBuildEngineeringAndEquipmentCenterMapper {
    int countByExample(DeclareBuildEngineeringAndEquipmentCenterExample example);

    int deleteByExample(DeclareBuildEngineeringAndEquipmentCenterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareBuildEngineeringAndEquipmentCenter record);

    int insertSelective(DeclareBuildEngineeringAndEquipmentCenter record);

    List<DeclareBuildEngineeringAndEquipmentCenter> selectByExample(DeclareBuildEngineeringAndEquipmentCenterExample example);

    DeclareBuildEngineeringAndEquipmentCenter selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareBuildEngineeringAndEquipmentCenter record, @Param("example") DeclareBuildEngineeringAndEquipmentCenterExample example);

    int updateByExample(@Param("record") DeclareBuildEngineeringAndEquipmentCenter record, @Param("example") DeclareBuildEngineeringAndEquipmentCenterExample example);

    int updateByPrimaryKeySelective(DeclareBuildEngineeringAndEquipmentCenter record);

    int updateByPrimaryKey(DeclareBuildEngineeringAndEquipmentCenter record);
}