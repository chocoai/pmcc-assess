package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineProperty;
import com.copower.pmcc.assess.dal.basis.entity.ExaminePropertyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExaminePropertyMapper {
    int countByExample(ExaminePropertyExample example);

    int deleteByExample(ExaminePropertyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineProperty record);

    int insertSelective(ExamineProperty record);

    List<ExamineProperty> selectByExample(ExaminePropertyExample example);

    ExamineProperty selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineProperty record, @Param("example") ExaminePropertyExample example);

    int updateByExample(@Param("record") ExamineProperty record, @Param("example") ExaminePropertyExample example);

    int updateByPrimaryKeySelective(ExamineProperty record);

    int updateByPrimaryKey(ExamineProperty record);
}