package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataLandApproximationMethodSetting;
import com.copower.pmcc.assess.dal.basis.entity.DataLandApproximationMethodSettingExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataLandApproximationMethodSettingMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zch
 * @date: 2019/4/29 18:27
 * @description:
 */
@Repository
public class DataLandApproximationMethodSettingDao {

    @Autowired
    private DataLandApproximationMethodSettingMapper mapper;

    public boolean saveDataLandApproximationMethodSetting(DataLandApproximationMethodSetting oo){
        return mapper.insertSelective(oo) == 1;
    }

    public boolean editDataLandApproximationMethodSetting(DataLandApproximationMethodSetting oo){
        return mapper.updateByPrimaryKeySelective(oo)==1;
    }

    public boolean deleteDataLandApproximationMethodSetting(Integer id){
        return mapper.deleteByPrimaryKey(id)==1;
    }

    public DataLandApproximationMethodSetting getDataLandApproximationMethodSettingById(Integer id){
        return mapper.selectByPrimaryKey(id);
    }

    public List<DataLandApproximationMethodSetting> getDataLandApproximationMethodSettingList(DataLandApproximationMethodSetting oo){
        DataLandApproximationMethodSettingExample example = getExample(oo);
        return mapper.selectByExample(example);
    }

    public void deleteDataLandApproximationMethodSettingList(DataLandApproximationMethodSetting oo){
        DataLandApproximationMethodSettingExample example = getExample(oo);
        mapper.deleteByExample(example);
    }

    private DataLandApproximationMethodSettingExample getExample(DataLandApproximationMethodSetting oo){
        DataLandApproximationMethodSettingExample example = new DataLandApproximationMethodSettingExample();
        MybatisUtils.convertObj2Example(oo, example);
        DataLandApproximationMethodSettingExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        return example;
    }

    public List<DataLandApproximationMethodSetting> getDataLandApproximationMethodSettingList(String province, String city, String district){
        DataLandApproximationMethodSettingExample example = new DataLandApproximationMethodSettingExample();
        DataLandApproximationMethodSettingExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(province)){
            criteria.andProvinceEqualTo(province);
        }
        if(!StringUtils.isEmpty(city)){
            criteria.andCityEqualTo(city);
        }
        if(!StringUtils.isEmpty(district)){
            criteria.andDistrictEqualTo(district);
        }else {
            criteria.andDistrictEqualTo("");
        }
        return mapper.selectByExample(example);
    }
}
