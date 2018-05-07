package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.DataReportAnalysisField;
import com.copower.pmcc.assess.dal.entity.DataReportAnalysisFieldExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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