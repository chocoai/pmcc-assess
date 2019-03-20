package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRightRecordCenter;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRightRecordCenterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SurveyAssetInventoryRightRecordCenterMapper {
    int countByExample(SurveyAssetInventoryRightRecordCenterExample example);

    int deleteByExample(SurveyAssetInventoryRightRecordCenterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SurveyAssetInventoryRightRecordCenter record);

    int insertSelective(SurveyAssetInventoryRightRecordCenter record);

    List<SurveyAssetInventoryRightRecordCenter> selectByExample(SurveyAssetInventoryRightRecordCenterExample example);

    SurveyAssetInventoryRightRecordCenter selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SurveyAssetInventoryRightRecordCenter record, @Param("example") SurveyAssetInventoryRightRecordCenterExample example);

    int updateByExample(@Param("record") SurveyAssetInventoryRightRecordCenter record, @Param("example") SurveyAssetInventoryRightRecordCenterExample example);

    int updateByPrimaryKeySelective(SurveyAssetInventoryRightRecordCenter record);

    int updateByPrimaryKey(SurveyAssetInventoryRightRecordCenter record);
}