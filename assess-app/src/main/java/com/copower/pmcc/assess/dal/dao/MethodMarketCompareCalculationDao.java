package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.MethodMarketCompareCalculation;
import com.copower.pmcc.assess.dal.entity.MethodMarketCompareCalculationExample;
import com.copower.pmcc.assess.dal.mapper.MethodMarketCompareCalculationMapper;
import com.copower.pmcc.assess.dto.input.project.MethodMarketCompareCalculationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zly on 2018/5/31.
 */
@Repository
public class MethodMarketCompareCalculationDao {

    @Autowired
    private MethodMarketCompareCalculationMapper methodMarketCompareCalculationMapper;

    public boolean save(MethodMarketCompareCalculationDto methodMarketCompareCalculationDto){
        int i = methodMarketCompareCalculationMapper.insertSelective(methodMarketCompareCalculationDto);
        return i>0;
    }

    public List<MethodMarketCompareCalculation> getDataByEvaluationObjectId(Integer schemeEvaluationObjectId) {
        MethodMarketCompareCalculationExample example = new MethodMarketCompareCalculationExample();
        example.createCriteria().andEvaluationObjectIdEqualTo(schemeEvaluationObjectId);
        return methodMarketCompareCalculationMapper.selectByExample(example);
    }
}
