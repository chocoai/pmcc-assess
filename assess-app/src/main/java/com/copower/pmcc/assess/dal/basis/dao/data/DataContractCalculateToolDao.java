package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataContractCalculateTool;
import com.copower.pmcc.assess.dal.basis.entity.DataContractCalculateToolExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataContractCalculateToolMapper;
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
public class DataContractCalculateToolDao {
    @Autowired
    private DataContractCalculateToolMapper dataContractCalculateToolMapper;

    public List<DataContractCalculateTool> getListObject(DataContractCalculateTool dataContractCalculateTool) {
        DataContractCalculateToolExample example = new DataContractCalculateToolExample();
        MybatisUtils.convertObj2Example(dataContractCalculateTool, example);
        return dataContractCalculateToolMapper.selectByExample(example);
    }

    public DataContractCalculateTool getSingleObject(DataContractCalculateTool dataContractCalculateTool) {
        DataContractCalculateToolExample example = new DataContractCalculateToolExample();
        MybatisUtils.convertObj2Example(dataContractCalculateTool, example);
        List<DataContractCalculateTool> vacationTypeList = dataContractCalculateToolMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(vacationTypeList)) return null;
        return vacationTypeList.get(0);
    }

    public DataContractCalculateTool getSingleObject(Integer id) {
        return dataContractCalculateToolMapper.selectByPrimaryKey(id);
    }

    public boolean addObject(DataContractCalculateTool bidCostInfo) {
        return dataContractCalculateToolMapper.insertSelective(bidCostInfo) == 1;
    }

    public boolean updateObject(DataContractCalculateTool bidCostInfo) {
        return dataContractCalculateToolMapper.updateByPrimaryKeySelective(bidCostInfo) == 1;
    }

    public boolean deleteObject(Integer id) {
        return dataContractCalculateToolMapper.deleteByPrimaryKey(id) == 1;
    }
}
