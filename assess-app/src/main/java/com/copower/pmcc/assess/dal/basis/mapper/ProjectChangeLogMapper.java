package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectChangeLog;
import com.copower.pmcc.assess.dal.basis.entity.ProjectChangeLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectChangeLogMapper {
    int countByExample(ProjectChangeLogExample example);

    int deleteByExample(ProjectChangeLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectChangeLog record);

    int insertSelective(ProjectChangeLog record);

    List<ProjectChangeLog> selectByExample(ProjectChangeLogExample example);

    ProjectChangeLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectChangeLog record, @Param("example") ProjectChangeLogExample example);

    int updateByExample(@Param("record") ProjectChangeLog record, @Param("example") ProjectChangeLogExample example);

    int updateByPrimaryKeySelective(ProjectChangeLog record);

    int updateByPrimaryKey(ProjectChangeLog record);
}