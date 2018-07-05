package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BaseReportTemplate;
import com.copower.pmcc.assess.dal.basis.entity.BaseReportTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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