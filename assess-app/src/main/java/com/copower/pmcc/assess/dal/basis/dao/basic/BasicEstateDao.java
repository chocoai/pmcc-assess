package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstate;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicEstateMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.BeanUtils;
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

    public BasicEstate getBasicEstateById(Integer id) {
        return basicEstateMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicEstate(BasicEstate basicEstate) {
        basicEstateMapper.insertSelective(basicEstate);
        return basicEstate.getId();
    }

    public boolean updateBasicEstate(BasicEstate basicEstate, boolean updateNull) {
        return updateNull ? basicEstateMapper.updateByPrimaryKey(basicEstate) == 1 : basicEstateMapper.updateByPrimaryKeySelective(basicEstate) == 1;
    }

    public boolean deleteBasicEstate(Integer id) {
        return basicEstateMapper.deleteByPrimaryKey(id) == 1;
    }


    public List<BasicEstate> basicEstateList(BasicEstate basicEstate) {
        BasicEstateExample example = new BasicEstateExample();
        MybatisUtils.convertObj2Example(basicEstate, example);
        return basicEstateMapper.selectByExample(example);
    }
}
