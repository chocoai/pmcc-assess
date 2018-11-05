package com.copower.pmcc.assess.dal.basic.mapper;

import com.copower.pmcc.assess.dal.basic.entity.BasicUnitDecorate;
import com.copower.pmcc.assess.dal.basic.entity.BasicUnitDecorateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicUnitDecorateMapper {
    int countByExample(BasicUnitDecorateExample example);

    int deleteByExample(BasicUnitDecorateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BasicUnitDecorate record);

    int insertSelective(BasicUnitDecorate record);

    List<BasicUnitDecorate> selectByExample(BasicUnitDecorateExample example);

    BasicUnitDecorate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BasicUnitDecorate record, @Param("example") BasicUnitDecorateExample example);

    int updateByExample(@Param("record") BasicUnitDecorate record, @Param("example") BasicUnitDecorateExample example);

    int updateByPrimaryKeySelective(BasicUnitDecorate record);

    int updateByPrimaryKey(BasicUnitDecorate record);
}