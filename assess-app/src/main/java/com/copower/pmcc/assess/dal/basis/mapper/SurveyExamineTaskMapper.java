package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SurveyExamineTask;
import com.copower.pmcc.assess.dal.basis.entity.SurveyExamineTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SurveyExamineTaskMapper {
    int countByExample(SurveyExamineTaskExample example);

    int deleteByExample(SurveyExamineTaskExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SurveyExamineTask record);

    int insertSelective(SurveyExamineTask record);

    List<SurveyExamineTask> selectByExample(SurveyExamineTaskExample example);

    SurveyExamineTask selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SurveyExamineTask record, @Param("example") SurveyExamineTaskExample example);

    int updateByExample(@Param("record") SurveyExamineTask record, @Param("example") SurveyExamineTaskExample example);

    int updateByPrimaryKeySelective(SurveyExamineTask record);

    int updateByPrimaryKey(SurveyExamineTask record);
}