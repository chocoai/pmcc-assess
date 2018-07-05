package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataReportAnalysisField;
import com.copower.pmcc.assess.dal.basis.entity.DataReportAnalysisFieldExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataReportAnalysisFieldMapper {
    int countByExample(DataReportAnalysisFieldExample example);

    int deleteByExample(DataReportAnalysisFieldExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataReportAnalysisField record);

    int insertSelective(DataReportAnalysisField record);

    List<DataReportAnalysisField> selectByExample(DataReportAnalysisFieldExample example);

    DataReportAnalysisField selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataReportAnalysisField record, @Param("example") DataReportAnalysisFieldExample example);

    int updateByExample(@Param("record") DataReportAnalysisField record, @Param("example") DataReportAnalysisFieldExample example);

    int updateByPrimaryKeySelective(DataReportAnalysisField record);

    int updateByPrimaryKey(DataReportAnalysisField record);
}