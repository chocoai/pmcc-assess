package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineUnitHuxing;
import com.copower.pmcc.assess.dal.basis.entity.ExamineUnitHuxingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineUnitHuxingMapper {
    int countByExample(ExamineUnitHuxingExample example);

    int deleteByExample(ExamineUnitHuxingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineUnitHuxing record);

    int insertSelective(ExamineUnitHuxing record);

    List<ExamineUnitHuxing> selectByExample(ExamineUnitHuxingExample example);

    ExamineUnitHuxing selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineUnitHuxing record, @Param("example") ExamineUnitHuxingExample example);

    int updateByExample(@Param("record") ExamineUnitHuxing record, @Param("example") ExamineUnitHuxingExample example);

    int updateByPrimaryKeySelective(ExamineUnitHuxing record);

    int updateByPrimaryKey(ExamineUnitHuxing record);
}