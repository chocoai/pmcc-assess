package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicUnitElevator;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnitElevatorExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicUnitElevatorMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/5 16:16
 * @Description:
 */
@Repository
public class BasicUnitElevatorDao {
    @Autowired
    private BasicUnitElevatorMapper basicUnitElevatorMapper;

    public BasicUnitElevator getBasicUnitElevatorById(Integer id)  {
        return basicUnitElevatorMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicUnitElevator(BasicUnitElevator basicUnitElevator)  {
        basicUnitElevatorMapper.insertSelective(basicUnitElevator);
        return basicUnitElevator.getId();
    }

    public boolean updateBasicUnitElevator(BasicUnitElevator basicUnitElevator, boolean updateNull)  {
        basicUnitElevator.setBisDelete(false);
        return updateNull ? basicUnitElevatorMapper.updateByPrimaryKey(basicUnitElevator) == 1 : basicUnitElevatorMapper.updateByPrimaryKeySelective(basicUnitElevator) == 1;
    }

    public boolean deleteBasicUnitElevator(Integer id)  {
        BasicUnitElevator basicUnitElevator = getBasicUnitElevatorById(id);
        if(basicUnitElevator==null) return false;
        basicUnitElevator.setBisDelete(true);
        return basicUnitElevatorMapper.updateByPrimaryKeySelective(basicUnitElevator) == 1;
    }

    public List<BasicUnitElevator> basicUnitElevatorList(BasicUnitElevator basicUnitElevator) {
        BasicUnitElevatorExample example = new BasicUnitElevatorExample();
        BasicUnitElevatorExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicUnitElevator, criteria);
        return basicUnitElevatorMapper.selectByExample(example);
    }
}
