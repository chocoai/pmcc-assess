package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingSurface;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingSurfaceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseBuildingSurfaceMapper {
    int countByExample(CaseBuildingSurfaceExample example);

    int deleteByExample(CaseBuildingSurfaceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseBuildingSurface record);

    int insertSelective(CaseBuildingSurface record);

    List<CaseBuildingSurface> selectByExample(CaseBuildingSurfaceExample example);

    CaseBuildingSurface selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseBuildingSurface record, @Param("example") CaseBuildingSurfaceExample example);

    int updateByExample(@Param("record") CaseBuildingSurface record, @Param("example") CaseBuildingSurfaceExample example);

    int updateByPrimaryKeySelective(CaseBuildingSurface record);

    int updateByPrimaryKey(CaseBuildingSurface record);
}