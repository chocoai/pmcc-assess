package com.copower.pmcc.assess.dal.basis.dao.net;

import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordHouse;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordHouseExample;
import com.copower.pmcc.assess.dal.basis.mapper.NetInfoRecordHouseMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/7 09:52
 * @Description:
 */
@Repository
public class NetInfoRecordHouseDao {
    @Autowired
    private NetInfoRecordHouseMapper netInfoRecordHouseMapper;

    public Integer addNetInfoRecordHouse(NetInfoRecordHouse netInfoRecordHouse) {
        netInfoRecordHouseMapper.insertSelective(netInfoRecordHouse);
        return netInfoRecordHouse.getId();
    }

    public NetInfoRecordHouse getNetInfoRecordHouseById(Integer id) {
        return netInfoRecordHouseMapper.selectByPrimaryKey(id);
    }

    public boolean updateNetInfoRecordHouse(NetInfoRecordHouse netInfoRecordHouse) {
        return netInfoRecordHouseMapper.updateByPrimaryKeySelective(netInfoRecordHouse) == 1;
    }


    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteInfo(Integer id) {
        return netInfoRecordHouseMapper.deleteByPrimaryKey(id) > 0;
    }

    public List<NetInfoRecordHouse> getNetInfoRecordHouseList(NetInfoRecordHouse netInfoRecordHouse) {
        NetInfoRecordHouseExample example = new NetInfoRecordHouseExample();
        MybatisUtils.convertObj2Example(netInfoRecordHouse, example);
        return netInfoRecordHouseMapper.selectByExample(example);
    }

}
