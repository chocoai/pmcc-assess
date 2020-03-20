package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxRebateRatio;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxRebateRatioExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectXlxRebateRatioMapper {
    int countByExample(ProjectXlxRebateRatioExample example);

    int deleteByExample(ProjectXlxRebateRatioExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectXlxRebateRatio record);

    int insertSelective(ProjectXlxRebateRatio record);

    List<ProjectXlxRebateRatio> selectByExample(ProjectXlxRebateRatioExample example);

    ProjectXlxRebateRatio selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectXlxRebateRatio record, @Param("example") ProjectXlxRebateRatioExample example);

    int updateByExample(@Param("record") ProjectXlxRebateRatio record, @Param("example") ProjectXlxRebateRatioExample example);

    int updateByPrimaryKeySelective(ProjectXlxRebateRatio record);

    int updateByPrimaryKey(ProjectXlxRebateRatio record);
}