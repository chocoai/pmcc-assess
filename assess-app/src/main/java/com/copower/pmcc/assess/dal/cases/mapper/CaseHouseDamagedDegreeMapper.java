package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseDamagedDegree;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseDamagedDegreeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseHouseDamagedDegreeMapper {
    int countByExample(CaseHouseDamagedDegreeExample example);

    int deleteByExample(CaseHouseDamagedDegreeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseHouseDamagedDegree record);

    int insertSelective(CaseHouseDamagedDegree record);

    List<CaseHouseDamagedDegree> selectByExample(CaseHouseDamagedDegreeExample example);

    CaseHouseDamagedDegree selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseHouseDamagedDegree record, @Param("example") CaseHouseDamagedDegreeExample example);

    int updateByExample(@Param("record") CaseHouseDamagedDegree record, @Param("example") CaseHouseDamagedDegreeExample example);

    int updateByPrimaryKeySelective(CaseHouseDamagedDegree record);

    int updateByPrimaryKey(CaseHouseDamagedDegree record);
}