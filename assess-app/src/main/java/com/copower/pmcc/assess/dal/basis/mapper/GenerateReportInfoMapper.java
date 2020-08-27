package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.GenerateReportInfo;
import com.copower.pmcc.assess.dal.basis.entity.GenerateReportInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GenerateReportInfoMapper {
    long countByExample(GenerateReportInfoExample example);

    int deleteByExample(GenerateReportInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GenerateReportInfo record);

    int insertSelective(@Param("record") GenerateReportInfo record, @Param("selective") GenerateReportInfo.Column ... selective);

    List<GenerateReportInfo> selectByExample(GenerateReportInfoExample example);

    GenerateReportInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GenerateReportInfo record, @Param("example") GenerateReportInfoExample example, @Param("selective") GenerateReportInfo.Column ... selective);

    int updateByExample(@Param("record") GenerateReportInfo record, @Param("example") GenerateReportInfoExample example);

    int updateByPrimaryKeySelective(@Param("record") GenerateReportInfo record, @Param("selective") GenerateReportInfo.Column ... selective);

    int updateByPrimaryKey(GenerateReportInfo record);

    int batchInsert(@Param("list") List<GenerateReportInfo> list);

    int batchInsertSelective(@Param("list") List<GenerateReportInfo> list, @Param("selective") GenerateReportInfo.Column ... selective);
}