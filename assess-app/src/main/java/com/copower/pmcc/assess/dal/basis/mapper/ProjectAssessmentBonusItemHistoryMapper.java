package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectAssessmentBonusItemHistory;
import com.copower.pmcc.assess.dal.basis.entity.ProjectAssessmentBonusItemHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectAssessmentBonusItemHistoryMapper {
    long countByExample(ProjectAssessmentBonusItemHistoryExample example);

    int deleteByExample(ProjectAssessmentBonusItemHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectAssessmentBonusItemHistory record);

    int insertSelective(ProjectAssessmentBonusItemHistory record);

    List<ProjectAssessmentBonusItemHistory> selectByExample(ProjectAssessmentBonusItemHistoryExample example);

    ProjectAssessmentBonusItemHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectAssessmentBonusItemHistory record, @Param("example") ProjectAssessmentBonusItemHistoryExample example);

    int updateByExample(@Param("record") ProjectAssessmentBonusItemHistory record, @Param("example") ProjectAssessmentBonusItemHistoryExample example);

    int updateByPrimaryKeySelective(ProjectAssessmentBonusItemHistory record);

    int updateByPrimaryKey(ProjectAssessmentBonusItemHistory record);
}