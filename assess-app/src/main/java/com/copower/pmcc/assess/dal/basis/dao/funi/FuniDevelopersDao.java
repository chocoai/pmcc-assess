package com.copower.pmcc.assess.dal.basis.dao.funi;

import com.copower.pmcc.assess.dal.basis.entity.FuniDevelopers;
import com.copower.pmcc.assess.dal.basis.entity.FuniDevelopersExample;
import com.copower.pmcc.assess.dal.basis.mapper.FuniDevelopersMapper;
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
public class FuniDevelopersDao {
    @Autowired
    private FuniDevelopersMapper funiDevelopersMapper;

    public FuniDevelopers getFuniDevelopers(Integer id) {
        return funiDevelopersMapper.selectByPrimaryKey(id);
    }

    public FuniDevelopers getFuniDevelopers(String name) {
        FuniDevelopersExample example = new FuniDevelopersExample();
        example.createCriteria().andDevelopersNameEqualTo(name);
        List<FuniDevelopers> funiDeveloperss = funiDevelopersMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(funiDeveloperss))
        {
            return funiDeveloperss.get(0);
        }
        return null;
    }

    public List<FuniDevelopers> getFuniDevelopersList(FuniDevelopers funiDevelopers) {
        FuniDevelopersExample example = new FuniDevelopersExample();
        MybatisUtils.convertObj2Example(funiDevelopers, example);
        List<FuniDevelopers> funiDeveloperss = funiDevelopersMapper.selectByExample(example);
        return funiDeveloperss;
    }

    public boolean addFuniDevelopers(FuniDevelopers funiDevelopers) {
        int i = funiDevelopersMapper.insert(funiDevelopers);
        return i > 0;
    }

    public boolean editFuniDevelopers(FuniDevelopers funiDevelopers) {
        int i = funiDevelopersMapper.updateByPrimaryKeySelective(funiDevelopers);
        return i > 0;
    }

    public boolean deleteFuniDevelopers(Integer id) {
        int i = funiDevelopersMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
