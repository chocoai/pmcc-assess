package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataHisRightInfoPublicity;
import com.copower.pmcc.assess.dal.basis.entity.DataHisRightInfoPublicityExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataHisRightInfoPublicityMapper;
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
public class DataHisRightInfoPublicityDao {
    @Autowired
    private DataHisRightInfoPublicityMapper dataHisRightInfoPublicityMapper;

    public List<DataHisRightInfoPublicity> getCityContent(String areaId) {
        DataHisRightInfoPublicityExample example = new DataHisRightInfoPublicityExample();
        DataHisRightInfoPublicityExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(areaId)) {
            criteria.andCityEqualTo(areaId);
        }
        criteria.andDistrictIsNull();
        return dataHisRightInfoPublicityMapper.selectByExample(example);
    }

    public List<DataHisRightInfoPublicity> getListObject(DataHisRightInfoPublicity dataHisRightInfoPublicity) {
        DataHisRightInfoPublicityExample example = new DataHisRightInfoPublicityExample();
        MybatisUtils.convertObj2Example(dataHisRightInfoPublicity, example);
        return dataHisRightInfoPublicityMapper.selectByExample(example);
    }

    public DataHisRightInfoPublicity getSingleObject(DataHisRightInfoPublicity dataHisRightInfoPublicity) {
        DataHisRightInfoPublicityExample example = new DataHisRightInfoPublicityExample();
        MybatisUtils.convertObj2Example(dataHisRightInfoPublicity, example);
        List<DataHisRightInfoPublicity> vacationTypeList = dataHisRightInfoPublicityMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(vacationTypeList)) return null;
        return vacationTypeList.get(0);
    }

    public DataHisRightInfoPublicity getSingleObject(Integer id) {
        return dataHisRightInfoPublicityMapper.selectByPrimaryKey(id);
    }

    public boolean addObject(DataHisRightInfoPublicity bidCostInfo) {
        return dataHisRightInfoPublicityMapper.insertSelective(bidCostInfo) == 1;
    }

    public boolean updateObject(DataHisRightInfoPublicity bidCostInfo) {
        return dataHisRightInfoPublicityMapper.updateByPrimaryKeySelective(bidCostInfo) == 1;
    }

    public boolean deleteObject(Integer id) {
        return dataHisRightInfoPublicityMapper.deleteByPrimaryKey(id) == 1;
    }
}
