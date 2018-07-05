package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.CompileReport;
import com.copower.pmcc.assess.dal.basis.entity.CompileReportExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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