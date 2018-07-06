package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineUnit;
import com.copower.pmcc.assess.dal.basis.entity.ExamineUnitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineUnitMapper {
    int countByExample(ExamineUnitExample example);

    int deleteByExample(ExamineUnitExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineUnit record);

    int insertSelective(ExamineUnit record);

    List<ExamineUnit> selectByExample(ExamineUnitExample example);

    ExamineUnit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineUnit record, @Param("example") ExamineUnitExample example);

    int updateByExample(@Param("record") ExamineUnit record, @Param("example") ExamineUnitExample example);

    int updateByPrimaryKeySelective(ExamineUnit record);

    int updateByPrimaryKey(ExamineUnit record);
}