package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataValueDefinition;
import com.copower.pmcc.assess.dal.basis.entity.DataValueDefinitionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataValueDefinitionMapper {
    int countByExample(DataValueDefinitionExample example);

    int deleteByExample(DataValueDefinitionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataValueDefinition record);

    int insertSelective(DataValueDefinition record);

    List<DataValueDefinition> selectByExample(DataValueDefinitionExample example);

    DataValueDefinition selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataValueDefinition record, @Param("example") DataValueDefinitionExample example);

    int updateByExample(@Param("record") DataValueDefinition record, @Param("example") DataValueDefinitionExample example);

    int updateByPrimaryKeySelective(DataValueDefinition record);

    int updateByPrimaryKey(DataValueDefinition record);
}