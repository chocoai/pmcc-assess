package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureMatchingCost;
import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureMatchingCostExample;
import com.copower.pmcc.assess.dal.basis.entity.InfrastructureMatchingCost;
import com.copower.pmcc.assess.dal.basis.entity.InfrastructureMatchingCostExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataInfrastructureMatchingCostMapper;
import com.copower.pmcc.assess.dal.basis.mapper.InfrastructureMatchingCostMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
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
    private DataInfrastructureMatchingCostMapper infrastructureMatchingCostMapper;
    //删除
    public Boolean deleteDataInfrastructureCost(Integer id){
        int result = infrastructureMatchingCostMapper.deleteByPrimaryKey(id);
        return result > 0;
    }
    //修改
    public Boolean editDataInfrastructureCost(DataInfrastructureMatchingCost infrastructureMatchingCost){
        int result = infrastructureMatchingCostMapper.updateByPrimaryKeySelective(infrastructureMatchingCost);
        return result > 0;
    }
    //新增
    public Boolean addDataInfrastructureCost(DataInfrastructureMatchingCost infrastructureMatchingCost){
        int result = infrastructureMatchingCostMapper.insertSelective(infrastructureMatchingCost);
        return result > 0;
    }
    //查询
    public List<DataInfrastructureMatchingCost> getDataInfrastructureCostList(DataInfrastructureMatchingCost dataInfrastructureMatchingCost){
        DataInfrastructureMatchingCostExample example = new DataInfrastructureMatchingCostExample();
        MybatisUtils.convertObj2Example(dataInfrastructureMatchingCost, example);
        return infrastructureMatchingCostMapper.selectByExample(example) ;
    }
}
