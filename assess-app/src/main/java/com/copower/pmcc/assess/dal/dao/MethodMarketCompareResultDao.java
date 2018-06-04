package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.MethodMarketCompareResult;
import com.copower.pmcc.assess.dal.entity.MethodMarketCompareResultExample;
import com.copower.pmcc.assess.dal.mapper.MethodMarketCompareResultMapper;
import com.copower.pmcc.assess.dto.input.project.MethodMarketCompareResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zly on 2018/5/31.
 */
@Repository
public class MethodMarketCompareResultDao {
    @Autowired
    private MethodMarketCompareResultMapper methodMarketCompareResultMapper;

    public boolean save(MethodMarketCompareResultDto methodMarketCompareResultDto){
        int i = methodMarketCompareResultMapper.insertSelective(methodMarketCompareResultDto);
        return i>0;
    }

    public List<MethodMarketCompareResult> getDataByEvaluationObjectId(Integer schemeEvaluationObjectId) {
        MethodMarketCompareResultExample example = new MethodMarketCompareResultExample();
        example.createCriteria().andEvaluationObjectIdEqualTo(schemeEvaluationObjectId);
        return methodMarketCompareResultMapper.selectByExample(example);
    }

    public void update(MethodMarketCompareResultDto methodMarketCompareResultDto) {
        methodMarketCompareResultMapper.updateByPrimaryKeySelective(methodMarketCompareResultDto);
    }
}
