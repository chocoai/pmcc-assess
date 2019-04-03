package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.CompileReportDetail;
import com.copower.pmcc.assess.dal.basis.entity.CompileReportDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CompileReportDetailMapper {
    int countByExample(CompileReportDetailExample example);

    int deleteByExample(CompileReportDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CompileReportDetail record);

    int insertSelective(CompileReportDetail record);

    List<CompileReportDetail> selectByExampleWithBLOBs(CompileReportDetailExample example);

    List<CompileReportDetail> selectByExample(CompileReportDetailExample example);

    CompileReportDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CompileReportDetail record, @Param("example") CompileReportDetailExample example);

    int updateByExampleWithBLOBs(@Param("record") CompileReportDetail record, @Param("example") CompileReportDetailExample example);

    int updateByExample(@Param("record") CompileReportDetail record, @Param("example") CompileReportDetailExample example);

    int updateByPrimaryKeySelective(CompileReportDetail record);

    int updateByPrimaryKeyWithBLOBs(CompileReportDetail record);

    int updateByPrimaryKey(CompileReportDetail record);
}