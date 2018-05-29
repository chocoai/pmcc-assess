package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.DataReportAnalysis;
import com.copower.pmcc.assess.dal.entity.DataReportAnalysisExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataReportAnalysisMapper {
    int countByExample(DataReportAnalysisExample example);

    int deleteByExample(DataReportAnalysisExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataReportAnalysis record);

    int insertSelective(DataReportAnalysis record);

    List<DataReportAnalysis> selectByExample(DataReportAnalysisExample example);

    DataReportAnalysis selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataReportAnalysis record, @Param("example") DataReportAnalysisExample example);

    int updateByExample(@Param("record") DataReportAnalysis record, @Param("example") DataReportAnalysisExample example);

    int updateByPrimaryKeySelective(DataReportAnalysis record);

    int updateByPrimaryKey(DataReportAnalysis record);
}