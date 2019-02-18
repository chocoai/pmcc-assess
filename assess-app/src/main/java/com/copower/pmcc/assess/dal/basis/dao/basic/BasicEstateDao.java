package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstate;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicEstateMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:21
 * @Description:案例基础数据
 */
@Repository
public class BasicEstateDao {

    @Autowired
    private BasicEstateMapper basicEstateMapper;

    public BasicEstate getBasicEstateById(Integer id) throws SQLException {
        return basicEstateMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicEstate(BasicEstate basicEstate) throws SQLException {
        basicEstateMapper.insertSelective(basicEstate);
        return basicEstate.getId();
    }

    public boolean updateBasicEstate(BasicEstate basicEstate) throws SQLException {
        return basicEstateMapper.updateByPrimaryKeySelective(basicEstate) == 1;
    }

    public boolean deleteBasicEstate(Integer id) throws SQLException {
        return basicEstateMapper.deleteByPrimaryKey(id) == 1;
    }


    public List<BasicEstate> basicEstateList(BasicEstate basicEstate) throws SQLException {
        BasicEstateExample example = new BasicEstateExample();
        MybatisUtils.convertObj2Example(basicEstate, example);
        return basicEstateMapper.selectByExample(example);
    }
}
