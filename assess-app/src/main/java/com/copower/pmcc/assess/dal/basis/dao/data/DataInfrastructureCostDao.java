package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureCost;
import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureCostExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataInfrastructureCostMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
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

    public DataInfrastructureCost getByDataInfrastructureCost(Integer id){
        return infrastructureCostMapper.selectByPrimaryKey(id);
    }

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
    public List<DataInfrastructureCost> getDataInfrastructureCostList(DataInfrastructureCost dataInfrastructureCost){
        DataInfrastructureCostExample example = new DataInfrastructureCostExample();
        MybatisUtils.convertObj2Example(dataInfrastructureCost, example);
        return infrastructureCostMapper.selectByExample(example) ;
    }
}
