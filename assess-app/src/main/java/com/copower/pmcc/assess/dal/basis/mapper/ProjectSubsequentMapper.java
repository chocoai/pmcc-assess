package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectSubsequent;
import com.copower.pmcc.assess.dal.basis.entity.ProjectSubsequentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectSubsequentMapper {
    int countByExample(ProjectSubsequentExample example);

    int deleteByExample(ProjectSubsequentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectSubsequent record);

    int insertSelective(ProjectSubsequent record);

    List<ProjectSubsequent> selectByExample(ProjectSubsequentExample example);

    ProjectSubsequent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectSubsequent record, @Param("example") ProjectSubsequentExample example);

    int updateByExample(@Param("record") ProjectSubsequent record, @Param("example") ProjectSubsequentExample example);

    int updateByPrimaryKeySelective(ProjectSubsequent record);

    int updateByPrimaryKey(ProjectSubsequent record);
}