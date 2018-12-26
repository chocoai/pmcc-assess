package com.copower.pmcc.assess.dal.basis.dao.net;

import com.copower.pmcc.assess.dal.basis.entity.NetBeforehandAnnouncement;
import com.copower.pmcc.assess.dal.basis.entity.NetBeforehandAnnouncementExample;
import com.copower.pmcc.assess.dal.basis.mapper.NetBeforehandAnnouncementMapper;
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
public class NetBeforehandAnnouncementDao {
    @Autowired
    private NetBeforehandAnnouncementMapper netBeforehandAnnouncementMapper;

    public NetBeforehandAnnouncement getNetBeforehandAnnouncement(Integer id) {
        return netBeforehandAnnouncementMapper.selectByPrimaryKey(id);
    }

    public List<NetBeforehandAnnouncement> getNetBeforehandAnnouncementList(Integer mainId) {
        NetBeforehandAnnouncementExample example = new NetBeforehandAnnouncementExample();
        example.createCriteria().andMainIdEqualTo(mainId);
        List<NetBeforehandAnnouncement> netBeforehandAnnouncements = netBeforehandAnnouncementMapper.selectByExample(example);
        return netBeforehandAnnouncements;
    }
    public List<NetBeforehandAnnouncement> getNetBeforehandAnnouncementList(NetBeforehandAnnouncement netBeforehandAnnouncement) {
        NetBeforehandAnnouncementExample example = new NetBeforehandAnnouncementExample();
        MybatisUtils.convertObj2Example(netBeforehandAnnouncement, example);
        List<NetBeforehandAnnouncement> netBeforehandAnnouncements = netBeforehandAnnouncementMapper.selectByExample(example);
        return netBeforehandAnnouncements;
    }

    public boolean addNetBeforehandAnnouncement(NetBeforehandAnnouncement netBeforehandAnnouncement) {
        int i = netBeforehandAnnouncementMapper.insert(netBeforehandAnnouncement);
        return i > 0;
    }

    public boolean editNetBeforehandAnnouncement(NetBeforehandAnnouncement netBeforehandAnnouncement) {
        int i = netBeforehandAnnouncementMapper.updateByPrimaryKeySelective(netBeforehandAnnouncement);
        return i > 0;
    }

    public boolean deleteNetBeforehandAnnouncement(Integer id) {
        int i = netBeforehandAnnouncementMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
