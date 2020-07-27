package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectSpotCheck;
import com.copower.pmcc.assess.dal.basis.entity.ProjectSpotCheckExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectSpotCheckMapper {
    long countByExample(ProjectSpotCheckExample example);

    int deleteByExample(ProjectSpotCheckExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectSpotCheck record);

    int insertSelective(ProjectSpotCheck record);

    List<ProjectSpotCheck> selectByExample(ProjectSpotCheckExample example);

    ProjectSpotCheck selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectSpotCheck record, @Param("example") ProjectSpotCheckExample example);

    int updateByExample(@Param("record") ProjectSpotCheck record, @Param("example") ProjectSpotCheckExample example);

    int updateByPrimaryKeySelective(ProjectSpotCheck record);

    int updateByPrimaryKey(ProjectSpotCheck record);
}