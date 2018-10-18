package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureDevTax;
import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureDevTaxExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataInfrastructureDevTaxMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/18 11:49
 * @Description:
 */
@Repository
public class DataInfrastructureDevTaxDao {
    @Autowired
    private DataInfrastructureDevTaxMapper dataInfrastructureDevTaxMapper;

    public Integer addDataInfrastructureDevTax(DataInfrastructureDevTax dataInfrastructureDevTax){
        dataInfrastructureDevTaxMapper.insertSelective(dataInfrastructureDevTax);
        return dataInfrastructureDevTax.getId();
    }

    public DataInfrastructureDevTax getDataInfrastructureDevTaxById(Integer id){
        return dataInfrastructureDevTaxMapper.selectByPrimaryKey(id);
    }

    public boolean updateDataInfrastructureDevTax(DataInfrastructureDevTax dataInfrastructureDevTax){
        return dataInfrastructureDevTaxMapper.updateByPrimaryKeySelective(dataInfrastructureDevTax)==1;
    }

    public void removeDataInfrastructureDevTax(DataInfrastructureDevTax dataInfrastructureDevTax){
        DataInfrastructureDevTaxExample example = new DataInfrastructureDevTaxExample();
        MybatisUtils.convertObj2Example(dataInfrastructureDevTax, example);
        dataInfrastructureDevTaxMapper.deleteByExample(example);
    }

    public List<DataInfrastructureDevTax> getDataInfrastructureDevTaxList(DataInfrastructureDevTax dataInfrastructureDevTax){
        DataInfrastructureDevTaxExample example = new DataInfrastructureDevTaxExample();
        MybatisUtils.convertObj2Example(dataInfrastructureDevTax, example);
        return dataInfrastructureDevTaxMapper.selectByExample(example);
    }
}
