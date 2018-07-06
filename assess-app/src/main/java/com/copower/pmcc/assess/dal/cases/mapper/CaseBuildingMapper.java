package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseBuilding;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseBuildingMapper {
    int countByExample(CaseBuildingExample example);

    int deleteByExample(CaseBuildingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseBuilding record);

    int insertSelective(CaseBuilding record);

    List<CaseBuilding> selectByExample(CaseBuildingExample example);

    CaseBuilding selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseBuilding record, @Param("example") CaseBuildingExample example);

    int updateByExample(@Param("record") CaseBuilding record, @Param("example") CaseBuildingExample example);

    int updateByPrimaryKeySelective(CaseBuilding record);

    int updateByPrimaryKey(CaseBuilding record);
}