package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.VwDbTableInfo;
import com.copower.pmcc.assess.dal.basis.entity.VwDbTableInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VwDbTableInfoMapper {
    int countByExample(VwDbTableInfoExample example);

    int deleteByExample(VwDbTableInfoExample example);

    int insert(VwDbTableInfo record);

    int insertSelective(VwDbTableInfo record);

    List<VwDbTableInfo> selectByExample(VwDbTableInfoExample example);

    int updateByExampleSelective(@Param("record") VwDbTableInfo record, @Param("example") VwDbTableInfoExample example);

    int updateByExample(@Param("record") VwDbTableInfo record, @Param("example") VwDbTableInfoExample example);
}