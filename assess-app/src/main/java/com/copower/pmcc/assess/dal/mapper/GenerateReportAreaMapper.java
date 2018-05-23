package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.GenerateReportArea;
import com.copower.pmcc.assess.dal.entity.GenerateReportAreaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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