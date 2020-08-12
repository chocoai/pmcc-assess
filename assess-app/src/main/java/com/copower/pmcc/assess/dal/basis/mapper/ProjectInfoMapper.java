package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectInfoMapper {
    long countByExample(ProjectInfoExample example);

    int deleteByExample(ProjectInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectInfo record);

    int insertSelective(@Param("record") ProjectInfo record, @Param("selective") ProjectInfo.Column ... selective);

    List<ProjectInfo> selectByExample(ProjectInfoExample example);

    ProjectInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectInfo record, @Param("example") ProjectInfoExample example, @Param("selective") ProjectInfo.Column ... selective);

    int updateByExample(@Param("record") ProjectInfo record, @Param("example") ProjectInfoExample example);

    int updateByPrimaryKeySelective(@Param("record") ProjectInfo record, @Param("selective") ProjectInfo.Column ... selective);

    int updateByPrimaryKey(ProjectInfo record);

    int batchInsert(@Param("list") List<ProjectInfo> list);

    int batchInsertSelective(@Param("list") List<ProjectInfo> list, @Param("selective") ProjectInfo.Column ... selective);
}