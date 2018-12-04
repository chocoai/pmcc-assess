package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataPosition;
import com.copower.pmcc.assess.dal.basis.entity.DataPositionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataPositionMapper {
    int countByExample(DataPositionExample example);

    int deleteByExample(DataPositionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataPosition record);

    int insertSelective(DataPosition record);

    List<DataPosition> selectByExample(DataPositionExample example);

    DataPosition selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataPosition record, @Param("example") DataPositionExample example);

    int updateByExample(@Param("record") DataPosition record, @Param("example") DataPositionExample example);

    int updateByPrimaryKeySelective(DataPosition record);

    int updateByPrimaryKey(DataPosition record);
}