package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.BaseReportTemplate;
import com.copower.pmcc.assess.dal.entity.BaseReportTemplateExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseReportTemplateMapper {
    int countByExample(BaseReportTemplateExample example);

    int deleteByExample(BaseReportTemplateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseReportTemplate record);

    int insertSelective(BaseReportTemplate record);

    List<BaseReportTemplate> selectByExample(BaseReportTemplateExample example);

    BaseReportTemplate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseReportTemplate record, @Param("example") BaseReportTemplateExample example);

    int updateByExample(@Param("record") BaseReportTemplate record, @Param("example") BaseReportTemplateExample example);

    int updateByPrimaryKeySelective(BaseReportTemplate record);

    int updateByPrimaryKey(BaseReportTemplate record);
}