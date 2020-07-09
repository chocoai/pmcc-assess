package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataSetUseFieldItem;
import com.copower.pmcc.assess.dal.basis.entity.DataSetUseFieldItemExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataSetUseFieldItemMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2020-1-19.
 */
@Repository
public class DataSetUseFieldItemDao {

    @Autowired
    private DataSetUseFieldItemMapper mapper;

    public boolean updateDataSetUseFieldItem(DataSetUseFieldItem oo, boolean updateNull) {
        return updateNull ? mapper.updateByPrimaryKey(oo) == 1 : mapper.updateByPrimaryKeySelective(oo) == 1;
    }

    public boolean deleteDataSetUseFieldItemById(Integer id) {
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public void deleteDataSetUseFieldItemByIds(List<Integer> ids) {
        DataSetUseFieldItemExample example = new DataSetUseFieldItemExample();
        example.createCriteria().andIdIn(ids);
        mapper.deleteByExample(example);
    }

    public DataSetUseFieldItem getDataSetUseFieldItemById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public boolean saveDataSetUseFieldItem(DataSetUseFieldItem oo) {
        return mapper.insertSelective(oo) == 1;
    }

    public List<DataSetUseFieldItem> getDataSetUseFieldItemByIds(List<Integer> ids) {
        DataSetUseFieldItemExample example = new DataSetUseFieldItemExample();
        example.createCriteria().andIdIn(ids);
        return mapper.selectByExample(example);
    }

    public List<DataSetUseFieldItem> getDataSetUseFieldItemListByExample(DataSetUseFieldItem oo) {
        DataSetUseFieldItemExample example = new DataSetUseFieldItemExample();
        DataSetUseFieldItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        MybatisUtils.convertObj2Criteria(oo, criteria);
        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }

    public Integer getDataSetUseFieldItemCount(Integer masterId, String type, String category) {
        DataSetUseFieldItemExample example = new DataSetUseFieldItemExample();
        DataSetUseFieldItemExample.Criteria criteria = example.createCriteria();
        criteria.andMasterIdEqualTo(masterId);
        if (StringUtils.isNotBlank(type))
            criteria.andTypeEqualTo(type);
        if (StringUtils.isNotBlank(category))
            criteria.andCategoryEqualTo(category);
        return mapper.countByExample(example);
    }

    public DataSetUseFieldItem getDataSetUseFieldItem(Integer masterId, String type, String category) {
        DataSetUseFieldItemExample example = new DataSetUseFieldItemExample();
        DataSetUseFieldItemExample.Criteria criteria = example.createCriteria();
        criteria.andMasterIdEqualTo(masterId);
        if (StringUtils.isNotBlank(type))
            criteria.andTypeEqualTo(type);
        if (StringUtils.isNotBlank(category))
            criteria.andCategoryEqualTo(category);
        List<DataSetUseFieldItem> dataSetUseFieldItems = mapper.selectByExample(example);
        if (CollectionUtils.isEmpty(dataSetUseFieldItems)) return null;
        return dataSetUseFieldItems.get(0);
    }
}
