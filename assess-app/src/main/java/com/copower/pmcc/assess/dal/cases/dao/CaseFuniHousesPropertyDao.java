package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHousesProperty;
import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHousesPropertyExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseFuniHousesPropertyMapper;
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
public class CaseFuniHousesPropertyDao {
    @Autowired
    private CaseFuniHousesPropertyMapper caseFuniHousesPropertyMapper;

    public CaseFuniHousesProperty getCaseFuniHousesProperty(Integer id) {
        return caseFuniHousesPropertyMapper.selectByPrimaryKey(id);
    }

    public List<CaseFuniHousesProperty> getCaseFuniHousesPropertyList(Integer lpbh) {
        CaseFuniHousesPropertyExample example = new CaseFuniHousesPropertyExample();
        example.createCriteria().andLpbhEqualTo(lpbh);
        List<CaseFuniHousesProperty> caseFuniHousesPropertys = caseFuniHousesPropertyMapper.selectByExample(example);
        return caseFuniHousesPropertys;
    }

    public List<CaseFuniHousesProperty> getCaseFuniHousesPropertyList(CaseFuniHousesProperty caseFuniHousesProperty) {
        CaseFuniHousesPropertyExample example = new CaseFuniHousesPropertyExample();
        MybatisUtils.convertObj2Example(caseFuniHousesProperty, example);
        List<CaseFuniHousesProperty> caseFuniHousesPropertys = caseFuniHousesPropertyMapper.selectByExample(example);
        return caseFuniHousesPropertys;
    }

    public boolean addCaseFuniHousesProperty(CaseFuniHousesProperty caseFuniHousesProperty) {
        int i = caseFuniHousesPropertyMapper.insert(caseFuniHousesProperty);
        return i > 0;
    }

    public boolean editCaseFuniHousesProperty(CaseFuniHousesProperty caseFuniHousesProperty) {
        int i = caseFuniHousesPropertyMapper.updateByPrimaryKeySelective(caseFuniHousesProperty);
        return i > 0;
    }

    public boolean deleteCaseFuniHousesProperty(Integer id) {
        int i = caseFuniHousesPropertyMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
