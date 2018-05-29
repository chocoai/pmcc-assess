package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.HousePriceIndex;
import com.copower.pmcc.assess.dal.entity.HousePriceIndexExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HousePriceIndexMapper {
    int countByExample(HousePriceIndexExample example);

    int deleteByExample(HousePriceIndexExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HousePriceIndex record);

    int insertSelective(HousePriceIndex record);

    List<HousePriceIndex> selectByExample(HousePriceIndexExample example);

    HousePriceIndex selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HousePriceIndex record, @Param("example") HousePriceIndexExample example);

    int updateByExample(@Param("record") HousePriceIndex record, @Param("example") HousePriceIndexExample example);

    int updateByPrimaryKeySelective(HousePriceIndex record);

    int updateByPrimaryKey(HousePriceIndex record);
}