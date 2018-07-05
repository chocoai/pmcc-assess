package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BaseReportBatchTemplate;
import com.copower.pmcc.assess.dal.basis.entity.BaseReportBatchTemplateExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseReportBatchTemplateMapper {
    int countByExample(BaseReportBatchTemplateExample example);

    int deleteByExample(BaseReportBatchTemplateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseReportBatchTemplate record);

    int insertSelective(BaseReportBatchTemplate record);

    List<BaseReportBatchTemplate> selectByExample(BaseReportBatchTemplateExample example);

    BaseReportBatchTemplate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseReportBatchTemplate record, @Param("example") BaseReportBatchTemplateExample example);

    int updateByExample(@Param("record") BaseReportBatchTemplate record, @Param("example") BaseReportBatchTemplateExample example);

    int updateByPrimaryKeySelective(BaseReportBatchTemplate record);

    int updateByPrimaryKey(BaseReportBatchTemplate record);
}