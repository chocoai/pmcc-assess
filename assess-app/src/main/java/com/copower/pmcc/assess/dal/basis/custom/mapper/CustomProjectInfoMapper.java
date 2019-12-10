package com.copower.pmcc.assess.dal.basis.custom.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dto.input.project.QueryProjectInfo;
import java.util.List;


public interface CustomProjectInfoMapper {
    //查询项目
    List<ProjectInfo> getProjectListByUserAccount(QueryProjectInfo queryProjectInfo);//查询
}
