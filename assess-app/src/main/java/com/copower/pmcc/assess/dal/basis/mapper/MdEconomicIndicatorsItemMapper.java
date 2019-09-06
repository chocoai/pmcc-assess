package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdEconomicIndicatorsItem;
import com.copower.pmcc.assess.dal.basis.entity.MdEconomicIndicatorsItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdEconomicIndicatorsItemMapper {
    int countByExample(MdEconomicIndicatorsItemExample example);

    int deleteByExample(MdEconomicIndicatorsItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdEconomicIndicatorsItem record);

    int insertSelective(MdEconomicIndicatorsItem record);

    List<MdEconomicIndicatorsItem> selectByExample(MdEconomicIndicatorsItemExample example);

    MdEconomicIndicatorsItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdEconomicIndicatorsItem record, @Param("example") MdEconomicIndicatorsItemExample example);

    int updateByExample(@Param("record") MdEconomicIndicatorsItem record, @Param("example") MdEconomicIndicatorsItemExample example);

    int updateByPrimaryKeySelective(MdEconomicIndicatorsItem record);

    int updateByPrimaryKey(MdEconomicIndicatorsItem record);
}