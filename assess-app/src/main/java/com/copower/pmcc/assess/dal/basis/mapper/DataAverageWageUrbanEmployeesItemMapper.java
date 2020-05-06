package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataAverageWageUrbanEmployeesItem;
import com.copower.pmcc.assess.dal.basis.entity.DataAverageWageUrbanEmployeesItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataAverageWageUrbanEmployeesItemMapper {
    int countByExample(DataAverageWageUrbanEmployeesItemExample example);

    int deleteByExample(DataAverageWageUrbanEmployeesItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataAverageWageUrbanEmployeesItem record);

    int insertSelective(DataAverageWageUrbanEmployeesItem record);

    List<DataAverageWageUrbanEmployeesItem> selectByExample(DataAverageWageUrbanEmployeesItemExample example);

    DataAverageWageUrbanEmployeesItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataAverageWageUrbanEmployeesItem record, @Param("example") DataAverageWageUrbanEmployeesItemExample example);

    int updateByExample(@Param("record") DataAverageWageUrbanEmployeesItem record, @Param("example") DataAverageWageUrbanEmployeesItemExample example);

    int updateByPrimaryKeySelective(DataAverageWageUrbanEmployeesItem record);

    int updateByPrimaryKey(DataAverageWageUrbanEmployeesItem record);
}