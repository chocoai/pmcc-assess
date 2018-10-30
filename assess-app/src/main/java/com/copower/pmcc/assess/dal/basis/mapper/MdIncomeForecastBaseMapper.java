package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecastBase;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecastBaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdIncomeForecastBaseMapper {
    int countByExample(MdIncomeForecastBaseExample example);

    int deleteByExample(MdIncomeForecastBaseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdIncomeForecastBase record);

    int insertSelective(MdIncomeForecastBase record);

    List<MdIncomeForecastBase> selectByExample(MdIncomeForecastBaseExample example);

    MdIncomeForecastBase selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdIncomeForecastBase record, @Param("example") MdIncomeForecastBaseExample example);

    int updateByExample(@Param("record") MdIncomeForecastBase record, @Param("example") MdIncomeForecastBaseExample example);

    int updateByPrimaryKeySelective(MdIncomeForecastBase record);

    int updateByPrimaryKey(MdIncomeForecastBase record);
}