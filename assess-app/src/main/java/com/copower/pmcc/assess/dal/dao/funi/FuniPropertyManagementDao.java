package com.copower.pmcc.assess.dal.dao.funi;

import com.copower.pmcc.assess.dal.entity.FuniPropertyManagement;
import com.copower.pmcc.assess.dal.entity.FuniPropertyManagementExample;
import com.copower.pmcc.assess.dal.entity.FuniPropertyManagement;
import com.copower.pmcc.assess.dal.entity.FuniPropertyManagementExample;
import com.copower.pmcc.assess.dal.mapper.FuniPropertyManagementMapper;
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
public class FuniPropertyManagementDao {
    @Autowired
    private FuniPropertyManagementMapper funiPropertyManagementMapper;

    public FuniPropertyManagement getFuniPropertyManagement(Integer id) {
        return funiPropertyManagementMapper.selectByPrimaryKey(id);
    }
    public FuniPropertyManagement getFuniPropertyManagement(String name) {
        FuniPropertyManagementExample example = new FuniPropertyManagementExample();
        example.createCriteria().andPropertyManagementNameEqualTo(name);
        List<FuniPropertyManagement> funiPropertyManagements = funiPropertyManagementMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(funiPropertyManagements))
        {
            return funiPropertyManagements.get(0);
        }
        return null;
    }
    public List<FuniPropertyManagement> getFuniPropertyManagementList(FuniPropertyManagement funiPropertyManagement) {
        FuniPropertyManagementExample example = new FuniPropertyManagementExample();
        MybatisUtils.convertObj2Example(funiPropertyManagement, example);
        List<FuniPropertyManagement> funiPropertyManagements = funiPropertyManagementMapper.selectByExample(example);
        return funiPropertyManagements;
    }

    public boolean addFuniPropertyManagement(FuniPropertyManagement funiPropertyManagement) {
        int i = funiPropertyManagementMapper.insert(funiPropertyManagement);
        return i > 0;
    }

    public boolean editFuniPropertyManagement(FuniPropertyManagement funiPropertyManagement) {
        int i = funiPropertyManagementMapper.updateByPrimaryKeySelective(funiPropertyManagement);
        return i > 0;
    }

    public boolean deleteFuniPropertyManagement(Integer id) {
        int i = funiPropertyManagementMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
