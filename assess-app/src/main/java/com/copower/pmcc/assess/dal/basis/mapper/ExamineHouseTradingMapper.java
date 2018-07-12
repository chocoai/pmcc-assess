package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTrading;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTradingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineHouseTradingMapper {
    int countByExample(ExamineHouseTradingExample example);

    int deleteByExample(ExamineHouseTradingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineHouseTrading record);

    int insertSelective(ExamineHouseTrading record);

    List<ExamineHouseTrading> selectByExample(ExamineHouseTradingExample example);

    ExamineHouseTrading selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineHouseTrading record, @Param("example") ExamineHouseTradingExample example);

    int updateByExample(@Param("record") ExamineHouseTrading record, @Param("example") ExamineHouseTradingExample example);

    int updateByPrimaryKeySelective(ExamineHouseTrading record);

    int updateByPrimaryKey(ExamineHouseTrading record);
}