package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseEquipment;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseEquipmentExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseHouseEquipmentMapper;
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
public class CaseHouseEquipmentDao {
    @Autowired
    private CaseHouseEquipmentMapper caseHouseEquipmentMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseHouseEquipment getHouseEquipmentById(Integer id) {
        return caseHouseEquipmentMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseHouseEquipment
     * @return
     */
    public List<CaseHouseEquipment> getHouseEquipmentList(CaseHouseEquipment caseHouseEquipment) {
        CaseHouseEquipmentExample example = new CaseHouseEquipmentExample();
        MybatisUtils.convertObj2Example(caseHouseEquipment, example);
        return caseHouseEquipmentMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseHouseEquipment
     * @return
     */
    public boolean addHouseEquipment(CaseHouseEquipment caseHouseEquipment) {
        return caseHouseEquipmentMapper.insertSelective(caseHouseEquipment) > 0;
    }

    /**
     * 编辑
     * @param caseHouseEquipment
     * @return
     */
    public boolean updateHouseEquipment(CaseHouseEquipment caseHouseEquipment) {
        return caseHouseEquipmentMapper.updateByPrimaryKeySelective(caseHouseEquipment) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteHouseEquipment(Integer id){
        return caseHouseEquipmentMapper.deleteByPrimaryKey(id) > 0;
    }

}