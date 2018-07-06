package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseRoom;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseRoomExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseHouseRoomMapper;
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
public class CaseHouseRoomDao {
    @Autowired
    private CaseHouseRoomMapper caseHouseRoomMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseHouseRoom getHouseRoomById(Integer id) {
        return caseHouseRoomMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseHouseRoom
     * @return
     */
    public List<CaseHouseRoom> getHouseRoomList(CaseHouseRoom caseHouseRoom) {
        CaseHouseRoomExample example = new CaseHouseRoomExample();
        MybatisUtils.convertObj2Example(caseHouseRoom, example);
        return caseHouseRoomMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseHouseRoom
     * @return
     */
    public boolean addHouseRoom(CaseHouseRoom caseHouseRoom) {
        return caseHouseRoomMapper.insertSelective(caseHouseRoom) > 0;
    }

    /**
     * 编辑
     * @param caseHouseRoom
     * @return
     */
    public boolean updateHouseRoom(CaseHouseRoom caseHouseRoom) {
        return caseHouseRoomMapper.updateByPrimaryKeySelective(caseHouseRoom) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteHouseRoom(Integer id){
        return caseHouseRoomMapper.deleteByPrimaryKey(id) > 0;
    }

}