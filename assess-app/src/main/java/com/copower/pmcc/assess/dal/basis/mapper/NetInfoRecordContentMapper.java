package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordContent;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NetInfoRecordContentMapper {
    int countByExample(NetInfoRecordContentExample example);

    int deleteByExample(NetInfoRecordContentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NetInfoRecordContent record);

    int insertSelective(NetInfoRecordContent record);

    List<NetInfoRecordContent> selectByExampleWithBLOBs(NetInfoRecordContentExample example);

    List<NetInfoRecordContent> selectByExample(NetInfoRecordContentExample example);

    NetInfoRecordContent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NetInfoRecordContent record, @Param("example") NetInfoRecordContentExample example);

    int updateByExampleWithBLOBs(@Param("record") NetInfoRecordContent record, @Param("example") NetInfoRecordContentExample example);

    int updateByExample(@Param("record") NetInfoRecordContent record, @Param("example") NetInfoRecordContentExample example);

    int updateByPrimaryKeySelective(NetInfoRecordContent record);

    int updateByPrimaryKeyWithBLOBs(NetInfoRecordContent record);

    int updateByPrimaryKey(NetInfoRecordContent record);
}