package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetOtherTemplate;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetOtherTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SurveyAssetOtherTemplateMapper {
    int countByExample(SurveyAssetOtherTemplateExample example);

    int deleteByExample(SurveyAssetOtherTemplateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SurveyAssetOtherTemplate record);

    int insertSelective(SurveyAssetOtherTemplate record);

    List<SurveyAssetOtherTemplate> selectByExample(SurveyAssetOtherTemplateExample example);

    SurveyAssetOtherTemplate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SurveyAssetOtherTemplate record, @Param("example") SurveyAssetOtherTemplateExample example);

    int updateByExample(@Param("record") SurveyAssetOtherTemplate record, @Param("example") SurveyAssetOtherTemplateExample example);

    int updateByPrimaryKeySelective(SurveyAssetOtherTemplate record);

    int updateByPrimaryKey(SurveyAssetOtherTemplate record);
}