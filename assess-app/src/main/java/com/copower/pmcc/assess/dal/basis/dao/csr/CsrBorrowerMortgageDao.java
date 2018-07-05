package com.copower.pmcc.assess.dal.basis.dao.csr;


import com.copower.pmcc.assess.dal.basis.entity.CsrBorrowerMortgage;
import com.copower.pmcc.assess.dal.basis.entity.CsrBorrowerMortgageExample;
import com.copower.pmcc.assess.dal.basis.mapper.CsrBorrowerMortgageMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/2/23
 * @time: 14:23
 */
@Repository
public class CsrBorrowerMortgageDao {
    @Autowired
    private CsrBorrowerMortgageMapper csrBorrowerMortgageMapper;

    public List<CsrBorrowerMortgage> getCsrProjectMortgages(Integer projectID){
        CsrBorrowerMortgageExample example = new CsrBorrowerMortgageExample();
        example.createCriteria().andIdIsNotNull().andCsrProjectIdEqualTo(projectID);
        return csrBorrowerMortgageMapper.selectByExample(example);
    }

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public CsrBorrowerMortgage getCsrBorrowerMortgageById(Integer id) {
        return csrBorrowerMortgageMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     *
     * @param csrBorrowerMortgage
     * @return
     */
    public List<CsrBorrowerMortgage> getCsrBorrowerMortgageList(CsrBorrowerMortgage csrBorrowerMortgage) {
        CsrBorrowerMortgageExample example = new CsrBorrowerMortgageExample();
        MybatisUtils.convertObj2Example(csrBorrowerMortgage, example);
        return csrBorrowerMortgageMapper.selectByExample(example);
    }

    /**
     * 获取数据列表
     *
     * @param borrowerIds
     * @return
     */
    public List<CsrBorrowerMortgage> getCsrBorrowerMortgageList(List<String> borrowerIds) {
        if (CollectionUtils.isEmpty(borrowerIds)) return null;
        CsrBorrowerMortgageExample example = new CsrBorrowerMortgageExample();
        CsrBorrowerMortgageExample.Criteria criteria = example.createCriteria();
        criteria.andBorrowerIdIn(borrowerIds);
        return csrBorrowerMortgageMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param csrBorrowerMortgage
     * @return
     */
    public boolean addCsrBorrowerMortgage(CsrBorrowerMortgage csrBorrowerMortgage) {
        return csrBorrowerMortgageMapper.insertSelective(csrBorrowerMortgage) > 0;
    }

    /**
     * 编辑
     *
     * @param csrBorrowerMortgage
     * @return
     */
    public boolean updateCsrBorrowerMortgage(CsrBorrowerMortgage csrBorrowerMortgage) {
        return csrBorrowerMortgageMapper.updateByPrimaryKeySelective(csrBorrowerMortgage) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteCsrBorrowerMortgage(Integer id) {
        return csrBorrowerMortgageMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 批量删除
     *
     * @param csrProjectId
     * @return
     */
    public boolean deleteByCsrProjectId(Integer csrProjectId) {
        CsrBorrowerMortgageExample example = new CsrBorrowerMortgageExample();
        example.createCriteria().andCsrProjectIdEqualTo(csrProjectId);
        return csrBorrowerMortgageMapper.deleteByExample(example) > 0;
    }
}
