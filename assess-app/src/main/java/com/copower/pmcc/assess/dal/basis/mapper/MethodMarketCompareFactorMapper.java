package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MethodMarketCompareFactor;
import com.copower.pmcc.assess.dal.basis.entity.MethodMarketCompareFactorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MethodMarketCompareFactorMapper {
    int countByExample(MethodMarketCompareFactorExample example);

    int deleteByExample(MethodMarketCompareFactorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MethodMarketCompareFactor record);

    int insertSelective(MethodMarketCompareFactor record);

    List<MethodMarketCompareFactor> selectByExample(MethodMarketCompareFactorExample example);

    MethodMarketCompareFactor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MethodMarketCompareFactor record, @Param("example") MethodMarketCompareFactorExample example);

    int updateByExample(@Param("record") MethodMarketCompareFactor record, @Param("example") MethodMarketCompareFactorExample example);

    int updateByPrimaryKeySelective(MethodMarketCompareFactor record);

    int updateByPrimaryKey(MethodMarketCompareFactor record);
}