package com.copower.pmcc.assess.dal.basis.dao.funi;

import com.copower.pmcc.assess.dal.basis.entity.FuniHousesBuildUnit;
import com.copower.pmcc.assess.dal.basis.entity.FuniHousesBuildUnitExample;
import com.copower.pmcc.assess.dal.basis.mapper.FuniHousesBuildUnitMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
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
public class FuniHousesBuildUnitDao {
    @Autowired
    private FuniHousesBuildUnitMapper funiHousesBuildUnitMapper;

    public FuniHousesBuildUnit getFuniHousesBuildUnit(Integer id) {
        return funiHousesBuildUnitMapper.selectByPrimaryKey(id);
    }

    public List<FuniHousesBuildUnit> getFuniHousesBuildUnitList(FuniHousesBuildUnit funiHousesBuildUnit) {
        FuniHousesBuildUnitExample example = new FuniHousesBuildUnitExample();
        MybatisUtils.convertObj2Example(funiHousesBuildUnit, example);
        List<FuniHousesBuildUnit> funiHousesBuildUnits = funiHousesBuildUnitMapper.selectByExample(example);
        return funiHousesBuildUnits;
    }

    public boolean addFuniHousesBuildUnit(FuniHousesBuildUnit funiHousesBuildUnit) {
        int i = funiHousesBuildUnitMapper.insert(funiHousesBuildUnit);
        return i > 0;
    }

    public boolean editFuniHousesBuildUnit(FuniHousesBuildUnit funiHousesBuildUnit) {
        int i = funiHousesBuildUnitMapper.updateByPrimaryKeySelective(funiHousesBuildUnit);
        return i > 0;
    }

    public boolean deleteFuniHousesBuildUnit(Integer id) {
        int i = funiHousesBuildUnitMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
