package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateSupplyPower;
import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateSupplyPowerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineEstateSupplyPowerMapper {
    int countByExample(ExamineEstateSupplyPowerExample example);

    int deleteByExample(ExamineEstateSupplyPowerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineEstateSupplyPower record);

    int insertSelective(ExamineEstateSupplyPower record);

    List<ExamineEstateSupplyPower> selectByExample(ExamineEstateSupplyPowerExample example);

    ExamineEstateSupplyPower selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineEstateSupplyPower record, @Param("example") ExamineEstateSupplyPowerExample example);

    int updateByExample(@Param("record") ExamineEstateSupplyPower record, @Param("example") ExamineEstateSupplyPowerExample example);

    int updateByPrimaryKeySelective(ExamineEstateSupplyPower record);

    int updateByPrimaryKey(ExamineEstateSupplyPower record);
}