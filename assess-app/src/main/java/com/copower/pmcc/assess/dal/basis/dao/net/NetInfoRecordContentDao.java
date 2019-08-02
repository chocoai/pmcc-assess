package com.copower.pmcc.assess.dal.basis.dao.net;

import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordContent;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordContentExample;
import com.copower.pmcc.assess.dal.basis.mapper.NetInfoRecordContentMapper;
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
public class NetInfoRecordContentDao {
    @Autowired
    private NetInfoRecordContentMapper netInfoRecordContentMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public NetInfoRecordContent getInfoById(Integer id) {
        return netInfoRecordContentMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据
     *
     * @param netInfoRecordContent
     * @return
     */
    public NetInfoRecordContent getNetInfoRecordContent(NetInfoRecordContent netInfoRecordContent) {
        NetInfoRecordContentExample example = new NetInfoRecordContentExample();
        MybatisUtils.convertObj2Example(netInfoRecordContent, example);
        List<NetInfoRecordContent> netInfoRecordContents = netInfoRecordContentMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(netInfoRecordContents)) return netInfoRecordContents.get(0);
        return null;
    }

    /**
     * 获取数据列表
     *
     * @param examineInfo
     * @return
     */
    public List<NetInfoRecordContent> getInfoList(NetInfoRecordContent examineInfo) {
        NetInfoRecordContentExample example = new NetInfoRecordContentExample();
        MybatisUtils.convertObj2Example(examineInfo, example);
        return netInfoRecordContentMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param examineInfo
     * @return
     */
    public boolean addInfo(NetInfoRecordContent examineInfo) {
        return netInfoRecordContentMapper.insertSelective(examineInfo) > 0;
    }

    /**
     * 编辑
     *
     * @param examineInfo
     * @return
     */
    public boolean updateInfo(NetInfoRecordContent examineInfo) {
        return netInfoRecordContentMapper.updateByPrimaryKeySelective(examineInfo) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteInfo(Integer id) {
        return netInfoRecordContentMapper.deleteByPrimaryKey(id) > 0;
    }


}
