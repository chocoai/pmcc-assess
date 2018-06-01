package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.MethodMarketCompareIndex;
import com.copower.pmcc.assess.dal.entity.MethodMarketCompareIndexExample;
import com.copower.pmcc.assess.dal.mapper.MethodMarketCompareIndexMapper;
import com.copower.pmcc.assess.dto.input.project.MethodMarketCompareIndexDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zly on 2018/5/31.
 */
@Repository
public class MethodMarketCompareIndexDao {

    @Autowired
    MethodMarketCompareIndexMapper methodMarketCompareIndexMapper;

    public boolean save(MethodMarketCompareIndexDto methodMarketCompareIndexDto){
    int i = methodMarketCompareIndexMapper.insertSelective(methodMarketCompareIndexDto);
        return i>0;
    }

    public List<MethodMarketCompareIndex> getDataByEvaluationObjectId(Integer schemeEvaluationObjectId) {
        MethodMarketCompareIndexExample example = new MethodMarketCompareIndexExample();
        example.createCriteria().andEvaluationObjectIdEqualTo(schemeEvaluationObjectId);
        return methodMarketCompareIndexMapper.selectByExample(example);
    }
}
