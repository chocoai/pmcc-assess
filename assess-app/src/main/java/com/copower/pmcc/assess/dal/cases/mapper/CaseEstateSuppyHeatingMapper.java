package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseEstateSuppyHeating;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateSuppyHeatingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseEstateSuppyHeatingMapper {
    int countByExample(CaseEstateSuppyHeatingExample example);

    int deleteByExample(CaseEstateSuppyHeatingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseEstateSuppyHeating record);

    int insertSelective(CaseEstateSuppyHeating record);

    List<CaseEstateSuppyHeating> selectByExample(CaseEstateSuppyHeatingExample example);

    CaseEstateSuppyHeating selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseEstateSuppyHeating record, @Param("example") CaseEstateSuppyHeatingExample example);

    int updateByExample(@Param("record") CaseEstateSuppyHeating record, @Param("example") CaseEstateSuppyHeatingExample example);

    int updateByPrimaryKeySelective(CaseEstateSuppyHeating record);

    int updateByPrimaryKey(CaseEstateSuppyHeating record);
}