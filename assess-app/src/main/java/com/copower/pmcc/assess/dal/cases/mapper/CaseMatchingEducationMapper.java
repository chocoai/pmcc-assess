package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingEducation;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingEducationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseMatchingEducationMapper {
    int countByExample(CaseMatchingEducationExample example);

    int deleteByExample(CaseMatchingEducationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseMatchingEducation record);

    int insertSelective(CaseMatchingEducation record);

    List<CaseMatchingEducation> selectByExample(CaseMatchingEducationExample example);

    CaseMatchingEducation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseMatchingEducation record, @Param("example") CaseMatchingEducationExample example);

    int updateByExample(@Param("record") CaseMatchingEducation record, @Param("example") CaseMatchingEducationExample example);

    int updateByPrimaryKeySelective(CaseMatchingEducation record);

    int updateByPrimaryKey(CaseMatchingEducation record);
}