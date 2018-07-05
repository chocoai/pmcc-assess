package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.VwDbColumnsInfo;
import com.copower.pmcc.assess.dal.basis.entity.VwDbColumnsInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VwDbColumnsInfoMapper {
    int countByExample(VwDbColumnsInfoExample example);

    int deleteByExample(VwDbColumnsInfoExample example);

    int insert(VwDbColumnsInfo record);

    int insertSelective(VwDbColumnsInfo record);

    List<VwDbColumnsInfo> selectByExample(VwDbColumnsInfoExample example);

    int updateByExampleSelective(@Param("record") VwDbColumnsInfo record, @Param("example") VwDbColumnsInfoExample example);

    int updateByExample(@Param("record") VwDbColumnsInfo record, @Param("example") VwDbColumnsInfoExample example);
}