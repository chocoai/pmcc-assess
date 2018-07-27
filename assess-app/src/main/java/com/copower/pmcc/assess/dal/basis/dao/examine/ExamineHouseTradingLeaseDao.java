package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTradingLease;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTradingLeaseExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineHouseTradingLeaseMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/27 18:37
 * @Description:
 */
@Repository
public class ExamineHouseTradingLeaseDao {
    @Autowired
    private ExamineHouseTradingLeaseMapper examineHouseTradingLeaseMapper;

    public boolean addExamineHouseTradingLease(ExamineHouseTradingLease examineHouseTradingLease){
        return examineHouseTradingLeaseMapper.insertSelective(examineHouseTradingLease)==1;
    }

    public List<ExamineHouseTradingLease> examineHouseTradingLeaseList(ExamineHouseTradingLease examineHouseTradingLease){
        ExamineHouseTradingLeaseExample example = new ExamineHouseTradingLeaseExample();
        MybatisUtils.convertObj2Example(examineHouseTradingLease, example);
        return examineHouseTradingLeaseMapper.selectByExample(example);
    }
}
