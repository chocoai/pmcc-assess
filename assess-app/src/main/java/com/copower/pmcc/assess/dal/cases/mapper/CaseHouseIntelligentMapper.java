package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseIntelligent;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseIntelligentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseHouseIntelligentMapper {
    int countByExample(CaseHouseIntelligentExample example);

    int deleteByExample(CaseHouseIntelligentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseHouseIntelligent record);

    int insertSelective(CaseHouseIntelligent record);

    List<CaseHouseIntelligent> selectByExampleWithBLOBs(CaseHouseIntelligentExample example);

    List<CaseHouseIntelligent> selectByExample(CaseHouseIntelligentExample example);

    CaseHouseIntelligent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseHouseIntelligent record, @Param("example") CaseHouseIntelligentExample example);

    int updateByExampleWithBLOBs(@Param("record") CaseHouseIntelligent record, @Param("example") CaseHouseIntelligentExample example);

    int updateByExample(@Param("record") CaseHouseIntelligent record, @Param("example") CaseHouseIntelligentExample example);

    int updateByPrimaryKeySelective(CaseHouseIntelligent record);

    int updateByPrimaryKeyWithBLOBs(CaseHouseIntelligent record);

    int updateByPrimaryKey(CaseHouseIntelligent record);
}