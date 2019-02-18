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

    public BasicEstateNetwork getBasicEstateNetworkById(Integer id)throws SQLException {
        return basicEstateNetworkMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicEstateNetwork(BasicEstateNetwork basicEstateNetwork)throws SQLException{
        basicEstateNetworkMapper.insertSelective(basicEstateNetwork);
        return basicEstateNetwork.getId();
    }

    public boolean updateBasicEstateNetwork(BasicEstateNetwork basicEstateNetwork)throws SQLException{
        return basicEstateNetworkMapper.updateByPrimaryKeySelective(basicEstateNetwork)==1;
    }

    public void removeBasicEstateNetwork(BasicEstateNetwork basicEstateNetwork)throws SQLException{
        BasicEstateNetworkExample example = new BasicEstateNetworkExample();
        MybatisUtils.convertObj2Example(basicEstateNetwork, example);
        basicEstateNetworkMapper.deleteByExample(example);
    }

    public boolean deleteBasicEstateNetwork(Integer id)throws SQLException{
        return  basicEstateNetworkMapper.deleteByPrimaryKey(id)==1;
    }

    public List<BasicEstateNetwork> basicEstateNetworkList(BasicEstateNetwork basicEstateNetwork){
        BasicEstateNetworkExample example = new BasicEstateNetworkExample();
        MybatisUtils.convertObj2Example(basicEstateNetwork, example);
        return basicEstateNetworkMapper.selectByExample(example);
    }
}
