package com.copower.pmcc.assess.dal.dao.project.method;

import com.copower.pmcc.assess.dal.entity.MethodMarketCompareFactor;
import com.copower.pmcc.assess.dal.entity.MethodMarketCompareFactorExample;
import com.copower.pmcc.assess.dal.mapper.MethodMarketCompareFactorMapper;
import com.copower.pmcc.assess.dto.input.project.method.MethodMarketCompareFactorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zly on 2018/5/31.
 */
@Repository
public class MethodMarketCompareFactorDao {

    @Autowired
    private MethodMarketCompareFactorMapper methodMarketCompareFactorMapper;

    public boolean save(MethodMarketCompareFactorDto methodMarketCompareFactorDto){
        int i = methodMarketCompareFactorMapper.insertSelective(methodMarketCompareFactorDto);
        return i > 0;
    }

    public List<MethodMarketCompareFactor> getDataByEvaluationObjectId(Integer schemeEvaluationObjectId) {
        MethodMarketCompareFactorExample example = new MethodMarketCompareFactorExample();
        example.createCriteria().andEvaluationObjectIdEqualTo(schemeEvaluationObjectId);
        return methodMarketCompareFactorMapper.selectByExample(example);
    }

    public void update(MethodMarketCompareFactorDto methodMarketCompareFactorDto) {
        methodMarketCompareFactorMapper.updateByPrimaryKeySelective(methodMarketCompareFactorDto);
    }
}
