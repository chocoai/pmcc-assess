package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEconomicIndicators;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEconomicIndicatorsExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareBuildEconomicIndicatorsMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/27 18:01
 * @Description:
 */
@Repository
public class DeclareBuildEconomicIndicatorsDao {
    @Autowired
    private DeclareBuildEconomicIndicatorsMapper declareBuildEconomicIndicatorsMapper;

    public Integer addDeclareBuildEconomicIndicators(DeclareBuildEconomicIndicators declareBuildEconomicIndicators){
        declareBuildEconomicIndicatorsMapper.insertSelective(declareBuildEconomicIndicators);
        return declareBuildEconomicIndicators.getId();
    }

    public DeclareBuildEconomicIndicators getDeclareBuildEconomicIndicatorsById(Integer id){
        return declareBuildEconomicIndicatorsMapper.selectByPrimaryKey(id);
    }

    public boolean updateDeclareBuildEconomicIndicators(DeclareBuildEconomicIndicators declareBuildEconomicIndicators){
        return declareBuildEconomicIndicatorsMapper.updateByPrimaryKeySelective(declareBuildEconomicIndicators)==1;
    }

    public void removeDeclareBuildEconomicIndicators(DeclareBuildEconomicIndicators declareBuildEconomicIndicators){
        DeclareBuildEconomicIndicatorsExample example = new DeclareBuildEconomicIndicatorsExample();
        MybatisUtils.convertObj2Example(declareBuildEconomicIndicators, example);
        declareBuildEconomicIndicatorsMapper.deleteByExample(example);
    }

    public List<DeclareBuildEconomicIndicators> getDeclareBuildEconomicIndicatorsList(DeclareBuildEconomicIndicators declareBuildEconomicIndicators){
        DeclareBuildEconomicIndicatorsExample example = new DeclareBuildEconomicIndicatorsExample();
        MybatisUtils.convertObj2Example(declareBuildEconomicIndicators, example);
        return declareBuildEconomicIndicatorsMapper.selectByExample(example);
    }
}
