package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataHousePriceIndex;
import com.copower.pmcc.assess.dal.basis.entity.DataHousePriceIndexExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataHousePriceIndexMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class DataHousePriceIndexDao {

    @Autowired
    private DataHousePriceIndexMapper mapper;

    public boolean saveDataHousePriceIndex(DataHousePriceIndex oo){
        return mapper.insertSelective(oo) == 1;
    }

    public boolean editDataHousePriceIndex(DataHousePriceIndex oo){
        return mapper.updateByPrimaryKeySelective(oo)==1;
    }

    public boolean deleteDataHousePriceIndex(Integer id){
        return mapper.deleteByPrimaryKey(id)==1;
    }

    public DataHousePriceIndex getDataHousePriceIndexById(Integer id){
        return mapper.selectByPrimaryKey(id);
    }

    public List<DataHousePriceIndex> getDataHousePriceIndexList(DataHousePriceIndex oo){
        DataHousePriceIndexExample example = getExample(oo);
        return mapper.selectByExample(example);
    }

    public void deleteDataHousePriceIndexList(DataHousePriceIndex oo){
        DataHousePriceIndexExample example = getExample(oo);
        mapper.deleteByExample(example);
    }



    private DataHousePriceIndexExample getExample(DataHousePriceIndex oo){
        DataHousePriceIndexExample example = new DataHousePriceIndexExample();
        MybatisUtils.convertObj2Example(oo, example);
        DataHousePriceIndexExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        return example;
    }
}
