package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ProjectAssessmentBonus;
import com.copower.pmcc.assess.dal.basis.entity.ProjectAssessmentBonusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectAssessmentBonusMapper {
    long countByExample(ProjectAssessmentBonusExample example);

    int deleteByExample(ProjectAssessmentBonusExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectAssessmentBonus record);

    int insertSelective(ProjectAssessmentBonus record);

    List<ProjectAssessmentBonus> selectByExample(ProjectAssessmentBonusExample example);

    ProjectAssessmentBonus selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectAssessmentBonus record, @Param("example") ProjectAssessmentBonusExample example);

    int updateByExample(@Param("record") ProjectAssessmentBonus record, @Param("example") ProjectAssessmentBonusExample example);

    int updateByPrimaryKeySelective(ProjectAssessmentBonus record);

    int updateByPrimaryKey(ProjectAssessmentBonus record);
}