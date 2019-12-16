package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRightDeclare;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRightDeclareExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SurveyAssetRightDeclareMapper {
    int countByExample(SurveyAssetRightDeclareExample example);

    int deleteByExample(SurveyAssetRightDeclareExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SurveyAssetRightDeclare record);

    int insertSelective(SurveyAssetRightDeclare record);

    List<SurveyAssetRightDeclare> selectByExample(SurveyAssetRightDeclareExample example);

    SurveyAssetRightDeclare selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SurveyAssetRightDeclare record, @Param("example") SurveyAssetRightDeclareExample example);

    int updateByExample(@Param("record") SurveyAssetRightDeclare record, @Param("example") SurveyAssetRightDeclareExample example);

    int updateByPrimaryKeySelective(SurveyAssetRightDeclare record);

    int updateByPrimaryKey(SurveyAssetRightDeclare record);
}