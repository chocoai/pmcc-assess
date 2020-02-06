package com.copower.pmcc.assess.dal.basis.dao.funi;

import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHousesMating;
import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHousesMatingExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseFuniHousesMatingMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
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
public class CaseFuniHousesMatingDao {
    @Autowired
    private CaseFuniHousesMatingMapper caseFuniHousesMatingMapper;

    public CaseFuniHousesMating getCaseFuniHousesMating(Integer id) {
        return caseFuniHousesMatingMapper.selectByPrimaryKey(id);
    }

    public List<CaseFuniHousesMating> getCaseFuniHousesMatingList(CaseFuniHousesMating caseFuniHousesMating) {
        CaseFuniHousesMatingExample example = new CaseFuniHousesMatingExample();
        MybatisUtils.convertObj2Example(caseFuniHousesMating, example);
        List<CaseFuniHousesMating> caseFuniHousesMatings = caseFuniHousesMatingMapper.selectByExample(example);
        return caseFuniHousesMatings;
    }

    public CaseFuniHousesMating getCaseFuniHousesMatingByLpbh(Integer lpbh) {
        CaseFuniHousesMatingExample example = new CaseFuniHousesMatingExample();
        example.createCriteria().andLpbhEqualTo(lpbh);
        List<CaseFuniHousesMating> caseFuniHousesMatings = caseFuniHousesMatingMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(caseFuniHousesMatings)) {
            return caseFuniHousesMatings.get(0);
        }
        return new CaseFuniHousesMating();
    }

    public boolean addCaseFuniHousesMating(CaseFuniHousesMating caseFuniHousesMating) {
        int i = caseFuniHousesMatingMapper.insert(caseFuniHousesMating);
        return i > 0;
    }

    public boolean editCaseFuniHousesMating(CaseFuniHousesMating caseFuniHousesMating) {
        int i = caseFuniHousesMatingMapper.updateByPrimaryKeySelective(caseFuniHousesMating);
        return i > 0;
    }

    public boolean deleteCaseFuniHousesMating(Integer id) {
        int i = caseFuniHousesMatingMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
