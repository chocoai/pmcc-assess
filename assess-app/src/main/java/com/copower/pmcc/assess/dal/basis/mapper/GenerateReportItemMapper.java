package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.GenerateReportItem;
import com.copower.pmcc.assess.dal.basis.entity.GenerateReportItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GenerateReportItemMapper {
    int countByExample(GenerateReportItemExample example);

    int deleteByExample(GenerateReportItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GenerateReportItem record);

    int insertSelective(GenerateReportItem record);

    List<GenerateReportItem> selectByExample(GenerateReportItemExample example);

    GenerateReportItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GenerateReportItem record, @Param("example") GenerateReportItemExample example);

    int updateByExample(@Param("record") GenerateReportItem record, @Param("example") GenerateReportItemExample example);

    int updateByPrimaryKeySelective(GenerateReportItem record);

    int updateByPrimaryKey(GenerateReportItem record);
}