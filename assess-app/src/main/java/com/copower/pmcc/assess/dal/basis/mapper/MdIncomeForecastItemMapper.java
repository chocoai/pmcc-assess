package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecastItem;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecastItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdIncomeForecastItemMapper {
    int countByExample(MdIncomeForecastItemExample example);

    int deleteByExample(MdIncomeForecastItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdIncomeForecastItem record);

    int insertSelective(MdIncomeForecastItem record);

    List<MdIncomeForecastItem> selectByExample(MdIncomeForecastItemExample example);

    MdIncomeForecastItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdIncomeForecastItem record, @Param("example") MdIncomeForecastItemExample example);

    int updateByExample(@Param("record") MdIncomeForecastItem record, @Param("example") MdIncomeForecastItemExample example);

    int updateByPrimaryKeySelective(MdIncomeForecastItem record);

    int updateByPrimaryKey(MdIncomeForecastItem record);
}