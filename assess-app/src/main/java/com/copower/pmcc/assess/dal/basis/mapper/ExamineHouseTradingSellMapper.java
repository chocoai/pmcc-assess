package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTradingSell;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTradingSellExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineHouseTradingSellMapper {
    int countByExample(ExamineHouseTradingSellExample example);

    int deleteByExample(ExamineHouseTradingSellExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineHouseTradingSell record);

    int insertSelective(ExamineHouseTradingSell record);

    List<ExamineHouseTradingSell> selectByExample(ExamineHouseTradingSellExample example);

    ExamineHouseTradingSell selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineHouseTradingSell record, @Param("example") ExamineHouseTradingSellExample example);

    int updateByExample(@Param("record") ExamineHouseTradingSell record, @Param("example") ExamineHouseTradingSellExample example);

    int updateByPrimaryKeySelective(ExamineHouseTradingSell record);

    int updateByPrimaryKey(ExamineHouseTradingSell record);
}