package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.SurveyAssetInventory;
import com.copower.pmcc.assess.dal.entity.SurveyAssetInventoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SurveyAssetInventoryMapper {
    int countByExample(SurveyAssetInventoryExample example);

    int deleteByExample(SurveyAssetInventoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SurveyAssetInventory record);

    int insertSelective(SurveyAssetInventory record);

    List<SurveyAssetInventory> selectByExample(SurveyAssetInventoryExample example);

    SurveyAssetInventory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SurveyAssetInventory record, @Param("example") SurveyAssetInventoryExample example);

    int updateByExample(@Param("record") SurveyAssetInventory record, @Param("example") SurveyAssetInventoryExample example);

    int updateByPrimaryKeySelective(SurveyAssetInventory record);

    int updateByPrimaryKey(SurveyAssetInventory record);
}