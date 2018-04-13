package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.entity.ProjectPlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectPlanMapper {
    int countByExample(ProjectPlanExample example);

    int deleteByExample(ProjectPlanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectPlan record);

    int insertSelective(ProjectPlan record);

    List<ProjectPlan> selectByExample(ProjectPlanExample example);

    ProjectPlan selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectPlan record, @Param("example") ProjectPlanExample example);

    int updateByExample(@Param("record") ProjectPlan record, @Param("example") ProjectPlanExample example);

    int updateByPrimaryKeySelective(ProjectPlan record);

    int updateByPrimaryKey(ProjectPlan record);
}