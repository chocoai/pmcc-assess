package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.StageWeightProportion;
import com.copower.pmcc.assess.dal.entity.StageWeightProportionExample;
import com.copower.pmcc.assess.dal.mapper.StageWeightProportionMapper;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StageWeightProportionDao {
    @Autowired
    private StageWeightProportionMapper stageWeightProportionMapper;

    public List<StageWeightProportion> getList(Integer entrustmentPurpose, Integer stage) {
        StageWeightProportionExample example = new StageWeightProportionExample();
        StageWeightProportionExample.Criteria criteria = example.createCriteria();

        if (entrustmentPurpose != null) {
            criteria.andEntrustPurposeEqualTo(entrustmentPurpose);
        }
        if (stage != null) {
            criteria.andStageEqualTo(stage);
        }
        example.setOrderByClause("entrust_purpose ASC,stage ASC");
        List<StageWeightProportion> stageWeightProportions = stageWeightProportionMapper.selectByExample(example);

        return stageWeightProportions;
    }

    public boolean update(StageWeightProportion stageWeightProportion) {
        int i = stageWeightProportionMapper.updateByPrimaryKeySelective(stageWeightProportion);
        return i > 0;
    }

    public boolean save(StageWeightProportion stageWeightProportion) {
        StageWeightProportionExample example = new StageWeightProportionExample();
        StageWeightProportionExample.Criteria criteria = example.createCriteria();
        criteria.andEntrustPurposeEqualTo(stageWeightProportion.getEntrustPurpose());
        criteria.andStageEqualTo(stageWeightProportion.getStage());
        List<StageWeightProportion> stageWeightProportions = stageWeightProportionMapper.selectByExample(example);
        if (stageWeightProportions != null && stageWeightProportions.size() > 0) {
            return false;
        }else {
            int i = stageWeightProportionMapper.insertSelective(stageWeightProportion);
            return i > 0;
        }
    }

    public boolean delete(Integer id) {
        int i = stageWeightProportionMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public StageWeightProportion getSingleObject(Integer id) {
        StageWeightProportion stageWeightProportion = stageWeightProportionMapper.selectByPrimaryKey(id);
        return stageWeightProportion;
    }

}
