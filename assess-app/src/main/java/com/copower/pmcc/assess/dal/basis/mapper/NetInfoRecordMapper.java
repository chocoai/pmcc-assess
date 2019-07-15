package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecord;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NetInfoRecordMapper {
    int countByExample(NetInfoRecordExample example);

    int deleteByExample(NetInfoRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NetInfoRecord record);

    int insertSelective(NetInfoRecord record);

    List<NetInfoRecord> selectByExample(NetInfoRecordExample example);

    NetInfoRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NetInfoRecord record, @Param("example") NetInfoRecordExample example);

    int updateByExample(@Param("record") NetInfoRecord record, @Param("example") NetInfoRecordExample example);

    int updateByPrimaryKeySelective(NetInfoRecord record);

    int updateByPrimaryKey(NetInfoRecord record);
}