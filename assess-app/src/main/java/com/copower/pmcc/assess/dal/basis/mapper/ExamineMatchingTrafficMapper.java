package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingTraffic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingTrafficExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineMatchingTrafficMapper {
    int countByExample(ExamineMatchingTrafficExample example);

    int deleteByExample(ExamineMatchingTrafficExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineMatchingTraffic record);

    int insertSelective(ExamineMatchingTraffic record);

    List<ExamineMatchingTraffic> selectByExample(ExamineMatchingTrafficExample example);

    ExamineMatchingTraffic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineMatchingTraffic record, @Param("example") ExamineMatchingTrafficExample example);

    int updateByExample(@Param("record") ExamineMatchingTraffic record, @Param("example") ExamineMatchingTrafficExample example);

    int updateByPrimaryKeySelective(ExamineMatchingTraffic record);

    int updateByPrimaryKey(ExamineMatchingTraffic record);
}