package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseEstateLandState;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateLandStateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseEstateLandStateMapper {
    int countByExample(CaseEstateLandStateExample example);

    int deleteByExample(CaseEstateLandStateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseEstateLandState record);

    int insertSelective(CaseEstateLandState record);

    List<CaseEstateLandState> selectByExample(CaseEstateLandStateExample example);

    CaseEstateLandState selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseEstateLandState record, @Param("example") CaseEstateLandStateExample example);

    int updateByExample(@Param("record") CaseEstateLandState record, @Param("example") CaseEstateLandStateExample example);

    int updateByPrimaryKeySelective(CaseEstateLandState record);

    int updateByPrimaryKey(CaseEstateLandState record);
}