package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataLoanBenchmarkInterestRate;
import com.copower.pmcc.assess.dal.basis.entity.DataLoanBenchmarkInterestRateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataLoanBenchmarkInterestRateMapper {
    int countByExample(DataLoanBenchmarkInterestRateExample example);

    int deleteByExample(DataLoanBenchmarkInterestRateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataLoanBenchmarkInterestRate record);

    int insertSelective(DataLoanBenchmarkInterestRate record);

    List<DataLoanBenchmarkInterestRate> selectByExample(DataLoanBenchmarkInterestRateExample example);

    DataLoanBenchmarkInterestRate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataLoanBenchmarkInterestRate record, @Param("example") DataLoanBenchmarkInterestRateExample example);

    int updateByExample(@Param("record") DataLoanBenchmarkInterestRate record, @Param("example") DataLoanBenchmarkInterestRateExample example);

    int updateByPrimaryKeySelective(DataLoanBenchmarkInterestRate record);

    int updateByPrimaryKey(DataLoanBenchmarkInterestRate record);
}