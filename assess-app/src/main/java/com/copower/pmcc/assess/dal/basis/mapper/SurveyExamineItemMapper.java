package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SurveyExamineItem;
import com.copower.pmcc.assess.dal.basis.entity.SurveyExamineItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SurveyExamineItemMapper {
    int countByExample(SurveyExamineItemExample example);

    int deleteByExample(SurveyExamineItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SurveyExamineItem record);

    int insertSelective(SurveyExamineItem record);

    List<SurveyExamineItem> selectByExample(SurveyExamineItemExample example);

    SurveyExamineItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SurveyExamineItem record, @Param("example") SurveyExamineItemExample example);

    int updateByExample(@Param("record") SurveyExamineItem record, @Param("example") SurveyExamineItemExample example);

    int updateByPrimaryKeySelective(SurveyExamineItem record);

    int updateByPrimaryKey(SurveyExamineItem record);
}