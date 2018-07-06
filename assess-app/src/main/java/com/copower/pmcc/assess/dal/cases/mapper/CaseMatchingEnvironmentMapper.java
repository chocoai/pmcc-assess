package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingEnvironment;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingEnvironmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseMatchingEnvironmentMapper {
    int countByExample(CaseMatchingEnvironmentExample example);

    int deleteByExample(CaseMatchingEnvironmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseMatchingEnvironment record);

    int insertSelective(CaseMatchingEnvironment record);

    List<CaseMatchingEnvironment> selectByExample(CaseMatchingEnvironmentExample example);

    CaseMatchingEnvironment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseMatchingEnvironment record, @Param("example") CaseMatchingEnvironmentExample example);

    int updateByExample(@Param("record") CaseMatchingEnvironment record, @Param("example") CaseMatchingEnvironmentExample example);

    int updateByPrimaryKeySelective(CaseMatchingEnvironment record);

    int updateByPrimaryKey(CaseMatchingEnvironment record);
}