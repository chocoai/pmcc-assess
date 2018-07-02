package com.copower.pmcc.assess.dal.dao.data;

import com.copower.pmcc.assess.dal.entity.DataStageWeightProportion;
import com.copower.pmcc.assess.dal.entity.DataStageWeightProportionExample;
import com.copower.pmcc.assess.dal.mapper.DataStageWeightProportionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StageWeightProportionDao {
    @Autowired
    private DataStageWeightProportionMapper stageWeightProportionMapper;

    public List<DataStageWeightProportion> getList(Integer entrustmentPurpose) {
        DataStageWeightProportionExample example = new DataStageWeightProportionExample();
        DataStageWeightProportionExample.Criteria criteria = example.createCriteria();

        if (entrustmentPurpose != null) {
            criteria.andEntrustPurposeEqualTo(entrustmentPurpose);
        }

        example.setOrderByClause("entrust_purpose,stage");
        List<DataStageWeightProportion> stageWeightProportions = stageWeightProportionMapper.selectByExample(example);

        return stageWeightProportions;
    }

    public boolean update(DataStageWeightProportion stageWeightProportion) {
        int i = stageWeightProportionMapper.updateByPrimaryKeySelective(stageWeightProportion);
        return i > 0;
    }

    public boolean save(DataStageWeightProportion stageWeightProportion) {
        DataStageWeightProportionExample example = new DataStageWeightProportionExample();
        example.createCriteria().andEntrustPurposeEqualTo(stageWeightProportion.getEntrustPurpose()).andStageEqualTo(stageWeightProportion.getStage());
        List<DataStageWeightProportion> stageWeightProportions = stageWeightProportionMapper.selectByExample(example);
        if (stageWeightProportions != null && stageWeightProportions.size() > 0) {
            return false;
        }else {
            int i = stageWeightProportionMapper.insertSelective(stageWeightProportion);
            return i > 0;
        }
    }

    public boolean delete(Integer entrustPurpose) {
        DataStageWeightProportionExample example = new DataStageWeightProportionExample();
        example.createCriteria().andEntrustPurposeEqualTo(entrustPurpose);
        int i = stageWeightProportionMapper.deleteByExample(example);
        return i > 0;
    }

    public DataStageWeightProportion getSingleObject(Integer id) {
        DataStageWeightProportion stageWeightProportion = stageWeightProportionMapper.selectByPrimaryKey(id);
        return stageWeightProportion;
    }

    public List<DataStageWeightProportion> edit(DataStageWeightProportion stageWeightProportion) {
        DataStageWeightProportionExample example = new DataStageWeightProportionExample();
        example.createCriteria().andEntrustPurposeEqualTo(stageWeightProportion.getEntrustPurpose());
        List<DataStageWeightProportion> stageWeightProportions = stageWeightProportionMapper.selectByExample(example);
        return stageWeightProportions;
    }
}
