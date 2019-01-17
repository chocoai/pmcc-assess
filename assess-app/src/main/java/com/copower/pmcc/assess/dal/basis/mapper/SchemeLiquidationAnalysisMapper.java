package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SchemeLiquidationAnalysis;
import com.copower.pmcc.assess.dal.basis.entity.SchemeLiquidationAnalysisExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchemeLiquidationAnalysisMapper {
    int countByExample(SchemeLiquidationAnalysisExample example);

    int deleteByExample(SchemeLiquidationAnalysisExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchemeLiquidationAnalysis record);

    int insertSelective(SchemeLiquidationAnalysis record);

    List<SchemeLiquidationAnalysis> selectByExample(SchemeLiquidationAnalysisExample example);

    SchemeLiquidationAnalysis selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchemeLiquidationAnalysis record, @Param("example") SchemeLiquidationAnalysisExample example);

    int updateByExample(@Param("record") SchemeLiquidationAnalysis record, @Param("example") SchemeLiquidationAnalysisExample example);

    int updateByPrimaryKeySelective(SchemeLiquidationAnalysis record);

    int updateByPrimaryKey(SchemeLiquidationAnalysis record);
}