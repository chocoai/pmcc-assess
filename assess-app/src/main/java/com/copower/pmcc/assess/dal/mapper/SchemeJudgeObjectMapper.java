package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dal.entity.SchemeJudgeObjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchemeJudgeObjectMapper {
    int countByExample(SchemeJudgeObjectExample example);

    int deleteByExample(SchemeJudgeObjectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchemeJudgeObject record);

    int insertSelective(SchemeJudgeObject record);

    List<SchemeJudgeObject> selectByExample(SchemeJudgeObjectExample example);

    SchemeJudgeObject selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchemeJudgeObject record, @Param("example") SchemeJudgeObjectExample example);

    int updateByExample(@Param("record") SchemeJudgeObject record, @Param("example") SchemeJudgeObjectExample example);

    int updateByPrimaryKeySelective(SchemeJudgeObject record);

    int updateByPrimaryKey(SchemeJudgeObject record);
}