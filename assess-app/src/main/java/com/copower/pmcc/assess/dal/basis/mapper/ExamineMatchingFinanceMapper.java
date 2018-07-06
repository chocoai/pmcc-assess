package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingFinance;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingFinanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineMatchingFinanceMapper {
    int countByExample(ExamineMatchingFinanceExample example);

    int deleteByExample(ExamineMatchingFinanceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineMatchingFinance record);

    int insertSelective(ExamineMatchingFinance record);

    List<ExamineMatchingFinance> selectByExample(ExamineMatchingFinanceExample example);

    ExamineMatchingFinance selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineMatchingFinance record, @Param("example") ExamineMatchingFinanceExample example);

    int updateByExample(@Param("record") ExamineMatchingFinance record, @Param("example") ExamineMatchingFinanceExample example);

    int updateByPrimaryKeySelective(ExamineMatchingFinance record);

    int updateByPrimaryKey(ExamineMatchingFinance record);
}