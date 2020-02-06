package com.copower.pmcc.assess.dal.basis.dao.funi;

import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHousesSiting;
import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHousesSitingExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseFuniHousesSitingMapper;
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
public class CaseFuniHousesSitingDao {
    @Autowired
    private CaseFuniHousesSitingMapper caseFuniHousesSitingMapper;

    public CaseFuniHousesSiting getCaseFuniHousesSiting(Integer id) {
        return caseFuniHousesSitingMapper.selectByPrimaryKey(id);
    }

    public List<CaseFuniHousesSiting> getCaseFuniHousesSitingList(CaseFuniHousesSiting caseFuniHousesSiting) {
        CaseFuniHousesSitingExample example = new CaseFuniHousesSitingExample();
        MybatisUtils.convertObj2Example(caseFuniHousesSiting, example);
        List<CaseFuniHousesSiting> caseFuniHousesSitings = caseFuniHousesSitingMapper.selectByExample(example);
        return caseFuniHousesSitings;
    }

    public boolean addCaseFuniHousesSiting(CaseFuniHousesSiting caseFuniHousesSiting) {
        int i = caseFuniHousesSitingMapper.insert(caseFuniHousesSiting);
        return i > 0;
    }

    public boolean editCaseFuniHousesSiting(CaseFuniHousesSiting caseFuniHousesSiting) {
        int i = caseFuniHousesSitingMapper.updateByPrimaryKeySelective(caseFuniHousesSiting);
        return i > 0;
    }

    public boolean deleteCaseFuniHousesSiting(Integer id) {
        int i = caseFuniHousesSitingMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
