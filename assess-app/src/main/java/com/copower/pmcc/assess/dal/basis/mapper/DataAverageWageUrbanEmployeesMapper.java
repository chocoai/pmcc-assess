package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataAverageWageUrbanEmployees;
import com.copower.pmcc.assess.dal.basis.entity.DataAverageWageUrbanEmployeesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataAverageWageUrbanEmployeesMapper {
    int countByExample(DataAverageWageUrbanEmployeesExample example);

    int deleteByExample(DataAverageWageUrbanEmployeesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataAverageWageUrbanEmployees record);

    int insertSelective(DataAverageWageUrbanEmployees record);

    List<DataAverageWageUrbanEmployees> selectByExample(DataAverageWageUrbanEmployeesExample example);

    DataAverageWageUrbanEmployees selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataAverageWageUrbanEmployees record, @Param("example") DataAverageWageUrbanEmployeesExample example);

    int updateByExample(@Param("record") DataAverageWageUrbanEmployees record, @Param("example") DataAverageWageUrbanEmployeesExample example);

    int updateByPrimaryKeySelective(DataAverageWageUrbanEmployees record);

    int updateByPrimaryKey(DataAverageWageUrbanEmployees record);
}