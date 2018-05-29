package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.SurveyLocaleCorrelationCard;
import com.copower.pmcc.assess.dal.entity.SurveyLocaleCorrelationCardExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SurveyLocaleCorrelationCardMapper {
    int countByExample(SurveyLocaleCorrelationCardExample example);

    int deleteByExample(SurveyLocaleCorrelationCardExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SurveyLocaleCorrelationCard record);

    int insertSelective(SurveyLocaleCorrelationCard record);

    List<SurveyLocaleCorrelationCard> selectByExample(SurveyLocaleCorrelationCardExample example);

    SurveyLocaleCorrelationCard selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SurveyLocaleCorrelationCard record, @Param("example") SurveyLocaleCorrelationCardExample example);

    int updateByExample(@Param("record") SurveyLocaleCorrelationCard record, @Param("example") SurveyLocaleCorrelationCardExample example);

    int updateByPrimaryKeySelective(SurveyLocaleCorrelationCard record);

    int updateByPrimaryKey(SurveyLocaleCorrelationCard record);
}