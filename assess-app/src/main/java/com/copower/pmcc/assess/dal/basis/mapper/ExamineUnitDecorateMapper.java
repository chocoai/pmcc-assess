package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineUnitDecorate;
import com.copower.pmcc.assess.dal.basis.entity.ExamineUnitDecorateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineUnitDecorateMapper {
    int countByExample(ExamineUnitDecorateExample example);

    int deleteByExample(ExamineUnitDecorateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineUnitDecorate record);

    int insertSelective(ExamineUnitDecorate record);

    List<ExamineUnitDecorate> selectByExample(ExamineUnitDecorateExample example);

    ExamineUnitDecorate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineUnitDecorate record, @Param("example") ExamineUnitDecorateExample example);

    int updateByExample(@Param("record") ExamineUnitDecorate record, @Param("example") ExamineUnitDecorateExample example);

    int updateByPrimaryKeySelective(ExamineUnitDecorate record);

    int updateByPrimaryKey(ExamineUnitDecorate record);
}