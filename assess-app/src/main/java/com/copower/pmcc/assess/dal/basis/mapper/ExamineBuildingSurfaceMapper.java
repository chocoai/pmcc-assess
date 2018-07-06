package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineBuildingSurface;
import com.copower.pmcc.assess.dal.basis.entity.ExamineBuildingSurfaceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineBuildingSurfaceMapper {
    int countByExample(ExamineBuildingSurfaceExample example);

    int deleteByExample(ExamineBuildingSurfaceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineBuildingSurface record);

    int insertSelective(ExamineBuildingSurface record);

    List<ExamineBuildingSurface> selectByExample(ExamineBuildingSurfaceExample example);

    ExamineBuildingSurface selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineBuildingSurface record, @Param("example") ExamineBuildingSurfaceExample example);

    int updateByExample(@Param("record") ExamineBuildingSurface record, @Param("example") ExamineBuildingSurfaceExample example);

    int updateByPrimaryKeySelective(ExamineBuildingSurface record);

    int updateByPrimaryKey(ExamineBuildingSurface record);
}