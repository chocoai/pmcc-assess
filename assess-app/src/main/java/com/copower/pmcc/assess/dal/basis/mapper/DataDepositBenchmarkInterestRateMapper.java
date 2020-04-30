package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataDepositBenchmarkInterestRate;
import com.copower.pmcc.assess.dal.basis.entity.DataDepositBenchmarkInterestRateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataDepositBenchmarkInterestRateMapper {
    int countByExample(DataDepositBenchmarkInterestRateExample example);

    int deleteByExample(DataDepositBenchmarkInterestRateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataDepositBenchmarkInterestRate record);

    int insertSelective(DataDepositBenchmarkInterestRate record);

    List<DataDepositBenchmarkInterestRate> selectByExample(DataDepositBenchmarkInterestRateExample example);

    DataDepositBenchmarkInterestRate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataDepositBenchmarkInterestRate record, @Param("example") DataDepositBenchmarkInterestRateExample example);

    int updateByExample(@Param("record") DataDepositBenchmarkInterestRate record, @Param("example") DataDepositBenchmarkInterestRateExample example);

    int updateByPrimaryKeySelective(DataDepositBenchmarkInterestRate record);

    int updateByPrimaryKey(DataDepositBenchmarkInterestRate record);
}