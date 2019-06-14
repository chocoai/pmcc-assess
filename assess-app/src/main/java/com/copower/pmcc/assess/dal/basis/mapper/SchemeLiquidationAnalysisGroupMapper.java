package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SchemeLiquidationAnalysisGroup;
import com.copower.pmcc.assess.dal.basis.entity.SchemeLiquidationAnalysisGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchemeLiquidationAnalysisGroupMapper {
    int countByExample(SchemeLiquidationAnalysisGroupExample example);

    int deleteByExample(SchemeLiquidationAnalysisGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchemeLiquidationAnalysisGroup record);

    int insertSelective(SchemeLiquidationAnalysisGroup record);

    List<SchemeLiquidationAnalysisGroup> selectByExample(SchemeLiquidationAnalysisGroupExample example);

    SchemeLiquidationAnalysisGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchemeLiquidationAnalysisGroup record, @Param("example") SchemeLiquidationAnalysisGroupExample example);

    int updateByExample(@Param("record") SchemeLiquidationAnalysisGroup record, @Param("example") SchemeLiquidationAnalysisGroupExample example);

    int updateByPrimaryKeySelective(SchemeLiquidationAnalysisGroup record);

    int updateByPrimaryKey(SchemeLiquidationAnalysisGroup record);
}