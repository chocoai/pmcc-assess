package com.copower.pmcc.assess.dal.basic.mapper;

import com.copower.pmcc.assess.dal.basic.entity.BasicMatchingEducation;
import com.copower.pmcc.assess.dal.basic.entity.BasicMatchingEducationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicMatchingEducationMapper {
    int countByExample(BasicMatchingEducationExample example);

    int deleteByExample(BasicMatchingEducationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicMatchingEducation record);

    int insertSelective(BasicMatchingEducation record);

    List<BasicMatchingEducation> selectByExample(BasicMatchingEducationExample example);

    BasicMatchingEducation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicMatchingEducation record, @Param("example") BasicMatchingEducationExample example);

    int updateByExample(@Param("record") BasicMatchingEducation record, @Param("example") BasicMatchingEducationExample example);

    int updateByPrimaryKeySelective(BasicMatchingEducation record);

    int updateByPrimaryKey(BasicMatchingEducation record);
}