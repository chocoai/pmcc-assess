package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.BaseDataRegion;
import com.copower.pmcc.assess.dal.mapper.BaseDataRegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/3/8
 * @time: 15:28
 */
@Repository
public class BaseDataRegionDao {
    @Autowired
    private BaseDataRegionMapper baseDataRegionMapper;

    public BaseDataRegion getBaseDataRegionById(Integer id) {
        return baseDataRegionMapper.selectByPrimaryKey(id);
    }

    public void addBaseDataRegion(BaseDataRegion BaseDataRegion) {
        baseDataRegionMapper.insertSelective(BaseDataRegion);
    }
}
