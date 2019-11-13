package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObjectHistory;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObjectHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchemeJudgeObjectHistoryMapper {
    int countByExample(SchemeJudgeObjectHistoryExample example);

    int deleteByExample(SchemeJudgeObjectHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchemeJudgeObjectHistory record);

    int insertSelective(SchemeJudgeObjectHistory record);

    List<SchemeJudgeObjectHistory> selectByExample(SchemeJudgeObjectHistoryExample example);

    SchemeJudgeObjectHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchemeJudgeObjectHistory record, @Param("example") SchemeJudgeObjectHistoryExample example);

    int updateByExample(@Param("record") SchemeJudgeObjectHistory record, @Param("example") SchemeJudgeObjectHistoryExample example);

    int updateByPrimaryKeySelective(SchemeJudgeObjectHistory record);

    int updateByPrimaryKey(SchemeJudgeObjectHistory record);
}