package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BasicUnitCommonPart;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnitCommonPartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicUnitCommonPartMapper {
    int countByExample(BasicUnitCommonPartExample example);

    int deleteByExample(BasicUnitCommonPartExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicUnitCommonPart record);

    int insertSelective(BasicUnitCommonPart record);

    List<BasicUnitCommonPart> selectByExample(BasicUnitCommonPartExample example);

    BasicUnitCommonPart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicUnitCommonPart record, @Param("example") BasicUnitCommonPartExample example);

    int updateByExample(@Param("record") BasicUnitCommonPart record, @Param("example") BasicUnitCommonPartExample example);

    int updateByPrimaryKeySelective(BasicUnitCommonPart record);

    int updateByPrimaryKey(BasicUnitCommonPart record);
}