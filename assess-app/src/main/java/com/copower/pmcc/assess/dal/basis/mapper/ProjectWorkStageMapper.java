package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStage;
import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectWorkStageMapper {
    long countByExample(ProjectWorkStageExample example);

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