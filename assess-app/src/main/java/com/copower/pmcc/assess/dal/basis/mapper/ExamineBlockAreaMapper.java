package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineBlockArea;
import com.copower.pmcc.assess.dal.basis.entity.ExamineBlockAreaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineBlockAreaMapper {
    int countByExample(ExamineBlockAreaExample example);

    int deleteByExample(ExamineBlockAreaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineBlockArea record);

    int insertSelective(ExamineBlockArea record);

    List<ExamineBlockArea> selectByExample(ExamineBlockAreaExample example);

    ExamineBlockArea selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineBlockArea record, @Param("example") ExamineBlockAreaExample example);

    int updateByExample(@Param("record") ExamineBlockArea record, @Param("example") ExamineBlockAreaExample example);

    int updateByPrimaryKeySelective(ExamineBlockArea record);

    int updateByPrimaryKey(ExamineBlockArea record);
}