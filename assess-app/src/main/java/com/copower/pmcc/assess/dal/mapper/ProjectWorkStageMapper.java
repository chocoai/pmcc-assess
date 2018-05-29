package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.ProjectWorkStage;
import com.copower.pmcc.assess.dal.entity.ProjectWorkStageExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectWorkStageMapper {
    int countByExample(ProjectWorkStageExample example);

    int deleteByExample(ProjectWorkStageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectWorkStage record);

    int insertSelective(ProjectWorkStage record);

    List<ProjectWorkStage> selectByExample(ProjectWorkStageExample example);

    ProjectWorkStage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectWorkStage record, @Param("example") ProjectWorkStageExample example);

    int updateByExample(@Param("record") ProjectWorkStage record, @Param("example") ProjectWorkStageExample example);

    int updateByPrimaryKeySelective(ProjectWorkStage record);

    int updateByPrimaryKey(ProjectWorkStage record);
}