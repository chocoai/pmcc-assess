package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataExamineTask;
import com.copower.pmcc.assess.dal.basis.entity.DataExamineTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataExamineTaskMapper {
    int countByExample(DataExamineTaskExample example);

    int deleteByExample(DataExamineTaskExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataExamineTask record);

    int insertSelective(DataExamineTask record);

    List<DataExamineTask> selectByExample(DataExamineTaskExample example);

    DataExamineTask selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataExamineTask record, @Param("example") DataExamineTaskExample example);

    int updateByExample(@Param("record") DataExamineTask record, @Param("example") DataExamineTaskExample example);

    int updateByPrimaryKeySelective(DataExamineTask record);

    int updateByPrimaryKey(DataExamineTask record);
}