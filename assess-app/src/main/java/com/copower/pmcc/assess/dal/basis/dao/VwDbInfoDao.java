package com.copower.pmcc.assess.dal.basis.dao;

import com.copower.pmcc.assess.dal.basis.entity.VwDbColumnsInfo;
import com.copower.pmcc.assess.dal.basis.entity.VwDbColumnsInfoExample;
import com.copower.pmcc.assess.dal.basis.entity.VwDbTableInfo;
import com.copower.pmcc.assess.dal.basis.entity.VwDbTableInfoExample;
import com.copower.pmcc.assess.dal.basis.mapper.VwDbColumnsInfoMapper;
import com.copower.pmcc.assess.dal.basis.mapper.VwDbTableInfoMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/12/28
 * @time: 16:07
 */
@Repository
public class VwDbInfoDao {

    @Autowired
    private VwDbTableInfoMapper vwDbTableInfoMapper;
    @Autowired
    private VwDbColumnsInfoMapper vwDbColumnsInfoMapper;

    public List<VwDbTableInfo> getTableAll() {
        VwDbTableInfoExample example = new VwDbTableInfoExample();
        return vwDbTableInfoMapper.selectByExample(example);
    }

    public List<VwDbColumnsInfo> getTableColumnsByTableName(String tableName) {
        VwDbColumnsInfoExample example = new VwDbColumnsInfoExample();
        VwDbColumnsInfoExample.Criteria criteria = example.createCriteria().andTableNameEqualTo(tableName);
        List<VwDbColumnsInfo> vwDbColumnsInfos = vwDbColumnsInfoMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(vwDbColumnsInfos)) {
            return null;
        }
        return vwDbColumnsInfos;
    }

}
