package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomePriceInvestigation;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomePriceInvestigationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdIncomePriceInvestigationMapper {
    int countByExample(MdIncomePriceInvestigationExample example);

    int deleteByExample(MdIncomePriceInvestigationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MdIncomePriceInvestigation record);

    int insertSelective(MdIncomePriceInvestigation record);

    List<MdIncomePriceInvestigation> selectByExample(MdIncomePriceInvestigationExample example);

    MdIncomePriceInvestigation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MdIncomePriceInvestigation record, @Param("example") MdIncomePriceInvestigationExample example);

    int updateByExample(@Param("record") MdIncomePriceInvestigation record, @Param("example") MdIncomePriceInvestigationExample example);

    int updateByPrimaryKeySelective(MdIncomePriceInvestigation record);

    int updateByPrimaryKey(MdIncomePriceInvestigation record);
}