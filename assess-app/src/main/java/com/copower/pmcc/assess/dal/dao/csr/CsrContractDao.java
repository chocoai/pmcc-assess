package com.copower.pmcc.assess.dal.dao.csr;


import com.copower.pmcc.assess.dal.entity.CsrCalculationExample;
import com.copower.pmcc.assess.dal.entity.CsrContract;
import com.copower.pmcc.assess.dal.entity.CsrContractExample;
import com.copower.pmcc.assess.dal.mapper.CsrContractMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
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
public class CsrContractDao {
    @Autowired
    private CsrContractMapper csrContractMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CsrContract getCsrContractById(Integer id) {
        return csrContractMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param csrContract
     * @return
     */
    public List<CsrContract> getCsrContractList(CsrContract csrContract) {
        CsrContractExample example = new CsrContractExample();
        MybatisUtils.convertObj2Example(csrContract, example);
        return csrContractMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param csrContract
     * @return
     */
    public boolean addCsrContract(CsrContract csrContract) {
        return csrContractMapper.insertSelective(csrContract) > 0;
    }

    /**
     * 编辑
     * @param csrContract
     * @return
     */
    public boolean updateCsrContract(CsrContract csrContract) {
        return csrContractMapper.updateByPrimaryKeySelective(csrContract) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteCsrContract(Integer id){
        return csrContractMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 批量删除
     *
     * @param csrProjectId
     * @return
     */
    public boolean deleteByCsrProjectId(Integer csrProjectId) {
        CsrContractExample example = new CsrContractExample();
        example.createCriteria().andCsrProjectIdEqualTo(csrProjectId);
        return csrContractMapper.deleteByExample(example) > 0;
    }
}
