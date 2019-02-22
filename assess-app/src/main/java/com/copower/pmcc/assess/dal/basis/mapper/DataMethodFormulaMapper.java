package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataMethodFormula;
import com.copower.pmcc.assess.dal.basis.entity.DataMethodFormulaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataMethodFormulaMapper {
    int countByExample(DataMethodFormulaExample example);

    int deleteByExample(DataMethodFormulaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataMethodFormula record);

    int insertSelective(DataMethodFormula record);

    List<DataMethodFormula> selectByExample(DataMethodFormulaExample example);

    DataMethodFormula selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataMethodFormula record, @Param("example") DataMethodFormulaExample example);

    int updateByExample(@Param("record") DataMethodFormula record, @Param("example") DataMethodFormulaExample example);

    int updateByPrimaryKeySelective(DataMethodFormula record);

    int updateByPrimaryKey(DataMethodFormula record);
}