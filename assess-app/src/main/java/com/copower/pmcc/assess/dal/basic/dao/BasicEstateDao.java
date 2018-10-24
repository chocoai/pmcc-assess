package com.copower.pmcc.assess.dal.basic.dao;

import com.copower.pmcc.assess.dal.basic.entity.BasicEstate;
import com.copower.pmcc.assess.dal.basic.entity.BasicEstateExample;
import com.copower.pmcc.assess.dal.basic.mapper.BasicEstateMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang.StringUtils;
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

    public Integer saveBasicEstate(BasicEstate basicEstate) throws SQLException {
        basicEstateMapper.insertSelective(basicEstate);
        return basicEstate.getId();
    }

    public boolean updateBasicEstate(BasicEstate basicEstate) throws SQLException {
        return basicEstateMapper.updateByPrimaryKeySelective(basicEstate) == 1;
    }

    public boolean deleteBasicEstate(Integer id) throws SQLException {
        return basicEstateMapper.deleteByPrimaryKey(id) == 1;
    }

    public List<BasicEstate> autoComplete(BasicEstate basicEstate)throws SQLException{
        BasicEstateExample example = new BasicEstateExample();
        if (StringUtils.isNotBlank(basicEstate.getName())) {
            BasicEstateExample.Criteria criteria = example.createCriteria();
            criteria.andIdIsNotNull();
            criteria.andNameLike(new StringBuilder("%").append(basicEstate.getName()).append("%").toString());
        }
        return basicEstateMapper.selectByExample(example);
    }

    public List<BasicEstate> basicEstateList(BasicEstate basicEstate) throws SQLException {
        BasicEstateExample example = new BasicEstateExample();
        MybatisUtils.convertObj2Example(basicEstate, example);
        return basicEstateMapper.selectByExample(example);
    }
}
