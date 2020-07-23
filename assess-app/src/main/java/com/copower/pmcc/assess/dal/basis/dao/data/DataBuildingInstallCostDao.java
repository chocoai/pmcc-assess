package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataBuildingInstallCost;
import com.copower.pmcc.assess.dal.basis.entity.DataBuildingInstallCostExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataBuildingInstallCostMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/11 09:55
 * @Description:
 */
@Repository
public class DataBuildingInstallCostDao {
    @Autowired
    private DataBuildingInstallCostMapper dataBuildingInstallCostMapper;

    public Integer addDataBuildingInstallCost(DataBuildingInstallCost dataBuildingInstallCost) {
        dataBuildingInstallCostMapper.insertSelective(dataBuildingInstallCost);
        return dataBuildingInstallCost.getId();
    }

    public DataBuildingInstallCost getDataBuildingInstallCostById(Integer id) {
        return dataBuildingInstallCostMapper.selectByPrimaryKey(id);
    }

    public boolean updateDataBuildingInstallCost(DataBuildingInstallCost dataBuildingInstallCost) {
        return dataBuildingInstallCostMapper.updateByPrimaryKeySelective(dataBuildingInstallCost) == 1;
    }

    public void removeDataBuildingInstallCost(DataBuildingInstallCost dataBuildingInstallCost) {
        DataBuildingInstallCostExample example = new DataBuildingInstallCostExample();
        MybatisUtils.convertObj2Example(dataBuildingInstallCost, example);
        dataBuildingInstallCostMapper.deleteByExample(example);
    }

    public List<DataBuildingInstallCost> getDataBuildingInstallCostList(DataBuildingInstallCost dataBuildingInstallCost) {
        DataBuildingInstallCostExample example = new DataBuildingInstallCostExample();
        MybatisUtils.convertObj2Example(dataBuildingInstallCost, example);
        return dataBuildingInstallCostMapper.selectByExample(example);
    }

    public List<DataBuildingInstallCost> getDataBuildingInstallCostList(String province, String city, String district) {
        DataBuildingInstallCostExample example = new DataBuildingInstallCostExample();
        DataBuildingInstallCostExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(province)){
            criteria.andProvinceEqualTo(province);
        }
        if(!StringUtils.isEmpty(city)){
            criteria.andCityEqualTo(city);
        }
        if(!StringUtils.isEmpty(district)){
            criteria.andDistrictEqualTo(district);
        }
        example.setOrderByClause("id desc");
        return dataBuildingInstallCostMapper.selectByExample(example);
    }
}
