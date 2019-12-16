package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRight;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRightExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SurveyAssetRightMapper {
    int countByExample(SurveyAssetRightExample example);

    int deleteByExample(SurveyAssetRightExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SurveyAssetRight record);

    int insertSelective(SurveyAssetRight record);

    List<SurveyAssetRight> selectByExample(SurveyAssetRightExample example);

    SurveyAssetRight selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SurveyAssetRight record, @Param("example") SurveyAssetRightExample example);

    int updateByExample(@Param("record") SurveyAssetRight record, @Param("example") SurveyAssetRightExample example);

    int updateByPrimaryKeySelective(SurveyAssetRight record);

    int updateByPrimaryKey(SurveyAssetRight record);
}