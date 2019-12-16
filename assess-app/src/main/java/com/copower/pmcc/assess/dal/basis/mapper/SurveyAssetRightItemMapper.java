package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRightItem;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRightItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SurveyAssetRightItemMapper {
    int countByExample(SurveyAssetRightItemExample example);

    int deleteByExample(SurveyAssetRightItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SurveyAssetRightItem record);

    int insertSelective(SurveyAssetRightItem record);

    List<SurveyAssetRightItem> selectByExample(SurveyAssetRightItemExample example);

    SurveyAssetRightItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SurveyAssetRightItem record, @Param("example") SurveyAssetRightItemExample example);

    int updateByExample(@Param("record") SurveyAssetRightItem record, @Param("example") SurveyAssetRightItemExample example);

    int updateByPrimaryKeySelective(SurveyAssetRightItem record);

    int updateByPrimaryKey(SurveyAssetRightItem record);
}