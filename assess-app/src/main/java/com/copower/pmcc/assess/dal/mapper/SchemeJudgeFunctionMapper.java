package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.SchemeJudgeFunction;
import com.copower.pmcc.assess.dal.entity.SchemeJudgeFunctionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchemeJudgeFunctionMapper {
    int countByExample(SchemeJudgeFunctionExample example);

    int deleteByExample(SchemeJudgeFunctionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchemeJudgeFunction record);

    int insertSelective(SchemeJudgeFunction record);

    List<SchemeJudgeFunction> selectByExample(SchemeJudgeFunctionExample example);

    SchemeJudgeFunction selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchemeJudgeFunction record, @Param("example") SchemeJudgeFunctionExample example);

    int updateByExample(@Param("record") SchemeJudgeFunction record, @Param("example") SchemeJudgeFunctionExample example);

    int updateByPrimaryKeySelective(SchemeJudgeFunction record);

    int updateByPrimaryKey(SchemeJudgeFunction record);
}