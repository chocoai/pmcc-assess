package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataHousePriceIndex;
import com.copower.pmcc.assess.dal.basis.entity.DataHousePriceIndexExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataHousePriceIndexMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class DataHousePriceIndexDao {

    @Autowired
    private DataHousePriceIndexMapper dataHousePriceIndexMapper;

    public Integer addDataHousePriceIndex(DataHousePriceIndex dataHousePriceIndex) {
        dataHousePriceIndexMapper.insertSelective(dataHousePriceIndex);
        return dataHousePriceIndex.getId();
    }

    public DataHousePriceIndex getDataHousePriceIndexById(Integer id) {
        return dataHousePriceIndexMapper.selectByPrimaryKey(id);
    }

    public boolean updateDataHousePriceIndex(DataHousePriceIndex dataHousePriceIndex) {
        return dataHousePriceIndexMapper.updateByPrimaryKeySelective(dataHousePriceIndex) == 1;
    }

    public List<DataHousePriceIndex> listEndStart(Date startTime, Date endTime, String province, String city, String district) {
        DataHousePriceIndexExample example = new DataHousePriceIndexExample();
        DataHousePriceIndexExample.Criteria criteria = example.createCriteria();
        if (startTime != null) {
            criteria.andYearMonthCalendarGreaterThan(startTime);//大于
        }
        if (endTime != null) {
            criteria.andYearMonthCalendarLessThan(endTime);//小于
        }
        if (StringUtils.isNotBlank(province)) {
            criteria.andProvinceEqualTo(province);
        }
        if (StringUtils.isNotBlank(city)) {
            criteria.andCityEqualTo(city);
        }
        if (StringUtils.isNotBlank(district)) {
            criteria.andDistrictEqualTo(district);
        }
        return dataHousePriceIndexMapper.selectByExample(example);
    }

    public void removeDataHousePriceIndex(DataHousePriceIndex dataHousePriceIndex) {
        DataHousePriceIndexExample example = new DataHousePriceIndexExample();
        MybatisUtils.convertObj2Example(dataHousePriceIndex, example);
        try {
            dataHousePriceIndexMapper.deleteByExample(example);
        } catch (Exception e1) {
            try {
                throw new SQLException("exception");
            } catch (SQLException e) {
            }
        }
    }

    public List<DataHousePriceIndex> getDataHousePriceIndexList(DataHousePriceIndex dataHousePriceIndex) {
        DataHousePriceIndexExample example = new DataHousePriceIndexExample();
        MybatisUtils.convertObj2Example(dataHousePriceIndex, example);
        return dataHousePriceIndexMapper.selectByExample(example);
    }
}
