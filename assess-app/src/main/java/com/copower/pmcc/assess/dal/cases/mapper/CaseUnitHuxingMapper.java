package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseUnitHuxing;
import com.copower.pmcc.assess.dal.cases.entity.CaseUnitHuxingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseUnitHuxingMapper {
    int countByExample(CaseUnitHuxingExample example);

    int deleteByExample(CaseUnitHuxingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseUnitHuxing record);

    int insertSelective(CaseUnitHuxing record);

    List<CaseUnitHuxing> selectByExample(CaseUnitHuxingExample example);

    CaseUnitHuxing selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseUnitHuxing record, @Param("example") CaseUnitHuxingExample example);

    int updateByExample(@Param("record") CaseUnitHuxing record, @Param("example") CaseUnitHuxingExample example);

    int updateByPrimaryKeySelective(CaseUnitHuxing record);

    int updateByPrimaryKey(CaseUnitHuxing record);
}