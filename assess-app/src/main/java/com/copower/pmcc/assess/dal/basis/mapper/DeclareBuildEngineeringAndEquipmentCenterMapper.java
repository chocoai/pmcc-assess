package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEngineeringAndEquipmentCenter;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEngineeringAndEquipmentCenterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareBuildEngineeringAndEquipmentCenterMapper {
    long countByExample(DeclareBuildEngineeringAndEquipmentCenterExample example);

    int deleteByExample(DeclareBuildEngineeringAndEquipmentCenterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareBuildEngineeringAndEquipmentCenter record);

    int insertSelective(@Param("record") DeclareBuildEngineeringAndEquipmentCenter record, @Param("selective") DeclareBuildEngineeringAndEquipmentCenter.Column ... selective);

    List<DeclareBuildEngineeringAndEquipmentCenter> selectByExample(DeclareBuildEngineeringAndEquipmentCenterExample example);

    DeclareBuildEngineeringAndEquipmentCenter selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareBuildEngineeringAndEquipmentCenter record, @Param("example") DeclareBuildEngineeringAndEquipmentCenterExample example, @Param("selective") DeclareBuildEngineeringAndEquipmentCenter.Column ... selective);

    int updateByExample(@Param("record") DeclareBuildEngineeringAndEquipmentCenter record, @Param("example") DeclareBuildEngineeringAndEquipmentCenterExample example);

    int updateByPrimaryKeySelective(@Param("record") DeclareBuildEngineeringAndEquipmentCenter record, @Param("selective") DeclareBuildEngineeringAndEquipmentCenter.Column ... selective);

    int updateByPrimaryKey(DeclareBuildEngineeringAndEquipmentCenter record);

    int batchInsert(@Param("list") List<DeclareBuildEngineeringAndEquipmentCenter> list);

    int batchInsertSelective(@Param("list") List<DeclareBuildEngineeringAndEquipmentCenter> list, @Param("selective") DeclareBuildEngineeringAndEquipmentCenter.Column ... selective);
}