package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectTakeNumber;
import com.copower.pmcc.assess.dal.basis.entity.ProjectTakeNumberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectTakeNumberMapper {
    long countByExample(ProjectTakeNumberExample example);

    int deleteByExample(ProjectTakeNumberExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectTakeNumber record);

    int insertSelective(ProjectTakeNumber record);

    List<ProjectTakeNumber> selectByExample(ProjectTakeNumberExample example);

    ProjectTakeNumber selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectTakeNumber record, @Param("example") ProjectTakeNumberExample example);

    int updateByExample(@Param("record") ProjectTakeNumber record, @Param("example") ProjectTakeNumberExample example);

    int updateByPrimaryKeySelective(ProjectTakeNumber record);

    int updateByPrimaryKey(ProjectTakeNumber record);
}