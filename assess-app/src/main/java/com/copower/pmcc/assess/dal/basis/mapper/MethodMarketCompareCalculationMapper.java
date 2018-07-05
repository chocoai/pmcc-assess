package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MethodMarketCompareCalculation;
import com.copower.pmcc.assess.dal.basis.entity.MethodMarketCompareCalculationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MethodMarketCompareCalculationMapper {
    int countByExample(MethodMarketCompareCalculationExample example);

    int deleteByExample(MethodMarketCompareCalculationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MethodMarketCompareCalculation record);

    int insertSelective(MethodMarketCompareCalculation record);

    List<MethodMarketCompareCalculation> selectByExample(MethodMarketCompareCalculationExample example);

    MethodMarketCompareCalculation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MethodMarketCompareCalculation record, @Param("example") MethodMarketCompareCalculationExample example);

    int updateByExample(@Param("record") MethodMarketCompareCalculation record, @Param("example") MethodMarketCompareCalculationExample example);

    int updateByPrimaryKeySelective(MethodMarketCompareCalculation record);

    int updateByPrimaryKey(MethodMarketCompareCalculation record);
}