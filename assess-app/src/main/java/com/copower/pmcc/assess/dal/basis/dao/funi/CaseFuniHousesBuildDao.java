package com.copower.pmcc.assess.dal.basis.dao.funi;

import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHousesBuild;
import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHousesBuildExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseFuniHousesBuildMapper;
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
public class CaseFuniHousesBuildDao {
    @Autowired
    private CaseFuniHousesBuildMapper caseFuniHousesBuildMapper;

    public CaseFuniHousesBuild getCaseFuniHousesBuild(Integer id) {
        return caseFuniHousesBuildMapper.selectByPrimaryKey(id);
    }

    public List<CaseFuniHousesBuild> getCaseFuniHousesBuildList(CaseFuniHousesBuild caseFuniHousesBuild) {
        CaseFuniHousesBuildExample example = new CaseFuniHousesBuildExample();
        MybatisUtils.convertObj2Example(caseFuniHousesBuild, example);
        List<CaseFuniHousesBuild> caseFuniHousesBuilds = caseFuniHousesBuildMapper.selectByExample(example);
        return caseFuniHousesBuilds;
    }

    public boolean addCaseFuniHousesBuild(CaseFuniHousesBuild caseFuniHousesBuild) {
        int i = caseFuniHousesBuildMapper.insert(caseFuniHousesBuild);
        return i > 0;
    }

    public boolean editCaseFuniHousesBuild(CaseFuniHousesBuild caseFuniHousesBuild) {
        int i = caseFuniHousesBuildMapper.updateByPrimaryKeySelective(caseFuniHousesBuild);
        return i > 0;
    }

    public boolean deleteCaseFuniHousesBuild(Integer id) {
        int i = caseFuniHousesBuildMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
