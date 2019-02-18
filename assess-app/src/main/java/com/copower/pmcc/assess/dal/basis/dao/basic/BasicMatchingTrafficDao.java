package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingTraffic;
import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingTrafficExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicMatchingTrafficMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:16
 * @Description:
 */
@Repository
public class BasicMatchingTrafficDao {

    @Autowired
    private BasicMatchingTrafficMapper basicMatchingTrafficMapper;

    public BasicMatchingTraffic getBasicMatchingTrafficById(Integer id)throws SQLException {
        return basicMatchingTrafficMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicMatchingTraffic(BasicMatchingTraffic basicMatchingTraffic)throws SQLException{
        basicMatchingTrafficMapper.insertSelective(basicMatchingTraffic);
        return basicMatchingTraffic.getId();
    }

    public boolean updateBasicMatchingTraffic(BasicMatchingTraffic basicMatchingTraffic)throws SQLException{
        return basicMatchingTrafficMapper.updateByPrimaryKeySelective(basicMatchingTraffic)==1;
    }

    public void removeBasicMatchingTraffic(BasicMatchingTraffic basicMatchingTraffic)throws SQLException{
        BasicMatchingTrafficExample example = new BasicMatchingTrafficExample();
        MybatisUtils.convertObj2Example(basicMatchingTraffic, example);
        basicMatchingTrafficMapper.deleteByExample(example);
    }

    public boolean deleteBasicMatchingTraffic(Integer id)throws SQLException{
        return  basicMatchingTrafficMapper.deleteByPrimaryKey(id)==1;
    }

    public List<BasicMatchingTraffic> basicMatchingTrafficList(BasicMatchingTraffic basicMatchingTraffic){
        BasicMatchingTrafficExample example = new BasicMatchingTrafficExample();
        MybatisUtils.convertObj2Example(basicMatchingTraffic, example);
        return basicMatchingTrafficMapper.selectByExample(example);
    }

    public void remove(List<Integer> ids){
        BasicMatchingTrafficExample example = new BasicMatchingTrafficExample();
        BasicMatchingTrafficExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        basicMatchingTrafficMapper.deleteByExample(example) ;
    }
    
}
