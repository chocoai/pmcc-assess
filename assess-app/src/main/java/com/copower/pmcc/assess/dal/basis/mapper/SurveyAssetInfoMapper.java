package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInfo;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SurveyAssetInfoMapper {
    int countByExample(SurveyAssetInfoExample example);

    int deleteByExample(SurveyAssetInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SurveyAssetInfo record);

    int insertSelective(SurveyAssetInfo record);

    List<SurveyAssetInfo> selectByExample(SurveyAssetInfoExample example);

    SurveyAssetInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SurveyAssetInfo record, @Param("example") SurveyAssetInfoExample example);

    int updateByExample(@Param("record") SurveyAssetInfo record, @Param("example") SurveyAssetInfoExample example);

    int updateByPrimaryKeySelective(SurveyAssetInfo record);

    int updateByPrimaryKey(SurveyAssetInfo record);
}