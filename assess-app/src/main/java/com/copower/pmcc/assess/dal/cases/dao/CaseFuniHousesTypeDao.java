package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHousesType;
import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHousesTypeExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseFuniHousesTypeMapper;
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
public class CaseFuniHousesTypeDao {
    @Autowired
    private CaseFuniHousesTypeMapper caseFuniHousesTypeMapper;

    public CaseFuniHousesType getCaseFuniHousesType(Integer id) {
        return caseFuniHousesTypeMapper.selectByPrimaryKey(id);
    }

    public List<CaseFuniHousesType> getCaseFuniHousesTypeList(Integer lpbh) {
        CaseFuniHousesTypeExample example = new CaseFuniHousesTypeExample();
        example.createCriteria().andLpbhEqualTo(lpbh);
        List<CaseFuniHousesType> caseFuniHousesTypes = caseFuniHousesTypeMapper.selectByExample(example);
        return caseFuniHousesTypes;
    }

    public List<CaseFuniHousesType> getCaseFuniHousesTypeList(CaseFuniHousesType caseFuniHousesType) {
        CaseFuniHousesTypeExample example = new CaseFuniHousesTypeExample();
        MybatisUtils.convertObj2Example(caseFuniHousesType, example);
        List<CaseFuniHousesType> caseFuniHousesTypes = caseFuniHousesTypeMapper.selectByExample(example);
        return caseFuniHousesTypes;
    }

    public boolean addCaseFuniHousesType(CaseFuniHousesType caseFuniHousesType) {
        int i = caseFuniHousesTypeMapper.insert(caseFuniHousesType);
        return i > 0;
    }

    public boolean editCaseFuniHousesType(CaseFuniHousesType caseFuniHousesType) {
        int i = caseFuniHousesTypeMapper.updateByPrimaryKeySelective(caseFuniHousesType);
        return i > 0;
    }

    public boolean deleteCaseFuniHousesType(Integer id) {
        int i = caseFuniHousesTypeMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
