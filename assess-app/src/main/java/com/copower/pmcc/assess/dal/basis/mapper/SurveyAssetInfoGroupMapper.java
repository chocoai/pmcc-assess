package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInfoGroup;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInfoGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SurveyAssetInfoGroupMapper {
    long countByExample(SurveyAssetInfoGroupExample example);

    int deleteByExample(SurveyAssetInfoGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SurveyAssetInfoGroup record);

    int insertSelective(SurveyAssetInfoGroup record);

    List<SurveyAssetInfoGroup> selectByExample(SurveyAssetInfoGroupExample example);

    SurveyAssetInfoGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SurveyAssetInfoGroup record, @Param("example") SurveyAssetInfoGroupExample example);

    int updateByExample(@Param("record") SurveyAssetInfoGroup record, @Param("example") SurveyAssetInfoGroupExample example);

    int updateByPrimaryKeySelective(SurveyAssetInfoGroup record);

    int updateByPrimaryKey(SurveyAssetInfoGroup record);
}