package com.copower.pmcc.assess.dal.basis.dao.net;

import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordHouse;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordHouseExample;
import com.copower.pmcc.assess.dal.basis.mapper.NetInfoRecordHouseMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang.StringUtils;
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

    public boolean updateNetInfoRecordHouse(NetInfoRecordHouse netInfoRecordHouse, boolean updateNull) {
        //return netInfoRecordHouseMapper.updateByPrimaryKeySelective(netInfoRecordHouse) == 1;
        return updateNull ? netInfoRecordHouseMapper.updateByPrimaryKey(netInfoRecordHouse) == 1 : netInfoRecordHouseMapper.updateByPrimaryKeySelective(netInfoRecordHouse) == 1;
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

    public List<NetInfoRecordHouse> getNetInfoRecordHouseList(Integer status, String province, String city, String district, String street, String name) {
        NetInfoRecordHouseExample example = new NetInfoRecordHouseExample();
        NetInfoRecordHouseExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(status);
        criteria.andBisNewestEqualTo(true);
        if (StringUtils.isNotBlank(province))
            criteria.andProvinceEqualTo(province);
        if (StringUtils.isNotBlank(city))
            criteria.andCityEqualTo(city);
        if (StringUtils.isNotBlank(district))
            criteria.andDistrictEqualTo(district);
        if (StringUtils.isNotBlank(street))
            criteria.andStreetLike(String.format("%%%s%%", street));
        if (StringUtils.isNotBlank(name))
            criteria.andNameLike(String.format("%%%s%%", name));
        return netInfoRecordHouseMapper.selectByExample(example);
    }

    public List<NetInfoRecordHouse> getHouseListByMasterIds(List<Integer> masterIds){
        NetInfoRecordHouseExample example = new NetInfoRecordHouseExample();
        NetInfoRecordHouseExample.Criteria criteria = example.createCriteria();
        criteria.andMasterIdIn(masterIds);
        return netInfoRecordHouseMapper.selectByExample(example);
    }

    public List<NetInfoRecordHouse> getHouseListByAssignTaskId(Integer assignTaskId){
        NetInfoRecordHouseExample example = new NetInfoRecordHouseExample();
        NetInfoRecordHouseExample.Criteria criteria = example.createCriteria();
        criteria.andAssignTaskIdEqualTo(assignTaskId);
        return netInfoRecordHouseMapper.selectByExample(example);
    }
}
