package com.copower.pmcc.assess.dal.dao.funi;

import com.copower.pmcc.assess.dal.entity.FuniHousesBuild;
import com.copower.pmcc.assess.dal.entity.FuniHousesBuildExample;
import com.copower.pmcc.assess.dal.mapper.FuniHousesBuildMapper;
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
public class FuniHousesBuildDao {
    @Autowired
    private FuniHousesBuildMapper funiHousesBuildMapper;

    public FuniHousesBuild getFuniHousesBuild(Integer id) {
        return funiHousesBuildMapper.selectByPrimaryKey(id);
    }

    public List<FuniHousesBuild> getFuniHousesBuildList(FuniHousesBuild funiHousesBuild) {
        FuniHousesBuildExample example = new FuniHousesBuildExample();
        MybatisUtils.convertObj2Example(funiHousesBuild, example);
        List<FuniHousesBuild> funiHousesBuilds = funiHousesBuildMapper.selectByExample(example);
        return funiHousesBuilds;
    }

    public boolean addFuniHousesBuild(FuniHousesBuild funiHousesBuild) {
        int i = funiHousesBuildMapper.insert(funiHousesBuild);
        return i > 0;
    }

    public boolean editFuniHousesBuild(FuniHousesBuild funiHousesBuild) {
        int i = funiHousesBuildMapper.updateByPrimaryKeySelective(funiHousesBuild);
        return i > 0;
    }

    public boolean deleteFuniHousesBuild(Integer id) {
        int i = funiHousesBuildMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
