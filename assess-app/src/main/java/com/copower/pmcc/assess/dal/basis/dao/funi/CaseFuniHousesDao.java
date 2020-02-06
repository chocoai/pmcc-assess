package com.copower.pmcc.assess.dal.basis.dao.funi;

import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHouses;
import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHousesExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseFuniHousesMapper;
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
public class CaseFuniHousesDao {
    @Autowired
    private CaseFuniHousesMapper caseFuniHousesMapper;

    public CaseFuniHouses getCaseFuniHouses(Integer id) {
        return caseFuniHousesMapper.selectByPrimaryKey(id);
    }

    public List<CaseFuniHouses> getCaseFuniHousesList(CaseFuniHouses caseFuniHouses, String search) {
        CaseFuniHousesExample example = new CaseFuniHousesExample();
        CaseFuniHousesExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(search)) {
            criteria.andLpmcLike(search);
        }
        MybatisUtils.convertObj2Criteria(caseFuniHouses, criteria);
        example.setOrderByClause(" id desc");
        List<CaseFuniHouses> caseFuniHousess = caseFuniHousesMapper.selectByExample(example);
        return caseFuniHousess;
    }

    public List<CaseFuniHouses> getNotCompleteList(CaseFuniHouses caseFuniHouses, String name) {
        CaseFuniHousesExample example = new CaseFuniHousesExample();
        CaseFuniHousesExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(name)) {
            criteria.andLpmcLike(String.format("%%%s%%", name));
        }
        //未进行任务分配的
        criteria.andCompleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(caseFuniHouses, criteria);
        example.setOrderByClause(" id desc");
        List<CaseFuniHouses> caseFuniHousess = caseFuniHousesMapper.selectByExample(example);
        return caseFuniHousess;
    }

    public boolean addCaseFuniHouses(CaseFuniHouses caseFuniHouses) {
        int i = caseFuniHousesMapper.insert(caseFuniHouses);
        return i > 0;
    }

    public boolean editCaseFuniHouses(CaseFuniHouses caseFuniHouses) {
        int i = caseFuniHousesMapper.updateByPrimaryKeySelective(caseFuniHouses);
        return i > 0;
    }

    public boolean deleteCaseFuniHouses(Integer id) {
        int i = caseFuniHousesMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
