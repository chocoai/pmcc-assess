package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateParking;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateParkingExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicEstateParkingMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 10:46
 * @Description:
 */
@Repository
public class BasicEstateParkingDao {
    @Autowired
    private BasicEstateParkingMapper basicEstateParkingMapper;

    public BasicEstateParking getBasicEstateParkingById(Integer id)throws SQLException {
        return basicEstateParkingMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicEstateParking(BasicEstateParking basicEstateParking)throws SQLException{
        basicEstateParkingMapper.insertSelective(basicEstateParking);
        return basicEstateParking.getId();
    }

    public boolean updateBasicEstateParking(BasicEstateParking basicEstateParking)throws SQLException{
        return basicEstateParkingMapper.updateByPrimaryKeySelective(basicEstateParking)==1;
    }

    public void removeBasicEstateParking(BasicEstateParking basicEstateParking)throws SQLException{
        BasicEstateParkingExample example = new BasicEstateParkingExample();
        MybatisUtils.convertObj2Example(basicEstateParking, example);
        basicEstateParkingMapper.deleteByExample(example);
    }

    public boolean deleteBasicEstateParking(Integer id)throws SQLException{
        return  basicEstateParkingMapper.deleteByPrimaryKey(id)==1;
    }

    public List<BasicEstateParking> basicEstateParkingList(BasicEstateParking basicEstateParking)throws SQLException{
        BasicEstateParkingExample example = new BasicEstateParkingExample();
        MybatisUtils.convertObj2Example(basicEstateParking, example);
        return basicEstateParkingMapper.selectByExample(example);
    }
}
