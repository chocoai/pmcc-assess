package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineBuilder;
import com.copower.pmcc.assess.dal.basis.entity.ExamineBuilderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineBuilderMapper {
    int countByExample(ExamineBuilderExample example);

    int deleteByExample(ExamineBuilderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineBuilder record);

    int insertSelective(ExamineBuilder record);

    List<ExamineBuilder> selectByExample(ExamineBuilderExample example);

    ExamineBuilder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineBuilder record, @Param("example") ExamineBuilderExample example);

    int updateByExample(@Param("record") ExamineBuilder record, @Param("example") ExamineBuilderExample example);

    int updateByPrimaryKeySelective(ExamineBuilder record);

    int updateByPrimaryKey(ExamineBuilder record);
}