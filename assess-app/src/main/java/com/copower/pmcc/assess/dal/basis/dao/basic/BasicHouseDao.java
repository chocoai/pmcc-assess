package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouse;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicHouseMapper;
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
public class BasicHouseDao {

    @Autowired
    private BasicHouseMapper basicHouseMapper;

    public BasicHouse getBasicHouseById(Integer id){
        return basicHouseMapper.selectByPrimaryKey(id);
    }

    public Integer addBasicHouse(BasicHouse basicHouse){
        basicHouseMapper.insertSelective(basicHouse);
        return basicHouse.getId();
    }

    public boolean updateBasicHouse(BasicHouse basicHouse){
        return basicHouseMapper.updateByPrimaryKeySelective(basicHouse)==1;
    }

    public boolean deleteBasicHouse(Integer id){
        return  basicHouseMapper.deleteByPrimaryKey(id)==1;
    }

    public List<BasicHouse> basicHouseList(BasicHouse basicHouse){
        BasicHouseExample example = new BasicHouseExample();
        MybatisUtils.convertObj2Example(basicHouse, example);
        return basicHouseMapper.selectByExample(example);
    }


}
