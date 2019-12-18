package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SchemeLiquidationAnalysisJudge;
import com.copower.pmcc.assess.dal.basis.entity.SchemeLiquidationAnalysisJudgeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchemeLiquidationAnalysisJudgeMapper {
    int countByExample(SchemeLiquidationAnalysisJudgeExample example);

    int deleteByExample(SchemeLiquidationAnalysisJudgeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchemeLiquidationAnalysisJudge record);

    int insertSelective(SchemeLiquidationAnalysisJudge record);

    List<SchemeLiquidationAnalysisJudge> selectByExample(SchemeLiquidationAnalysisJudgeExample example);

    SchemeLiquidationAnalysisJudge selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchemeLiquidationAnalysisJudge record, @Param("example") SchemeLiquidationAnalysisJudgeExample example);

    int updateByExample(@Param("record") SchemeLiquidationAnalysisJudge record, @Param("example") SchemeLiquidationAnalysisJudgeExample example);

    int updateByPrimaryKeySelective(SchemeLiquidationAnalysisJudge record);

    int updateByPrimaryKey(SchemeLiquidationAnalysisJudge record);
}