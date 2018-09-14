package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataBuildingNewRate;
import com.copower.pmcc.assess.dal.basis.entity.DataBuildingNewRateExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataBuildingNewRateMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataBuildingNewRateDao {

    @Autowired
    private DataBuildingNewRateMapper dataBuildingNewRateMapper;

    public boolean addDataBuildingNewRate(DataBuildingNewRate dataBuildingNewRate) {
        return dataBuildingNewRateMapper.insertSelective(dataBuildingNewRate) > 0;
    }

    public boolean editDataBuildingNewRate(DataBuildingNewRate dataBuildingNewRate) {
        int i = dataBuildingNewRateMapper.updateByPrimaryKeySelective(dataBuildingNewRate);
        return i > 0;
    }

    public DataBuildingNewRate getByiDdataBuildingNewRate(Integer id) {
        DataBuildingNewRate dataBuildingNewRate = dataBuildingNewRateMapper.selectByPrimaryKey(id);
        return dataBuildingNewRate;
    }

    public boolean deleteDataBuildingNewRate(Integer id) {
        int i = dataBuildingNewRateMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public List<DataBuildingNewRate> getDataBuildingNewRateList(DataBuildingNewRate dataBuildingNewRate) {
        DataBuildingNewRateExample example = new DataBuildingNewRateExample();
        MybatisUtils.convertObj2Example(dataBuildingNewRate, example);
        return dataBuildingNewRateMapper.selectByExample(example);
    }

}
