package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataReportGeneralFactors;
import com.copower.pmcc.assess.dal.basis.entity.DataReportGeneralFactorsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataReportGeneralFactorsMapper {
    long countByExample(DataReportGeneralFactorsExample example);

    int deleteByExample(DataReportGeneralFactorsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataReportGeneralFactors record);

    int insertSelective(@Param("record") DataReportGeneralFactors record, @Param("selective") DataReportGeneralFactors.Column ... selective);

    List<DataReportGeneralFactors> selectByExampleWithBLOBs(DataReportGeneralFactorsExample example);

    List<DataReportGeneralFactors> selectByExample(DataReportGeneralFactorsExample example);

    DataReportGeneralFactors selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataReportGeneralFactors record, @Param("example") DataReportGeneralFactorsExample example, @Param("selective") DataReportGeneralFactors.Column ... selective);

    int updateByExampleWithBLOBs(@Param("record") DataReportGeneralFactors record, @Param("example") DataReportGeneralFactorsExample example);

    int updateByExample(@Param("record") DataReportGeneralFactors record, @Param("example") DataReportGeneralFactorsExample example);

    int updateByPrimaryKeySelective(@Param("record") DataReportGeneralFactors record, @Param("selective") DataReportGeneralFactors.Column ... selective);

    int updateByPrimaryKeyWithBLOBs(DataReportGeneralFactors record);

    int updateByPrimaryKey(DataReportGeneralFactors record);

    int batchInsert(@Param("list") List<DataReportGeneralFactors> list);

    int batchInsertSelective(@Param("list") List<DataReportGeneralFactors> list, @Param("selective") DataReportGeneralFactors.Column ... selective);
}