package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataBuilder;
import com.copower.pmcc.assess.dal.basis.entity.DataBuilderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataBuilderMapper {
    int countByExample(DataBuilderExample example);

    int deleteByExample(DataBuilderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataBuilder record);

    int insertSelective(DataBuilder record);

    List<DataBuilder> selectByExample(DataBuilderExample example);

    DataBuilder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataBuilder record, @Param("example") DataBuilderExample example);

    int updateByExample(@Param("record") DataBuilder record, @Param("example") DataBuilderExample example);

    int updateByPrimaryKeySelective(DataBuilder record);

    int updateByPrimaryKey(DataBuilder record);
}