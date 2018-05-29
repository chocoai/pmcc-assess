package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.ProjectFollow;
import com.copower.pmcc.assess.dal.entity.ProjectFollowExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectFollowMapper {
    int countByExample(ProjectFollowExample example);

    int deleteByExample(ProjectFollowExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectFollow record);

    int insertSelective(ProjectFollow record);

    List<ProjectFollow> selectByExample(ProjectFollowExample example);

    ProjectFollow selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectFollow record, @Param("example") ProjectFollowExample example);

    int updateByExample(@Param("record") ProjectFollow record, @Param("example") ProjectFollowExample example);

    int updateByPrimaryKeySelective(ProjectFollow record);

    int updateByPrimaryKey(ProjectFollow record);
}