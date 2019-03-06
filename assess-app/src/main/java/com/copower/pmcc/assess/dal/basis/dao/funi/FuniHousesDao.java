package com.copower.pmcc.assess.dal.basis.dao.funi;

import com.copower.pmcc.assess.dal.basis.entity.FuniHouses;
import com.copower.pmcc.assess.dal.basis.entity.FuniHousesExample;
import com.copower.pmcc.assess.dal.basis.mapper.FuniHousesMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
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
public class FuniHousesDao {
    @Autowired
    private FuniHousesMapper funiHousesMapper;

    public FuniHouses getFuniHouses(Integer id) {
        return funiHousesMapper.selectByPrimaryKey(id);
    }

    public List<FuniHouses> getFuniHousesList(FuniHouses funiHouses, String search) {
        FuniHousesExample example = new FuniHousesExample();
        FuniHousesExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(search)) {
            criteria.andLpmcLike(search);
        }
        MybatisUtils.convertObj2Criteria(funiHouses, criteria);
        example.setOrderByClause(" id desc");
        List<FuniHouses> funiHousess = funiHousesMapper.selectByExample(example);
        return funiHousess;
    }

    public List<FuniHouses> getNotCompleteList(FuniHouses funiHouses) {
        FuniHousesExample example = new FuniHousesExample();
        FuniHousesExample.Criteria criteria = example.createCriteria();
        //未进行任务分配的
        criteria.andCompleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(funiHouses, criteria);
        example.setOrderByClause(" id desc");
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
