package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.CompileReportDetails;
import com.copower.pmcc.assess.dal.basis.entity.CompileReportDetailsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CompileReportDetailsMapper {
    int countByExample(CompileReportDetailsExample example);

    int deleteByExample(CompileReportDetailsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CompileReportDetails record);

    int insertSelective(CompileReportDetails record);

    List<CompileReportDetails> selectByExample(CompileReportDetailsExample example);

    CompileReportDetails selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CompileReportDetails record, @Param("example") CompileReportDetailsExample example);

    int updateByExample(@Param("record") CompileReportDetails record, @Param("example") CompileReportDetailsExample example);

    int updateByPrimaryKeySelective(CompileReportDetails record);

    int updateByPrimaryKey(CompileReportDetails record);
}