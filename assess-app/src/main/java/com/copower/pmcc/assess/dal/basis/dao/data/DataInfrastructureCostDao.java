package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureCost;
import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureCostExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataInfrastructureCostMapper;
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
   private DataInfrastructureCostMapper infrastructureCostMapper;
    //删除
    public Boolean deleteDataInfrastructureCost(Integer id){
        int result = infrastructureCostMapper.deleteByPrimaryKey(id);
        return result > 0;
    }
    //修改
    public Boolean editDataInfrastructureCost(DataInfrastructureCost infrastructureCost){
        int result = infrastructureCostMapper.updateByPrimaryKeySelective(infrastructureCost);
        return result > 0;
    }
    //新增
    public Boolean addDataInfrastructureCost(DataInfrastructureCost infrastructureCost){
        int result = infrastructureCostMapper.insertSelective(infrastructureCost);
        return result > 0;
    }
    //查询
    public List<DataInfrastructureCost> getDataInfrastructureCostList(String name){
        DataInfrastructureCostExample example = new DataInfrastructureCostExample();
        DataInfrastructureCostExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(name)) {
            criteria.andCreatorLike(String.format("%s%s%s", "%", name, "%"));
        }
        return infrastructureCostMapper.selectByExample(example) ;
    }
}
