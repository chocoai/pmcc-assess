package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordLand;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordLandExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NetInfoRecordLandMapper {
    long countByExample(NetInfoRecordLandExample example);

    int deleteByExample(NetInfoRecordLandExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NetInfoRecordLand record);

    int insertSelective(NetInfoRecordLand record);

    List<NetInfoRecordLand> selectByExample(NetInfoRecordLandExample example);

    NetInfoRecordLand selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NetInfoRecordLand record, @Param("example") NetInfoRecordLandExample example);

    int updateByExample(@Param("record") NetInfoRecordLand record, @Param("example") NetInfoRecordLandExample example);

    int updateByPrimaryKeySelective(NetInfoRecordLand record);

    int updateByPrimaryKey(NetInfoRecordLand record);
}