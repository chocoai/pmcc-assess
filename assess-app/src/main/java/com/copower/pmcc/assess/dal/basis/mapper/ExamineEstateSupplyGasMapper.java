package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateSupplyGas;
import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateSupplyGasExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineEstateSupplyGasMapper {
    int countByExample(ExamineEstateSupplyGasExample example);

    int deleteByExample(ExamineEstateSupplyGasExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineEstateSupplyGas record);

    int insertSelective(ExamineEstateSupplyGas record);

    List<ExamineEstateSupplyGas> selectByExample(ExamineEstateSupplyGasExample example);

    ExamineEstateSupplyGas selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineEstateSupplyGas record, @Param("example") ExamineEstateSupplyGasExample example);

    int updateByExample(@Param("record") ExamineEstateSupplyGas record, @Param("example") ExamineEstateSupplyGasExample example);

    int updateByPrimaryKeySelective(ExamineEstateSupplyGas record);

    int updateByPrimaryKey(ExamineEstateSupplyGas record);
}