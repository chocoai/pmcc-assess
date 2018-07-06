package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingTraffic;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingTrafficExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseMatchingTrafficMapper {
    int countByExample(CaseMatchingTrafficExample example);

    int deleteByExample(CaseMatchingTrafficExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseMatchingTraffic record);

    int insertSelective(CaseMatchingTraffic record);

    List<CaseMatchingTraffic> selectByExample(CaseMatchingTrafficExample example);

    CaseMatchingTraffic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseMatchingTraffic record, @Param("example") CaseMatchingTrafficExample example);

    int updateByExample(@Param("record") CaseMatchingTraffic record, @Param("example") CaseMatchingTrafficExample example);

    int updateByPrimaryKeySelective(CaseMatchingTraffic record);

    int updateByPrimaryKey(CaseMatchingTraffic record);
}