package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeFunction;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeFunctionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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