package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingMarket;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingMarketExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineMatchingMarketMapper {
    int countByExample(ExamineMatchingMarketExample example);

    int deleteByExample(ExamineMatchingMarketExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineMatchingMarket record);

    int insertSelective(ExamineMatchingMarket record);

    List<ExamineMatchingMarket> selectByExample(ExamineMatchingMarketExample example);

    ExamineMatchingMarket selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineMatchingMarket record, @Param("example") ExamineMatchingMarketExample example);

    int updateByExample(@Param("record") ExamineMatchingMarket record, @Param("example") ExamineMatchingMarketExample example);

    int updateByPrimaryKeySelective(ExamineMatchingMarket record);

    int updateByPrimaryKey(ExamineMatchingMarket record);
}