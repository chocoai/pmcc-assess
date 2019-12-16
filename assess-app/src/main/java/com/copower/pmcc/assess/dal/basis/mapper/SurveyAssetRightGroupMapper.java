package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRightGroup;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRightGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SurveyAssetRightGroupMapper {
    int countByExample(SurveyAssetRightGroupExample example);

    int deleteByExample(SurveyAssetRightGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SurveyAssetRightGroup record);

    int insertSelective(SurveyAssetRightGroup record);

    List<SurveyAssetRightGroup> selectByExample(SurveyAssetRightGroupExample example);

    SurveyAssetRightGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SurveyAssetRightGroup record, @Param("example") SurveyAssetRightGroupExample example);

    int updateByExample(@Param("record") SurveyAssetRightGroup record, @Param("example") SurveyAssetRightGroupExample example);

    int updateByPrimaryKeySelective(SurveyAssetRightGroup record);

    int updateByPrimaryKey(SurveyAssetRightGroup record);
}