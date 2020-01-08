package com.copower.pmcc.assess.dal.basis.dao.net;

import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordBackup;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordBackupExample;
import com.copower.pmcc.assess.dal.basis.mapper.NetInfoRecordBackupMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 方案主表
 * Created by 13426 on 2018/5/24.
 */
@Repository
public class NetInfoRecordBackupDao {
    @Autowired
    private NetInfoRecordBackupMapper netInfoRecordBackupMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public NetInfoRecordBackup getInfoById(Integer id) {
        return netInfoRecordBackupMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     *
     * @param queryTitle
     * @return
     */
    public List<NetInfoRecordBackup> getNetInfoRecordBackupListByName(String queryTitle, String queryWebName, String provinceName, String cityName, String queryContent,
                                                          String queryType, Date queryStratTime, Date queryEndTime, String executor,Integer status) {
        NetInfoRecordBackupExample example = new NetInfoRecordBackupExample();
        NetInfoRecordBackupExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(queryTitle)) {
            criteria.andTitleLike(String.format("%s%s%s", "%", queryTitle, "%"));
        }
        if (StringUtils.isNotBlank(queryWebName)) {
            criteria.andSourceSiteNameLike(String.format("%s%s%s", "%", queryWebName, "%"));
        }
        if (StringUtils.isNotBlank(provinceName)) {
            criteria.andProvinceLike(String.format("%s%s%s", "%", provinceName, "%"));
        }
        if (StringUtils.isNotBlank(executor)) {
            criteria.andExecutorLike(String.format("%s%s%s", "%", executor, "%"));
        }
        if (StringUtils.isNotBlank(cityName)) {
            criteria.andCityLike(String.format("%s%s%s", "%", cityName, "%"));
        }
        if (StringUtils.isNotBlank(queryContent)) {
            criteria.andContentLike(String.format("%s%s%s", "%", queryContent, "%"));
        }
        if (StringUtils.isNotBlank(queryType)) {
            criteria.andTypeLike(String.format("%s%s%s", "%", queryType, "%"));
        }
        if (queryStratTime != null) {
            criteria.andBeginTimeGreaterThanOrEqualTo(queryStratTime);
        }
        if (queryEndTime != null) {
            criteria.andBeginTimeLessThanOrEqualTo(queryEndTime);
        }
        if(status!=null){
            criteria.andStatusEqualTo(status);
        }
        example.setOrderByClause("id desc");
        return netInfoRecordBackupMapper.selectByExample(example);
    }

    /**
     * 获取数据
     *
     * @param netInfoRecordBackup
     * @return
     */
    public NetInfoRecordBackup getNetInfoRecordBackup(NetInfoRecordBackup netInfoRecordBackup) {
        NetInfoRecordBackupExample example = new NetInfoRecordBackupExample();
        MybatisUtils.convertObj2Example(netInfoRecordBackup, example);
        List<NetInfoRecordBackup> netInfoRecordBackups = netInfoRecordBackupMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(netInfoRecordBackups)) return netInfoRecordBackups.get(0);
        return null;
    }

    /**
     * 获取数据列表
     *
     * @param examineInfo
     * @return
     */
    public List<NetInfoRecordBackup> getInfoList(NetInfoRecordBackup examineInfo) {
        NetInfoRecordBackupExample example = new NetInfoRecordBackupExample();
        MybatisUtils.convertObj2Example(examineInfo, example);
        return netInfoRecordBackupMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param examineInfo
     * @return
     */
    public boolean addInfo(NetInfoRecordBackup examineInfo) {
        return netInfoRecordBackupMapper.insertSelective(examineInfo) > 0;
    }

    /**
     * 编辑
     *
     * @param examineInfo
     * @return
     */
    public boolean updateInfo(NetInfoRecordBackup examineInfo) {
        return netInfoRecordBackupMapper.updateByPrimaryKeySelective(examineInfo) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteInfo(Integer id) {
        return netInfoRecordBackupMapper.deleteByPrimaryKey(id) > 0;
    }


}
