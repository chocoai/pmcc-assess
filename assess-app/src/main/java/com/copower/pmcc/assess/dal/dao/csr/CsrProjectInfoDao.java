package com.copower.pmcc.assess.dal.dao.csr;


import com.copower.pmcc.assess.dal.entity.CsrProjectInfo;
import com.copower.pmcc.assess.dal.entity.CsrProjectInfoExample;
import com.copower.pmcc.assess.dal.mapper.CsrProjectInfoMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/2/23
 * @time: 14:23
 */
@Repository
public class CsrProjectInfoDao {
    @Autowired
    private CsrProjectInfoMapper csrProjectInfoMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public CsrProjectInfo getCsrProjectInfoById(Integer id) {
        return csrProjectInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     *
     * @param csrProjectInfo
     * @return
     */
    public List<CsrProjectInfo> getCsrProjectInfoList(CsrProjectInfo csrProjectInfo) {
        CsrProjectInfoExample example = new CsrProjectInfoExample();
        MybatisUtils.convertObj2Example(csrProjectInfo, example);
        return csrProjectInfoMapper.selectByExample(example);
    }

    /**
     * 获取所有列表
     *
     * @return
     */
    public List<CsrProjectInfo> getCsrProjectInfoList(String name) {
        CsrProjectInfoExample example = new CsrProjectInfoExample();
        if (org.springframework.util.StringUtils.isEmpty(name)) {
            example.createCriteria().andIdIsNotNull();
        } else {
            example.createCriteria().andIdIsNotNull().andNameLike("%" + name + "%");
        }
        return csrProjectInfoMapper.selectByExample(example);
    }

    public CsrProjectInfo getCsrProjectInfo(String processInsId) {
        if (StringUtils.isBlank(processInsId)) return null;
        CsrProjectInfoExample example = new CsrProjectInfoExample();
        example.createCriteria().andProcessInsIdEqualTo(processInsId);
        List<CsrProjectInfo> csrProjectInfos = csrProjectInfoMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(csrProjectInfos))
            return csrProjectInfos.get(0);
        return null;
    }

    /**
     * 新增
     *
     * @param csrProjectInfo
     * @return
     */
    public boolean addCsrProjectInfo(CsrProjectInfo csrProjectInfo) {
        return csrProjectInfoMapper.insertSelective(csrProjectInfo) > 0;
    }

    /**
     * 编辑
     *
     * @param csrProjectInfo
     * @return
     */
    public boolean updateCsrProjectInfo(CsrProjectInfo csrProjectInfo) {
        return csrProjectInfoMapper.updateByPrimaryKeySelective(csrProjectInfo) > 0;
    }
}
