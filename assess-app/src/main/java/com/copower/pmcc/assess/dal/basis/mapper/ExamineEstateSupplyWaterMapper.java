package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateSupplyWater;
import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateSupplyWaterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineEstateSupplyWaterMapper {
    int countByExample(ExamineEstateSupplyWaterExample example);

    int deleteByExample(ExamineEstateSupplyWaterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineEstateSupplyWater record);

    int insertSelective(ExamineEstateSupplyWater record);

    List<ExamineEstateSupplyWater> selectByExample(ExamineEstateSupplyWaterExample example);

    ExamineEstateSupplyWater selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineEstateSupplyWater record, @Param("example") ExamineEstateSupplyWaterExample example);

    int updateByExample(@Param("record") ExamineEstateSupplyWater record, @Param("example") ExamineEstateSupplyWaterExample example);

    int updateByPrimaryKeySelective(ExamineEstateSupplyWater record);

    int updateByPrimaryKey(ExamineEstateSupplyWater record);
}