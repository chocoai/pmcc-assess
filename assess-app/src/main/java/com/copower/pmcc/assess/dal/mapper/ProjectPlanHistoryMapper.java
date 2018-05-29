package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.ProjectPlanHistory;
import com.copower.pmcc.assess.dal.entity.ProjectPlanHistoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectPlanHistoryMapper {
    int countByExample(ProjectPlanHistoryExample example);

    int deleteByExample(ProjectPlanHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectPlanHistory record);

    int insertSelective(ProjectPlanHistory record);

    List<ProjectPlanHistory> selectByExample(ProjectPlanHistoryExample example);

    ProjectPlanHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectPlanHistory record, @Param("example") ProjectPlanHistoryExample example);

    int updateByExample(@Param("record") ProjectPlanHistory record, @Param("example") ProjectPlanHistoryExample example);

    int updateByPrimaryKeySelective(ProjectPlanHistory record);

    int updateByPrimaryKey(ProjectPlanHistory record);
}