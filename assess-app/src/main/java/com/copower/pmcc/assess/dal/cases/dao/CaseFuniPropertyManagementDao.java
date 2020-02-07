package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseFuniPropertyManagement;
import com.copower.pmcc.assess.dal.cases.entity.CaseFuniPropertyManagementExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseFuniPropertyManagementMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
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
public class CaseFuniPropertyManagementDao {
    @Autowired
    private CaseFuniPropertyManagementMapper caseFuniPropertyManagementMapper;

    public CaseFuniPropertyManagement getCaseFuniPropertyManagement(Integer id) {
        return caseFuniPropertyManagementMapper.selectByPrimaryKey(id);
    }

    public CaseFuniPropertyManagement getCaseFuniPropertyManagement(String name) {
        CaseFuniPropertyManagementExample example = new CaseFuniPropertyManagementExample();
        example.createCriteria().andPropertyManagementNameEqualTo(name);
        List<CaseFuniPropertyManagement> caseFuniPropertyManagements = caseFuniPropertyManagementMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(caseFuniPropertyManagements)) {
            return caseFuniPropertyManagements.get(0);
        }
        return null;
    }

    public List<CaseFuniPropertyManagement> getCaseFuniPropertyManagementList(CaseFuniPropertyManagement caseFuniPropertyManagement) {
        CaseFuniPropertyManagementExample example = new CaseFuniPropertyManagementExample();
        MybatisUtils.convertObj2Example(caseFuniPropertyManagement, example);
        List<CaseFuniPropertyManagement> caseFuniPropertyManagements = caseFuniPropertyManagementMapper.selectByExample(example);
        return caseFuniPropertyManagements;
    }

    public boolean addCaseFuniPropertyManagement(CaseFuniPropertyManagement caseFuniPropertyManagement) {
        int i = caseFuniPropertyManagementMapper.insert(caseFuniPropertyManagement);
        return i > 0;
    }

    public boolean editCaseFuniPropertyManagement(CaseFuniPropertyManagement caseFuniPropertyManagement) {
        int i = caseFuniPropertyManagementMapper.updateByPrimaryKeySelective(caseFuniPropertyManagement);
        return i > 0;
    }

    public boolean deleteCaseFuniPropertyManagement(Integer id) {
        int i = caseFuniPropertyManagementMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
