package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataBuildingNewRate;
import com.copower.pmcc.assess.dal.basis.entity.DataBuildingNewRateExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataBuildingNewRateMapper {
    int countByExample(DataBuildingNewRateExample example);

    int deleteByExample(DataBuildingNewRateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataBuildingNewRate record);

    int insertSelective(DataBuildingNewRate record);

    List<DataBuildingNewRate> selectByExample(DataBuildingNewRateExample example);

    DataBuildingNewRate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataBuildingNewRate record, @Param("example") DataBuildingNewRateExample example);

    int updateByExample(@Param("record") DataBuildingNewRate record, @Param("example") DataBuildingNewRateExample example);

    int updateByPrimaryKeySelective(DataBuildingNewRate record);

    int updateByPrimaryKey(DataBuildingNewRate record);
}