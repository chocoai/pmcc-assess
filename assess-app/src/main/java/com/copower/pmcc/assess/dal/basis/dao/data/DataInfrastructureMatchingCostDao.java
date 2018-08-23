package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.InfrastructureMatchingCost;
import com.copower.pmcc.assess.dal.basis.entity.InfrastructureMatchingCostExample;
import com.copower.pmcc.assess.dal.basis.mapper.InfrastructureMatchingCostMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liuwei
 */
@Repository
public class DataInfrastructureMatchingCostDao {
    @Autowired
    private InfrastructureMatchingCostMapper infrastructureMatchingCostMapper;
    //删除
    public Boolean deleteDataInfrastructureCost(Integer id){
        int result = infrastructureMatchingCostMapper.deleteByPrimaryKey(id);
        return result > 0;
    }
    //修改
    public Boolean editDataInfrastructureCost(InfrastructureMatchingCost infrastructureMatchingCost){
        int result = infrastructureMatchingCostMapper.updateByPrimaryKeySelective(infrastructureMatchingCost);
        return result > 0;
    }
    //新增
    public Boolean addDataInfrastructureCost(InfrastructureMatchingCost infrastructureMatchingCost){
        int result = infrastructureMatchingCostMapper.insertSelective(infrastructureMatchingCost);
        return result > 0;
    }
    //查询
    public List<InfrastructureMatchingCost> getDataInfrastructureCostList(String name){
        InfrastructureMatchingCostExample example = new InfrastructureMatchingCostExample();
        InfrastructureMatchingCostExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(name)) {
            criteria.andCreatorLike(String.format("%s%s%s", "%", name, "%"));
        }
        return infrastructureMatchingCostMapper.selectByExample(example) ;
    }
}
