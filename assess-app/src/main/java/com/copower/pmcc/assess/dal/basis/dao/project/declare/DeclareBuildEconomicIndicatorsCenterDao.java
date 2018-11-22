package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEconomicIndicatorsCenter;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEconomicIndicatorsCenterExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareBuildEconomicIndicatorsCenterMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/22 10:20
 * @Description:
 */
@Repository
public class DeclareBuildEconomicIndicatorsCenterDao {

    @Autowired
    private DeclareBuildEconomicIndicatorsCenterMapper declareBuildEconomicIndicatorsCenterMapper;


    public Integer addDeclareBuildEconomicIndicatorsCenter(DeclareBuildEconomicIndicatorsCenter declareBuildEconomicIndicatorsCenter){
        declareBuildEconomicIndicatorsCenterMapper.insertSelective(declareBuildEconomicIndicatorsCenter);
        return declareBuildEconomicIndicatorsCenter.getId();
    }

    public DeclareBuildEconomicIndicatorsCenter getDeclareBuildEconomicIndicatorsCenterById(Integer id){
        return declareBuildEconomicIndicatorsCenterMapper.selectByPrimaryKey(id);
    }

    public boolean updateDeclareBuildEconomicIndicatorsCenter(DeclareBuildEconomicIndicatorsCenter declareBuildEconomicIndicatorsCenter){
        return declareBuildEconomicIndicatorsCenterMapper.updateByPrimaryKeySelective(declareBuildEconomicIndicatorsCenter)==1;
    }

    public void removeDeclareBuildEconomicIndicatorsCenter(DeclareBuildEconomicIndicatorsCenter declareBuildEconomicIndicatorsCenter){
        DeclareBuildEconomicIndicatorsCenterExample example = new DeclareBuildEconomicIndicatorsCenterExample();
        MybatisUtils.convertObj2Example(declareBuildEconomicIndicatorsCenter, example);
        declareBuildEconomicIndicatorsCenterMapper.deleteByExample(example);
    }

    public List<DeclareBuildEconomicIndicatorsCenter> getDeclareBuildEconomicIndicatorsCenterList(DeclareBuildEconomicIndicatorsCenter declareBuildEconomicIndicatorsCenter){
        DeclareBuildEconomicIndicatorsCenterExample example = new DeclareBuildEconomicIndicatorsCenterExample();
        MybatisUtils.convertObj2Example(declareBuildEconomicIndicatorsCenter, example);
        return declareBuildEconomicIndicatorsCenterMapper.selectByExample(example);
    }
    
}
