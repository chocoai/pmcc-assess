package com.copower.pmcc.assess.dal.basis.dao.net;

import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecord;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordExample;
import com.copower.pmcc.assess.dal.basis.mapper.NetInfoRecordMapper;
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
public class NetInfoRecordDao {
    @Autowired
    private NetInfoRecordMapper netInfoRecordMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public NetInfoRecord getInfoById(Integer id) {
        return netInfoRecordMapper.selectByPrimaryKey(id);
    }

    public List<NetInfoRecord> getInfoByIds(List<Integer> ids) {
        NetInfoRecordExample example = new NetInfoRecordExample();
        NetInfoRecordExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids) ;
        return netInfoRecordMapper.selectByExample(example) ;
    }

    public void batchNetInfoRecord(NetInfoRecord obj,List<Integer> ids){
        NetInfoRecordExample example = new NetInfoRecordExample();
        NetInfoRecordExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids) ;
        netInfoRecordMapper.updateByExampleSelective(obj,example) ;
    }

    /**
     * 获取数据列表
     *
     * @param queryTitle
     * @return
     */
    public List<NetInfoRecord> getNetInfoRecordListByName(String queryTitle, String queryWebName, String provinceName, String cityName, String queryContent,
                                                          String queryType, Date queryStratTime, Date queryEndTime, String executor, Integer status) {
        NetInfoRecordExample example = new NetInfoRecordExample();
        NetInfoRecordExample.Criteria criteria = example.createCriteria();
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
        if (status != null) {
            criteria.andStatusEqualTo(status);
        }
        criteria.andBisDeleteEqualTo(false);
        example.setOrderByClause("id desc");
        return netInfoRecordMapper.selectByExample(example);
    }

    /**
     * 获取数据
     *
     * @param netInfoRecord
     * @return
     */
    public NetInfoRecord getNetInfoRecord(NetInfoRecord netInfoRecord) {
        NetInfoRecordExample example = new NetInfoRecordExample();
        MybatisUtils.convertObj2Example(netInfoRecord, example);
        List<NetInfoRecord> netInfoRecords = netInfoRecordMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(netInfoRecords)) return netInfoRecords.get(0);
        return null;
    }

    /**
     * 获取数据列表
     *
     * @param examineInfo
     * @return
     */
    public List<NetInfoRecord> getInfoList(NetInfoRecord examineInfo) {
        NetInfoRecordExample example = new NetInfoRecordExample();
        MybatisUtils.convertObj2Example(examineInfo, example);
        return netInfoRecordMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param netInfoRecord
     * @return
     */
    public boolean addInfo(NetInfoRecord netInfoRecord) {
        //验证重复 重复数据自动设置为删除状态
        NetInfoRecordExample example = new NetInfoRecordExample();
        NetInfoRecordExample.Criteria criteria = example.createCriteria();
        criteria.andProvinceEqualTo(netInfoRecord.getProvince())
                .andTitleEqualTo(netInfoRecord.getTitle()).andSourceSiteUrlEqualTo(netInfoRecord.getSourceSiteUrl());
        if (netInfoRecordMapper.countByExample(example) <= 0) {
            return netInfoRecordMapper.insertSelective(netInfoRecord) > 0;
        }
        return true;
    }

    /**
     * 编辑
     *
     * @param examineInfo
     * @return
     */
    public boolean updateInfo(NetInfoRecord examineInfo) {
        return netInfoRecordMapper.updateByPrimaryKeySelective(examineInfo) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteInfo(Integer id) {
        return netInfoRecordMapper.deleteByPrimaryKey(id) > 0;
    }


}
