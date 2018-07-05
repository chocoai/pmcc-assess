package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.CsrCalculation;
import com.copower.pmcc.assess.dal.basis.entity.CsrCalculationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CsrCalculationMapper {
    int countByExample(CsrCalculationExample example);

    int deleteByExample(CsrCalculationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CsrCalculation record);

    int insertSelective(CsrCalculation record);

    List<CsrCalculation> selectByExample(CsrCalculationExample example);

    CsrCalculation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CsrCalculation record, @Param("example") CsrCalculationExample example);

    int updateByExample(@Param("record") CsrCalculation record, @Param("example") CsrCalculationExample example);

    int updateByPrimaryKeySelective(CsrCalculation record);

    int updateByPrimaryKey(CsrCalculation record);
}