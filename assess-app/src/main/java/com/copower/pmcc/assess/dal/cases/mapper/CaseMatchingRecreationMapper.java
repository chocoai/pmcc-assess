package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingRecreation;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingRecreationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseMatchingRecreationMapper {
    int countByExample(CaseMatchingRecreationExample example);

    int deleteByExample(CaseMatchingRecreationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseMatchingRecreation record);

    int insertSelective(CaseMatchingRecreation record);

    List<CaseMatchingRecreation> selectByExample(CaseMatchingRecreationExample example);

    CaseMatchingRecreation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseMatchingRecreation record, @Param("example") CaseMatchingRecreationExample example);

    int updateByExample(@Param("record") CaseMatchingRecreation record, @Param("example") CaseMatchingRecreationExample example);

    int updateByPrimaryKeySelective(CaseMatchingRecreation record);

    int updateByPrimaryKey(CaseMatchingRecreation record);
}