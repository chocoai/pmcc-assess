package com.copower.pmcc.assess.dal.basis.dao.funi;

import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHousesBuildUnit;
import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHousesBuildUnitExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseFuniHousesBuildUnitMapper;
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
public class CaseFuniHousesBuildUnitDao {
    @Autowired
    private CaseFuniHousesBuildUnitMapper caseFuniHousesBuildUnitMapper;

    public CaseFuniHousesBuildUnit getCaseFuniHousesBuildUnit(Integer id) {
        return caseFuniHousesBuildUnitMapper.selectByPrimaryKey(id);
    }

    public List<CaseFuniHousesBuildUnit> getCaseFuniHousesBuildUnitList(CaseFuniHousesBuildUnit caseFuniHousesBuildUnit) {
        CaseFuniHousesBuildUnitExample example = new CaseFuniHousesBuildUnitExample();
        MybatisUtils.convertObj2Example(caseFuniHousesBuildUnit, example);
        List<CaseFuniHousesBuildUnit> caseFuniHousesBuildUnits = caseFuniHousesBuildUnitMapper.selectByExample(example);
        return caseFuniHousesBuildUnits;
    }

    public boolean addCaseFuniHousesBuildUnit(CaseFuniHousesBuildUnit caseFuniHousesBuildUnit) {
        int i = caseFuniHousesBuildUnitMapper.insert(caseFuniHousesBuildUnit);
        return i > 0;
    }

    public boolean editCaseFuniHousesBuildUnit(CaseFuniHousesBuildUnit caseFuniHousesBuildUnit) {
        int i = caseFuniHousesBuildUnitMapper.updateByPrimaryKeySelective(caseFuniHousesBuildUnit);
        return i > 0;
    }

    public boolean deleteCaseFuniHousesBuildUnit(Integer id) {
        int i = caseFuniHousesBuildUnitMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
