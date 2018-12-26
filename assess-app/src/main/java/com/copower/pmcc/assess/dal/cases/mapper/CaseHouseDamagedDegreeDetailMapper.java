package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseDamagedDegreeDetail;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseDamagedDegreeDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseHouseDamagedDegreeDetailMapper {
    int countByExample(CaseHouseDamagedDegreeDetailExample example);

    int deleteByExample(CaseHouseDamagedDegreeDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseHouseDamagedDegreeDetail record);

    int insertSelective(CaseHouseDamagedDegreeDetail record);

    List<CaseHouseDamagedDegreeDetail> selectByExample(CaseHouseDamagedDegreeDetailExample example);

    CaseHouseDamagedDegreeDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseHouseDamagedDegreeDetail record, @Param("example") CaseHouseDamagedDegreeDetailExample example);

    int updateByExample(@Param("record") CaseHouseDamagedDegreeDetail record, @Param("example") CaseHouseDamagedDegreeDetailExample example);

    int updateByPrimaryKeySelective(CaseHouseDamagedDegreeDetail record);

    int updateByPrimaryKey(CaseHouseDamagedDegreeDetail record);
}