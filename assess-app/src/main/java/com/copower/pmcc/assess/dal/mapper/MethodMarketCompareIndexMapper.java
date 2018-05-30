package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.MethodMarketCompareIndex;
import com.copower.pmcc.assess.dal.entity.MethodMarketCompareIndexExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MethodMarketCompareIndexMapper {
    int countByExample(MethodMarketCompareIndexExample example);

    int deleteByExample(MethodMarketCompareIndexExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MethodMarketCompareIndex record);

    int insertSelective(MethodMarketCompareIndex record);

    List<MethodMarketCompareIndex> selectByExample(MethodMarketCompareIndexExample example);

    MethodMarketCompareIndex selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MethodMarketCompareIndex record, @Param("example") MethodMarketCompareIndexExample example);

    int updateByExample(@Param("record") MethodMarketCompareIndex record, @Param("example") MethodMarketCompareIndexExample example);

    int updateByPrimaryKeySelective(MethodMarketCompareIndex record);

    int updateByPrimaryKey(MethodMarketCompareIndex record);
}