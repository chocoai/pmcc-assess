package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.NetAuctionAnnouncement;
import com.copower.pmcc.assess.dal.basis.entity.NetAuctionAnnouncementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NetAuctionAnnouncementMapper {
    int countByExample(NetAuctionAnnouncementExample example);

    int deleteByExample(NetAuctionAnnouncementExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NetAuctionAnnouncement record);

    int insertSelective(NetAuctionAnnouncement record);

    List<NetAuctionAnnouncement> selectByExample(NetAuctionAnnouncementExample example);

    NetAuctionAnnouncement selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NetAuctionAnnouncement record, @Param("example") NetAuctionAnnouncementExample example);

    int updateByExample(@Param("record") NetAuctionAnnouncement record, @Param("example") NetAuctionAnnouncementExample example);

    int updateByPrimaryKeySelective(NetAuctionAnnouncement record);

    int updateByPrimaryKey(NetAuctionAnnouncement record);
}