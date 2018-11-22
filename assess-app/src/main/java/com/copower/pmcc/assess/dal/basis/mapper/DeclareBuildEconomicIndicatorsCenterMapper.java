package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEconomicIndicatorsCenter;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEconomicIndicatorsCenterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareBuildEconomicIndicatorsCenterMapper {
    int countByExample(DeclareBuildEconomicIndicatorsCenterExample example);

    int deleteByExample(DeclareBuildEconomicIndicatorsCenterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareBuildEconomicIndicatorsCenter record);

    int insertSelective(DeclareBuildEconomicIndicatorsCenter record);

    List<DeclareBuildEconomicIndicatorsCenter> selectByExample(DeclareBuildEconomicIndicatorsCenterExample example);

    DeclareBuildEconomicIndicatorsCenter selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareBuildEconomicIndicatorsCenter record, @Param("example") DeclareBuildEconomicIndicatorsCenterExample example);

    int updateByExample(@Param("record") DeclareBuildEconomicIndicatorsCenter record, @Param("example") DeclareBuildEconomicIndicatorsCenterExample example);

    int updateByPrimaryKeySelective(DeclareBuildEconomicIndicatorsCenter record);

    int updateByPrimaryKey(DeclareBuildEconomicIndicatorsCenter record);
}