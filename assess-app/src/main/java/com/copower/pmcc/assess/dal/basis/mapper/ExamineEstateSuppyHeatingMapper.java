package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateSuppyHeating;
import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateSuppyHeatingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineEstateSuppyHeatingMapper {
    int countByExample(ExamineEstateSuppyHeatingExample example);

    int deleteByExample(ExamineEstateSuppyHeatingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineEstateSuppyHeating record);

    int insertSelective(ExamineEstateSuppyHeating record);

    List<ExamineEstateSuppyHeating> selectByExample(ExamineEstateSuppyHeatingExample example);

    ExamineEstateSuppyHeating selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineEstateSuppyHeating record, @Param("example") ExamineEstateSuppyHeatingExample example);

    int updateByExample(@Param("record") ExamineEstateSuppyHeating record, @Param("example") ExamineEstateSuppyHeatingExample example);

    int updateByPrimaryKeySelective(ExamineEstateSuppyHeating record);

    int updateByPrimaryKey(ExamineEstateSuppyHeating record);
}