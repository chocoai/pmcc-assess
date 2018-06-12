package com.copower.pmcc.assess.dal.dao.funi;

import com.copower.pmcc.assess.dal.entity.FuniHousesSiting;
import com.copower.pmcc.assess.dal.entity.FuniHousesSitingExample;
import com.copower.pmcc.assess.dal.mapper.FuniHousesSitingMapper;
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
public class FuniHousesSitingDao {
    @Autowired
    private FuniHousesSitingMapper funiHousesSitingMapper;

    public FuniHousesSiting getFuniHousesSiting(Integer id) {
        return funiHousesSitingMapper.selectByPrimaryKey(id);
    }

    public List<FuniHousesSiting> getFuniHousesSitingList(FuniHousesSiting funiHousesSiting) {
        FuniHousesSitingExample example = new FuniHousesSitingExample();
        MybatisUtils.convertObj2Example(funiHousesSiting, example);
        List<FuniHousesSiting> funiHousesSitings = funiHousesSitingMapper.selectByExample(example);
        return funiHousesSitings;
    }

    public boolean addFuniHousesSiting(FuniHousesSiting funiHousesSiting) {
        int i = funiHousesSitingMapper.insert(funiHousesSiting);
        return i > 0;
    }

    public boolean editFuniHousesSiting(FuniHousesSiting funiHousesSiting) {
        int i = funiHousesSitingMapper.updateByPrimaryKeySelective(funiHousesSiting);
        return i > 0;
    }

    public boolean deleteFuniHousesSiting(Integer id) {
        int i = funiHousesSitingMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
