package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseBuilder;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuilderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseBuilderMapper {
    int countByExample(CaseBuilderExample example);

    int deleteByExample(CaseBuilderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseBuilder record);

    int insertSelective(CaseBuilder record);

    List<CaseBuilder> selectByExample(CaseBuilderExample example);

    CaseBuilder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseBuilder record, @Param("example") CaseBuilderExample example);

    int updateByExample(@Param("record") CaseBuilder record, @Param("example") CaseBuilderExample example);

    int updateByPrimaryKeySelective(CaseBuilder record);

    int updateByPrimaryKey(CaseBuilder record);
}