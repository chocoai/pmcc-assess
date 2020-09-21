package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataHousePriceIndexDetail;
import com.copower.pmcc.assess.dal.basis.entity.DataHousePriceIndexDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataHousePriceIndexDetailMapper {
    long countByExample(DataHousePriceIndexDetailExample example);

    int deleteByExample(DataHousePriceIndexDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataHousePriceIndexDetail record);

    int insertSelective(DataHousePriceIndexDetail record);

    List<DataHousePriceIndexDetail> selectByExample(DataHousePriceIndexDetailExample example);

    DataHousePriceIndexDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataHousePriceIndexDetail record, @Param("example") DataHousePriceIndexDetailExample example);

    int updateByExample(@Param("record") DataHousePriceIndexDetail record, @Param("example") DataHousePriceIndexDetailExample example);

    int updateByPrimaryKeySelective(DataHousePriceIndexDetail record);

    int updateByPrimaryKey(DataHousePriceIndexDetail record);
}