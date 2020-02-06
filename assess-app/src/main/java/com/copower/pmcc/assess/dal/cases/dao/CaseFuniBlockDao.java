package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseFuniBlock;
import com.copower.pmcc.assess.dal.cases.entity.CaseFuniBlockExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseFuniBlockMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/6/12
 * @time: 15:04
 */
@Repository
public class CaseFuniBlockDao {
    @Autowired
    private CaseFuniBlockMapper caseFuniBlockMapper;

    public CaseFuniBlock getCaseFuniBlock(Integer id) {
        return caseFuniBlockMapper.selectByPrimaryKey(id);
    }

    public List<CaseFuniBlock> getCaseFuniBlockList(CaseFuniBlock caseFuniBlock) {
        CaseFuniBlockExample example = new CaseFuniBlockExample();
        MybatisUtils.convertObj2Example(caseFuniBlock, example);
        List<CaseFuniBlock> caseFuniBlocks = caseFuniBlockMapper.selectByExample(example);
        return caseFuniBlocks;
    }

    public boolean addCaseFuniBlock(CaseFuniBlock caseFuniBlock) {
        int i = caseFuniBlockMapper.insert(caseFuniBlock);
        return i > 0;
    }

    public boolean editCaseFuniBlock(CaseFuniBlock caseFuniBlock) {
        int i = caseFuniBlockMapper.updateByPrimaryKeySelective(caseFuniBlock);
        return i > 0;
    }

    public boolean deleteCaseFuniBlock(Integer id) {
        int i = caseFuniBlockMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
