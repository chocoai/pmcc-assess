package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseRoomDecorate;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseRoomDecorateExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseHouseRoomDecorateMapper;
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
public class CaseHouseRoomDecorateDao {
    @Autowired
    private CaseHouseRoomDecorateMapper caseHouseRoomDecorateMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseHouseRoomDecorate getHouseRoomDecorateById(Integer id) {
        return caseHouseRoomDecorateMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseHouseRoomDecorate
     * @return
     */
    public List<CaseHouseRoomDecorate> getHouseRoomDecorateList(CaseHouseRoomDecorate caseHouseRoomDecorate) {
        CaseHouseRoomDecorateExample example = new CaseHouseRoomDecorateExample();
        MybatisUtils.convertObj2Example(caseHouseRoomDecorate, example);
        return caseHouseRoomDecorateMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseHouseRoomDecorate
     * @return
     */
    public boolean addHouseRoomDecorate(CaseHouseRoomDecorate caseHouseRoomDecorate) {
        return caseHouseRoomDecorateMapper.insertSelective(caseHouseRoomDecorate) > 0;
    }

    /**
     * 编辑
     * @param caseHouseRoomDecorate
     * @return
     */
    public boolean updateHouseRoomDecorate(CaseHouseRoomDecorate caseHouseRoomDecorate) {
        return caseHouseRoomDecorateMapper.updateByPrimaryKeySelective(caseHouseRoomDecorate) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteHouseRoomDecorate(Integer id){
        return caseHouseRoomDecorateMapper.deleteByPrimaryKey(id) > 0;
    }

}