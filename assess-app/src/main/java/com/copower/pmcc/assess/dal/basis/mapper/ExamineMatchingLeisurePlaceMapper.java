package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingLeisurePlace;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingLeisurePlaceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineMatchingLeisurePlaceMapper {
    int countByExample(ExamineMatchingLeisurePlaceExample example);

    int deleteByExample(ExamineMatchingLeisurePlaceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineMatchingLeisurePlace record);

    int insertSelective(ExamineMatchingLeisurePlace record);

    List<ExamineMatchingLeisurePlace> selectByExample(ExamineMatchingLeisurePlaceExample example);

    ExamineMatchingLeisurePlace selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineMatchingLeisurePlace record, @Param("example") ExamineMatchingLeisurePlaceExample example);

    int updateByExample(@Param("record") ExamineMatchingLeisurePlace record, @Param("example") ExamineMatchingLeisurePlaceExample example);

    int updateByPrimaryKeySelective(ExamineMatchingLeisurePlace record);

    int updateByPrimaryKey(ExamineMatchingLeisurePlace record);
}