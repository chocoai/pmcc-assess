package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxCommissionRatio;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxCommissionRatioExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectXlxCommissionRatioMapper {
    int countByExample(ProjectXlxCommissionRatioExample example);

    int deleteByExample(ProjectXlxCommissionRatioExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectXlxCommissionRatio record);

    int insertSelective(ProjectXlxCommissionRatio record);

    List<ProjectXlxCommissionRatio> selectByExample(ProjectXlxCommissionRatioExample example);

    ProjectXlxCommissionRatio selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectXlxCommissionRatio record, @Param("example") ProjectXlxCommissionRatioExample example);

    int updateByExample(@Param("record") ProjectXlxCommissionRatio record, @Param("example") ProjectXlxCommissionRatioExample example);

    int updateByPrimaryKeySelective(ProjectXlxCommissionRatio record);

    int updateByPrimaryKey(ProjectXlxCommissionRatio record);
}