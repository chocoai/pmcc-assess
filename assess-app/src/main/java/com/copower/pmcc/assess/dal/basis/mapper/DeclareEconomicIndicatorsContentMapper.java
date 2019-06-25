package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareEconomicIndicatorsContent;
import com.copower.pmcc.assess.dal.basis.entity.DeclareEconomicIndicatorsContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareEconomicIndicatorsContentMapper {
    int countByExample(DeclareEconomicIndicatorsContentExample example);

    int deleteByExample(DeclareEconomicIndicatorsContentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareEconomicIndicatorsContent record);

    int insertSelective(DeclareEconomicIndicatorsContent record);

    List<DeclareEconomicIndicatorsContent> selectByExample(DeclareEconomicIndicatorsContentExample example);

    DeclareEconomicIndicatorsContent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareEconomicIndicatorsContent record, @Param("example") DeclareEconomicIndicatorsContentExample example);

    int updateByExample(@Param("record") DeclareEconomicIndicatorsContent record, @Param("example") DeclareEconomicIndicatorsContentExample example);

    int updateByPrimaryKeySelective(DeclareEconomicIndicatorsContent record);

    int updateByPrimaryKey(DeclareEconomicIndicatorsContent record);
}