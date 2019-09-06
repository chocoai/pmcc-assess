package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdEconomicIndicators;
import com.copower.pmcc.assess.dal.basis.entity.MdEconomicIndicatorsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdEconomicIndicatorsMapper {
    int countByExample(MdEconomicIndicatorsExample example);

    int deleteByExample(MdEconomicIndicatorsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdEconomicIndicators record);

    int insertSelective(MdEconomicIndicators record);

    List<MdEconomicIndicators> selectByExample(MdEconomicIndicatorsExample example);

    MdEconomicIndicators selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdEconomicIndicators record, @Param("example") MdEconomicIndicatorsExample example);

    int updateByExample(@Param("record") MdEconomicIndicators record, @Param("example") MdEconomicIndicatorsExample example);

    int updateByPrimaryKeySelective(MdEconomicIndicators record);

    int updateByPrimaryKey(MdEconomicIndicators record);
}