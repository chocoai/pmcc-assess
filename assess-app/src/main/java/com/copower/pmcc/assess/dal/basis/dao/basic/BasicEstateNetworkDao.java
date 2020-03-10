package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateNetwork;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateNetworkExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicEstateNetworkMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 10:42
 * @Description:
 */
@Repository
public class BasicEstateNetworkDao {
    @Autowired
    private BasicEstateNetworkMapper basicEstateNetworkMapper;

    public BasicEstateNetwork getBasicEstateNetworkById(Integer id) {
        return basicEstateNetworkMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicEstateNetwork(BasicEstateNetwork basicEstateNetwork) {
        basicEstateNetworkMapper.insertSelective(basicEstateNetwork);
        return basicEstateNetwork.getId();
    }

    public boolean updateBasicEstateNetwork(BasicEstateNetwork basicEstateNetwork, boolean updateNull) {
        return updateNull ? basicEstateNetworkMapper.updateByPrimaryKey(basicEstateNetwork) == 1 : basicEstateNetworkMapper.updateByPrimaryKeySelective(basicEstateNetwork) == 1;
    }

    public boolean deleteBasicEstateNetwork(Integer id) {
        BasicEstateNetwork basicEstateNetwork = getBasicEstateNetworkById(id);
        if (basicEstateNetwork == null) return false;
        basicEstateNetwork.setBisDelete(true);
        return basicEstateNetworkMapper.updateByPrimaryKeySelective(basicEstateNetwork) == 1;
    }

    public List<BasicEstateNetwork> basicEstateNetworkList(BasicEstateNetwork basicEstateNetwork) {
        BasicEstateNetworkExample example = new BasicEstateNetworkExample();
        BasicEstateNetworkExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicEstateNetwork, criteria);
        return basicEstateNetworkMapper.selectByExample(example);
    }
}
