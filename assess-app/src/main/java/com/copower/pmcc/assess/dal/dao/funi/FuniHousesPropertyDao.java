package com.copower.pmcc.assess.dal.dao.funi;

import com.copower.pmcc.assess.dal.entity.FuniHousesProperty;
import com.copower.pmcc.assess.dal.entity.FuniHousesPropertyExample;
import com.copower.pmcc.assess.dal.mapper.FuniHousesPropertyMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/6/12
 * @time: 15:04
 */
@Repository
public class FuniHousesPropertyDao {
    @Autowired
    private FuniHousesPropertyMapper funiHousesPropertyMapper;

    public FuniHousesProperty getFuniHousesProperty(Integer id) {
        return funiHousesPropertyMapper.selectByPrimaryKey(id);
    }

    public List<FuniHousesProperty> getFuniHousesPropertyList(FuniHousesProperty funiHousesProperty) {
        FuniHousesPropertyExample example = new FuniHousesPropertyExample();
        MybatisUtils.convertObj2Example(funiHousesProperty, example);
        List<FuniHousesProperty> funiHousesPropertys = funiHousesPropertyMapper.selectByExample(example);
        return funiHousesPropertys;
    }

    public boolean addFuniHousesProperty(FuniHousesProperty funiHousesProperty) {
        int i = funiHousesPropertyMapper.insert(funiHousesProperty);
        return i > 0;
    }

    public boolean editFuniHousesProperty(FuniHousesProperty funiHousesProperty) {
        int i = funiHousesPropertyMapper.updateByPrimaryKeySelective(funiHousesProperty);
        return i > 0;
    }

    public boolean deleteFuniHousesProperty(Integer id) {
        int i = funiHousesPropertyMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
