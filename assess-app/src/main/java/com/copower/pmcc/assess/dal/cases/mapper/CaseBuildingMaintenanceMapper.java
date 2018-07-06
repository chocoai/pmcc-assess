package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingMaintenance;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingMaintenanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseBuildingMaintenanceMapper {
    int countByExample(CaseBuildingMaintenanceExample example);

    int deleteByExample(CaseBuildingMaintenanceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseBuildingMaintenance record);

    int insertSelective(CaseBuildingMaintenance record);

    List<CaseBuildingMaintenance> selectByExample(CaseBuildingMaintenanceExample example);

    CaseBuildingMaintenance selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseBuildingMaintenance record, @Param("example") CaseBuildingMaintenanceExample example);

    int updateByExample(@Param("record") CaseBuildingMaintenance record, @Param("example") CaseBuildingMaintenanceExample example);

    int updateByPrimaryKeySelective(CaseBuildingMaintenance record);

    int updateByPrimaryKey(CaseBuildingMaintenance record);
}