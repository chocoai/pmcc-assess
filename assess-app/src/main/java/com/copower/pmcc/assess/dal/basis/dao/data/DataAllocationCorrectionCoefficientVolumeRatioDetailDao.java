package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataAllocationCorrectionCoefficientVolumeRatioDetail;
import com.copower.pmcc.assess.dal.basis.entity.DataAllocationCorrectionCoefficientVolumeRatioDetailExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataAllocationCorrectionCoefficientVolumeRatioDetailMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zch
 * @date: 2019/4/29 18:25
 * @description:
 */
@Repository
public class DataAllocationCorrectionCoefficientVolumeRatioDetailDao {

    @Autowired
    private DataAllocationCorrectionCoefficientVolumeRatioDetailMapper mapper;

    public boolean saveDataAllocationCorrectionCoefficientVolumeRatioDetail(DataAllocationCorrectionCoefficientVolumeRatioDetail oo){
        return mapper.insertSelective(oo) == 1;
    }

    public boolean editDataAllocationCorrectionCoefficientVolumeRatioDetail(DataAllocationCorrectionCoefficientVolumeRatioDetail oo){
        return mapper.updateByPrimaryKeySelective(oo)==1;
    }

    public boolean deleteDataAllocationCorrectionCoefficientVolumeRatioDetail(Integer id){
        return mapper.deleteByPrimaryKey(id)==1;
    }

    public DataAllocationCorrectionCoefficientVolumeRatioDetail getDataAllocationCorrectionCoefficientVolumeRatioDetailById(Integer id){
        return mapper.selectByPrimaryKey(id);
    }

    public List<DataAllocationCorrectionCoefficientVolumeRatioDetail> getDataAllocationCorrectionCoefficientVolumeRatioDetailList(DataAllocationCorrectionCoefficientVolumeRatioDetail oo){
        DataAllocationCorrectionCoefficientVolumeRatioDetailExample example = getExample(oo);
        return mapper.selectByExample(example);
    }

    public void deleteDataAllocationCorrectionCoefficientVolumeRatioDetailList(DataAllocationCorrectionCoefficientVolumeRatioDetail oo){
        DataAllocationCorrectionCoefficientVolumeRatioDetailExample example = getExample(oo);
        mapper.deleteByExample(example);
    }

    private DataAllocationCorrectionCoefficientVolumeRatioDetailExample getExample(DataAllocationCorrectionCoefficientVolumeRatioDetail oo){
        DataAllocationCorrectionCoefficientVolumeRatioDetailExample example = new DataAllocationCorrectionCoefficientVolumeRatioDetailExample();
        MybatisUtils.convertObj2Example(oo, example);
        DataAllocationCorrectionCoefficientVolumeRatioDetailExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        return example;
    }

}
