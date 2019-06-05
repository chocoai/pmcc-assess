package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.GenerateReportInfo;
import com.copower.pmcc.assess.dal.basis.entity.GenerateReportInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GenerateReportInfoMapper {
    int countByExample(GenerateReportInfoExample example);

    int deleteByExample(GenerateReportInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GenerateReportInfo record);

    int insertSelective(GenerateReportInfo record);

    List<GenerateReportInfo> selectByExample(GenerateReportInfoExample example);

    GenerateReportInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GenerateReportInfo record, @Param("example") GenerateReportInfoExample example);

    int updateByExample(@Param("record") GenerateReportInfo record, @Param("example") GenerateReportInfoExample example);

    int updateByPrimaryKeySelective(GenerateReportInfo record);

    int updateByPrimaryKey(GenerateReportInfo record);
}