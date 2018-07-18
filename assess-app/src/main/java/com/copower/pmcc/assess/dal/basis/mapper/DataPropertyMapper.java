package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataProperty;
import com.copower.pmcc.assess.dal.basis.entity.DataPropertyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataPropertyMapper {
    int countByExample(DataPropertyExample example);

    int deleteByExample(DataPropertyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataProperty record);

    int insertSelective(DataProperty record);

    List<DataProperty> selectByExample(DataPropertyExample example);

    DataProperty selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataProperty record, @Param("example") DataPropertyExample example);

    int updateByExample(@Param("record") DataProperty record, @Param("example") DataPropertyExample example);

    int updateByPrimaryKeySelective(DataProperty record);

    int updateByPrimaryKey(DataProperty record);
}