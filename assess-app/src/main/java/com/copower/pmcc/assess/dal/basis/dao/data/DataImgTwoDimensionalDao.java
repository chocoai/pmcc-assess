package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataImgTwoDimensional;
import com.copower.pmcc.assess.dal.basis.entity.DataImgTwoDimensionalExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataImgTwoDimensionalMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/28 13:50
 * @Description:
 */
@Repository
public class DataImgTwoDimensionalDao {
    @Autowired
    private DataImgTwoDimensionalMapper dataImgTwoDimensionalMapper;


    public Integer addDataImgTwoDimensional(DataImgTwoDimensional dataImgTwoDimensional){
        dataImgTwoDimensionalMapper.insertSelective(dataImgTwoDimensional);
        return dataImgTwoDimensional.getId();
    }

    public DataImgTwoDimensional getDataImgTwoDimensionalById(Integer id){
        return dataImgTwoDimensionalMapper.selectByPrimaryKey(id);
    }

    public boolean updateDataImgTwoDimensional(DataImgTwoDimensional dataImgTwoDimensional){
        return dataImgTwoDimensionalMapper.updateByPrimaryKeySelective(dataImgTwoDimensional)==1;
    }

    public void removeDataImgTwoDimensional(DataImgTwoDimensional dataImgTwoDimensional){
        DataImgTwoDimensionalExample example = new DataImgTwoDimensionalExample();
        MybatisUtils.convertObj2Example(dataImgTwoDimensional, example);
        dataImgTwoDimensionalMapper.deleteByExample(example);
    }

    public List<DataImgTwoDimensional> getDataImgTwoDimensionalList(DataImgTwoDimensional dataImgTwoDimensional){
        DataImgTwoDimensionalExample example = new DataImgTwoDimensionalExample();
//        MybatisUtils.convertObj2Example(dataImgTwoDimensional, example);
        DataImgTwoDimensionalExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        return dataImgTwoDimensionalMapper.selectByExample(example);
    }
}
