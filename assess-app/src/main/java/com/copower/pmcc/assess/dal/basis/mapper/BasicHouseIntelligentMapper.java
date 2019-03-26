package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseIntelligent;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseIntelligentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicHouseIntelligentMapper {
    int countByExample(BasicHouseIntelligentExample example);

    int deleteByExample(BasicHouseIntelligentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicHouseIntelligent record);

    int insertSelective(BasicHouseIntelligent record);

    List<BasicHouseIntelligent> selectByExampleWithBLOBs(BasicHouseIntelligentExample example);

    List<BasicHouseIntelligent> selectByExample(BasicHouseIntelligentExample example);

    BasicHouseIntelligent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicHouseIntelligent record, @Param("example") BasicHouseIntelligentExample example);

    int updateByExampleWithBLOBs(@Param("record") BasicHouseIntelligent record, @Param("example") BasicHouseIntelligentExample example);

    int updateByExample(@Param("record") BasicHouseIntelligent record, @Param("example") BasicHouseIntelligentExample example);

    int updateByPrimaryKeySelective(BasicHouseIntelligent record);

    int updateByPrimaryKeyWithBLOBs(BasicHouseIntelligent record);

    int updateByPrimaryKey(BasicHouseIntelligent record);
}