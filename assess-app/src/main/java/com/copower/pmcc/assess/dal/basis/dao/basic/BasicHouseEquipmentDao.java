package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseEquipment;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseEquipmentExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicHouseEquipmentMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:29
 * @Description:
 */
@Repository
public class BasicHouseEquipmentDao {

    @Autowired
    private BasicHouseEquipmentMapper basicHouseEquipmentMapper;

    public BasicHouseEquipment getBasicHouseEquipmentById(Integer id) {
        return basicHouseEquipmentMapper.selectByPrimaryKey(id);
    }

    public Integer saveBasicHouseEquipment(BasicHouseEquipment basicHouseEquipment) {
        basicHouseEquipmentMapper.insertSelective(basicHouseEquipment);
        return basicHouseEquipment.getId();
    }

    public boolean updateBasicHouseEquipment(BasicHouseEquipment basicHouseEquipment, boolean updateNull) {
        return updateNull ? basicHouseEquipmentMapper.updateByPrimaryKey(basicHouseEquipment) == 1 : basicHouseEquipmentMapper.updateByPrimaryKeySelective(basicHouseEquipment) == 1;
    }

    public boolean deleteBasicHouseEquipment(Integer id) {
        BasicHouseEquipment houseEquipment = getBasicHouseEquipmentById(id);
        if (houseEquipment == null) return false;
        houseEquipment.setBisDelete(true);
        return basicHouseEquipmentMapper.updateByPrimaryKeySelective(houseEquipment) == 1;
    }

    public List<BasicHouseEquipment> basicHouseEquipmentList(BasicHouseEquipment basicHouseEquipment) {
        BasicHouseEquipmentExample example = new BasicHouseEquipmentExample();
        BasicHouseEquipmentExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicHouseEquipment, criteria);
        return basicHouseEquipmentMapper.selectByExample(example);
    }

    /**
     * 获取数据条数
     *
     * @param houseId
     * @return
     */
    public int countByHouseId(Integer houseId, String type) {
        BasicHouseEquipmentExample example = new BasicHouseEquipmentExample();
        example.createCriteria().andBisDeleteEqualTo(false).andHouseIdEqualTo(houseId).andTypeEqualTo(type);
        return basicHouseEquipmentMapper.countByExample(example);
    }

}
