package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseIntelligent;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseIntelligentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamineHouseIntelligentMapper {
    int countByExample(ExamineHouseIntelligentExample example);

    int deleteByExample(ExamineHouseIntelligentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamineHouseIntelligent record);

    int insertSelective(ExamineHouseIntelligent record);

    List<ExamineHouseIntelligent> selectByExample(ExamineHouseIntelligentExample example);

    ExamineHouseIntelligent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamineHouseIntelligent record, @Param("example") ExamineHouseIntelligentExample example);

    int updateByExample(@Param("record") ExamineHouseIntelligent record, @Param("example") ExamineHouseIntelligentExample example);

    int updateByPrimaryKeySelective(ExamineHouseIntelligent record);

    int updateByPrimaryKey(ExamineHouseIntelligent record);
}