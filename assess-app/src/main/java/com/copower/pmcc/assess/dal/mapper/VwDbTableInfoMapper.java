package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.VwDbTableInfo;
import com.copower.pmcc.assess.dal.entity.VwDbTableInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VwDbTableInfoMapper {
    int countByExample(VwDbTableInfoExample example);

    int deleteByExample(VwDbTableInfoExample example);

    int insert(VwDbTableInfo record);

    int insertSelective(VwDbTableInfo record);

    List<VwDbTableInfo> selectByExample(VwDbTableInfoExample example);

    int updateByExampleSelective(@Param("record") VwDbTableInfo record, @Param("example") VwDbTableInfoExample example);

    int updateByExample(@Param("record") VwDbTableInfo record, @Param("example") VwDbTableInfoExample example);
}