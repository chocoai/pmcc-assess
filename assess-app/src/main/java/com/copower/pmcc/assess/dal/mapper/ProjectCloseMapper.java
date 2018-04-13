package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.ProjectClose;
import com.copower.pmcc.assess.dal.entity.ProjectCloseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectCloseMapper {
    int countByExample(ProjectCloseExample example);

    int deleteByExample(ProjectCloseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectClose record);

    int insertSelective(ProjectClose record);

    List<ProjectClose> selectByExample(ProjectCloseExample example);

    ProjectClose selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectClose record, @Param("example") ProjectCloseExample example);

    int updateByExample(@Param("record") ProjectClose record, @Param("example") ProjectCloseExample example);

    int updateByPrimaryKeySelective(ProjectClose record);

    int updateByPrimaryKey(ProjectClose record);
}