package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTradingLease;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTradingLeaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineHouseTradingLeaseMapper {
    int countByExample(ExamineHouseTradingLeaseExample example);

    int deleteByExample(ExamineHouseTradingLeaseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineHouseTradingLease record);

    int insertSelective(ExamineHouseTradingLease record);

    List<ExamineHouseTradingLease> selectByExample(ExamineHouseTradingLeaseExample example);

    ExamineHouseTradingLease selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineHouseTradingLease record, @Param("example") ExamineHouseTradingLeaseExample example);

    int updateByExample(@Param("record") ExamineHouseTradingLease record, @Param("example") ExamineHouseTradingLeaseExample example);

    int updateByPrimaryKeySelective(ExamineHouseTradingLease record);

    int updateByPrimaryKey(ExamineHouseTradingLease record);
}