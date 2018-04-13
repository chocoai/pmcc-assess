package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.ProjectMemberHistory;
import com.copower.pmcc.assess.dal.entity.ProjectMemberHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectMemberHistoryMapper {
    int countByExample(ProjectMemberHistoryExample example);

    int deleteByExample(ProjectMemberHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectMemberHistory record);

    int insertSelective(ProjectMemberHistory record);

    List<ProjectMemberHistory> selectByExample(ProjectMemberHistoryExample example);

    ProjectMemberHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectMemberHistory record, @Param("example") ProjectMemberHistoryExample example);

    int updateByExample(@Param("record") ProjectMemberHistory record, @Param("example") ProjectMemberHistoryExample example);

    int updateByPrimaryKeySelective(ProjectMemberHistory record);

    int updateByPrimaryKey(ProjectMemberHistory record);
}