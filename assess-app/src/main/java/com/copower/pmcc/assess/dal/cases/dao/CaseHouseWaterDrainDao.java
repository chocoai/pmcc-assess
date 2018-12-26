package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseWaterDrain;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseWaterDrainExample;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseWaterExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseHouseWaterDrainMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/12/11 11:46
 * @Description:
 */
@Repository
public class CaseHouseWaterDrainDao {

    @Autowired
    private CaseHouseWaterDrainMapper caseHouseWaterDrainMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseHouseWaterDrain getBuildingOutfitById(Integer id) {
        return caseHouseWaterDrainMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseHouseWaterDrain
     * @return
     */
    public List<CaseHouseWaterDrain> getBuildingOutfitList(CaseHouseWaterDrain caseHouseWaterDrain) {
        CaseHouseWaterDrainExample example = new CaseHouseWaterDrainExample();
        MybatisUtils.convertObj2Example(caseHouseWaterDrain, example);
        return caseHouseWaterDrainMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseHouseWaterDrain
     * @return
     */
    public Integer addBuildingOutfit(CaseHouseWaterDrain caseHouseWaterDrain) {
        caseHouseWaterDrainMapper.insertSelective(caseHouseWaterDrain);
        return caseHouseWaterDrain.getId();
    }

    /**
     * 编辑
     * @param caseHouseWaterDrain
     * @return
     */
    public boolean updateBuildingOutfit(CaseHouseWaterDrain caseHouseWaterDrain) {
        return caseHouseWaterDrainMapper.updateByPrimaryKeySelective(caseHouseWaterDrain) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteBuildingOutfit(Integer id){
        return caseHouseWaterDrainMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 获取数据条数
     * @param houseId
     * @return
     */
    public int countByHouseId(Integer houseId){
        CaseHouseWaterDrainExample example = new CaseHouseWaterDrainExample();
        example.createCriteria().andHouseIdEqualTo(houseId);
        return caseHouseWaterDrainMapper.countByExample(example);
    }
}
