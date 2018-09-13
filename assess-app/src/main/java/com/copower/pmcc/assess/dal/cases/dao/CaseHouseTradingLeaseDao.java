package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseTradingLease;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseTradingLeaseExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseHouseTradingLeaseMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/13 17:45
 * @Description:
 */
@Repository
public class CaseHouseTradingLeaseDao {
    @Autowired
    private CaseHouseTradingLeaseMapper caseHouseTradingLeaseMapper;

    public Integer addCaseHouseTradingLease(CaseHouseTradingLease caseHouseTradingLease){
        caseHouseTradingLeaseMapper.insertSelective(caseHouseTradingLease);
        return caseHouseTradingLease.getId();
    }

    public CaseHouseTradingLease getCaseHouseTradingLeaseById(Integer id){
        return caseHouseTradingLeaseMapper.selectByPrimaryKey(id);
    }

    public boolean updateCaseHouseTradingLease(CaseHouseTradingLease caseHouseTradingLease){
        return caseHouseTradingLeaseMapper.updateByPrimaryKeySelective(caseHouseTradingLease)==1;
    }

    public void removeCaseHouseTradingLease(CaseHouseTradingLease caseHouseTradingLease){
        CaseHouseTradingLeaseExample example = new CaseHouseTradingLeaseExample();
        MybatisUtils.convertObj2Example(caseHouseTradingLease, example);
        try {
            caseHouseTradingLeaseMapper.deleteByExample(example);
        } catch (Exception e1) {
            try {
                throw new SQLException("exception");
            } catch (SQLException e) {
            }
        }
    }

    public List<CaseHouseTradingLease> getCaseHouseTradingLeaseList(CaseHouseTradingLease caseHouseTradingLease){
        CaseHouseTradingLeaseExample example = new CaseHouseTradingLeaseExample();
        MybatisUtils.convertObj2Example(caseHouseTradingLease, example);
        return caseHouseTradingLeaseMapper.selectByExample(example);
    }
}
