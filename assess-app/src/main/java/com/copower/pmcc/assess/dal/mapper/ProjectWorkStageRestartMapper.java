package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.ProjectWorkStageRestart;
import com.copower.pmcc.assess.dal.entity.ProjectWorkStageRestartExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectWorkStageRestartMapper {
    int countByExample(ProjectWorkStageRestartExample example);

    int deleteByExample(ProjectWorkStageRestartExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectWorkStageRestart record);

    int insertSelective(ProjectWorkStageRestart record);

    List<ProjectWorkStageRestart> selectByExample(ProjectWorkStageRestartExample example);

    ProjectWorkStageRestart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectWorkStageRestart record, @Param("example") ProjectWorkStageRestartExample example);

    int updateByExample(@Param("record") ProjectWorkStageRestart record, @Param("example") ProjectWorkStageRestartExample example);

    int updateByPrimaryKeySelective(ProjectWorkStageRestart record);

    int updateByPrimaryKey(ProjectWorkStageRestart record);
}