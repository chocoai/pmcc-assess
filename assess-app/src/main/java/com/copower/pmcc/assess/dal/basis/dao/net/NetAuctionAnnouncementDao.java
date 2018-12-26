package com.copower.pmcc.assess.dal.basis.dao.net;

import com.copower.pmcc.assess.dal.basis.entity.NetAuctionAnnouncement;
import com.copower.pmcc.assess.dal.basis.entity.NetAuctionAnnouncementExample;
import com.copower.pmcc.assess.dal.basis.mapper.NetAuctionAnnouncementMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/6/12
 * @time: 15:04
 */
@Repository
public class NetAuctionAnnouncementDao {
    @Autowired
    private NetAuctionAnnouncementMapper netAuctionAnnouncementMapper;

    public NetAuctionAnnouncement getNetAuctionAnnouncement(Integer id) {
        return netAuctionAnnouncementMapper.selectByPrimaryKey(id);
    }

    public List<NetAuctionAnnouncement> getNetAuctionAnnouncementList(Integer mainId) {
        NetAuctionAnnouncementExample example = new NetAuctionAnnouncementExample();
        example.createCriteria().andMainIdEqualTo(mainId);
        List<NetAuctionAnnouncement> netAuctionAnnouncements = netAuctionAnnouncementMapper.selectByExample(example);
        return netAuctionAnnouncements;
    }
    public List<NetAuctionAnnouncement> getNetAuctionAnnouncementList(NetAuctionAnnouncement netAuctionAnnouncement) {
        NetAuctionAnnouncementExample example = new NetAuctionAnnouncementExample();
        MybatisUtils.convertObj2Example(netAuctionAnnouncement, example);
        List<NetAuctionAnnouncement> netAuctionAnnouncements = netAuctionAnnouncementMapper.selectByExample(example);
        return netAuctionAnnouncements;
    }

    public boolean addNetAuctionAnnouncement(NetAuctionAnnouncement netAuctionAnnouncement) {
        int i = netAuctionAnnouncementMapper.insert(netAuctionAnnouncement);
        return i > 0;
    }

    public boolean editNetAuctionAnnouncement(NetAuctionAnnouncement netAuctionAnnouncement) {
        int i = netAuctionAnnouncementMapper.updateByPrimaryKeySelective(netAuctionAnnouncement);
        return i > 0;
    }

    public boolean deleteNetAuctionAnnouncement(Integer id) {
        int i = netAuctionAnnouncementMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
