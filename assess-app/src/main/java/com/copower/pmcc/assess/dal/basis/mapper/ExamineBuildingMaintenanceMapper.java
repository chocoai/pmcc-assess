package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineBuildingMaintenance;
import com.copower.pmcc.assess.dal.basis.entity.ExamineBuildingMaintenanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineBuildingMaintenanceMapper {
    int countByExample(ExamineBuildingMaintenanceExample example);

    int deleteByExample(ExamineBuildingMaintenanceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineBuildingMaintenance record);

    int insertSelective(ExamineBuildingMaintenance record);

    List<ExamineBuildingMaintenance> selectByExample(ExamineBuildingMaintenanceExample example);

    ExamineBuildingMaintenance selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineBuildingMaintenance record, @Param("example") ExamineBuildingMaintenanceExample example);

    int updateByExample(@Param("record") ExamineBuildingMaintenance record, @Param("example") ExamineBuildingMaintenanceExample example);

    int updateByPrimaryKeySelective(ExamineBuildingMaintenance record);

    int updateByPrimaryKey(ExamineBuildingMaintenance record);
}