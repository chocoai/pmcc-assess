package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SchemeReportGeneration;
import com.copower.pmcc.assess.dal.basis.entity.SchemeReportGenerationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchemeReportGenerationMapper {
    int countByExample(SchemeReportGenerationExample example);

    int deleteByExample(SchemeReportGenerationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchemeReportGeneration record);

    int insertSelective(SchemeReportGeneration record);

    List<SchemeReportGeneration> selectByExample(SchemeReportGenerationExample example);

    SchemeReportGeneration selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchemeReportGeneration record, @Param("example") SchemeReportGenerationExample example);

    int updateByExample(@Param("record") SchemeReportGeneration record, @Param("example") SchemeReportGenerationExample example);

    int updateByPrimaryKeySelective(SchemeReportGeneration record);

    int updateByPrimaryKey(SchemeReportGeneration record);
}