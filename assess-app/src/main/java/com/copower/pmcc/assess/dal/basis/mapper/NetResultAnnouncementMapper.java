package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.NetResultAnnouncement;
import com.copower.pmcc.assess.dal.basis.entity.NetResultAnnouncementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NetResultAnnouncementMapper {
    int countByExample(NetResultAnnouncementExample example);

    int deleteByExample(NetResultAnnouncementExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NetResultAnnouncement record);

    int insertSelective(NetResultAnnouncement record);

    List<NetResultAnnouncement> selectByExample(NetResultAnnouncementExample example);

    NetResultAnnouncement selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NetResultAnnouncement record, @Param("example") NetResultAnnouncementExample example);

    int updateByExample(@Param("record") NetResultAnnouncement record, @Param("example") NetResultAnnouncementExample example);

    int updateByPrimaryKeySelective(NetResultAnnouncement record);

    int updateByPrimaryKey(NetResultAnnouncement record);
}