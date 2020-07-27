package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectSpotCheckItem;
import com.copower.pmcc.assess.dal.basis.entity.ProjectSpotCheckItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectSpotCheckItemMapper {
    long countByExample(ProjectSpotCheckItemExample example);

    int deleteByExample(ProjectSpotCheckItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectSpotCheckItem record);

    int insertSelective(ProjectSpotCheckItem record);

    List<ProjectSpotCheckItem> selectByExample(ProjectSpotCheckItemExample example);

    ProjectSpotCheckItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectSpotCheckItem record, @Param("example") ProjectSpotCheckItemExample example);

    int updateByExample(@Param("record") ProjectSpotCheckItem record, @Param("example") ProjectSpotCheckItemExample example);

    int updateByPrimaryKeySelective(ProjectSpotCheckItem record);

    int updateByPrimaryKey(ProjectSpotCheckItem record);
}