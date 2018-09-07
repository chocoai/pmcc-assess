package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataTaxRateAllocation;
import com.copower.pmcc.assess.dal.basis.entity.DataTaxRateAllocationExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataTaxRateAllocationMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/7 09:52
 * @Description:
 */
@Repository
public class DataTaxRateAllocationDao {
    @Autowired
    private DataTaxRateAllocationMapper dataHousePriceIndexMapper;

    public Integer addDataTaxRateAllocation(DataTaxRateAllocation dataHousePriceIndex) {
        dataHousePriceIndexMapper.insertSelective(dataHousePriceIndex);
        return dataHousePriceIndex.getId();
    }

    public DataTaxRateAllocation getDataTaxRateAllocationById(Integer id) {
        return dataHousePriceIndexMapper.selectByPrimaryKey(id);
    }

    public boolean updateDataTaxRateAllocation(DataTaxRateAllocation dataHousePriceIndex) {
        return dataHousePriceIndexMapper.updateByPrimaryKeySelective(dataHousePriceIndex) == 1;
    }


    public void removeDataTaxRateAllocation(DataTaxRateAllocation dataHousePriceIndex) {
        DataTaxRateAllocationExample example = new DataTaxRateAllocationExample();
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

    public List<DataTaxRateAllocation> getDataTaxRateAllocationList(DataTaxRateAllocation dataHousePriceIndex) {
        DataTaxRateAllocationExample example = new DataTaxRateAllocationExample();
        MybatisUtils.convertObj2Example(dataHousePriceIndex, example);
        return dataHousePriceIndexMapper.selectByExample(example);
    }
}
