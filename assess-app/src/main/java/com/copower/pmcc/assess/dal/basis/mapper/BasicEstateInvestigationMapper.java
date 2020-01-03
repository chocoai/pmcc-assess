package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateInvestigation;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateInvestigationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicEstateInvestigationMapper {
    int countByExample(BasicEstateInvestigationExample example);

    int deleteByExample(BasicEstateInvestigationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicEstateInvestigation record);

    int insertSelective(BasicEstateInvestigation record);

    List<BasicEstateInvestigation> selectByExample(BasicEstateInvestigationExample example);

    BasicEstateInvestigation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicEstateInvestigation record, @Param("example") BasicEstateInvestigationExample example);

    int updateByExample(@Param("record") BasicEstateInvestigation record, @Param("example") BasicEstateInvestigationExample example);

    int updateByPrimaryKeySelective(BasicEstateInvestigation record);

    int updateByPrimaryKey(BasicEstateInvestigation record);
}