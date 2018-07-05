package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataReportMergeTemplate;
import com.copower.pmcc.assess.dal.basis.entity.DataReportMergeTemplateExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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