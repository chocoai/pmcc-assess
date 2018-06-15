package com.copower.pmcc.assess.dal.dao.funi;

import com.copower.pmcc.assess.dal.entity.FuniHousesType;
import com.copower.pmcc.assess.dal.entity.FuniHousesTypeExample;
import com.copower.pmcc.assess.dal.mapper.FuniHousesTypeMapper;
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
public class FuniHousesTypeDao {
    @Autowired
    private FuniHousesTypeMapper funiHousesTypeMapper;

    public FuniHousesType getFuniHousesType(Integer id) {
        return funiHousesTypeMapper.selectByPrimaryKey(id);
    }

    public List<FuniHousesType> getFuniHousesTypeList(Integer lpbh) {
        FuniHousesTypeExample example = new FuniHousesTypeExample();
        example.createCriteria().andLpbhEqualTo(lpbh);
        List<FuniHousesType> funiHousesTypes = funiHousesTypeMapper.selectByExample(example);
        return funiHousesTypes;
    }
    public List<FuniHousesType> getFuniHousesTypeList(FuniHousesType funiHousesType) {
        FuniHousesTypeExample example = new FuniHousesTypeExample();
        MybatisUtils.convertObj2Example(funiHousesType, example);
        List<FuniHousesType> funiHousesTypes = funiHousesTypeMapper.selectByExample(example);
        return funiHousesTypes;
    }

    public boolean addFuniHousesType(FuniHousesType funiHousesType) {
        int i = funiHousesTypeMapper.insert(funiHousesType);
        return i > 0;
    }

    public boolean editFuniHousesType(FuniHousesType funiHousesType) {
        int i = funiHousesTypeMapper.updateByPrimaryKeySelective(funiHousesType);
        return i > 0;
    }

    public boolean deleteFuniHousesType(Integer id) {
        int i = funiHousesTypeMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
