package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecastMonth;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecastMonthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdIncomeForecastMonthMapper {
    int countByExample(MdIncomeForecastMonthExample example);

    int deleteByExample(MdIncomeForecastMonthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdIncomeForecastMonth record);

    int insertSelective(MdIncomeForecastMonth record);

    List<MdIncomeForecastMonth> selectByExample(MdIncomeForecastMonthExample example);

    MdIncomeForecastMonth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdIncomeForecastMonth record, @Param("example") MdIncomeForecastMonthExample example);

    int updateByExample(@Param("record") MdIncomeForecastMonth record, @Param("example") MdIncomeForecastMonthExample example);

    int updateByPrimaryKeySelective(MdIncomeForecastMonth record);

    int updateByPrimaryKey(MdIncomeForecastMonth record);
}