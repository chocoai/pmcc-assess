package com.copower.pmcc.assess.dal.basis.dao.net;

import com.copower.pmcc.assess.dal.basis.entity.NetAuctionInfo;
import com.copower.pmcc.assess.dal.basis.entity.NetAuctionInfoExample;
import com.copower.pmcc.assess.dal.basis.mapper.NetAuctionInfoMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 方案主表
 * Created by 13426 on 2018/5/24.
 */
@Repository
public class NetAuctionInfoDao {
    @Autowired
    private NetAuctionInfoMapper netAuctionInfoMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public NetAuctionInfo getInfoById(Integer id) {
        return netAuctionInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     *
     * @param queryTitle
     * @return
     */
    public List<NetAuctionInfo> getNetAuctionInfoListByName(String queryTitle) {
        NetAuctionInfoExample example = new NetAuctionInfoExample();
        NetAuctionInfoExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(queryTitle)) {
            criteria.andTitleLike(String.format("%s%s%s", "%", queryTitle, "%"));
        }

        example.setOrderByClause("id desc");
        return netAuctionInfoMapper.selectByExample(example);
    }
    
    /**
     * 获取数据
     *
     * @param netAuctionInfo
     * @return
     */
    public NetAuctionInfo getNetAuctionInfo(NetAuctionInfo netAuctionInfo) {
        NetAuctionInfoExample example = new NetAuctionInfoExample();
        MybatisUtils.convertObj2Example(netAuctionInfo, example);
        List<NetAuctionInfo> netAuctionInfos = netAuctionInfoMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(netAuctionInfos)) return netAuctionInfos.get(0);
        return null;
    }

    /**
     * 获取数据列表
     *
     * @param examineInfo
     * @return
     */
    public List<NetAuctionInfo> getInfoList(NetAuctionInfo examineInfo) {
        NetAuctionInfoExample example = new NetAuctionInfoExample();
        MybatisUtils.convertObj2Example(examineInfo, example);
        return netAuctionInfoMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param examineInfo
     * @return
     */
    public boolean addInfo(NetAuctionInfo examineInfo) {
        return netAuctionInfoMapper.insertSelective(examineInfo) > 0;
    }

    /**
     * 编辑
     *
     * @param examineInfo
     * @return
     */
    public boolean updateInfo(NetAuctionInfo examineInfo) {
        return netAuctionInfoMapper.updateByPrimaryKeySelective(examineInfo) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteInfo(Integer id) {
        return netAuctionInfoMapper.deleteByPrimaryKey(id) > 0;
    }


}
