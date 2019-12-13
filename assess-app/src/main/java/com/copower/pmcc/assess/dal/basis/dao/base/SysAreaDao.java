package com.copower.pmcc.assess.dal.basis.dao.base;

import com.copower.pmcc.assess.dal.basis.entity.SysArea;
import com.copower.pmcc.assess.dal.basis.entity.SysAreaExample;
import com.copower.pmcc.assess.dal.basis.mapper.SysAreaMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
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
    private final static String PERCENT_SIGN = "%";
    @Autowired
    private SysAreaMapper mapper;

    public boolean updateSysArea(SysArea oo, boolean updateNull) {
        return updateNull ? mapper.updateByPrimaryKey(oo) == 1 : mapper.updateByPrimaryKeySelective(oo) == 1;
    }

    public boolean deleteSysAreaById(Integer id) {
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public void deleteSysAreaByIds(List<Integer> ids) {
        SysAreaExample example = new SysAreaExample();
        example.createCriteria().andIdIn(ids);
        mapper.deleteByExample(example);
    }

    public SysArea getSysAreaById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public boolean saveSysArea(SysArea oo) {
        return mapper.insertSelective(oo) == 1;
    }

    public List<SysArea> getSysAreaByIds(List<Integer> ids) {
        SysAreaExample example = new SysAreaExample();
        example.createCriteria().andIdIn(ids);
        return mapper.selectByExample(example);
    }

    /**
     * 不要加上 bisEnable 为true(可用)这个 因为外面有个方法需要获取全部的数据
     * @param oo
     * @return
     */
    public List<SysArea> getSysAreaListByExample(SysArea oo) {
        SysAreaExample example = new SysAreaExample();
        MybatisUtils.convertObj2Example(oo, example);
        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }

    public List<SysArea> getSysAreaListSelect(SysArea sysArea) {
        SysAreaExample example = new SysAreaExample();
        SysAreaExample.Criteria criteria = example.createCriteria();
        if (sysArea.getId() != null) {
            criteria.andIdEqualTo(sysArea.getId());
        }
        if (StringUtils.isNotEmpty(sysArea.getAreaId())) {
            criteria.andAreaIdEqualTo(sysArea.getAreaId());
        }
        if (StringUtils.isNotEmpty(sysArea.getParentId())) {
            criteria.andParentIdEqualTo(sysArea.getParentId());
        }
        if (sysArea.getBisEnable() != null) {
            criteria.andBisEnableEqualTo(sysArea.getBisEnable());
        }
        if (StringUtils.isNotEmpty(sysArea.getName())) {
            criteria.andNameLike(String.join("", PERCENT_SIGN, sysArea.getName(), PERCENT_SIGN));
        }
        if (StringUtils.isNotEmpty(sysArea.getParentName())) {
            criteria.andParentNameLike(String.join("", PERCENT_SIGN, sysArea.getParentName(), PERCENT_SIGN));
        }
        if (StringUtils.isNotEmpty(sysArea.getAreaCode())) {
            criteria.andAreaCodeLike(String.join("", PERCENT_SIGN, sysArea.getAreaCode(), PERCENT_SIGN));
        }
        if (StringUtils.isNotEmpty(sysArea.getZipCode())) {
            criteria.andZipCodeLike(String.join("", PERCENT_SIGN, sysArea.getZipCode(), PERCENT_SIGN));
        }
        if (StringUtils.isNotEmpty(sysArea.getDepth())) {
            criteria.andDepthLike(String.join("", PERCENT_SIGN, sysArea.getDepth(), PERCENT_SIGN));
        }
        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }
}
