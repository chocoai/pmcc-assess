package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseEstateParkingExample;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseCorollaryEquipment;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseCorollaryEquipmentExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseHouseCorollaryEquipmentMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/18 14:54
 * @Description:
 */
@Repository
public class CaseHouseCorollaryEquipmentDao {
    @Autowired
    private CaseHouseCorollaryEquipmentMapper caseHouseCorollaryEquipmentMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseHouseCorollaryEquipment getEstateLandStateById(Integer id) {
        return caseHouseCorollaryEquipmentMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseHouseCorollaryEquipment
     * @return
     */
    public List<CaseHouseCorollaryEquipment> getEstateLandStateList(CaseHouseCorollaryEquipment caseHouseCorollaryEquipment) {
        CaseHouseCorollaryEquipmentExample example = new CaseHouseCorollaryEquipmentExample();
        MybatisUtils.convertObj2Example(caseHouseCorollaryEquipment, example);
        return caseHouseCorollaryEquipmentMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseHouseCorollaryEquipment
     * @return
     */
    public Integer addEstateLandState(CaseHouseCorollaryEquipment caseHouseCorollaryEquipment) {
       caseHouseCorollaryEquipmentMapper.insertSelective(caseHouseCorollaryEquipment);
       return caseHouseCorollaryEquipment.getId();
    }

    /**
     * 编辑
     * @param caseHouseCorollaryEquipment
     * @return
     */
    public boolean updateEstateLandState(CaseHouseCorollaryEquipment caseHouseCorollaryEquipment) {
        return caseHouseCorollaryEquipmentMapper.updateByPrimaryKeySelective(caseHouseCorollaryEquipment) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteEstateLandState(Integer id){
        return caseHouseCorollaryEquipmentMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 获取数据条数
     * @param houseId
     * @return
     */
    public int countByHouseId(Integer houseId){
        CaseHouseCorollaryEquipmentExample example = new CaseHouseCorollaryEquipmentExample();
        example.createCriteria().andHouseIdEqualTo(houseId);
        return caseHouseCorollaryEquipmentMapper.countByExample(example);
    }
}
