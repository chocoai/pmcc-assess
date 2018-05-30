package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.MethodMarketCompareResult;
import com.copower.pmcc.assess.dal.entity.MethodMarketCompareResultExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MethodMarketCompareResultMapper {
    int countByExample(MethodMarketCompareResultExample example);

    int deleteByExample(MethodMarketCompareResultExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MethodMarketCompareResult record);

    int insertSelective(MethodMarketCompareResult record);

    List<MethodMarketCompareResult> selectByExample(MethodMarketCompareResultExample example);

    MethodMarketCompareResult selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MethodMarketCompareResult record, @Param("example") MethodMarketCompareResultExample example);

    int updateByExample(@Param("record") MethodMarketCompareResult record, @Param("example") MethodMarketCompareResultExample example);

    int updateByPrimaryKeySelective(MethodMarketCompareResult record);

    int updateByPrimaryKey(MethodMarketCompareResult record);
}