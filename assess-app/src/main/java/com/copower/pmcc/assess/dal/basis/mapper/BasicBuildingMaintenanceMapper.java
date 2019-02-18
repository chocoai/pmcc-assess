package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicBuildingMaintenance;
import com.copower.pmcc.assess.dal.basis.entity.BasicBuildingMaintenanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicBuildingMaintenanceMapper {
    int countByExample(BasicBuildingMaintenanceExample example);

    int deleteByExample(BasicBuildingMaintenanceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicBuildingMaintenance record);

    int insertSelective(BasicBuildingMaintenance record);

    List<BasicBuildingMaintenance> selectByExample(BasicBuildingMaintenanceExample example);

    BasicBuildingMaintenance selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicBuildingMaintenance record, @Param("example") BasicBuildingMaintenanceExample example);

    int updateByExample(@Param("record") BasicBuildingMaintenance record, @Param("example") BasicBuildingMaintenanceExample example);

    int updateByPrimaryKeySelective(BasicBuildingMaintenance record);

    int updateByPrimaryKey(BasicBuildingMaintenance record);
}