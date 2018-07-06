package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingEducation;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingEducationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineMatchingEducationMapper {
    int countByExample(ExamineMatchingEducationExample example);

    int deleteByExample(ExamineMatchingEducationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineMatchingEducation record);

    int insertSelective(ExamineMatchingEducation record);

    List<ExamineMatchingEducation> selectByExample(ExamineMatchingEducationExample example);

    ExamineMatchingEducation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineMatchingEducation record, @Param("example") ExamineMatchingEducationExample example);

    int updateByExample(@Param("record") ExamineMatchingEducation record, @Param("example") ExamineMatchingEducationExample example);

    int updateByPrimaryKeySelective(ExamineMatchingEducation record);

    int updateByPrimaryKey(ExamineMatchingEducation record);
}