package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.DataLandLevel;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataLandLevelMapper {
    int countByExample(DataLandLevelExample example);

    int deleteByExample(DataLandLevelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataLandLevel record);

    int insertSelective(DataLandLevel record);

    List<DataLandLevel> selectByExample(DataLandLevelExample example);

    DataLandLevel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataLandLevel record, @Param("example") DataLandLevelExample example);

    int updateByExample(@Param("record") DataLandLevel record, @Param("example") DataLandLevelExample example);

    int updateByPrimaryKeySelective(DataLandLevel record);

    int updateByPrimaryKey(DataLandLevel record);
}