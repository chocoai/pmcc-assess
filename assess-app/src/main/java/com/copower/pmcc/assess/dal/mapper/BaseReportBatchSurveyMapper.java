package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.BaseReportBatchSurvey;
import com.copower.pmcc.assess.dal.entity.BaseReportBatchSurveyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseReportBatchSurveyMapper {
    int countByExample(BaseReportBatchSurveyExample example);

    int deleteByExample(BaseReportBatchSurveyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseReportBatchSurvey record);

    int insertSelective(BaseReportBatchSurvey record);

    List<BaseReportBatchSurvey> selectByExample(BaseReportBatchSurveyExample example);

    BaseReportBatchSurvey selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseReportBatchSurvey record, @Param("example") BaseReportBatchSurveyExample example);

    int updateByExample(@Param("record") BaseReportBatchSurvey record, @Param("example") BaseReportBatchSurveyExample example);

    int updateByPrimaryKeySelective(BaseReportBatchSurvey record);

    int updateByPrimaryKey(BaseReportBatchSurvey record);
}