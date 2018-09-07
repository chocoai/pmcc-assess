package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataTaxRateAllocation;
import com.copower.pmcc.assess.dal.basis.entity.DataTaxRateAllocationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataTaxRateAllocationMapper {
    int countByExample(DataTaxRateAllocationExample example);

    int deleteByExample(DataTaxRateAllocationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataTaxRateAllocation record);

    int insertSelective(DataTaxRateAllocation record);

    List<DataTaxRateAllocation> selectByExample(DataTaxRateAllocationExample example);

    DataTaxRateAllocation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataTaxRateAllocation record, @Param("example") DataTaxRateAllocationExample example);

    int updateByExample(@Param("record") DataTaxRateAllocation record, @Param("example") DataTaxRateAllocationExample example);

    int updateByPrimaryKeySelective(DataTaxRateAllocation record);

    int updateByPrimaryKey(DataTaxRateAllocation record);
}