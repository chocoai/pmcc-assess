package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInfoItem;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInfoItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SurveyAssetInfoItemMapper {
    long countByExample(SurveyAssetInfoItemExample example);

    int deleteByExample(SurveyAssetInfoItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SurveyAssetInfoItem record);

    int insertSelective(SurveyAssetInfoItem record);

    List<SurveyAssetInfoItem> selectByExample(SurveyAssetInfoItemExample example);

    SurveyAssetInfoItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SurveyAssetInfoItem record, @Param("example") SurveyAssetInfoItemExample example);

    int updateByExample(@Param("record") SurveyAssetInfoItem record, @Param("example") SurveyAssetInfoItemExample example);

    int updateByPrimaryKeySelective(SurveyAssetInfoItem record);

    int updateByPrimaryKey(SurveyAssetInfoItem record);
}