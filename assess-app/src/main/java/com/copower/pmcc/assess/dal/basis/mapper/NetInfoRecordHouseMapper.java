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

    int insertSelective(@Param("record") NetInfoRecordHouse record, @Param("selective") NetInfoRecordHouse.Column ... selective);

    List<NetInfoRecordHouse> selectByExample(NetInfoRecordHouseExample example);

    NetInfoRecordHouse selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NetInfoRecordHouse record, @Param("example") NetInfoRecordHouseExample example, @Param("selective") NetInfoRecordHouse.Column ... selective);

    int updateByExample(@Param("record") NetInfoRecordHouse record, @Param("example") NetInfoRecordHouseExample example);

    int updateByPrimaryKeySelective(@Param("record") NetInfoRecordHouse record, @Param("selective") NetInfoRecordHouse.Column ... selective);

    int updateByPrimaryKey(NetInfoRecordHouse record);

    int batchInsert(@Param("list") List<NetInfoRecordHouse> list);

    int batchInsertSelective(@Param("list") List<NetInfoRecordHouse> list, @Param("selective") NetInfoRecordHouse.Column ... selective);
}