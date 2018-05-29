package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.SurveyLocaleExplore;
import com.copower.pmcc.assess.dal.entity.SurveyLocaleExploreExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SurveyLocaleExploreMapper {
    int countByExample(SurveyLocaleExploreExample example);

    int deleteByExample(SurveyLocaleExploreExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SurveyLocaleExplore record);

    int insertSelective(SurveyLocaleExplore record);

    List<SurveyLocaleExplore> selectByExample(SurveyLocaleExploreExample example);

    SurveyLocaleExplore selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SurveyLocaleExplore record, @Param("example") SurveyLocaleExploreExample example);

    int updateByExample(@Param("record") SurveyLocaleExplore record, @Param("example") SurveyLocaleExploreExample example);

    int updateByPrimaryKeySelective(SurveyLocaleExplore record);

    int updateByPrimaryKey(SurveyLocaleExplore record);
}