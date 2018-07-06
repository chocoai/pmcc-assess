package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseUnit;
import com.copower.pmcc.assess.dal.cases.entity.CaseUnitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseUnitMapper {
    int countByExample(CaseUnitExample example);

    int deleteByExample(CaseUnitExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseUnit record);

    int insertSelective(CaseUnit record);

    List<CaseUnit> selectByExample(CaseUnitExample example);

    CaseUnit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseUnit record, @Param("example") CaseUnitExample example);

    int updateByExample(@Param("record") CaseUnit record, @Param("example") CaseUnitExample example);

    int updateByPrimaryKeySelective(CaseUnit record);

    int updateByPrimaryKey(CaseUnit record);
}