package com.copower.pmcc.assess.dal.basis.dao.funi;

import com.copower.pmcc.assess.dal.cases.entity.CaseFuniDevelopers;
import com.copower.pmcc.assess.dal.cases.entity.CaseFuniDevelopersExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseFuniDevelopersMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
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
public class CaseFuniDevelopersDao {
    @Autowired
    private CaseFuniDevelopersMapper caseFuniDevelopersMapper;

    public CaseFuniDevelopers getCaseFuniDevelopers(Integer id) {
        return caseFuniDevelopersMapper.selectByPrimaryKey(id);
    }

    public CaseFuniDevelopers getCaseFuniDevelopers(String name) {
        CaseFuniDevelopersExample example = new CaseFuniDevelopersExample();
        example.createCriteria().andDevelopersNameEqualTo(name);
        List<CaseFuniDevelopers> caseFuniDeveloperss = caseFuniDevelopersMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(caseFuniDeveloperss))
        {
            return caseFuniDeveloperss.get(0);
        }
        return null;
    }

    public List<CaseFuniDevelopers> getCaseFuniDevelopersList(CaseFuniDevelopers caseFuniDevelopers) {
        CaseFuniDevelopersExample example = new CaseFuniDevelopersExample();
        MybatisUtils.convertObj2Example(caseFuniDevelopers, example);
        List<CaseFuniDevelopers> caseFuniDeveloperss = caseFuniDevelopersMapper.selectByExample(example);
        return caseFuniDeveloperss;
    }

    public boolean addCaseFuniDevelopers(CaseFuniDevelopers caseFuniDevelopers) {
        int i = caseFuniDevelopersMapper.insert(caseFuniDevelopers);
        return i > 0;
    }

    public boolean editCaseFuniDevelopers(CaseFuniDevelopers caseFuniDevelopers) {
        int i = caseFuniDevelopersMapper.updateByPrimaryKeySelective(caseFuniDevelopers);
        return i > 0;
    }

    public boolean deleteCaseFuniDevelopers(Integer id) {
        int i = caseFuniDevelopersMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
