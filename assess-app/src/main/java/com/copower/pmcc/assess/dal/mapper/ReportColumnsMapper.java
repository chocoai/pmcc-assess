package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.ReportColumns;
import com.copower.pmcc.assess.dal.entity.ReportColumnsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportColumnsMapper {
    int countByExample(ReportColumnsExample example);

    int deleteByExample(ReportColumnsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReportColumns record);

    int insertSelective(ReportColumns record);

    List<ReportColumns> selectByExample(ReportColumnsExample example);

    ReportColumns selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReportColumns record, @Param("example") ReportColumnsExample example);

    int updateByExample(@Param("record") ReportColumns record, @Param("example") ReportColumnsExample example);

    int updateByPrimaryKeySelective(ReportColumns record);

    int updateByPrimaryKey(ReportColumns record);
}