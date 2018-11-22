package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEngineeringAndEquipmentCenter;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEngineeringAndEquipmentCenterExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareBuildEngineeringAndEquipmentCenterMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/22 10:22
 * @Description:
 */
@Repository
public class DeclareBuildEngineeringAndEquipmentCenterDao {


    @Autowired
    private DeclareBuildEngineeringAndEquipmentCenterMapper declareBuildEngineeringAndEquipmentCenterMapper;


    public Integer addDeclareBuildEngineeringAndEquipmentCenter(DeclareBuildEngineeringAndEquipmentCenter declareBuildEngineeringAndEquipmentCenter){
        declareBuildEngineeringAndEquipmentCenterMapper.insertSelective(declareBuildEngineeringAndEquipmentCenter);
        return declareBuildEngineeringAndEquipmentCenter.getId();
    }

    public DeclareBuildEngineeringAndEquipmentCenter getDeclareBuildEngineeringAndEquipmentCenterById(Integer id){
        return declareBuildEngineeringAndEquipmentCenterMapper.selectByPrimaryKey(id);
    }

    public boolean updateDeclareBuildEngineeringAndEquipmentCenter(DeclareBuildEngineeringAndEquipmentCenter declareBuildEngineeringAndEquipmentCenter){
        return declareBuildEngineeringAndEquipmentCenterMapper.updateByPrimaryKeySelective(declareBuildEngineeringAndEquipmentCenter)==1;
    }

    public void removeDeclareBuildEngineeringAndEquipmentCenter(DeclareBuildEngineeringAndEquipmentCenter declareBuildEngineeringAndEquipmentCenter){
        DeclareBuildEngineeringAndEquipmentCenterExample example = new DeclareBuildEngineeringAndEquipmentCenterExample();
        MybatisUtils.convertObj2Example(declareBuildEngineeringAndEquipmentCenter, example);
        declareBuildEngineeringAndEquipmentCenterMapper.deleteByExample(example);
    }

    public List<DeclareBuildEngineeringAndEquipmentCenter> getDeclareBuildEngineeringAndEquipmentCenterList(DeclareBuildEngineeringAndEquipmentCenter declareBuildEngineeringAndEquipmentCenter){
        DeclareBuildEngineeringAndEquipmentCenterExample example = new DeclareBuildEngineeringAndEquipmentCenterExample();
        MybatisUtils.convertObj2Example(declareBuildEngineeringAndEquipmentCenter, example);
        return declareBuildEngineeringAndEquipmentCenterMapper.selectByExample(example);
    }
    
    
}
