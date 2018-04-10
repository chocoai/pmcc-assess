package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.VwDbColumnsInfo;
import com.copower.pmcc.assess.dal.entity.VwDbColumnsInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VwDbColumnsInfoMapper {
    int countByExample(VwDbColumnsInfoExample example);

    int deleteByExample(VwDbColumnsInfoExample example);

    int insert(VwDbColumnsInfo record);

    int insertSelective(VwDbColumnsInfo record);

    List<VwDbColumnsInfo> selectByExample(VwDbColumnsInfoExample example);

    int updateByExampleSelective(@Param("record") VwDbColumnsInfo record, @Param("example") VwDbColumnsInfoExample example);

    int updateByExample(@Param("record") VwDbColumnsInfo record, @Param("example") VwDbColumnsInfoExample example);
}