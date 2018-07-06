package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineBlock;
import com.copower.pmcc.assess.dal.basis.entity.ExamineBlockExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineBlockMapper {
    int countByExample(ExamineBlockExample example);

    int deleteByExample(ExamineBlockExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineBlock record);

    int insertSelective(ExamineBlock record);

    List<ExamineBlock> selectByExample(ExamineBlockExample example);

    ExamineBlock selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineBlock record, @Param("example") ExamineBlockExample example);

    int updateByExample(@Param("record") ExamineBlock record, @Param("example") ExamineBlockExample example);

    int updateByPrimaryKeySelective(ExamineBlock record);

    int updateByPrimaryKey(ExamineBlock record);
}