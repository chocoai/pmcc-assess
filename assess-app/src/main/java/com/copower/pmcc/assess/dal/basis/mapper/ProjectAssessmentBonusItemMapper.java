package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectAssessmentBonusItem;
import com.copower.pmcc.assess.dal.basis.entity.ProjectAssessmentBonusItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectAssessmentBonusItemMapper {
    long countByExample(ProjectAssessmentBonusItemExample example);

    int deleteByExample(ProjectAssessmentBonusItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectAssessmentBonusItem record);

    int insertSelective(ProjectAssessmentBonusItem record);

    List<ProjectAssessmentBonusItem> selectByExample(ProjectAssessmentBonusItemExample example);

    ProjectAssessmentBonusItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectAssessmentBonusItem record, @Param("example") ProjectAssessmentBonusItemExample example);

    int updateByExample(@Param("record") ProjectAssessmentBonusItem record, @Param("example") ProjectAssessmentBonusItemExample example);

    int updateByPrimaryKeySelective(ProjectAssessmentBonusItem record);

    int updateByPrimaryKey(ProjectAssessmentBonusItem record);
}