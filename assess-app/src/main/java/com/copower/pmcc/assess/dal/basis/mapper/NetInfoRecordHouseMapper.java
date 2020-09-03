package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordHouse;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordHouseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NetInfoRecordHouseMapper {
    long countByExample(NetInfoRecordHouseExample example);

    int deleteByExample(NetInfoRecordHouseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NetInfoRecordHouse record);

    int insertSelective(NetInfoRecordHouse record);

    List<NetInfoRecordHouse> selectByExample(NetInfoRecordHouseExample example);

    NetInfoRecordHouse selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NetInfoRecordHouse record, @Param("example") NetInfoRecordHouseExample example);

    int updateByExample(@Param("record") NetInfoRecordHouse record, @Param("example") NetInfoRecordHouseExample example);

    int updateByPrimaryKeySelective(NetInfoRecordHouse record);

    int updateByPrimaryKey(NetInfoRecordHouse record);
}