package com.copower.pmcc.assess.dal.basis.dao.funi;

import com.copower.pmcc.assess.dal.basis.entity.FuniBlock;
import com.copower.pmcc.assess.dal.basis.entity.FuniBlockExample;
import com.copower.pmcc.assess.dal.basis.mapper.FuniBlockMapper;
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
public class FuniBlockDao {
    @Autowired
    private FuniBlockMapper funiBlockMapper;

    public FuniBlock getFuniBlock(Integer id) {
        return funiBlockMapper.selectByPrimaryKey(id);
    }

    public List<FuniBlock> getFuniBlockList(FuniBlock funiBlock) {
        FuniBlockExample example = new FuniBlockExample();
        MybatisUtils.convertObj2Example(funiBlock, example);
        List<FuniBlock> funiBlocks = funiBlockMapper.selectByExample(example);
        return funiBlocks;
    }

    public boolean addFuniBlock(FuniBlock funiBlock) {
        int i = funiBlockMapper.insert(funiBlock);
        return i > 0;
    }

    public boolean editFuniBlock(FuniBlock funiBlock) {
        int i = funiBlockMapper.updateByPrimaryKeySelective(funiBlock);
        return i > 0;
    }

    public boolean deleteFuniBlock(Integer id) {
        int i = funiBlockMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
