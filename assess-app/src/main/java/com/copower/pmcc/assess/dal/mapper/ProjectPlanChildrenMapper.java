package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.ProjectPlanChildren;
import com.copower.pmcc.assess.dal.entity.ProjectPlanChildrenExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectPlanChildrenMapper {
    int countByExample(ProjectPlanChildrenExample example);

    int deleteByExample(ProjectPlanChildrenExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectPlanChildren record);

    int insertSelective(ProjectPlanChildren record);

    List<ProjectPlanChildren> selectByExample(ProjectPlanChildrenExample example);

    ProjectPlanChildren selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectPlanChildren record, @Param("example") ProjectPlanChildrenExample example);

    int updateByExample(@Param("record") ProjectPlanChildren record, @Param("example") ProjectPlanChildrenExample example);

    int updateByPrimaryKeySelective(ProjectPlanChildren record);

    int updateByPrimaryKey(ProjectPlanChildren record);
}