package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectSpotCheckScore;
import com.copower.pmcc.assess.dal.basis.entity.ProjectSpotCheckScoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectSpotCheckScoreMapper {
    long countByExample(ProjectSpotCheckScoreExample example);

    int deleteByExample(ProjectSpotCheckScoreExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectSpotCheckScore record);

    int insertSelective(ProjectSpotCheckScore record);

    List<ProjectSpotCheckScore> selectByExample(ProjectSpotCheckScoreExample example);

    ProjectSpotCheckScore selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectSpotCheckScore record, @Param("example") ProjectSpotCheckScoreExample example);

    int updateByExample(@Param("record") ProjectSpotCheckScore record, @Param("example") ProjectSpotCheckScoreExample example);

    int updateByPrimaryKeySelective(ProjectSpotCheckScore record);

    int updateByPrimaryKey(ProjectSpotCheckScore record);
}