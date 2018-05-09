package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.InfrastructureCost;
import com.copower.pmcc.assess.dal.entity.InfrastructureCostExample;
import com.copower.pmcc.assess.dal.entity.InfrastructureMatchingCostExample;
import com.copower.pmcc.assess.dal.mapper.InfrastructureCostMapper;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liuwei
 */
@Repository
public class DataInfrastructureCostDao {

    @Autowired
   private InfrastructureCostMapper infrastructureCostMapper;
    //删除
    public Boolean deleteInfrastructureCost(Integer id){
        int result = infrastructureCostMapper.deleteByPrimaryKey(id);
        return result > 0;
    }
    //修改
    public Boolean editInfrastructureCost(InfrastructureCost infrastructureCost){
        int result = infrastructureCostMapper.updateByPrimaryKeySelective(infrastructureCost);
        return result > 0;
    }
    //新增
    public Boolean addInfrastructureCost(InfrastructureCost infrastructureCost){
        int result = infrastructureCostMapper.insertSelective(infrastructureCost);
        return result > 0;
    }
    //查询
    public List<InfrastructureCost> getInfrastructureCostList(String name){
        InfrastructureCostExample example = new InfrastructureCostExample();
        InfrastructureCostExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(name)) {
            criteria.andCreatorLike(String.format("%s%s%s", "%", name, "%"));
        }
        return infrastructureCostMapper.selectByExample(example) ;
    }
}
