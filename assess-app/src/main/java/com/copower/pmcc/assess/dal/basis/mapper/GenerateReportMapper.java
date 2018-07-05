package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.GenerateReport;
import com.copower.pmcc.assess.dal.basis.entity.GenerateReportExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GenerateReportMapper {
    int countByExample(GenerateReportExample example);

    int deleteByExample(GenerateReportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GenerateReport record);

    int insertSelective(GenerateReport record);

    List<GenerateReport> selectByExample(GenerateReportExample example);

    GenerateReport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GenerateReport record, @Param("example") GenerateReportExample example);

    int updateByExample(@Param("record") GenerateReport record, @Param("example") GenerateReportExample example);

    int updateByPrimaryKeySelective(GenerateReport record);

    int updateByPrimaryKey(GenerateReport record);
}