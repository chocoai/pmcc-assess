package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseTrading;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseTradingExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseHouseTradingMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/13 17:37
 * @Description:
 */
@Repository
public class CaseHouseTradingDao {
    @Autowired
    private CaseHouseTradingMapper caseHouseTradingMapper;

    public Integer addCaseHouseTrading(CaseHouseTrading caseHouseTrading){
        caseHouseTradingMapper.insertSelective(caseHouseTrading);
        return caseHouseTrading.getId();
    }

    public CaseHouseTrading getCaseHouseTradingById(Integer id){
        return caseHouseTradingMapper.selectByPrimaryKey(id);
    }

    public boolean updateCaseHouseTrading(CaseHouseTrading caseHouseTrading){
        return caseHouseTradingMapper.updateByPrimaryKeySelective(caseHouseTrading)==1;
    }

    public void removeCaseHouseTrading(CaseHouseTrading caseHouseTrading){
        CaseHouseTradingExample example = new CaseHouseTradingExample();
        MybatisUtils.convertObj2Example(caseHouseTrading, example);
        try {
            caseHouseTradingMapper.deleteByExample(example);
        } catch (Exception e1) {
            try {
                throw new SQLException("exception");
            } catch (SQLException e) {
            }
        }
    }

    public List<CaseHouseTrading> getCaseHouseTradingList(CaseHouseTrading caseHouseTrading){
        CaseHouseTradingExample example = new CaseHouseTradingExample();
        MybatisUtils.convertObj2Example(caseHouseTrading, example);
        return caseHouseTradingMapper.selectByExample(example);
    }
}
