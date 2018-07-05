package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataStageWeightProportion;
import com.copower.pmcc.assess.dal.basis.entity.DataStageWeightProportionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataStageWeightProportionMapper {
    int countByExample(DataStageWeightProportionExample example);

    int deleteByExample(DataStageWeightProportionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataStageWeightProportion record);

    int insertSelective(DataStageWeightProportion record);

    List<DataStageWeightProportion> selectByExample(DataStageWeightProportionExample example);

    DataStageWeightProportion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataStageWeightProportion record, @Param("example") DataStageWeightProportionExample example);

    int updateByExample(@Param("record") DataStageWeightProportion record, @Param("example") DataStageWeightProportionExample example);

    int updateByPrimaryKeySelective(DataStageWeightProportion record);

    int updateByPrimaryKey(DataStageWeightProportion record);
}