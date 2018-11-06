package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecastAnalyse;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecastAnalyseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdIncomeForecastAnalyseMapper {
    int countByExample(MdIncomeForecastAnalyseExample example);

    int deleteByExample(MdIncomeForecastAnalyseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdIncomeForecastAnalyse record);

    int insertSelective(MdIncomeForecastAnalyse record);

    List<MdIncomeForecastAnalyse> selectByExample(MdIncomeForecastAnalyseExample example);

    MdIncomeForecastAnalyse selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdIncomeForecastAnalyse record, @Param("example") MdIncomeForecastAnalyseExample example);

    int updateByExample(@Param("record") MdIncomeForecastAnalyse record, @Param("example") MdIncomeForecastAnalyseExample example);

    int updateByPrimaryKeySelective(MdIncomeForecastAnalyse record);

    int updateByPrimaryKey(MdIncomeForecastAnalyse record);
}