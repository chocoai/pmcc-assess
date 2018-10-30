package com.copower.pmcc.assess.dal.basic.mapper;

import com.copower.pmcc.assess.dal.basic.entity.BasicBuildingFunction;
import com.copower.pmcc.assess.dal.basic.entity.BasicBuildingFunctionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicBuildingFunctionMapper {
    int countByExample(BasicBuildingFunctionExample example);

    int deleteByExample(BasicBuildingFunctionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicBuildingFunction record);

    int insertSelective(BasicBuildingFunction record);

    List<BasicBuildingFunction> selectByExample(BasicBuildingFunctionExample example);

    BasicBuildingFunction selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicBuildingFunction record, @Param("example") BasicBuildingFunctionExample example);

    int updateByExample(@Param("record") BasicBuildingFunction record, @Param("example") BasicBuildingFunctionExample example);

    int updateByPrimaryKeySelective(BasicBuildingFunction record);

    int updateByPrimaryKey(BasicBuildingFunction record);
}