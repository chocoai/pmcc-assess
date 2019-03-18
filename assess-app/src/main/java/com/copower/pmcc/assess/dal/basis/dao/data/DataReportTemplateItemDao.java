package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataReportTemplateItem;
import com.copower.pmcc.assess.dal.basis.entity.DataReportTemplateItemExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataReportTemplateItemMapper;
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
public class DataReportTemplateItemDao {
    @Autowired
    private DataReportTemplateItemMapper dataReportTemplateItemMapper;

    public List<DataReportTemplateItem> getListByMasterId(Integer masterId, String type) {
        DataReportTemplateItemExample example = new DataReportTemplateItemExample();
        DataReportTemplateItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (masterId != null) {
            criteria.andMasterIdEqualTo(masterId);
        }
        if(StringUtils.isNotBlank(type)){
            criteria.andTypeLike(String.format("%%%s%%", type));
        }
        example.setOrderByClause("sorting");
        return dataReportTemplateItemMapper.selectByExample(example);
    }

    public List<DataReportTemplateItem> getListObject(DataReportTemplateItem dataReportTemplateItem) {
        DataReportTemplateItemExample example = new DataReportTemplateItemExample();
        MybatisUtils.convertObj2Example(dataReportTemplateItem, example);
        return dataReportTemplateItemMapper.selectByExample(example);
    }

    public DataReportTemplateItem getSingleObject(DataReportTemplateItem dataReportTemplateItem) {
        DataReportTemplateItemExample example = new DataReportTemplateItemExample();
        MybatisUtils.convertObj2Example(dataReportTemplateItem, example);
        List<DataReportTemplateItem> vacationTypeList = dataReportTemplateItemMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(vacationTypeList)) return null;
        return vacationTypeList.get(0);
    }

    public DataReportTemplateItem getSingleObject(Integer id) {
        return dataReportTemplateItemMapper.selectByPrimaryKey(id);
    }

    public boolean addObject(DataReportTemplateItem bidCostInfo) {
        return dataReportTemplateItemMapper.insertSelective(bidCostInfo) == 1;
    }

    public boolean updateObject(DataReportTemplateItem bidCostInfo) {
        return dataReportTemplateItemMapper.updateByPrimaryKeySelective(bidCostInfo) == 1;
    }

    public boolean deleteObject(Integer id) {
        return dataReportTemplateItemMapper.deleteByPrimaryKey(id) == 1;
    }
}
