package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseEquipment;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseEquipmentExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineHouseEquipmentMapper;
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
public class ExamineHouseEquipmentDao {
    @Autowired
    private ExamineHouseEquipmentMapper examineHouseEquipmentMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineHouseEquipment getHouseEquipmentById(Integer id) {
        return examineHouseEquipmentMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineHouseEquipment
     * @return
     */
    public List<ExamineHouseEquipment> getHouseEquipmentList(ExamineHouseEquipment examineHouseEquipment) {
        ExamineHouseEquipmentExample example = new ExamineHouseEquipmentExample();
        MybatisUtils.convertObj2Example(examineHouseEquipment, example);
        return examineHouseEquipmentMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineHouseEquipment
     * @return
     */
    public boolean addHouseEquipment(ExamineHouseEquipment examineHouseEquipment) {
        return examineHouseEquipmentMapper.insertSelective(examineHouseEquipment) > 0;
    }

    /**
     * 编辑
     * @param examineHouseEquipment
     * @return
     */
    public boolean updateHouseEquipment(ExamineHouseEquipment examineHouseEquipment) {
        return examineHouseEquipmentMapper.updateByPrimaryKeySelective(examineHouseEquipment) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteHouseEquipment(Integer id){
        return examineHouseEquipmentMapper.deleteByPrimaryKey(id) > 0;
    }

}