package com.copower.pmcc.assess.dal.basis.dao.net;

import com.copower.pmcc.assess.dal.basis.entity.NetHangTagAnnouncement;
import com.copower.pmcc.assess.dal.basis.entity.NetHangTagAnnouncementExample;
import com.copower.pmcc.assess.dal.basis.mapper.NetHangTagAnnouncementMapper;
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
public class NetHangTagAnnouncementDao {
    @Autowired
    private NetHangTagAnnouncementMapper netHangTagAnnouncementMapper;

    public NetHangTagAnnouncement getNetHangTagAnnouncement(Integer id) {
        return netHangTagAnnouncementMapper.selectByPrimaryKey(id);
    }

    public List<NetHangTagAnnouncement> getNetHangTagAnnouncementList(Integer mainId) {
        NetHangTagAnnouncementExample example = new NetHangTagAnnouncementExample();
        example.createCriteria().andMainIdEqualTo(mainId);
        List<NetHangTagAnnouncement> netHangTagAnnouncements = netHangTagAnnouncementMapper.selectByExample(example);
        return netHangTagAnnouncements;
    }
    public List<NetHangTagAnnouncement> getNetHangTagAnnouncementList(NetHangTagAnnouncement netHangTagAnnouncement) {
        NetHangTagAnnouncementExample example = new NetHangTagAnnouncementExample();
        MybatisUtils.convertObj2Example(netHangTagAnnouncement, example);
        List<NetHangTagAnnouncement> netHangTagAnnouncements = netHangTagAnnouncementMapper.selectByExample(example);
        return netHangTagAnnouncements;
    }

    public boolean addNetHangTagAnnouncement(NetHangTagAnnouncement netHangTagAnnouncement) {
        int i = netHangTagAnnouncementMapper.insert(netHangTagAnnouncement);
        return i > 0;
    }

    public boolean editNetHangTagAnnouncement(NetHangTagAnnouncement netHangTagAnnouncement) {
        int i = netHangTagAnnouncementMapper.updateByPrimaryKeySelective(netHangTagAnnouncement);
        return i > 0;
    }

    public boolean deleteNetHangTagAnnouncement(Integer id) {
        int i = netHangTagAnnouncementMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
