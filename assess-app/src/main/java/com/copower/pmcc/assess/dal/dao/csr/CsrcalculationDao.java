package com.copower.pmcc.assess.dal.dao.csr;

import com.copower.pmcc.assess.dal.entity.CsrBorrowerMortgageExample;
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
    private CsrCalculationMapper csrCalculationMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CsrCalculation getCsrCalculationById(Integer id) {
        return csrCalculationMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param csrCalculation
     * @return
     */
    public List<CsrCalculation> getCsrCalculationList(CsrCalculation csrCalculation) {
        CsrCalculationExample example = new CsrCalculationExample();
        MybatisUtils.convertObj2Example(csrCalculation, example);
        return csrCalculationMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param csrCalculation
     * @return
     */
    public boolean addCsrCalculation(CsrCalculation csrCalculation) {
        return csrCalculationMapper.insertSelective(csrCalculation) > 0;
    }

    /**
     * 编辑
     * @param csrCalculation
     * @return
     */
    public boolean updateCsrCalculation(CsrCalculation csrCalculation) {
        return csrCalculationMapper.updateByPrimaryKeySelective(csrCalculation) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteCsrCalculation(Integer id){
        return csrCalculationMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 批量删除
     *
     * @param borrowerId
     * @return
     */
    public boolean deleteByBorrowerId(Integer borrowerId) {
        CsrCalculationExample example = new CsrCalculationExample();
        example.createCriteria().andBorrowerIdEqualTo(borrowerId);
        return csrCalculationMapper.deleteByExample(example) > 0;
    }
}