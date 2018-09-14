package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineBuildingFunction;
import com.copower.pmcc.assess.dal.basis.entity.ExamineBuildingFunctionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineBuildingFunctionMapper {
    int countByExample(ExamineBuildingFunctionExample example);

    int deleteByExample(ExamineBuildingFunctionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineBuildingFunction record);

    int insertSelective(ExamineBuildingFunction record);

    List<ExamineBuildingFunction> selectByExample(ExamineBuildingFunctionExample example);

    ExamineBuildingFunction selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineBuildingFunction record, @Param("example") ExamineBuildingFunctionExample example);

    int updateByExample(@Param("record") ExamineBuildingFunction record, @Param("example") ExamineBuildingFunctionExample example);

    int updateByPrimaryKeySelective(ExamineBuildingFunction record);

    int updateByPrimaryKey(ExamineBuildingFunction record);
}