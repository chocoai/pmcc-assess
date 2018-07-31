package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SchemeEvaluationObject;
import com.copower.pmcc.assess.dal.basis.entity.SchemeEvaluationObjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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