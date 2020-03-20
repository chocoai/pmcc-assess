package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxReportIndividual;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxReportIndividualExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectXlxReportIndividualMapper {
    int countByExample(ProjectXlxReportIndividualExample example);

    int deleteByExample(ProjectXlxReportIndividualExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectXlxReportIndividual record);

    int insertSelective(ProjectXlxReportIndividual record);

    List<ProjectXlxReportIndividual> selectByExample(ProjectXlxReportIndividualExample example);

    ProjectXlxReportIndividual selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectXlxReportIndividual record, @Param("example") ProjectXlxReportIndividualExample example);

    int updateByExample(@Param("record") ProjectXlxReportIndividual record, @Param("example") ProjectXlxReportIndividualExample example);

    int updateByPrimaryKeySelective(ProjectXlxReportIndividual record);

    int updateByPrimaryKey(ProjectXlxReportIndividual record);
}