package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseFaceStreet;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseFaceStreetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineHouseFaceStreetMapper {
    int countByExample(ExamineHouseFaceStreetExample example);

    int deleteByExample(ExamineHouseFaceStreetExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineHouseFaceStreet record);

    int insertSelective(ExamineHouseFaceStreet record);

    List<ExamineHouseFaceStreet> selectByExample(ExamineHouseFaceStreetExample example);

    ExamineHouseFaceStreet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineHouseFaceStreet record, @Param("example") ExamineHouseFaceStreetExample example);

    int updateByExample(@Param("record") ExamineHouseFaceStreet record, @Param("example") ExamineHouseFaceStreetExample example);

    int updateByPrimaryKeySelective(ExamineHouseFaceStreet record);

    int updateByPrimaryKey(ExamineHouseFaceStreet record);
}