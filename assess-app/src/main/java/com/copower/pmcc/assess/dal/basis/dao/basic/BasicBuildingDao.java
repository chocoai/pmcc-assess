package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicBuilding;
import com.copower.pmcc.assess.dal.basis.entity.BasicBuildingExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicBuildingMapper;
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
public class BasicBuildingDao {

    @Autowired
    private BasicBuildingMapper basicBuildingMapper;

    public BasicBuilding getBasicBuildingById(Integer id) {
        return basicBuildingMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicBuilding(BasicBuilding basicBuilding) {
        basicBuildingMapper.insertSelective(basicBuilding);
        return basicBuilding.getId();
    }

    public boolean updateBasicBuilding(BasicBuilding basicBuilding, boolean updateNull) {
        return updateNull ? basicBuildingMapper.updateByPrimaryKey(basicBuilding) == 1 : basicBuildingMapper.updateByPrimaryKeySelective(basicBuilding) == 1;
    }

    public boolean deleteBasicBuilding(Integer id) {
        return basicBuildingMapper.deleteByPrimaryKey(id) == 1;
    }

    public List<BasicBuilding> getBasicBuildingList(BasicBuilding basicBuilding) {
        BasicBuildingExample example = new BasicBuildingExample();
        MybatisUtils.convertObj2Example(basicBuilding, example);
        return basicBuildingMapper.selectByExample(example);
    }

}
