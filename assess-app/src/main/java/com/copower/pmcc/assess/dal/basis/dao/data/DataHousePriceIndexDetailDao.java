package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataHousePriceIndexDetail;
import com.copower.pmcc.assess.dal.basis.entity.DataHousePriceIndexDetailExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataHousePriceIndexDetailMapper;
import com.copower.pmcc.assess.dal.basis.mapper.DataHousePriceIndexDetailMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zch
 * @date: 2019/4/29 18:23
 * @description:
 */
@Repository
public class DataHousePriceIndexDetailDao {
    @Autowired
    private DataHousePriceIndexDetailMapper mapper;

    public boolean saveDataHousePriceIndexDetail(DataHousePriceIndexDetail oo){
        return mapper.insertSelective(oo) == 1;
    }

    public boolean editDataHousePriceIndexDetail(DataHousePriceIndexDetail oo){
        return mapper.updateByPrimaryKeySelective(oo)==1;
    }

    public boolean deleteDataHousePriceIndexDetail(Integer id){
        return mapper.deleteByPrimaryKey(id)==1;
    }

    public DataHousePriceIndexDetail getDataHousePriceIndexDetailById(Integer id){
        return mapper.selectByPrimaryKey(id);
    }

    public List<DataHousePriceIndexDetail> getDataHousePriceIndexDetailList(DataHousePriceIndexDetail oo){
        DataHousePriceIndexDetailExample example = getExample(oo);
        example.setOrderByClause("start_date");
        return mapper.selectByExample(example);
    }

    public List<DataHousePriceIndexDetail> getDataHousePriceIndexDetailList(Integer housePriceId){
        DataHousePriceIndexDetailExample example = new DataHousePriceIndexDetailExample();
        example.createCriteria().andHousePriceIdEqualTo(housePriceId);
        return mapper.selectByExample(example);
    }

    public void deleteDataHousePriceIndexDetailList(DataHousePriceIndexDetail oo){
        DataHousePriceIndexDetailExample example = getExample(oo);
        mapper.deleteByExample(example);
    }

    private DataHousePriceIndexDetailExample getExample(DataHousePriceIndexDetail oo){
        DataHousePriceIndexDetailExample example = new DataHousePriceIndexDetailExample();
        MybatisUtils.convertObj2Example(oo, example);
        DataHousePriceIndexDetailExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        return example;
    }

}
