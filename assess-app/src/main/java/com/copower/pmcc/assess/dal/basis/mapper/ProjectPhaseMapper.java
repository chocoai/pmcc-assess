package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectPhase;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPhaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectPhaseMapper {
    long countByExample(ProjectPhaseExample example);

    int deleteByExample(ProjectPhaseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectPhase record);

    int insertSelective(ProjectPhase record);

    List<ProjectPhase> selectByExample(ProjectPhaseExample example);

    ProjectPhase selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectPhase record, @Param("example") ProjectPhaseExample example);

    int updateByExample(@Param("record") ProjectPhase record, @Param("example") ProjectPhaseExample example);

    int updateByPrimaryKeySelective(ProjectPhase record);

    int updateByPrimaryKey(ProjectPhase record);
}