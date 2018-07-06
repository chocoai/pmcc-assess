package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseUnitDecorate;
import com.copower.pmcc.assess.dal.cases.entity.CaseUnitDecorateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseUnitDecorateMapper {
    int countByExample(CaseUnitDecorateExample example);

    int deleteByExample(CaseUnitDecorateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseUnitDecorate record);

    int insertSelective(CaseUnitDecorate record);

    List<CaseUnitDecorate> selectByExample(CaseUnitDecorateExample example);

    CaseUnitDecorate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseUnitDecorate record, @Param("example") CaseUnitDecorateExample example);

    int updateByExample(@Param("record") CaseUnitDecorate record, @Param("example") CaseUnitDecorateExample example);

    int updateByPrimaryKeySelective(CaseUnitDecorate record);

    int updateByPrimaryKey(CaseUnitDecorate record);
}