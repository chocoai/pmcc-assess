package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataAllocationCorrectionCoefficientVolumeRatio;
import com.copower.pmcc.assess.dal.basis.entity.DataAllocationCorrectionCoefficientVolumeRatioExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataAllocationCorrectionCoefficientVolumeRatioMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zch
 * @date: 2019/4/29 18:24
 * @description:
 */
@Repository
public class DataAllocationCorrectionCoefficientVolumeRatioDao{

    @Autowired
    private DataAllocationCorrectionCoefficientVolumeRatioMapper mapper;

    public boolean saveDataAllocationCorrectionCoefficientVolumeRatio(DataAllocationCorrectionCoefficientVolumeRatio oo){
        return mapper.insertSelective(oo) == 1;
    }

    public boolean editDataAllocationCorrectionCoefficientVolumeRatio(DataAllocationCorrectionCoefficientVolumeRatio oo){
        return mapper.updateByPrimaryKeySelective(oo)==1;
    }

    public boolean deleteDataAllocationCorrectionCoefficientVolumeRatio(Integer id){
        return mapper.deleteByPrimaryKey(id)==1;
    }

    public DataAllocationCorrectionCoefficientVolumeRatio getDataAllocationCorrectionCoefficientVolumeRatioById(Integer id){
        return mapper.selectByPrimaryKey(id);
    }

    public List<DataAllocationCorrectionCoefficientVolumeRatio> getDataAllocationCorrectionCoefficientVolumeRatioList(DataAllocationCorrectionCoefficientVolumeRatio oo){
        DataAllocationCorrectionCoefficientVolumeRatioExample example = getExample(oo);
        return mapper.selectByExample(example);
    }

    public void deleteDataAllocationCorrectionCoefficientVolumeRatioList(DataAllocationCorrectionCoefficientVolumeRatio oo){
        DataAllocationCorrectionCoefficientVolumeRatioExample example = getExample(oo);
        mapper.deleteByExample(example);
    }

    private DataAllocationCorrectionCoefficientVolumeRatioExample getExample(DataAllocationCorrectionCoefficientVolumeRatio oo){
        DataAllocationCorrectionCoefficientVolumeRatioExample example = new DataAllocationCorrectionCoefficientVolumeRatioExample();
        MybatisUtils.convertObj2Example(oo, example);
        DataAllocationCorrectionCoefficientVolumeRatioExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        return example;
    }

    public List<DataAllocationCorrectionCoefficientVolumeRatio> getDataAllocationCorrectionCoefficientVolumeRatioList(String province, String city, String district){
        DataAllocationCorrectionCoefficientVolumeRatioExample example = new DataAllocationCorrectionCoefficientVolumeRatioExample();
        DataAllocationCorrectionCoefficientVolumeRatioExample.Criteria criteria = example.createCriteria();
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
