package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseEstateSupplyGas;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateSupplyGasExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseEstateSupplyGasMapper {
    int countByExample(CaseEstateSupplyGasExample example);

    int deleteByExample(CaseEstateSupplyGasExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseEstateSupplyGas record);

    int insertSelective(CaseEstateSupplyGas record);

    List<CaseEstateSupplyGas> selectByExample(CaseEstateSupplyGasExample example);

    CaseEstateSupplyGas selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseEstateSupplyGas record, @Param("example") CaseEstateSupplyGasExample example);

    int updateByExample(@Param("record") CaseEstateSupplyGas record, @Param("example") CaseEstateSupplyGasExample example);

    int updateByPrimaryKeySelective(CaseEstateSupplyGas record);

    int updateByPrimaryKey(CaseEstateSupplyGas record);
}