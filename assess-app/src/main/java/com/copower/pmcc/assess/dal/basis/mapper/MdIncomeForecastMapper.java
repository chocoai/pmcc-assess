package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecast;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecastExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdIncomeForecastMapper {
    int countByExample(MdIncomeForecastExample example);

    int deleteByExample(MdIncomeForecastExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdIncomeForecast record);

    int insertSelective(MdIncomeForecast record);

    List<MdIncomeForecast> selectByExample(MdIncomeForecastExample example);

    MdIncomeForecast selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdIncomeForecast record, @Param("example") MdIncomeForecastExample example);

    int updateByExample(@Param("record") MdIncomeForecast record, @Param("example") MdIncomeForecastExample example);

    int updateByPrimaryKeySelective(MdIncomeForecast record);

    int updateByPrimaryKey(MdIncomeForecast record);
}