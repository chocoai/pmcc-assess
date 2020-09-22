package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryContent;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SurveyAssetInventoryContentMapper {
    long countByExample(SurveyAssetInventoryContentExample example);

    int deleteByExample(SurveyAssetInventoryContentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SurveyAssetInventoryContent record);

    int insertSelective(SurveyAssetInventoryContent record);

    List<SurveyAssetInventoryContent> selectByExample(SurveyAssetInventoryContentExample example);

    SurveyAssetInventoryContent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SurveyAssetInventoryContent record, @Param("example") SurveyAssetInventoryContentExample example);

    int updateByExample(@Param("record") SurveyAssetInventoryContent record, @Param("example") SurveyAssetInventoryContentExample example);

    int updateByPrimaryKeySelective(SurveyAssetInventoryContent record);

    int updateByPrimaryKey(SurveyAssetInventoryContent record);
}