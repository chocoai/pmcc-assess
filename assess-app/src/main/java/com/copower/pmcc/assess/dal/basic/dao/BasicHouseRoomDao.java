package com.copower.pmcc.assess.dal.basic.dao;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouseRoom;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouseRoomExample;
import com.copower.pmcc.assess.dal.basic.mapper.BasicHouseRoomMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/2 09:50
 * @Description:
 */
@Repository
public class BasicHouseRoomDao {
    @Autowired
    private BasicHouseRoomMapper basicHouseRoomMapper;

    public BasicHouseRoom getBasicHouseRoomById(Integer id) throws SQLException {
        return basicHouseRoomMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicHouseRoom(BasicHouseRoom basicHouseRoom) throws SQLException {
        basicHouseRoomMapper.insertSelective(basicHouseRoom);
        return basicHouseRoom.getId();
    }

    public boolean updateBasicHouseRoom(BasicHouseRoom basicHouseRoom) throws SQLException {
        return basicHouseRoomMapper.updateByPrimaryKeySelective(basicHouseRoom) == 1;
    }

    public boolean deleteBasicHouseRoom(Integer id) throws SQLException {
        return basicHouseRoomMapper.deleteByPrimaryKey(id) == 1;
    }


    public List<BasicHouseRoom> basicHouseRoomList(BasicHouseRoom basicHouseRoom) {
        BasicHouseRoomExample example = new BasicHouseRoomExample();
        MybatisUtils.convertObj2Example(basicHouseRoom, example);
        return basicHouseRoomMapper.selectByExample(example);
    }

    /**
     * 获取数据条数
     * @param houseId
     * @return
     */
    public int countByHouseId(Integer houseId){
        BasicHouseRoomExample example = new BasicHouseRoomExample();
        example.createCriteria().andHouseIdEqualTo(houseId);
        return basicHouseRoomMapper.countByExample(example);
    }
}
