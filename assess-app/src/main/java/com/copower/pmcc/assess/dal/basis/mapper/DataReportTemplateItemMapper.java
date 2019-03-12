package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataReportTemplateItem;
import com.copower.pmcc.assess.dal.basis.entity.DataReportTemplateItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataReportTemplateItemMapper {
    int countByExample(DataReportTemplateItemExample example);

    int deleteByExample(DataReportTemplateItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataReportTemplateItem record);

    int insertSelective(DataReportTemplateItem record);

    List<DataReportTemplateItem> selectByExample(DataReportTemplateItemExample example);

    DataReportTemplateItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataReportTemplateItem record, @Param("example") DataReportTemplateItemExample example);

    int updateByExample(@Param("record") DataReportTemplateItem record, @Param("example") DataReportTemplateItemExample example);

    int updateByPrimaryKeySelective(DataReportTemplateItem record);

    int updateByPrimaryKey(DataReportTemplateItem record);
}