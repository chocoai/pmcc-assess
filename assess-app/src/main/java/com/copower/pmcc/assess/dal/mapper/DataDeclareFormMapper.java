package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.DataDeclareForm;
import com.copower.pmcc.assess.dal.entity.DataDeclareFormExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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