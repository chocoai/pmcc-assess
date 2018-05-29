package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.StageWeightProportion;
import com.copower.pmcc.assess.dal.entity.StageWeightProportionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StageWeightProportionMapper {
    int countByExample(StageWeightProportionExample example);

    int deleteByExample(StageWeightProportionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StageWeightProportion record);

    int insertSelective(StageWeightProportion record);

    List<StageWeightProportion> selectByExample(StageWeightProportionExample example);

    StageWeightProportion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StageWeightProportion record, @Param("example") StageWeightProportionExample example);

    int updateByExample(@Param("record") StageWeightProportion record, @Param("example") StageWeightProportionExample example);

    int updateByPrimaryKeySelective(StageWeightProportion record);

    int updateByPrimaryKey(StageWeightProportion record);
}