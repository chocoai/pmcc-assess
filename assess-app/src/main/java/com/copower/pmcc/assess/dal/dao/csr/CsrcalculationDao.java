package com.copower.pmcc.assess.dal.dao.csr;

import com.copower.pmcc.assess.dal.entity.CsrCalculation;
import com.copower.pmcc.assess.dal.entity.CsrCalculationExample;
import com.copower.pmcc.assess.dal.mapper.CsrCalculationMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/6/1
 * @time: 17:36
 */
@Repository
public class CsrcalculationDao{
    @Autowired
    private CsrCalculationMapper csrContractMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CsrCalculation getCsrCalculationById(Integer id) {
        return csrContractMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param csrContract
     * @return
     */
    public List<CsrCalculation> getCsrCalculationList(CsrCalculation csrContract) {
        CsrCalculationExample example = new CsrCalculationExample();
        MybatisUtils.convertObj2Example(csrContract, example);
        return csrContractMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param csrContract
     * @return
     */
    public boolean addCsrCalculation(CsrCalculation csrContract) {
        return csrContractMapper.insertSelective(csrContract) > 0;
    }

    /**
     * 编辑
     * @param csrContract
     * @return
     */
    public boolean updateCsrCalculation(CsrCalculation csrContract) {
        return csrContractMapper.updateByPrimaryKeySelective(csrContract) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteCsrCalculation(Integer id){
        return csrContractMapper.deleteByPrimaryKey(id) > 0;
    }
}