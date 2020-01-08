package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordBackup;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordBackupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NetInfoRecordBackupMapper {
    int countByExample(NetInfoRecordBackupExample example);

    int deleteByExample(NetInfoRecordBackupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NetInfoRecordBackup record);

    int insertSelective(NetInfoRecordBackup record);

    List<NetInfoRecordBackup> selectByExample(NetInfoRecordBackupExample example);

    NetInfoRecordBackup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NetInfoRecordBackup record, @Param("example") NetInfoRecordBackupExample example);

    int updateByExample(@Param("record") NetInfoRecordBackup record, @Param("example") NetInfoRecordBackupExample example);

    int updateByPrimaryKeySelective(NetInfoRecordBackup record);

    int updateByPrimaryKey(NetInfoRecordBackup record);
}