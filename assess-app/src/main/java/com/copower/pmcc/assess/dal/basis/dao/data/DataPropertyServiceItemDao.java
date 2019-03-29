package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataPropertyServiceItem;
import com.copower.pmcc.assess.dal.basis.entity.DataPropertyServiceItemExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataPropertyServiceItemMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
public class DataPropertyServiceItemDao {
    @Autowired
    private DataPropertyServiceItemMapper dataPropertyServiceItemMapper;

    public List<DataPropertyServiceItem> getListByMasterId(Integer masterId) {
        DataPropertyServiceItemExample example = new DataPropertyServiceItemExample();
        DataPropertyServiceItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (masterId != null) {
             criteria.andMasterIdEqualTo(masterId);
        }
        return dataPropertyServiceItemMapper.selectByExample(example);
    }

    public List<DataPropertyServiceItem> getListObject(DataPropertyServiceItem dataPropertyServiceItem) {
        DataPropertyServiceItemExample example = new DataPropertyServiceItemExample();
        MybatisUtils.convertObj2Example(dataPropertyServiceItem, example);
        return dataPropertyServiceItemMapper.selectByExample(example);
    }

    public DataPropertyServiceItem getSingleObject(DataPropertyServiceItem dataPropertyServiceItem) {
        DataPropertyServiceItemExample example = new DataPropertyServiceItemExample();
        MybatisUtils.convertObj2Example(dataPropertyServiceItem, example);
        List<DataPropertyServiceItem> vacationTypeList = dataPropertyServiceItemMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(vacationTypeList)) return null;
        return vacationTypeList.get(0);
    }

    public DataPropertyServiceItem getSingleObject(Integer id) {
        return dataPropertyServiceItemMapper.selectByPrimaryKey(id);
    }

    public boolean addObject(DataPropertyServiceItem bidCostInfo) {
        return dataPropertyServiceItemMapper.insertSelective(bidCostInfo) == 1;
    }

    public boolean updateObject(DataPropertyServiceItem bidCostInfo) {
        return dataPropertyServiceItemMapper.updateByPrimaryKeySelective(bidCostInfo) == 1;
    }

    public boolean deleteObject(Integer id) {
        return dataPropertyServiceItemMapper.deleteByPrimaryKey(id) == 1;
    }
}
