package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataValueDefinition;
import com.copower.pmcc.assess.dal.basis.entity.DataValueDefinitionExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataValueDefinitionMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author Red
 * @version 1.0
 * @date: 2017/09/13 09:18
 */
@Repository
public class DataValueDefinitionDao {
    @Autowired
    private DataValueDefinitionMapper dataValueDefinitionMapper;

    public List<DataValueDefinition> getListObject(DataValueDefinition dataValueDefinition) {
        DataValueDefinitionExample example = new DataValueDefinitionExample();
        MybatisUtils.convertObj2Example(dataValueDefinition, example);
        return dataValueDefinitionMapper.selectByExample(example);
    }

    public DataValueDefinition getSingleObject(DataValueDefinition dataValueDefinition) {
        DataValueDefinitionExample example = new DataValueDefinitionExample();
        MybatisUtils.convertObj2Example(dataValueDefinition, example);
        List<DataValueDefinition> vacationTypeList = dataValueDefinitionMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(vacationTypeList)) return null;
        return vacationTypeList.get(0);
    }

    public DataValueDefinition getSingleObject(Integer id) {
        return dataValueDefinitionMapper.selectByPrimaryKey(id);
    }

    public boolean addObject(DataValueDefinition bidCostInfo) {
        return dataValueDefinitionMapper.insertSelective(bidCostInfo) == 1;
    }

    public boolean updateObject(DataValueDefinition bidCostInfo) {
        return dataValueDefinitionMapper.updateByPrimaryKeySelective(bidCostInfo) == 1;
    }

    public boolean deleteObject(Integer id) {
        return dataValueDefinitionMapper.deleteByPrimaryKey(id) == 1;
    }
}
