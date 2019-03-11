package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataReportAnalysis;
import com.copower.pmcc.assess.dal.basis.entity.DataReportAnalysisExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataReportAnalysisMapper {
    int countByExample(DataReportAnalysisExample example);

    int deleteByExample(DataReportAnalysisExample example);

    int insert(DataReportAnalysis record);

    int insertSelective(DataReportAnalysis record);

    List<DataReportAnalysis> selectByExample(DataReportAnalysisExample example);

    int updateByExampleSelective(@Param("record") DataReportAnalysis record, @Param("example") DataReportAnalysisExample example);

    int updateByExample(@Param("record") DataReportAnalysis record, @Param("example") DataReportAnalysisExample example);
}