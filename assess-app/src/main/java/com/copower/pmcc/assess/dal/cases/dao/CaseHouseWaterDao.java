package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseRoomExample;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseWater;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseWaterExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseHouseWaterMapper;
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
public class CaseHouseWaterDao {
    @Autowired
    private CaseHouseWaterMapper caseHouseWaterMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseHouseWater getHouseWaterById(Integer id) {
        return caseHouseWaterMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseHouseWater
     * @return
     */
    public List<CaseHouseWater> getHouseWaterList(CaseHouseWater caseHouseWater) {
        CaseHouseWaterExample example = new CaseHouseWaterExample();
        MybatisUtils.convertObj2Example(caseHouseWater, example);
        return caseHouseWaterMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseHouseWater
     * @return
     */
    public boolean addHouseWater(CaseHouseWater caseHouseWater) {
        return caseHouseWaterMapper.insertSelective(caseHouseWater) > 0;
    }

    /**
     * 编辑
     * @param caseHouseWater
     * @return
     */
    public boolean updateHouseWater(CaseHouseWater caseHouseWater) {
        return caseHouseWaterMapper.updateByPrimaryKeySelective(caseHouseWater) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteHouseWater(Integer id){
        return caseHouseWaterMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 获取数据条数
     * @param houseId
     * @return
     */
    public int countByHouseId(Integer houseId){
        CaseHouseWaterExample example = new CaseHouseWaterExample();
        example.createCriteria().andHouseIdEqualTo(houseId);
        return caseHouseWaterMapper.countByExample(example);
    }
}