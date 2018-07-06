package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateLandState;
import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateLandStateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineEstateLandStateMapper {
    int countByExample(ExamineEstateLandStateExample example);

    int deleteByExample(ExamineEstateLandStateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineEstateLandState record);

    int insertSelective(ExamineEstateLandState record);

    List<ExamineEstateLandState> selectByExample(ExamineEstateLandStateExample example);

    ExamineEstateLandState selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineEstateLandState record, @Param("example") ExamineEstateLandStateExample example);

    int updateByExample(@Param("record") ExamineEstateLandState record, @Param("example") ExamineEstateLandStateExample example);

    int updateByPrimaryKeySelective(ExamineEstateLandState record);

    int updateByPrimaryKey(ExamineEstateLandState record);
}