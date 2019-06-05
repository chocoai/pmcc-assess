package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEngineeringAndEquipmentCenter;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEngineeringAndEquipmentCenterExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareBuildEngineeringAndEquipmentCenterMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
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

    public void deleteIds(List<Integer> ids){
        DeclareBuildEngineeringAndEquipmentCenterExample example = new DeclareBuildEngineeringAndEquipmentCenterExample();
        example.createCriteria().andIdIn(ids) ;
        declareBuildEngineeringAndEquipmentCenterMapper.deleteByExample(example);
    }

    public List<DeclareBuildEngineeringAndEquipmentCenter> getDeclareBuildEngineeringAndEquipmentCenterList(DeclareBuildEngineeringAndEquipmentCenter declareBuildEngineeringAndEquipmentCenter){
        DeclareBuildEngineeringAndEquipmentCenterExample example = new DeclareBuildEngineeringAndEquipmentCenterExample();
        MybatisUtils.convertObj2Example(declareBuildEngineeringAndEquipmentCenter, example);
        return declareBuildEngineeringAndEquipmentCenterMapper.selectByExample(example);
    }
    public List<DeclareBuildEngineeringAndEquipmentCenter> getDeclareBuildEngineeringAndEquipmentCenterList(String type,Integer buildEngineeringId,Integer buildEquipmentId){
        DeclareBuildEngineeringAndEquipmentCenterExample example = new DeclareBuildEngineeringAndEquipmentCenterExample();
        DeclareBuildEngineeringAndEquipmentCenterExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (StringUtils.isNotBlank(type)){
            criteria.andTypeLike(String.format("%s%s%s","%",type,"%"));
        }
        if (buildEngineeringId != null){
            criteria.andBuildEngineeringIdEqualTo(buildEngineeringId);
        }
        if (buildEquipmentId != null){
            criteria.andBuildEquipmentIdEqualTo(buildEquipmentId);
        }
        return declareBuildEngineeringAndEquipmentCenterMapper.selectByExample(example);
    }
    
    
}
