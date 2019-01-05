package com.copower.pmcc.assess.dal.basic.dao;

import com.copower.pmcc.assess.dal.basic.entity.BasicBuilding;
import com.copower.pmcc.assess.dal.basic.entity.BasicBuildingExample;
import com.copower.pmcc.assess.dal.basic.mapper.BasicBuildingMapper;
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
public class BasicBuildingDao {

    @Autowired
    private BasicBuildingMapper basicBuildingMapper;

    public BasicBuilding getBasicBuildingById(Integer id)throws SQLException{
        return basicBuildingMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicBuilding(BasicBuilding basicBuilding)throws SQLException{
        basicBuildingMapper.insertSelective(basicBuilding);
        return basicBuilding.getId();
    }

    public boolean updateBasicBuilding(BasicBuilding basicBuilding)throws SQLException{
        return basicBuildingMapper.updateByPrimaryKeySelective(basicBuilding)==1;
    }

    public boolean deleteBasicBuilding(Integer id)throws SQLException{
        return  basicBuildingMapper.deleteByPrimaryKey(id)==1;
    }

    public List<BasicBuilding> getBasicBuildingList(BasicBuilding basicBuilding){
        BasicBuildingExample example = new BasicBuildingExample();
        MybatisUtils.convertObj2Example(basicBuilding, example);
        return basicBuildingMapper.selectByExample(example);
    }

}
