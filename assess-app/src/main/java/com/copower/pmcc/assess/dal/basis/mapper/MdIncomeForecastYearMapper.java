package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecastYear;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecastYearExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdIncomeForecastYearMapper {
    int countByExample(MdIncomeForecastYearExample example);

    int deleteByExample(MdIncomeForecastYearExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdIncomeForecastYear record);

    int insertSelective(MdIncomeForecastYear record);

    List<MdIncomeForecastYear> selectByExample(MdIncomeForecastYearExample example);

    MdIncomeForecastYear selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdIncomeForecastYear record, @Param("example") MdIncomeForecastYearExample example);

    int updateByExample(@Param("record") MdIncomeForecastYear record, @Param("example") MdIncomeForecastYearExample example);

    int updateByPrimaryKeySelective(MdIncomeForecastYear record);

    int updateByPrimaryKey(MdIncomeForecastYear record);
}