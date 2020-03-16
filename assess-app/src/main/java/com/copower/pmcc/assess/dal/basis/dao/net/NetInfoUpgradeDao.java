package com.copower.pmcc.assess.dal.basis.dao.net;

import com.copower.pmcc.assess.common.enums.ProjectChangeTypeEnum;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoUpgrade;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoUpgradeExample;
import com.copower.pmcc.assess.dal.basis.mapper.NetInfoUpgradeMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author Red
 * @version 1.0
 * @date: 2018/09/03 10:29
 */

@Repository
public class NetInfoUpgradeDao {
    @Autowired
    private NetInfoUpgradeMapper netInfoUpgradeMapper;

    /**
     * @param where
     * @return
     */
    public List<NetInfoUpgrade> getNetInfoUpgrade(NetInfoUpgrade where) {
        NetInfoUpgradeExample example = new NetInfoUpgradeExample();
        MybatisUtils.convertObj2Example(where, example);
        return netInfoUpgradeMapper.selectByExample(example);
    }


    /**
     * 新增数据
     * @param record
     * @return
     */
    public boolean addNetInfoUpgrade(NetInfoUpgrade record) {
        return netInfoUpgradeMapper.insertSelective(record) == 1;
    }

    /**
     * 更新数据
     * @param record
     * @return
     */
    public boolean modifyNetInfoUpgrade(NetInfoUpgrade record) {
        return netInfoUpgradeMapper.updateByPrimaryKeySelective(record) == 1;
    }

    /**
     * 根据条件更新
     * @param record
     * @param where
     * @return
     */
    public int modifyNetInfoUpgrade(NetInfoUpgrade record, NetInfoUpgrade where) {
        NetInfoUpgradeExample example = new NetInfoUpgradeExample();
        MybatisUtils.convertObj2Example(where, example);
        return netInfoUpgradeMapper.updateByExampleSelective(record, example);
    }

    public NetInfoUpgrade getDataById(Integer id) {
        return netInfoUpgradeMapper.selectByPrimaryKey(id);
    }

    public boolean deleteData(Integer id) {
        int i = netInfoUpgradeMapper.deleteByPrimaryKey(id);
        return i > 0;
    }


    public List<NetInfoUpgrade> getNetInfoUpgradeList(Integer dataId,String type) {
        NetInfoUpgradeExample example = new NetInfoUpgradeExample();
        NetInfoUpgradeExample.Criteria criteria = example.createCriteria();
        criteria.andStatusIsNull();
        if (dataId!=null)
            criteria.andDataIdEqualTo(dataId);
        if(StringUtils.isNotEmpty(type))
            criteria.andTypeEqualTo(type);
        return netInfoUpgradeMapper.selectByExample(example);
    }
}
