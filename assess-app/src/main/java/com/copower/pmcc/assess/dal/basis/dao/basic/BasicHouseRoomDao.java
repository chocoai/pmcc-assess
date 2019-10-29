package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseRoom;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseRoomExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicHouseRoomMapper;
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

    public boolean updateBasicHouseRoom(BasicHouseRoom basicHouseRoom, boolean updateNull) throws SQLException {
        return updateNull ? basicHouseRoomMapper.updateByPrimaryKey(basicHouseRoom) == 1 : basicHouseRoomMapper.updateByPrimaryKeySelective(basicHouseRoom) == 1;
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
     *
     * @param houseId
     * @return
     */
    public int countByHouseId(Integer houseId) {
        BasicHouseRoomExample example = new BasicHouseRoomExample();
        example.createCriteria().andHouseIdEqualTo(houseId);
        return basicHouseRoomMapper.countByExample(example);
    }
}
