package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicUnitHuxing;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnitHuxingExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicUnitHuxingMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/5 16:11
 * @Description:
 */
@Repository
public class BasicUnitHuxingDao {
    @Autowired
    private BasicUnitHuxingMapper basicUnitHuxingMapper;

    public BasicUnitHuxing getBasicUnitHuxingById(Integer id)throws SQLException {
        return basicUnitHuxingMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicUnitHuxing(BasicUnitHuxing basicUnitHuxing)throws SQLException{
        basicUnitHuxingMapper.insertSelective(basicUnitHuxing);
        return basicUnitHuxing.getId();
    }

    public boolean updateBasicUnitHuxing(BasicUnitHuxing basicUnitHuxing)throws SQLException{
        return basicUnitHuxingMapper.updateByPrimaryKeySelective(basicUnitHuxing)==1;
    }

    public void removeBasicUnitHuxing(BasicUnitHuxing basicUnitHuxing)throws SQLException{
        BasicUnitHuxingExample example = new BasicUnitHuxingExample();
        MybatisUtils.convertObj2Example(basicUnitHuxing, example);
        basicUnitHuxingMapper.deleteByExample(example);
    }

    public boolean deleteBasicUnitHuxing(Integer id)throws SQLException{
        return  basicUnitHuxingMapper.deleteByPrimaryKey(id)==1;
    }

    public List<BasicUnitHuxing> basicUnitHuxingList(BasicUnitHuxing basicUnitHuxing)throws SQLException{
        BasicUnitHuxingExample example = new BasicUnitHuxingExample();
        MybatisUtils.convertObj2Example(basicUnitHuxing, example);
        return basicUnitHuxingMapper.selectByExample(example);
    }
}
