package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataEarlyWarning;
import com.copower.pmcc.assess.dal.basis.entity.DataEarlyWarningExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataEarlyWarningMapper {
    int countByExample(DataEarlyWarningExample example);

    int deleteByExample(DataEarlyWarningExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataEarlyWarning record);

    int insertSelective(DataEarlyWarning record);

    List<DataEarlyWarning> selectByExample(DataEarlyWarningExample example);

    DataEarlyWarning selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataEarlyWarning record, @Param("example") DataEarlyWarningExample example);

    int updateByExample(@Param("record") DataEarlyWarning record, @Param("example") DataEarlyWarningExample example);

    int updateByPrimaryKeySelective(DataEarlyWarning record);

    int updateByPrimaryKey(DataEarlyWarning record);
}