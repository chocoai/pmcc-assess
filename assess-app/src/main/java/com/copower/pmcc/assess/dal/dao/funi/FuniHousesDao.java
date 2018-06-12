package com.copower.pmcc.assess.dal.dao.funi;

import com.copower.pmcc.assess.dal.entity.FuniHouses;
import com.copower.pmcc.assess.dal.entity.FuniHousesExample;
import com.copower.pmcc.assess.dal.mapper.FuniHousesMapper;
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
public class FuniHousesDao {
    @Autowired
    private FuniHousesMapper funiHousesMapper;

    public FuniHouses getFuniHouses(Integer id) {
        return funiHousesMapper.selectByPrimaryKey(id);
    }

    public List<FuniHouses> getFuniHousesList(FuniHouses funiHouses) {
        FuniHousesExample example = new FuniHousesExample();
        MybatisUtils.convertObj2Example(funiHouses, example);
        List<FuniHouses> funiHousess = funiHousesMapper.selectByExample(example);
        return funiHousess;
    }

    public boolean addFuniHouses(FuniHouses funiHouses) {
        int i = funiHousesMapper.insert(funiHouses);
        return i > 0;
    }

    public boolean editFuniHouses(FuniHouses funiHouses) {
        int i = funiHousesMapper.updateByPrimaryKeySelective(funiHouses);
        return i > 0;
    }

    public boolean deleteFuniHouses(Integer id) {
        int i = funiHousesMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
