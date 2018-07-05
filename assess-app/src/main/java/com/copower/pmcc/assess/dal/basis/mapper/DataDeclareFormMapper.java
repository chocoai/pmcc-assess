package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataDeclareForm;
import com.copower.pmcc.assess.dal.basis.entity.DataDeclareFormExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataDeclareFormMapper {
    int countByExample(DataDeclareFormExample example);

    int deleteByExample(DataDeclareFormExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataDeclareForm record);

    int insertSelective(DataDeclareForm record);

    List<DataDeclareForm> selectByExample(DataDeclareFormExample example);

    DataDeclareForm selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataDeclareForm record, @Param("example") DataDeclareFormExample example);

    int updateByExample(@Param("record") DataDeclareForm record, @Param("example") DataDeclareFormExample example);

    int updateByPrimaryKeySelective(DataDeclareForm record);

    int updateByPrimaryKey(DataDeclareForm record);
}