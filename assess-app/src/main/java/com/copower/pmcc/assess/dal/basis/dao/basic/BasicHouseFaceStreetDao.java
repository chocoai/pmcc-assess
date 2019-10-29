package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseFaceStreet;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseFaceStreetExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicHouseFaceStreetMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:26
 * @Description:
 */
@Repository
public class BasicHouseFaceStreetDao {

    @Autowired
    private BasicHouseFaceStreetMapper basicHouseFaceStreetMapper;

    public BasicHouseFaceStreet getBasicHouseFaceStreetById(Integer id) throws SQLException {
        return basicHouseFaceStreetMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicHouseFaceStreet(BasicHouseFaceStreet basicHouseFaceStreet) throws SQLException {
        basicHouseFaceStreetMapper.insertSelective(basicHouseFaceStreet);
        return basicHouseFaceStreet.getId();
    }

    public boolean updateBasicHouseFaceStreet(BasicHouseFaceStreet basicHouseFaceStreet, boolean updateNull) throws SQLException {
        return updateNull ? basicHouseFaceStreetMapper.updateByPrimaryKey(basicHouseFaceStreet) == 1 : basicHouseFaceStreetMapper.updateByPrimaryKeySelective(basicHouseFaceStreet) == 1;
    }

    public boolean deleteBasicHouseFaceStreet(BasicHouseFaceStreet basicHouseFaceStreet) throws SQLException {
        BasicHouseFaceStreetExample example = new BasicHouseFaceStreetExample();
        MybatisUtils.convertObj2Example(basicHouseFaceStreet, example);
        return basicHouseFaceStreetMapper.deleteByExample(example) > 0;
    }

    public boolean deleteBasicHouseFaceStreet(Integer id) throws SQLException {
        return basicHouseFaceStreetMapper.deleteByPrimaryKey(id) == 1;
    }

    public List<BasicHouseFaceStreet> basicHouseFaceStreetList(BasicHouseFaceStreet basicHouseFaceStreet) {
        BasicHouseFaceStreetExample example = new BasicHouseFaceStreetExample();
        MybatisUtils.convertObj2Example(basicHouseFaceStreet, example);
        return basicHouseFaceStreetMapper.selectByExample(example);
    }

    /**
     * 获取数据条数
     *
     * @param houseId
     * @return
     */
    public int countByHouseId(Integer houseId) {
        BasicHouseFaceStreetExample example = new BasicHouseFaceStreetExample();
        example.createCriteria().andHouseIdEqualTo(houseId);
        return basicHouseFaceStreetMapper.countByExample(example);
    }
}
