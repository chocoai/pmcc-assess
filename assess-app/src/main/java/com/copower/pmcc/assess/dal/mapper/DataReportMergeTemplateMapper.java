package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.DataReportMergeTemplate;
import com.copower.pmcc.assess.dal.entity.DataReportMergeTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataReportMergeTemplateMapper {
    int countByExample(DataReportMergeTemplateExample example);

    int deleteByExample(DataReportMergeTemplateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataReportMergeTemplate record);

    int insertSelective(DataReportMergeTemplate record);

    List<DataReportMergeTemplate> selectByExample(DataReportMergeTemplateExample example);

    DataReportMergeTemplate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataReportMergeTemplate record, @Param("example") DataReportMergeTemplateExample example);

    int updateByExample(@Param("record") DataReportMergeTemplate record, @Param("example") DataReportMergeTemplateExample example);

    int updateByPrimaryKeySelective(DataReportMergeTemplate record);

    int updateByPrimaryKey(DataReportMergeTemplate record);
}