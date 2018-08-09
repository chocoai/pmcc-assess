package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdCostBuilding;
import com.copower.pmcc.assess.dal.basis.entity.MdCostBuildingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdCostBuildingMapper {
    int countByExample(MdCostBuildingExample example);

    int deleteByExample(MdCostBuildingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdCostBuilding record);

    int insertSelective(MdCostBuilding record);

    List<MdCostBuilding> selectByExample(MdCostBuildingExample example);

    MdCostBuilding selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdCostBuilding record, @Param("example") MdCostBuildingExample example);

    int updateByExample(@Param("record") MdCostBuilding record, @Param("example") MdCostBuildingExample example);

    int updateByPrimaryKeySelective(MdCostBuilding record);

    int updateByPrimaryKey(MdCostBuilding record);
}