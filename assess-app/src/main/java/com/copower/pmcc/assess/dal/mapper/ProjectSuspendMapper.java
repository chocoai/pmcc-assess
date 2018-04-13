package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.ProjectSuspend;
import com.copower.pmcc.assess.dal.entity.ProjectSuspendExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectSuspendMapper {
    int countByExample(ProjectSuspendExample example);

    int deleteByExample(ProjectSuspendExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectSuspend record);

    int insertSelective(ProjectSuspend record);

    List<ProjectSuspend> selectByExample(ProjectSuspendExample example);

    ProjectSuspend selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectSuspend record, @Param("example") ProjectSuspendExample example);

    int updateByExample(@Param("record") ProjectSuspend record, @Param("example") ProjectSuspendExample example);

    int updateByPrimaryKeySelective(ProjectSuspend record);

    int updateByPrimaryKey(ProjectSuspend record);
}