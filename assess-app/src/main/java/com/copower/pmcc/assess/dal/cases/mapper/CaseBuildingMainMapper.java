package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingMain;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingMainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseBuildingMainMapper {
    int countByExample(CaseBuildingMainExample example);

    int deleteByExample(CaseBuildingMainExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseBuildingMain record);

    int insertSelective(CaseBuildingMain record);

    List<CaseBuildingMain> selectByExample(CaseBuildingMainExample example);

    CaseBuildingMain selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseBuildingMain record, @Param("example") CaseBuildingMainExample example);

    int updateByExample(@Param("record") CaseBuildingMain record, @Param("example") CaseBuildingMainExample example);

    int updateByPrimaryKeySelective(CaseBuildingMain record);

    int updateByPrimaryKey(CaseBuildingMain record);
}