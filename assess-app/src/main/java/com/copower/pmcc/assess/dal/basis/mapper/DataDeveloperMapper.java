package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataDeveloper;
import com.copower.pmcc.assess.dal.basis.entity.DataDeveloperExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataDeveloperMapper {
    int countByExample(DataDeveloperExample example);

    int deleteByExample(DataDeveloperExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataDeveloper record);

    int insertSelective(DataDeveloper record);

    List<DataDeveloper> selectByExample(DataDeveloperExample example);

    DataDeveloper selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataDeveloper record, @Param("example") DataDeveloperExample example);

    int updateByExample(@Param("record") DataDeveloper record, @Param("example") DataDeveloperExample example);

    int updateByPrimaryKeySelective(DataDeveloper record);

    int updateByPrimaryKey(DataDeveloper record);
}