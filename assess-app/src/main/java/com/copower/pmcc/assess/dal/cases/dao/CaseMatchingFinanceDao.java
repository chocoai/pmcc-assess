package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingFinance;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingFinanceExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseMatchingFinanceMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: wangpc
 * @data: 2018/07/06
 * @time: 14:36
 */
@Repository
public class CaseMatchingFinanceDao {
    @Autowired
    private CaseMatchingFinanceMapper caseMatchingFinanceMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseMatchingFinance getMatchingFinanceById(Integer id) {
        return caseMatchingFinanceMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseMatchingFinance
     * @return
     */
    public List<CaseMatchingFinance> getMatchingFinanceList(CaseMatchingFinance caseMatchingFinance) {
        CaseMatchingFinanceExample example = new CaseMatchingFinanceExample();
        MybatisUtils.convertObj2Example(caseMatchingFinance, example);
        return caseMatchingFinanceMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseMatchingFinance
     * @return
     */
    public boolean addMatchingFinance(CaseMatchingFinance caseMatchingFinance) {
        return caseMatchingFinanceMapper.insertSelective(caseMatchingFinance) > 0;
    }

    /**
     * 编辑
     * @param caseMatchingFinance
     * @return
     */
    public boolean updateMatchingFinance(CaseMatchingFinance caseMatchingFinance) {
        return caseMatchingFinanceMapper.updateByPrimaryKeySelective(caseMatchingFinance) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteMatchingFinance(Integer id){
        return caseMatchingFinanceMapper.deleteByPrimaryKey(id) > 0;
    }

}