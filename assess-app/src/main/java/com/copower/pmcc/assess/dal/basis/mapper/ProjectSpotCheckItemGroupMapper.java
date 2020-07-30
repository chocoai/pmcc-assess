package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectSpotCheckItemGroup;
import com.copower.pmcc.assess.dal.basis.entity.ProjectSpotCheckItemGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectSpotCheckItemGroupMapper {
    long countByExample(ProjectSpotCheckItemGroupExample example);

    int deleteByExample(ProjectSpotCheckItemGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectSpotCheckItemGroup record);

    int insertSelective(ProjectSpotCheckItemGroup record);

    List<ProjectSpotCheckItemGroup> selectByExample(ProjectSpotCheckItemGroupExample example);

    ProjectSpotCheckItemGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectSpotCheckItemGroup record, @Param("example") ProjectSpotCheckItemGroupExample example);

    int updateByExample(@Param("record") ProjectSpotCheckItemGroup record, @Param("example") ProjectSpotCheckItemGroupExample example);

    int updateByPrimaryKeySelective(ProjectSpotCheckItemGroup record);

    int updateByPrimaryKey(ProjectSpotCheckItemGroup record);
}