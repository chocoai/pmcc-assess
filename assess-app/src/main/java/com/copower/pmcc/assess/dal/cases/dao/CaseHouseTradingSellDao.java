package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseTradingSell;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseTradingSellExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseHouseTradingSellMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/13 17:43
 * @Description:
 */
@Repository
public class CaseHouseTradingSellDao {
    @Autowired
    private CaseHouseTradingSellMapper caseHouseTradingSellMapper;

    public Integer addCaseHouseTradingSell(CaseHouseTradingSell caseHouseTradingSell){
        caseHouseTradingSellMapper.insertSelective(caseHouseTradingSell);
        return caseHouseTradingSell.getId();
    }

    public CaseHouseTradingSell getCaseHouseTradingSellById(Integer id){
        return caseHouseTradingSellMapper.selectByPrimaryKey(id);
    }

    public boolean updateCaseHouseTradingSell(CaseHouseTradingSell caseHouseTradingSell){
        return caseHouseTradingSellMapper.updateByPrimaryKeySelective(caseHouseTradingSell)==1;
    }

    public void removeCaseHouseTradingSell(CaseHouseTradingSell caseHouseTradingSell){
        CaseHouseTradingSellExample example = new CaseHouseTradingSellExample();
        MybatisUtils.convertObj2Example(caseHouseTradingSell, example);
        try {
            caseHouseTradingSellMapper.deleteByExample(example);
        } catch (Exception e1) {
            try {
                throw new SQLException("exception");
            } catch (SQLException e) {
            }
        }
    }

    public List<CaseHouseTradingSell> getCaseHouseTradingSellList(CaseHouseTradingSell caseHouseTradingSell){
        CaseHouseTradingSellExample example = new CaseHouseTradingSellExample();
        MybatisUtils.convertObj2Example(caseHouseTradingSell, example);
        return caseHouseTradingSellMapper.selectByExample(example);
    }
}
