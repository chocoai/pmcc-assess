package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.CompileReport;
import com.copower.pmcc.assess.dal.entity.CompileReportExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CompileReportMapper {
    int countByExample(CompileReportExample example);

    int deleteByExample(CompileReportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CompileReport record);

    int insertSelective(CompileReport record);

    List<CompileReport> selectByExample(CompileReportExample example);

    CompileReport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CompileReport record, @Param("example") CompileReportExample example);

    int updateByExample(@Param("record") CompileReport record, @Param("example") CompileReportExample example);

    int updateByPrimaryKeySelective(CompileReport record);

    int updateByPrimaryKey(CompileReport record);
}