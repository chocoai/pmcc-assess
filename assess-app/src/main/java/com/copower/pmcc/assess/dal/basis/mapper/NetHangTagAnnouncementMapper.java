package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.NetHangTagAnnouncement;
import com.copower.pmcc.assess.dal.basis.entity.NetHangTagAnnouncementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NetHangTagAnnouncementMapper {
    int countByExample(NetHangTagAnnouncementExample example);

    int deleteByExample(NetHangTagAnnouncementExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NetHangTagAnnouncement record);

    int insertSelective(NetHangTagAnnouncement record);

    List<NetHangTagAnnouncement> selectByExample(NetHangTagAnnouncementExample example);

    NetHangTagAnnouncement selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NetHangTagAnnouncement record, @Param("example") NetHangTagAnnouncementExample example);

    int updateByExample(@Param("record") NetHangTagAnnouncement record, @Param("example") NetHangTagAnnouncementExample example);

    int updateByPrimaryKeySelective(NetHangTagAnnouncement record);

    int updateByPrimaryKey(NetHangTagAnnouncement record);
}