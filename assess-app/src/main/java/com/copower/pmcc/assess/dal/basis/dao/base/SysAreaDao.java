package com.copower.pmcc.assess.dal.basis.dao.base;

import com.copower.pmcc.assess.dal.basis.entity.SysArea;
import com.copower.pmcc.assess.dal.basis.entity.SysAreaExample;
import com.copower.pmcc.assess.dal.basis.mapper.SysAreaMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 10:42
 * @Description:
 */
@Repository
public class SysAreaDao {
    @Autowired
    private SysAreaMapper sysAreaMapper;

    public SysArea getSysAreaById(Integer id) {
        return sysAreaMapper.selectByPrimaryKey(id);
    }

    public Integer saveSysArea(SysArea sysArea) {
        sysAreaMapper.insertSelective(sysArea);
        return sysArea.getId();
    }

    public boolean updateSysArea(SysArea sysArea, boolean updateNull) {
        return updateNull ? sysAreaMapper.updateByPrimaryKey(sysArea) == 1 : sysAreaMapper.updateByPrimaryKeySelective(sysArea) == 1;
    }

    public boolean deleteSysArea(Integer id) {
        return sysAreaMapper.deleteByPrimaryKey(id) == 1;
    }

    public List<SysArea> SysAreaList(SysArea sysArea) {
        SysAreaExample example = new SysAreaExample();
        SysAreaExample.Criteria criteria = example.createCriteria();
        MybatisUtils.convertObj2Criteria(sysArea, criteria);
        return sysAreaMapper.selectByExample(example);
    }
}
