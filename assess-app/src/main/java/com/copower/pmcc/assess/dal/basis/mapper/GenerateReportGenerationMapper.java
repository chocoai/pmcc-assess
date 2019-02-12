package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.GenerateReportGeneration;
import com.copower.pmcc.assess.dal.basis.entity.GenerateReportGenerationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GenerateReportGenerationMapper {
    int countByExample(GenerateReportGenerationExample example);

    int deleteByExample(GenerateReportGenerationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GenerateReportGeneration record);

    int insertSelective(GenerateReportGeneration record);

    List<GenerateReportGeneration> selectByExample(GenerateReportGenerationExample example);

    GenerateReportGeneration selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GenerateReportGeneration record, @Param("example") GenerateReportGenerationExample example);

    int updateByExample(@Param("record") GenerateReportGeneration record, @Param("example") GenerateReportGenerationExample example);

    int updateByPrimaryKeySelective(GenerateReportGeneration record);

    int updateByPrimaryKey(GenerateReportGeneration record);
}