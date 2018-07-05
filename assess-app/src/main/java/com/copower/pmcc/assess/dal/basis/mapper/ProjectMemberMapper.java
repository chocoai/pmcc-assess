package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectMember;
import com.copower.pmcc.assess.dal.basis.entity.ProjectMemberExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectMemberMapper {
    int countByExample(ProjectMemberExample example);

    int deleteByExample(ProjectMemberExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectMember record);

    int insertSelective(ProjectMember record);

    List<ProjectMember> selectByExample(ProjectMemberExample example);

    ProjectMember selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectMember record, @Param("example") ProjectMemberExample example);

    int updateByExample(@Param("record") ProjectMember record, @Param("example") ProjectMemberExample example);

    int updateByPrimaryKeySelective(ProjectMember record);

    int updateByPrimaryKey(ProjectMember record);
}