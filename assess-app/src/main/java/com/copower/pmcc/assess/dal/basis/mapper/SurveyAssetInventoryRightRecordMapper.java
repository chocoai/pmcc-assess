package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRightRecord;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRightRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SurveyAssetInventoryRightRecordMapper {
    int countByExample(SurveyAssetInventoryRightRecordExample example);

    int deleteByExample(SurveyAssetInventoryRightRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SurveyAssetInventoryRightRecord record);

    int insertSelective(SurveyAssetInventoryRightRecord record);

    List<SurveyAssetInventoryRightRecord> selectByExample(SurveyAssetInventoryRightRecordExample example);

    SurveyAssetInventoryRightRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SurveyAssetInventoryRightRecord record, @Param("example") SurveyAssetInventoryRightRecordExample example);

    int updateByExample(@Param("record") SurveyAssetInventoryRightRecord record, @Param("example") SurveyAssetInventoryRightRecordExample example);

    int updateByPrimaryKeySelective(SurveyAssetInventoryRightRecord record);

    int updateByPrimaryKey(SurveyAssetInventoryRightRecord record);
}