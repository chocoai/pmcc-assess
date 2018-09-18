package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingFunction;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingFunctionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseBuildingFunctionMapper {
    int countByExample(CaseBuildingFunctionExample example);

    int deleteByExample(CaseBuildingFunctionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseBuildingFunction record);

    int insertSelective(CaseBuildingFunction record);

    List<CaseBuildingFunction> selectByExample(CaseBuildingFunctionExample example);

    CaseBuildingFunction selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseBuildingFunction record, @Param("example") CaseBuildingFunctionExample example);

    int updateByExample(@Param("record") CaseBuildingFunction record, @Param("example") CaseBuildingFunctionExample example);

    int updateByPrimaryKeySelective(CaseBuildingFunction record);

    int updateByPrimaryKey(CaseBuildingFunction record);
}