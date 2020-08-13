package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdEconomicIndicatorsItem;
import com.copower.pmcc.assess.dal.basis.entity.MdEconomicIndicatorsItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdEconomicIndicatorsItemMapper {
    long countByExample(MdEconomicIndicatorsItemExample example);

    int deleteByExample(MdEconomicIndicatorsItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdEconomicIndicatorsItem record);

    int insertSelective(@Param("record") MdEconomicIndicatorsItem record, @Param("selective") MdEconomicIndicatorsItem.Column ... selective);

    List<MdEconomicIndicatorsItem> selectByExample(MdEconomicIndicatorsItemExample example);

    MdEconomicIndicatorsItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdEconomicIndicatorsItem record, @Param("example") MdEconomicIndicatorsItemExample example, @Param("selective") MdEconomicIndicatorsItem.Column ... selective);

    int updateByExample(@Param("record") MdEconomicIndicatorsItem record, @Param("example") MdEconomicIndicatorsItemExample example);

    int updateByPrimaryKeySelective(@Param("record") MdEconomicIndicatorsItem record, @Param("selective") MdEconomicIndicatorsItem.Column ... selective);

    int updateByPrimaryKey(MdEconomicIndicatorsItem record);

    int batchInsert(@Param("list") List<MdEconomicIndicatorsItem> list);

    int batchInsertSelective(@Param("list") List<MdEconomicIndicatorsItem> list, @Param("selective") MdEconomicIndicatorsItem.Column ... selective);
}