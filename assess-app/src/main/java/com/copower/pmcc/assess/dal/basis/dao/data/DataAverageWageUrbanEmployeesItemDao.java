package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataAverageWageUrbanEmployeesItem;
import com.copower.pmcc.assess.dal.basis.entity.DataAverageWageUrbanEmployeesItemExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataAverageWageUrbanEmployeesItemMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2020-1-19.
 */
@Repository
public class DataAverageWageUrbanEmployeesItemDao {

    @Autowired
    private DataAverageWageUrbanEmployeesItemMapper mapper;

    public boolean updateDataAverageWageUrbanEmployeesItem(DataAverageWageUrbanEmployeesItem oo, boolean updateNull) {
        return updateNull ? mapper.updateByPrimaryKey(oo) == 1 : mapper.updateByPrimaryKeySelective(oo) == 1;
    }

    public boolean deleteDataAverageWageUrbanEmployeesItemById(Integer id) {
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public void deleteDataAverageWageUrbanEmployeesItemByIds(List<Integer> ids) {
        DataAverageWageUrbanEmployeesItemExample example = new DataAverageWageUrbanEmployeesItemExample();
        example.createCriteria().andIdIn(ids);
        mapper.deleteByExample(example);
    }

    public DataAverageWageUrbanEmployeesItem getDataAverageWageUrbanEmployeesItemById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public boolean saveDataAverageWageUrbanEmployeesItem(DataAverageWageUrbanEmployeesItem oo) {
        return mapper.insertSelective(oo) == 1;
    }

    public List<DataAverageWageUrbanEmployeesItem> getDataAverageWageUrbanEmployeesItemByIds(List<Integer> ids) {
        DataAverageWageUrbanEmployeesItemExample example = new DataAverageWageUrbanEmployeesItemExample();
        example.createCriteria().andIdIn(ids);
        return mapper.selectByExample(example);
    }

    public List<DataAverageWageUrbanEmployeesItem> getDataAverageWageUrbanEmployeesItemListByExample(DataAverageWageUrbanEmployeesItem oo) {
        DataAverageWageUrbanEmployeesItemExample example = new DataAverageWageUrbanEmployeesItemExample();
        DataAverageWageUrbanEmployeesItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (StringUtils.isNotBlank(oo.getType())) {
            criteria.andTypeLike(String.format("%%%s%%", oo.getType()));
        }
        if (StringUtils.isNotBlank(oo.getCategory())) {
            criteria.andCategoryLike(String.format("%%%s%%", oo.getCategory()));
        }
        MybatisUtils.convertObj2Criteria(oo, criteria);
        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }

    public List<DataAverageWageUrbanEmployeesItem> getDataAverageWageUrbanEmployeesItemListByLike(DataAverageWageUrbanEmployeesItem oo) {
        DataAverageWageUrbanEmployeesItemExample example = new DataAverageWageUrbanEmployeesItemExample();
        DataAverageWageUrbanEmployeesItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (StringUtils.isNotBlank(oo.getType())) {
            criteria.andTypeLike(String.format("%%%s%%", oo.getType()));
        }
        if (StringUtils.isNotBlank(oo.getCategory())) {
            criteria.andCategoryLike(String.format("%%%s%%", oo.getCategory()));
        }
        if (oo.getMasterId() != null){
            criteria.andMasterIdEqualTo(oo.getMasterId()) ;
        }
        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }


}
