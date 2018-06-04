package com.copower.pmcc.assess.dal.dao.csr;


import com.copower.pmcc.assess.dal.entity.CsrLitigationExample;
import com.copower.pmcc.assess.dal.entity.CsrPrincipalInterest;
import com.copower.pmcc.assess.dal.entity.CsrPrincipalInterestExample;
import com.copower.pmcc.assess.dal.mapper.CsrPrincipalInterestMapper;
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
public class CsrPrincipalInterestDao {
    @Autowired
    private CsrPrincipalInterestMapper csrPrincipalInterestMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CsrPrincipalInterest getCsrPrincipalInterestById(Integer id) {
        return csrPrincipalInterestMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param csrPrincipalInterest
     * @return
     */
    public List<CsrPrincipalInterest> getCsrPrincipalInterestList(CsrPrincipalInterest csrPrincipalInterest) {
        CsrPrincipalInterestExample example = new CsrPrincipalInterestExample();
        MybatisUtils.convertObj2Example(csrPrincipalInterest, example);
        return csrPrincipalInterestMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param csrPrincipalInterest
     * @return
     */
    public boolean addCsrPrincipalInterest(CsrPrincipalInterest csrPrincipalInterest) {
        return csrPrincipalInterestMapper.insertSelective(csrPrincipalInterest) > 0;
    }

    /**
     * 编辑
     * @param csrPrincipalInterest
     * @return
     */
    public boolean updateCsrPrincipalInterest(CsrPrincipalInterest csrPrincipalInterest) {
        return csrPrincipalInterestMapper.updateByPrimaryKeySelective(csrPrincipalInterest) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteCsrPrincipalInterest(Integer id){
        return csrPrincipalInterestMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 批量删除
     *
     * @param borrowerId
     * @return
     */
    public boolean deleteByBorrowerId(Integer borrowerId) {
        CsrPrincipalInterestExample example = new CsrPrincipalInterestExample();
        example.createCriteria().andBorrowerIdEqualTo(borrowerId);
        return csrPrincipalInterestMapper.deleteByExample(example) > 0;
    }
}
