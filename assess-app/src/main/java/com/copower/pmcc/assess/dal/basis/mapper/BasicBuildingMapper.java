package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicBuilding;
import com.copower.pmcc.assess.dal.basis.entity.BasicBuildingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicBuildingMapper {
    long countByExample(BasicBuildingExample example);

    int deleteByExample(BasicBuildingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicBuilding record);

    int insertSelective(BasicBuilding record);

    List<BasicBuilding> selectByExample(BasicBuildingExample example);

    BasicBuilding selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicBuilding record, @Param("example") BasicBuildingExample example);

    int updateByExample(@Param("record") BasicBuilding record, @Param("example") BasicBuildingExample example);

    int updateByPrimaryKeySelective(BasicBuilding record);

    int updateByPrimaryKey(BasicBuilding record);
}