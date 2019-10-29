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

    public BasicHouseRoomDecorate getBasicHouseRoomDecorateById(Integer id)  {
        return basicHouseRoomDecorateMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicHouseRoomDecorate(BasicHouseRoomDecorate basicHouseRoomDecorate) {
        basicHouseRoomDecorateMapper.insertSelective(basicHouseRoomDecorate);
        return basicHouseRoomDecorate.getId();
    }

    public boolean updateBasicHouseRoomDecorate(BasicHouseRoomDecorate basicHouseRoomDecorate, boolean updateNull)  {
        return updateNull?basicHouseRoomDecorateMapper.updateByPrimaryKey(basicHouseRoomDecorate) == 1:basicHouseRoomDecorateMapper.updateByPrimaryKeySelective(basicHouseRoomDecorate) == 1;
    }

    public boolean deleteBasicHouseRoomDecorate(Integer id) {
        return basicHouseRoomDecorateMapper.deleteByPrimaryKey(id) == 1;
    }


    public List<BasicHouseRoomDecorate> basicHouseRoomDecorateList(BasicHouseRoomDecorate basicHouseRoomDecorate)  {
        BasicHouseRoomDecorateExample example = new BasicHouseRoomDecorateExample();
        MybatisUtils.convertObj2Example(basicHouseRoomDecorate, example);
        return basicHouseRoomDecorateMapper.selectByExample(example);
    }
}
