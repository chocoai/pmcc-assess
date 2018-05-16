package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.ReportMergeRule;
import com.copower.pmcc.assess.dal.entity.ReportMergeRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportMergeRuleMapper {
    int countByExample(ReportMergeRuleExample example);

    int deleteByExample(ReportMergeRuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReportMergeRule record);

    int insertSelective(ReportMergeRule record);

    List<ReportMergeRule> selectByExample(ReportMergeRuleExample example);

    ReportMergeRule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReportMergeRule record, @Param("example") ReportMergeRuleExample example);

    int updateByExample(@Param("record") ReportMergeRule record, @Param("example") ReportMergeRuleExample example);

    int updateByPrimaryKeySelective(ReportMergeRule record);

    int updateByPrimaryKey(ReportMergeRule record);
}