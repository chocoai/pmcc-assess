package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingLeisurePlace;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingLeisurePlaceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseMatchingLeisurePlaceMapper {
    int countByExample(CaseMatchingLeisurePlaceExample example);

    int deleteByExample(CaseMatchingLeisurePlaceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseMatchingLeisurePlace record);

    int insertSelective(CaseMatchingLeisurePlace record);

    List<CaseMatchingLeisurePlace> selectByExample(CaseMatchingLeisurePlaceExample example);

    CaseMatchingLeisurePlace selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseMatchingLeisurePlace record, @Param("example") CaseMatchingLeisurePlaceExample example);

    int updateByExample(@Param("record") CaseMatchingLeisurePlace record, @Param("example") CaseMatchingLeisurePlaceExample example);

    int updateByPrimaryKeySelective(CaseMatchingLeisurePlace record);

    int updateByPrimaryKey(CaseMatchingLeisurePlace record);
}