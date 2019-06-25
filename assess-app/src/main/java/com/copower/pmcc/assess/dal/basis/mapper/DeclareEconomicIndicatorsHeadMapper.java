package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DeclareEconomicIndicatorsHead;
import com.copower.pmcc.assess.dal.basis.entity.DeclareEconomicIndicatorsHeadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareEconomicIndicatorsHeadMapper {
    int countByExample(DeclareEconomicIndicatorsHeadExample example);

    int deleteByExample(DeclareEconomicIndicatorsHeadExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeclareEconomicIndicatorsHead record);

    int insertSelective(DeclareEconomicIndicatorsHead record);

    List<DeclareEconomicIndicatorsHead> selectByExample(DeclareEconomicIndicatorsHeadExample example);

    DeclareEconomicIndicatorsHead selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeclareEconomicIndicatorsHead record, @Param("example") DeclareEconomicIndicatorsHeadExample example);

    int updateByExample(@Param("record") DeclareEconomicIndicatorsHead record, @Param("example") DeclareEconomicIndicatorsHeadExample example);

    int updateByPrimaryKeySelective(DeclareEconomicIndicatorsHead record);

    int updateByPrimaryKey(DeclareEconomicIndicatorsHead record);
}