package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataBlock;
import com.copower.pmcc.assess.dal.basis.entity.DataBlockExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataBlockMapper;
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
public class DataBlockDao {
    @Autowired
    private DataBlockMapper dataBlockMapper;

    public Integer addDataBlock(DataBlock dataBlock) {
        dataBlockMapper.insertSelective(dataBlock);
        return dataBlock.getId();
    }

    public DataBlock getDataBlockById(Integer id) {
        return dataBlockMapper.selectByPrimaryKey(id);
    }

    public boolean updateDataBlock(DataBlock dataBlock) {
        return dataBlockMapper.updateByPrimaryKeySelective(dataBlock) == 1;
    }

    public void removeDataBlock(DataBlock dataBlock) {
        DataBlockExample example = new DataBlockExample();
        MybatisUtils.convertObj2Example(dataBlock, example);
        dataBlockMapper.deleteByExample(example);
    }

    public List<DataBlock> getDataBlockList(DataBlock dataBlock) {
        DataBlockExample example = new DataBlockExample();
        MybatisUtils.convertObj2Example(dataBlock, example);
        return dataBlockMapper.selectByExample(example);
    }

    public List<DataBlock> getDataBlockList(String province, String city, String district,String name) {
        DataBlockExample example = new DataBlockExample();
        DataBlockExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(province)){
            criteria.andProvinceEqualTo(province);
        }
        if(!StringUtils.isEmpty(city)){
            criteria.andCityEqualTo(city);
        }
        if(!StringUtils.isEmpty(district)){
            criteria.andDistrictEqualTo(district);
        }
        if(!StringUtils.isEmpty(name)){
            criteria.andNameLike(String.format("%%%s%%",name));
        }
        return dataBlockMapper.selectByExample(example);
    }
}
