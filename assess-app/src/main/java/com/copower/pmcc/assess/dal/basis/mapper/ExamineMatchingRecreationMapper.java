package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingRecreation;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingRecreationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineMatchingRecreationMapper {
    int countByExample(ExamineMatchingRecreationExample example);

    int deleteByExample(ExamineMatchingRecreationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineMatchingRecreation record);

    int insertSelective(ExamineMatchingRecreation record);

    List<ExamineMatchingRecreation> selectByExample(ExamineMatchingRecreationExample example);

    ExamineMatchingRecreation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineMatchingRecreation record, @Param("example") ExamineMatchingRecreationExample example);

    int updateByExample(@Param("record") ExamineMatchingRecreation record, @Param("example") ExamineMatchingRecreationExample example);

    int updateByPrimaryKeySelective(ExamineMatchingRecreation record);

    int updateByPrimaryKey(ExamineMatchingRecreation record);
}