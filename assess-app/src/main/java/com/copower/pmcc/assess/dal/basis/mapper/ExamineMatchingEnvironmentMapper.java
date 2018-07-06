package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingEnvironment;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingEnvironmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineMatchingEnvironmentMapper {
    int countByExample(ExamineMatchingEnvironmentExample example);

    int deleteByExample(ExamineMatchingEnvironmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineMatchingEnvironment record);

    int insertSelective(ExamineMatchingEnvironment record);

    List<ExamineMatchingEnvironment> selectByExample(ExamineMatchingEnvironmentExample example);

    ExamineMatchingEnvironment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineMatchingEnvironment record, @Param("example") ExamineMatchingEnvironmentExample example);

    int updateByExample(@Param("record") ExamineMatchingEnvironment record, @Param("example") ExamineMatchingEnvironmentExample example);

    int updateByPrimaryKeySelective(ExamineMatchingEnvironment record);

    int updateByPrimaryKey(ExamineMatchingEnvironment record);
}