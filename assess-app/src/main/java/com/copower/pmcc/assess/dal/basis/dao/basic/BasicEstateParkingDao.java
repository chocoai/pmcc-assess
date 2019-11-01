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

    public BasicEstateParking getBasicEstateParkingById(Integer id) {
        return basicEstateParkingMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicEstateParking(BasicEstateParking basicEstateParking) {
        basicEstateParkingMapper.insertSelective(basicEstateParking);
        return basicEstateParking.getId();
    }

    public boolean updateBasicEstateParking(BasicEstateParking basicEstateParking, boolean updateNull) {
        return updateNull ? basicEstateParkingMapper.updateByPrimaryKey(basicEstateParking) == 1 : basicEstateParkingMapper.updateByPrimaryKeySelective(basicEstateParking) == 1;
    }

    public boolean deleteBasicEstateParking(Integer id) {
        BasicEstateParking basicEstateParking = getBasicEstateParkingById(id);
        if (basicEstateParking == null) return false;
        return basicEstateParkingMapper.updateByPrimaryKeySelective(basicEstateParking) == 1;
    }

    public List<BasicEstateParking> basicEstateParkingList(BasicEstateParking basicEstateParking) {
        BasicEstateParkingExample example = new BasicEstateParkingExample();
        BasicEstateParkingExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Example(basicEstateParking, criteria);
        return basicEstateParkingMapper.selectByExample(example);
    }
}
