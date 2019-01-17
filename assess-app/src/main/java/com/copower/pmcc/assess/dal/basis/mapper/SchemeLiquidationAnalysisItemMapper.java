package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SchemeLiquidationAnalysisItem;
import com.copower.pmcc.assess.dal.basis.entity.SchemeLiquidationAnalysisItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchemeLiquidationAnalysisItemMapper {
    int countByExample(SchemeLiquidationAnalysisItemExample example);

    int deleteByExample(SchemeLiquidationAnalysisItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchemeLiquidationAnalysisItem record);

    int insertSelective(SchemeLiquidationAnalysisItem record);

    List<SchemeLiquidationAnalysisItem> selectByExample(SchemeLiquidationAnalysisItemExample example);

    SchemeLiquidationAnalysisItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchemeLiquidationAnalysisItem record, @Param("example") SchemeLiquidationAnalysisItemExample example);

    int updateByExample(@Param("record") SchemeLiquidationAnalysisItem record, @Param("example") SchemeLiquidationAnalysisItemExample example);

    int updateByPrimaryKeySelective(SchemeLiquidationAnalysisItem record);

    int updateByPrimaryKey(SchemeLiquidationAnalysisItem record);
}