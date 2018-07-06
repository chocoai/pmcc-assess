package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseEstate;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseEstateMapper {
    int countByExample(CaseEstateExample example);

    int deleteByExample(CaseEstateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseEstate record);

    int insertSelective(CaseEstate record);

    List<CaseEstate> selectByExample(CaseEstateExample example);

    CaseEstate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseEstate record, @Param("example") CaseEstateExample example);

    int updateByExample(@Param("record") CaseEstate record, @Param("example") CaseEstateExample example);

    int updateByPrimaryKeySelective(CaseEstate record);

    int updateByPrimaryKey(CaseEstate record);
}