package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseRoomDecorate;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseRoomDecorateExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicHouseRoomDecorateMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/2 09:53
 * @Description:
 */
@Repository
public class BasicHouseRoomDecorateDao {
    @Autowired
    private BasicHouseRoomDecorateMapper basicHouseRoomDecorateMapper;

    public BasicHouseRoomDecorate getBasicHouseRoomDecorateById(Integer id) throws SQLException {
        return basicHouseRoomDecorateMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicHouseRoomDecorate(BasicHouseRoomDecorate basicHouseRoomDecorate) throws SQLException {
        basicHouseRoomDecorateMapper.insertSelective(basicHouseRoomDecorate);
        return basicHouseRoomDecorate.getId();
    }

    public boolean updateBasicHouseRoomDecorate(BasicHouseRoomDecorate basicHouseRoomDecorate) throws SQLException {
        return basicHouseRoomDecorateMapper.updateByPrimaryKeySelective(basicHouseRoomDecorate) == 1;
    }

    public boolean deleteBasicHouseRoomDecorate(Integer id) throws SQLException {
        return basicHouseRoomDecorateMapper.deleteByPrimaryKey(id) == 1;
    }


    public List<BasicHouseRoomDecorate> basicHouseRoomDecorateList(BasicHouseRoomDecorate basicHouseRoomDecorate) throws SQLException {
        BasicHouseRoomDecorateExample example = new BasicHouseRoomDecorateExample();
        MybatisUtils.convertObj2Example(basicHouseRoomDecorate, example);
        return basicHouseRoomDecorateMapper.selectByExample(example);
    }
}
