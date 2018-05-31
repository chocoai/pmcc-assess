package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.CsrInvalidRule;
import com.copower.pmcc.assess.dal.entity.CsrInvalidRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CsrInvalidRuleMapper {
    int countByExample(CsrInvalidRuleExample example);

    int deleteByExample(CsrInvalidRuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CsrInvalidRule record);

    int insertSelective(CsrInvalidRule record);

    List<CsrInvalidRule> selectByExample(CsrInvalidRuleExample example);

    CsrInvalidRule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CsrInvalidRule record, @Param("example") CsrInvalidRuleExample example);

    int updateByExample(@Param("record") CsrInvalidRule record, @Param("example") CsrInvalidRuleExample example);

    int updateByPrimaryKeySelective(CsrInvalidRule record);

    int updateByPrimaryKey(CsrInvalidRule record);
}