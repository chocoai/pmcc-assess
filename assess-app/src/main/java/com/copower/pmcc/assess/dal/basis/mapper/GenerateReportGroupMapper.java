package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.GenerateReportGroup;
import com.copower.pmcc.assess.dal.basis.entity.GenerateReportGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GenerateReportGroupMapper {
    int countByExample(GenerateReportGroupExample example);

    int deleteByExample(GenerateReportGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GenerateReportGroup record);

    int insertSelective(GenerateReportGroup record);

    List<GenerateReportGroup> selectByExample(GenerateReportGroupExample example);

    GenerateReportGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GenerateReportGroup record, @Param("example") GenerateReportGroupExample example);

    int updateByExample(@Param("record") GenerateReportGroup record, @Param("example") GenerateReportGroupExample example);

    int updateByPrimaryKeySelective(GenerateReportGroup record);

    int updateByPrimaryKey(GenerateReportGroup record);
}