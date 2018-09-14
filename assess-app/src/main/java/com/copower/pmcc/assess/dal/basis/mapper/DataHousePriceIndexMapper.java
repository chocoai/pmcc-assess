package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataHousePriceIndex;
import com.copower.pmcc.assess.dal.basis.entity.DataHousePriceIndexExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataHousePriceIndexMapper {
    int countByExample(DataHousePriceIndexExample example);

    int deleteByExample(DataHousePriceIndexExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataHousePriceIndex record);

    int insertSelective(DataHousePriceIndex record);

    List<DataHousePriceIndex> selectByExample(DataHousePriceIndexExample example);

    DataHousePriceIndex selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataHousePriceIndex record, @Param("example") DataHousePriceIndexExample example);

    int updateByExample(@Param("record") DataHousePriceIndex record, @Param("example") DataHousePriceIndexExample example);

    int updateByPrimaryKeySelective(DataHousePriceIndex record);

    int updateByPrimaryKey(DataHousePriceIndex record);
}