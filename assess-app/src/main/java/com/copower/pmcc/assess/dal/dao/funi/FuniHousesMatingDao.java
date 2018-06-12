package com.copower.pmcc.assess.dal.dao.funi;

import com.copower.pmcc.assess.dal.entity.FuniHousesMating;
import com.copower.pmcc.assess.dal.entity.FuniHousesMatingExample;
import com.copower.pmcc.assess.dal.mapper.FuniHousesMatingMapper;
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
public class FuniHousesMatingDao {
    @Autowired
    private FuniHousesMatingMapper funiHousesMatingMapper;

    public FuniHousesMating getFuniHousesMating(Integer id) {
        return funiHousesMatingMapper.selectByPrimaryKey(id);
    }

    public List<FuniHousesMating> getFuniHousesMatingList(FuniHousesMating funiHousesMating) {
        FuniHousesMatingExample example = new FuniHousesMatingExample();
        MybatisUtils.convertObj2Example(funiHousesMating, example);
        List<FuniHousesMating> funiHousesMatings = funiHousesMatingMapper.selectByExample(example);
        return funiHousesMatings;
    }

    public boolean addFuniHousesMating(FuniHousesMating funiHousesMating) {
        int i = funiHousesMatingMapper.insert(funiHousesMating);
        return i > 0;
    }

    public boolean editFuniHousesMating(FuniHousesMating funiHousesMating) {
        int i = funiHousesMatingMapper.updateByPrimaryKeySelective(funiHousesMating);
        return i > 0;
    }

    public boolean deleteFuniHousesMating(Integer id) {
        int i = funiHousesMatingMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
