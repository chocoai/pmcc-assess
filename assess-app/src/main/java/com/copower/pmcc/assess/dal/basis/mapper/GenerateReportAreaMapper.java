package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.GenerateReportArea;
import com.copower.pmcc.assess.dal.basis.entity.GenerateReportAreaExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GenerateReportAreaMapper {
    int countByExample(GenerateReportAreaExample example);

    int deleteByExample(GenerateReportAreaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GenerateReportArea record);

    int insertSelective(GenerateReportArea record);

    List<GenerateReportArea> selectByExample(GenerateReportAreaExample example);

    GenerateReportArea selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GenerateReportArea record, @Param("example") GenerateReportAreaExample example);

    int updateByExample(@Param("record") GenerateReportArea record, @Param("example") GenerateReportAreaExample example);

    int updateByPrimaryKeySelective(GenerateReportArea record);

    int updateByPrimaryKey(GenerateReportArea record);
}