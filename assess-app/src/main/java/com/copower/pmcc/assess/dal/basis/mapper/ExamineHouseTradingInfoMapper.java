package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTradingInfo;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTradingInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineHouseTradingInfoMapper {
    int countByExample(ExamineHouseTradingInfoExample example);

    int deleteByExample(ExamineHouseTradingInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineHouseTradingInfo record);

    int insertSelective(ExamineHouseTradingInfo record);

    List<ExamineHouseTradingInfo> selectByExample(ExamineHouseTradingInfoExample example);

    ExamineHouseTradingInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineHouseTradingInfo record, @Param("example") ExamineHouseTradingInfoExample example);

    int updateByExample(@Param("record") ExamineHouseTradingInfo record, @Param("example") ExamineHouseTradingInfoExample example);

    int updateByPrimaryKeySelective(ExamineHouseTradingInfo record);

    int updateByPrimaryKey(ExamineHouseTradingInfo record);
}