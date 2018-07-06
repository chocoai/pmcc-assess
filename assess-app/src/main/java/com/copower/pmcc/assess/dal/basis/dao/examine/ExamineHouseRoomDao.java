package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseRoom;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseRoomExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineHouseRoomMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: wangpc
 * @data: 2018/07/06
 * @time: 14:36
 */
@Repository
public class ExamineHouseRoomDao {
    @Autowired
    private ExamineHouseRoomMapper examineHouseRoomMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineHouseRoom getHouseRoomById(Integer id) {
        return examineHouseRoomMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineHouseRoom
     * @return
     */
    public List<ExamineHouseRoom> getHouseRoomList(ExamineHouseRoom examineHouseRoom) {
        ExamineHouseRoomExample example = new ExamineHouseRoomExample();
        MybatisUtils.convertObj2Example(examineHouseRoom, example);
        return examineHouseRoomMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineHouseRoom
     * @return
     */
    public boolean addHouseRoom(ExamineHouseRoom examineHouseRoom) {
        return examineHouseRoomMapper.insertSelective(examineHouseRoom) > 0;
    }

    /**
     * 编辑
     * @param examineHouseRoom
     * @return
     */
    public boolean updateHouseRoom(ExamineHouseRoom examineHouseRoom) {
        return examineHouseRoomMapper.updateByPrimaryKeySelective(examineHouseRoom) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteHouseRoom(Integer id){
        return examineHouseRoomMapper.deleteByPrimaryKey(id) > 0;
    }

}