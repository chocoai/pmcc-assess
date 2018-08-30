package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRight;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRightExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SurveyAssetInventoryRightMapper {
    int countByExample(SurveyAssetInventoryRightExample example);

    int deleteByExample(SurveyAssetInventoryRightExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SurveyAssetInventoryRight record);

    int insertSelective(SurveyAssetInventoryRight record);

    List<SurveyAssetInventoryRight> selectByExample(SurveyAssetInventoryRightExample example);

    SurveyAssetInventoryRight selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SurveyAssetInventoryRight record, @Param("example") SurveyAssetInventoryRightExample example);

    int updateByExample(@Param("record") SurveyAssetInventoryRight record, @Param("example") SurveyAssetInventoryRightExample example);

    int updateByPrimaryKeySelective(SurveyAssetInventoryRight record);

    int updateByPrimaryKey(SurveyAssetInventoryRight record);
}