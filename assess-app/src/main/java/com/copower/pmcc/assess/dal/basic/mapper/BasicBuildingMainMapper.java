package com.copower.pmcc.assess.dal.basic.mapper;

import com.copower.pmcc.assess.dal.basic.entity.BasicBuildingMain;
import com.copower.pmcc.assess.dal.basic.entity.BasicBuildingMainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicBuildingMainMapper {
    int countByExample(BasicBuildingMainExample example);

    int deleteByExample(BasicBuildingMainExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicBuildingMain record);

    int insertSelective(BasicBuildingMain record);

    List<BasicBuildingMain> selectByExample(BasicBuildingMainExample example);

    BasicBuildingMain selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicBuildingMain record, @Param("example") BasicBuildingMainExample example);

    int updateByExample(@Param("record") BasicBuildingMain record, @Param("example") BasicBuildingMainExample example);

    int updateByPrimaryKeySelective(BasicBuildingMain record);

    int updateByPrimaryKey(BasicBuildingMain record);
}