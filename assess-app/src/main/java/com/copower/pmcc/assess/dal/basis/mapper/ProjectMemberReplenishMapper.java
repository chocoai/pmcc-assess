package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectMember;

import java.util.List;

public interface ProjectMemberReplenishMapper {

    List<ProjectMember> projectIdByUser(String user);

}