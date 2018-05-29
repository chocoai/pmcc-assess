package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.InitiatePossessor;
import com.copower.pmcc.assess.dal.entity.InitiatePossessorExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InitiatePossessorMapper {
    int countByExample(InitiatePossessorExample example);

    int deleteByExample(InitiatePossessorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InitiatePossessor record);

    int insertSelective(InitiatePossessor record);

    List<InitiatePossessor> selectByExample(InitiatePossessorExample example);

    InitiatePossessor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InitiatePossessor record, @Param("example") InitiatePossessorExample example);

    int updateByExample(@Param("record") InitiatePossessor record, @Param("example") InitiatePossessorExample example);

    int updateByPrimaryKeySelective(InitiatePossessor record);

    int updateByPrimaryKey(InitiatePossessor record);
}