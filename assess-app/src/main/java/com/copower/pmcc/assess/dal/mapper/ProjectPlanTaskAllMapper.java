package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.ProjectPlanTaskAll;
import com.copower.pmcc.assess.dal.entity.ProjectPlanTaskAllExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectPlanTaskAllMapper {
    int countByExample(ProjectPlanTaskAllExample example);

    int deleteByExample(ProjectPlanTaskAllExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectPlanTaskAll record);

    int insertSelective(ProjectPlanTaskAll record);

    List<ProjectPlanTaskAll> selectByExample(ProjectPlanTaskAllExample example);

    ProjectPlanTaskAll selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectPlanTaskAll record, @Param("example") ProjectPlanTaskAllExample example);

    int updateByExample(@Param("record") ProjectPlanTaskAll record, @Param("example") ProjectPlanTaskAllExample example);

    int updateByPrimaryKeySelective(ProjectPlanTaskAll record);

    int updateByPrimaryKey(ProjectPlanTaskAll record);
}