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

    int insertSelective(@Param("record") NetInfoRecordLand record, @Param("selective") NetInfoRecordLand.Column ... selective);

    List<NetInfoRecordLand> selectByExample(NetInfoRecordLandExample example);

    NetInfoRecordLand selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NetInfoRecordLand record, @Param("example") NetInfoRecordLandExample example, @Param("selective") NetInfoRecordLand.Column ... selective);

    int updateByExample(@Param("record") NetInfoRecordLand record, @Param("example") NetInfoRecordLandExample example);

    int updateByPrimaryKeySelective(@Param("record") NetInfoRecordLand record, @Param("selective") NetInfoRecordLand.Column ... selective);

    int updateByPrimaryKey(NetInfoRecordLand record);

    int batchInsert(@Param("list") List<NetInfoRecordLand> list);

    int batchInsertSelective(@Param("list") List<NetInfoRecordLand> list, @Param("selective") NetInfoRecordLand.Column ... selective);
}