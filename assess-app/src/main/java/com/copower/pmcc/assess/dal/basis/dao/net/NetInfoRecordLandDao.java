package com.copower.pmcc.assess.dal.basis.dao.net;

import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordLand;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordLandExample;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordLand;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordLandExample;
import com.copower.pmcc.assess.dal.basis.mapper.NetInfoRecordLandMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/7 09:52
 * @Description:
 */
@Repository
public class NetInfoRecordLandDao {
    @Autowired
    private NetInfoRecordLandMapper netInfoRecordLandMapper;

    public Integer addNetInfoRecordLand(NetInfoRecordLand netInfoRecordLand) {
        netInfoRecordLandMapper.insertSelective(netInfoRecordLand);
        return netInfoRecordLand.getId();
    }

    public NetInfoRecordLand getNetInfoRecordLandById(Integer id) {
        return netInfoRecordLandMapper.selectByPrimaryKey(id);
    }

    public boolean updateNetInfoRecordLand(NetInfoRecordLand netInfoRecordLand) {
        return netInfoRecordLandMapper.updateByPrimaryKeySelective(netInfoRecordLand) == 1;
    }


    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteInfo(Integer id) {
        return netInfoRecordLandMapper.deleteByPrimaryKey(id) > 0;
    }

    public List<NetInfoRecordLand> getNetInfoRecordLandList(NetInfoRecordLand netInfoRecordLand) {
        NetInfoRecordLandExample example = new NetInfoRecordLandExample();
        MybatisUtils.convertObj2Example(netInfoRecordLand, example);
        return netInfoRecordLandMapper.selectByExample(example);
    }

    public List<NetInfoRecordLand> getNetInfoRecordLandList(Integer status, String province, String city, String district, String street, String name) {
        NetInfoRecordLandExample example = new NetInfoRecordLandExample();
        NetInfoRecordLandExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(status);
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
        return netInfoRecordLandMapper.selectByExample(example);
    }

    public List<NetInfoRecordLand> getLandListByMasterIds(List<Integer> masterIds){
        NetInfoRecordLandExample example = new NetInfoRecordLandExample();
        NetInfoRecordLandExample.Criteria criteria = example.createCriteria();
        criteria.andMasterIdIn(masterIds);
        return netInfoRecordLandMapper.selectByExample(example);
    }

}
