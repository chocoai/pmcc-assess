package com.copower.pmcc.assess.dal.basis.custom.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CustomProjectInfoMapper {
    //查询项目
    List<ProjectInfo> getProjectListByUserAccount(@Param("userAccount") String userAccount,
                                                  @Param("projectName") String projectName,
                                                  @Param("projectStatus") String projectStatus,
                                                  @Param("queryCreator")String queryCreator,
                                                  @Param("queryMember")String queryMember,
                                                  @Param("entrustPurpose")Integer entrustPurpose);//查询
}
