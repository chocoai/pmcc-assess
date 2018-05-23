package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.GenerateReportRecord;
import com.copower.pmcc.assess.dal.entity.GenerateReportRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GenerateReportRecordMapper {
    int countByExample(GenerateReportRecordExample example);

    int deleteByExample(GenerateReportRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GenerateReportRecord record);

    int insertSelective(GenerateReportRecord record);

    List<GenerateReportRecord> selectByExample(GenerateReportRecordExample example);

    GenerateReportRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GenerateReportRecord record, @Param("example") GenerateReportRecordExample example);

    int updateByExample(@Param("record") GenerateReportRecord record, @Param("example") GenerateReportRecordExample example);

    int updateByPrimaryKeySelective(GenerateReportRecord record);

    int updateByPrimaryKey(GenerateReportRecord record);
}