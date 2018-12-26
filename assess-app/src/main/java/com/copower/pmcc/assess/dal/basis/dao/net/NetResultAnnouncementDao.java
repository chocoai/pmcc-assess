package com.copower.pmcc.assess.dal.basis.dao.net;

import com.copower.pmcc.assess.dal.basis.entity.NetResultAnnouncement;
import com.copower.pmcc.assess.dal.basis.entity.NetResultAnnouncementExample;
import com.copower.pmcc.assess.dal.basis.mapper.NetResultAnnouncementMapper;
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
public class NetResultAnnouncementDao {
    @Autowired
    private NetResultAnnouncementMapper netResultAnnouncementMapper;

    public NetResultAnnouncement getNetResultAnnouncement(Integer id) {
        return netResultAnnouncementMapper.selectByPrimaryKey(id);
    }

    public List<NetResultAnnouncement> getNetResultAnnouncementList(Integer mainId) {
        NetResultAnnouncementExample example = new NetResultAnnouncementExample();
        example.createCriteria().andMainIdEqualTo(mainId);
        List<NetResultAnnouncement> netResultAnnouncements = netResultAnnouncementMapper.selectByExample(example);
        return netResultAnnouncements;
    }
    public List<NetResultAnnouncement> getNetResultAnnouncementList(NetResultAnnouncement netResultAnnouncement) {
        NetResultAnnouncementExample example = new NetResultAnnouncementExample();
        MybatisUtils.convertObj2Example(netResultAnnouncement, example);
        List<NetResultAnnouncement> netResultAnnouncements = netResultAnnouncementMapper.selectByExample(example);
        return netResultAnnouncements;
    }

    public boolean addNetResultAnnouncement(NetResultAnnouncement netResultAnnouncement) {
        int i = netResultAnnouncementMapper.insert(netResultAnnouncement);
        return i > 0;
    }

    public boolean editNetResultAnnouncement(NetResultAnnouncement netResultAnnouncement) {
        int i = netResultAnnouncementMapper.updateByPrimaryKeySelective(netResultAnnouncement);
        return i > 0;
    }

    public boolean deleteNetResultAnnouncement(Integer id) {
        int i = netResultAnnouncementMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
