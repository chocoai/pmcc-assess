package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataReportGeneralFactors;
import com.copower.pmcc.assess.dal.basis.entity.DataReportGeneralFactorsExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataReportGeneralFactorsMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataReportGeneralFactorsDao {

    @Autowired
    private DataReportGeneralFactorsMapper mapper;

    public boolean saveDataReportGeneralFactors(DataReportGeneralFactors obj) {
        return mapper.insertSelective(obj) == 1;
    }

    public boolean editDataReportGeneralFactors(DataReportGeneralFactors obj) {
        return mapper.updateByPrimaryKeySelective(obj) == 1;
    }

    public boolean updateByPrimaryKey(DataReportGeneralFactors obj) {
        return mapper.updateByPrimaryKeyWithBLOBs(obj) == 1;
    }

    public boolean deleteByIds(List<Integer> ids) {
        DataReportGeneralFactorsExample example = new DataReportGeneralFactorsExample();
        DataReportGeneralFactorsExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        int i = mapper.deleteByExample(example);
        return i > 0;
    }

    public DataReportGeneralFactors getDataReportGeneralFactorsById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<DataReportGeneralFactors> getDataReportGeneralFactorsList(DataReportGeneralFactors obj) {
        DataReportGeneralFactorsExample example = new DataReportGeneralFactorsExample();
        MybatisUtils.convertObj2Example(obj, example);
        example.setOrderByClause(DataReportGeneralFactors.Column.sorting.getJavaProperty());
        return mapper.selectByExampleWithBLOBs(example);
    }

    public List<DataReportGeneralFactors> getDataReportGeneralFactorsList(String type,String province, String city, String district, String name, String fieldName, Boolean bisEnable, Integer pid) {
        DataReportGeneralFactorsExample example = new DataReportGeneralFactorsExample();
        DataReportGeneralFactorsExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike(String.format("%%%s%%", name));
        }
        if (StringUtils.isNotBlank(fieldName)) {
            criteria.andFieldNameEqualTo(fieldName);
        }
        if (StringUtils.isNotBlank(type)) {
            criteria.andTypeEqualTo(type);
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
        if (bisEnable != null) {
            criteria.andBisEnableEqualTo(bisEnable);
        }
        if (pid != null) {
            criteria.andPidEqualTo(pid);
        }
        example.setOrderByClause(DataReportGeneralFactors.Column.sorting.getJavaProperty());
        return mapper.selectByExampleWithBLOBs(example);
    }

}
