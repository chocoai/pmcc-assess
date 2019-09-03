package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecastAnalyseItem;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecastAnalyseItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdIncomeForecastAnalyseItemMapper {
    int countByExample(MdIncomeForecastAnalyseItemExample example);

    int deleteByExample(MdIncomeForecastAnalyseItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdIncomeForecastAnalyseItem record);

    int insertSelective(MdIncomeForecastAnalyseItem record);

    List<MdIncomeForecastAnalyseItem> selectByExample(MdIncomeForecastAnalyseItemExample example);

    MdIncomeForecastAnalyseItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdIncomeForecastAnalyseItem record, @Param("example") MdIncomeForecastAnalyseItemExample example);

    int updateByExample(@Param("record") MdIncomeForecastAnalyseItem record, @Param("example") MdIncomeForecastAnalyseItemExample example);

    int updateByPrimaryKeySelective(MdIncomeForecastAnalyseItem record);

    int updateByPrimaryKey(MdIncomeForecastAnalyseItem record);
}