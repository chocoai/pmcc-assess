package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.NetBeforehandAnnouncement;
import com.copower.pmcc.assess.dal.basis.entity.NetBeforehandAnnouncementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NetBeforehandAnnouncementMapper {
    int countByExample(NetBeforehandAnnouncementExample example);

    int deleteByExample(NetBeforehandAnnouncementExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NetBeforehandAnnouncement record);

    int insertSelective(NetBeforehandAnnouncement record);

    List<NetBeforehandAnnouncement> selectByExample(NetBeforehandAnnouncementExample example);

    NetBeforehandAnnouncement selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NetBeforehandAnnouncement record, @Param("example") NetBeforehandAnnouncementExample example);

    int updateByExample(@Param("record") NetBeforehandAnnouncement record, @Param("example") NetBeforehandAnnouncementExample example);

    int updateByPrimaryKeySelective(NetBeforehandAnnouncement record);

    int updateByPrimaryKey(NetBeforehandAnnouncement record);
}