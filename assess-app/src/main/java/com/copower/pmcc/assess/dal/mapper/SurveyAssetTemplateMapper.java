package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.SurveyAssetTemplate;
import com.copower.pmcc.assess.dal.entity.SurveyAssetTemplateExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SurveyAssetTemplateMapper {
    int countByExample(SurveyAssetTemplateExample example);

    int deleteByExample(SurveyAssetTemplateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SurveyAssetTemplate record);

    int insertSelective(SurveyAssetTemplate record);

    List<SurveyAssetTemplate> selectByExample(SurveyAssetTemplateExample example);

    SurveyAssetTemplate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SurveyAssetTemplate record, @Param("example") SurveyAssetTemplateExample example);

    int updateByExample(@Param("record") SurveyAssetTemplate record, @Param("example") SurveyAssetTemplateExample example);

    int updateByPrimaryKeySelective(SurveyAssetTemplate record);

    int updateByPrimaryKey(SurveyAssetTemplate record);
}