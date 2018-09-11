package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataBlock;
import com.copower.pmcc.assess.dal.basis.entity.DataBlockExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataBlockMapper {
    int countByExample(DataBlockExample example);

    int deleteByExample(DataBlockExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataBlock record);

    int insertSelective(DataBlock record);

    List<DataBlock> selectByExample(DataBlockExample example);

    DataBlock selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataBlock record, @Param("example") DataBlockExample example);

    int updateByExample(@Param("record") DataBlock record, @Param("example") DataBlockExample example);

    int updateByPrimaryKeySelective(DataBlock record);

    int updateByPrimaryKey(DataBlock record);
}