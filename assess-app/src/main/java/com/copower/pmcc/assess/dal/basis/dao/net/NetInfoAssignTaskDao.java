package com.copower.pmcc.assess.dal.basis.dao.net;

import com.copower.pmcc.assess.common.enums.ProjectChangeTypeEnum;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoAssignTask;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoAssignTaskExample;
import com.copower.pmcc.assess.dal.basis.mapper.NetInfoAssignTaskMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
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
public class NetInfoAssignTaskDao {
    @Autowired
    private NetInfoAssignTaskMapper netInfoAssignTaskMapper;

    /**
     * @param where
     * @return
     */
    public List<NetInfoAssignTask> getNetInfoAssignTask(NetInfoAssignTask where) {
        NetInfoAssignTaskExample example = new NetInfoAssignTaskExample();
        MybatisUtils.convertObj2Example(where, example);
        return netInfoAssignTaskMapper.selectByExample(example);
    }

    /**
     * 新增数据
     *
     * @param record
     * @return
     */
    public boolean addNetInfoAssignTask(NetInfoAssignTask record) {
        return netInfoAssignTaskMapper.insertSelective(record) == 1;
    }

    /**
     * 更新数据
     *
     * @param record
     * @return
     */
    public boolean modifyNetInfoAssignTask(NetInfoAssignTask record) {
        return netInfoAssignTaskMapper.updateByPrimaryKeySelective(record) == 1;
    }

    /**
     * 根据条件更新
     *
     * @param record
     * @param where
     * @return
     */
    public int modifyNetInfoAssignTask(NetInfoAssignTask record, NetInfoAssignTask where) {
        NetInfoAssignTaskExample example = new NetInfoAssignTaskExample();
        MybatisUtils.convertObj2Example(where, example);
        return netInfoAssignTaskMapper.updateByExampleSelective(record, example);
    }

    public NetInfoAssignTask getDataById(Integer id) {
        return netInfoAssignTaskMapper.selectByPrimaryKey(id);
    }

    public boolean deleteData(Integer id) {
        int i = netInfoAssignTaskMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
