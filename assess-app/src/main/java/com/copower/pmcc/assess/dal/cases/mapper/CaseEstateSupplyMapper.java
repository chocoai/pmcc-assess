package com.copower.pmcc.assess.dal.cases.mapper;

import com.copower.pmcc.assess.dal.cases.entity.CaseEstateSupply;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateSupplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseEstateSupplyMapper {
    int countByExample(CaseEstateSupplyExample example);

    int deleteByExample(CaseEstateSupplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CaseEstateSupply record);

    int insertSelective(CaseEstateSupply record);

    List<CaseEstateSupply> selectByExample(CaseEstateSupplyExample example);

    CaseEstateSupply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CaseEstateSupply record, @Param("example") CaseEstateSupplyExample example);

    int updateByExample(@Param("record") CaseEstateSupply record, @Param("example") CaseEstateSupplyExample example);

    int updateByPrimaryKeySelective(CaseEstateSupply record);

    int updateByPrimaryKey(CaseEstateSupply record);
}