package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseCorollaryEquipment;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseCorollaryEquipmentExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineHouseCorollaryEquipmentMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/8/2 14:45
 * @Description:
 */
@Repository
public class ExamineHouseCorollaryEquipmentDao {

    @Autowired
    private ExamineHouseCorollaryEquipmentMapper examineHouseCorollaryEquipmentMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineHouseCorollaryEquipment getHouseById(Integer id) {
        return examineHouseCorollaryEquipmentMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据信息
     * @param declareId
     * @return
     */
    public ExamineHouseCorollaryEquipment getHouseByDeclareId(Integer declareId,Integer examineType) {
        ExamineHouseCorollaryEquipmentExample example = new ExamineHouseCorollaryEquipmentExample();
        example.createCriteria().andDeclareIdEqualTo(declareId).andExamineTypeEqualTo(examineType);
        List<ExamineHouseCorollaryEquipment> blockList = examineHouseCorollaryEquipmentMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(blockList)) return blockList.get(0);
        return null;
    }

    /**
     * 获取数据列表
     * @param examineHouseCorollaryEquipment
     * @return
     */
    public List<ExamineHouseCorollaryEquipment> getHouseList(ExamineHouseCorollaryEquipment examineHouseCorollaryEquipment) {
        ExamineHouseCorollaryEquipmentExample example = new ExamineHouseCorollaryEquipmentExample();
        MybatisUtils.convertObj2Example(examineHouseCorollaryEquipment, example);
        return examineHouseCorollaryEquipmentMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineHouseCorollaryEquipment
     * @return
     */
    public int addHouse(ExamineHouseCorollaryEquipment examineHouseCorollaryEquipment) {
         examineHouseCorollaryEquipmentMapper.insertSelective(examineHouseCorollaryEquipment);
         return  examineHouseCorollaryEquipment.getId();
    }

    /**
     * 编辑
     * @param examineHouseCorollaryEquipment
     * @return
     */
    public boolean updateHouse(ExamineHouseCorollaryEquipment examineHouseCorollaryEquipment) {
        return examineHouseCorollaryEquipmentMapper.updateByPrimaryKeySelective(examineHouseCorollaryEquipment) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteHouse(Integer id){
        return examineHouseCorollaryEquipmentMapper.deleteByPrimaryKey(id) > 0;
    }
}
