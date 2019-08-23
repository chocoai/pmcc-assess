package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataBuildingInstallCost;
import com.copower.pmcc.assess.dal.basis.entity.DataBuildingInstallCostExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataBuildingInstallCostMapper {
    int countByExample(DataBuildingInstallCostExample example);

    int deleteByExample(DataBuildingInstallCostExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataBuildingInstallCost record);

    int insertSelective(DataBuildingInstallCost record);

    List<DataBuildingInstallCost> selectByExample(DataBuildingInstallCostExample example);

    DataBuildingInstallCost selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataBuildingInstallCost record, @Param("example") DataBuildingInstallCostExample example);

    int updateByExample(@Param("record") DataBuildingInstallCost record, @Param("example") DataBuildingInstallCostExample example);

    int updateByPrimaryKeySelective(DataBuildingInstallCost record);

    int updateByPrimaryKey(DataBuildingInstallCost record);
}