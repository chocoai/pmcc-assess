package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureDevTax;
import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureDevTaxExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataInfrastructureDevTaxMapper {
    int countByExample(DataInfrastructureDevTaxExample example);

    int deleteByExample(DataInfrastructureDevTaxExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataInfrastructureDevTax record);

    int insertSelective(DataInfrastructureDevTax record);

    List<DataInfrastructureDevTax> selectByExample(DataInfrastructureDevTaxExample example);

    DataInfrastructureDevTax selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataInfrastructureDevTax record, @Param("example") DataInfrastructureDevTaxExample example);

    int updateByExample(@Param("record") DataInfrastructureDevTax record, @Param("example") DataInfrastructureDevTaxExample example);

    int updateByPrimaryKeySelective(DataInfrastructureDevTax record);

    int updateByPrimaryKey(DataInfrastructureDevTax record);
}