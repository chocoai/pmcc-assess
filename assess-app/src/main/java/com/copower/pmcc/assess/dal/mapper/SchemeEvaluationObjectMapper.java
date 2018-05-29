package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.SchemeEvaluationObject;
import com.copower.pmcc.assess.dal.entity.SchemeEvaluationObjectExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchemeEvaluationObjectMapper {
    int countByExample(SchemeEvaluationObjectExample example);

    int deleteByExample(SchemeEvaluationObjectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchemeEvaluationObject record);

    int insertSelective(SchemeEvaluationObject record);

    List<SchemeEvaluationObject> selectByExample(SchemeEvaluationObjectExample example);

    SchemeEvaluationObject selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchemeEvaluationObject record, @Param("example") SchemeEvaluationObjectExample example);

    int updateByExample(@Param("record") SchemeEvaluationObject record, @Param("example") SchemeEvaluationObjectExample example);

    int updateByPrimaryKeySelective(SchemeEvaluationObject record);

    int updateByPrimaryKey(SchemeEvaluationObject record);
}