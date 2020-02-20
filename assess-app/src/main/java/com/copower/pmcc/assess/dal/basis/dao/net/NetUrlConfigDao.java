package com.copower.pmcc.assess.dal.basis.dao.net;

import com.copower.pmcc.assess.dal.basis.entity.NetUrlConfig;
import com.copower.pmcc.assess.dal.basis.entity.NetUrlConfigExample;
import com.copower.pmcc.assess.dal.basis.mapper.NetUrlConfigMapper;
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
public class NetUrlConfigDao {
    @Autowired
    private NetUrlConfigMapper netUrlConfigMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public NetUrlConfig getNetUrlConfigById(Integer id) {
        return netUrlConfigMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取有效数据
     *
     * @return
     */
    public List<NetUrlConfig> getEnableNetUrlConfigList() {
        NetUrlConfigExample example = new NetUrlConfigExample();
        example.createCriteria().andBisEnableEqualTo(true);
        List<NetUrlConfig> netUrlConfigs = netUrlConfigMapper.selectByExample(example);
        return netUrlConfigs;
    }

    public NetUrlConfig getNetUrlConfigByUrl(String url) {
        NetUrlConfigExample example = new NetUrlConfigExample();
        example.createCriteria().andBisEnableEqualTo(true).andUrlEqualTo(url);
        List<NetUrlConfig> netUrlConfigs = netUrlConfigMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(netUrlConfigs)) return null;
        return netUrlConfigs.get(0);
    }
}
