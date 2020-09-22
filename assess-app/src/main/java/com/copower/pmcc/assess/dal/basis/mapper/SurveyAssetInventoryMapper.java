package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventory;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SurveyAssetInventoryMapper {
    long countByExample(SurveyAssetInventoryExample example);

    int deleteByExample(SurveyAssetInventoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SurveyAssetInventory record);

    int insertSelective(SurveyAssetInventory record);

    List<SurveyAssetInventory> selectByExampleWithBLOBs(SurveyAssetInventoryExample example);

    List<SurveyAssetInventory> selectByExample(SurveyAssetInventoryExample example);

    SurveyAssetInventory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SurveyAssetInventory record, @Param("example") SurveyAssetInventoryExample example);

    int updateByExampleWithBLOBs(@Param("record") SurveyAssetInventory record, @Param("example") SurveyAssetInventoryExample example);

    int updateByExample(@Param("record") SurveyAssetInventory record, @Param("example") SurveyAssetInventoryExample example);

    int updateByPrimaryKeySelective(SurveyAssetInventory record);

    int updateByPrimaryKeyWithBLOBs(SurveyAssetInventory record);

    int updateByPrimaryKey(SurveyAssetInventory record);
}