package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEconomicIndicators;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEconomicIndicatorsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareBuildEconomicIndicatorsMapper {
    int countByExample(DeclareBuildEconomicIndicatorsExample example);

    int deleteByExample(DeclareBuildEconomicIndicatorsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareBuildEconomicIndicators record);

    int insertSelective(DeclareBuildEconomicIndicators record);

    List<DeclareBuildEconomicIndicators> selectByExample(DeclareBuildEconomicIndicatorsExample example);

    DeclareBuildEconomicIndicators selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareBuildEconomicIndicators record, @Param("example") DeclareBuildEconomicIndicatorsExample example);

    int updateByExample(@Param("record") DeclareBuildEconomicIndicators record, @Param("example") DeclareBuildEconomicIndicatorsExample example);

    int updateByPrimaryKeySelective(DeclareBuildEconomicIndicators record);

    int updateByPrimaryKey(DeclareBuildEconomicIndicators record);
}