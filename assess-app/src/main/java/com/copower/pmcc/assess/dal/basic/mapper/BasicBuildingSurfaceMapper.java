package com.copower.pmcc.assess.dal.basic.mapper;

import com.copower.pmcc.assess.dal.basic.entity.BasicBuildingSurface;
import com.copower.pmcc.assess.dal.basic.entity.BasicBuildingSurfaceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicBuildingSurfaceMapper {
    int countByExample(BasicBuildingSurfaceExample example);

    int deleteByExample(BasicBuildingSurfaceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicBuildingSurface record);

    int insertSelective(BasicBuildingSurface record);

    List<BasicBuildingSurface> selectByExample(BasicBuildingSurfaceExample example);

    BasicBuildingSurface selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicBuildingSurface record, @Param("example") BasicBuildingSurfaceExample example);

    int updateByExample(@Param("record") BasicBuildingSurface record, @Param("example") BasicBuildingSurfaceExample example);

    int updateByPrimaryKeySelective(BasicBuildingSurface record);

    int updateByPrimaryKey(BasicBuildingSurface record);
}