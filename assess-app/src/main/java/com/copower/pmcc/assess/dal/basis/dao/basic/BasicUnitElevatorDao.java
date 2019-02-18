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

    public BasicUnitElevator getBasicUnitElevatorById(Integer id) throws SQLException {
        return basicUnitElevatorMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicUnitElevator(BasicUnitElevator basicUnitElevator) throws SQLException {
        basicUnitElevatorMapper.insertSelective(basicUnitElevator);
        return basicUnitElevator.getId();
    }

    public boolean updateBasicUnitElevator(BasicUnitElevator basicUnitElevator) throws SQLException {
        return basicUnitElevatorMapper.updateByPrimaryKeySelective(basicUnitElevator) == 1;
    }

    public void removeBasicUnitElevator(BasicUnitElevator basicUnitElevator) throws SQLException {
        BasicUnitElevatorExample example = new BasicUnitElevatorExample();
        MybatisUtils.convertObj2Example(basicUnitElevator, example);
        basicUnitElevatorMapper.deleteByExample(example);
    }

    public boolean deleteBasicUnitElevator(Integer id) throws SQLException {
        return basicUnitElevatorMapper.deleteByPrimaryKey(id) == 1;
    }

    public List<BasicUnitElevator> basicUnitElevatorList(BasicUnitElevator basicUnitElevator) {
        BasicUnitElevatorExample example = new BasicUnitElevatorExample();
        MybatisUtils.convertObj2Example(basicUnitElevator, example);
        return basicUnitElevatorMapper.selectByExample(example);
    }
}
