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


    public Integer addDeclareBuildEngineeringAndEquipmentCenter(DeclareBuildEngineeringAndEquipmentCenter declareBuildEngineeringAndEquipmentCenter) {
        declareBuildEngineeringAndEquipmentCenterMapper.insertSelective(declareBuildEngineeringAndEquipmentCenter);
        return declareBuildEngineeringAndEquipmentCenter.getId();
    }

    public DeclareBuildEngineeringAndEquipmentCenter getDeclareBuildEngineeringAndEquipmentCenterById(Integer id) {
        return declareBuildEngineeringAndEquipmentCenterMapper.selectByPrimaryKey(id);
    }

    public boolean updateDeclareBuildEngineeringAndEquipmentCenter(DeclareBuildEngineeringAndEquipmentCenter declareBuildEngineeringAndEquipmentCenter) {
        return declareBuildEngineeringAndEquipmentCenterMapper.updateByPrimaryKeySelective(declareBuildEngineeringAndEquipmentCenter) == 1;
    }

    public boolean updateDeclareBuildEngineeringAndEquipmentCenter(DeclareBuildEngineeringAndEquipmentCenter center, boolean updateNull) {

        if (updateNull) {
            DeclareBuildEngineeringAndEquipmentCenter target = getDeclareBuildEngineeringAndEquipmentCenterById(center.getId());

            if (center.getPlanDetailsId() == null) {
                center.setPlanDetailsId(target.getPlanDetailsId());
            }
            if (center.getLandId() == null) {
                center.setLandId(target.getLandId());
            }
            if (center.getLandUsePermitId() == null) {
                center.setLandUsePermitId(target.getLandUsePermitId());
            }
            if (center.getHouseId() == null) {
                center.setHouseId(target.getHouseId());
            }
            if (center.getRealEstateId() == null) {
                center.setRealEstateId(target.getRealEstateId());
            }
            if (center.getIndicatorId() == null) {
                center.setIndicatorId(target.getIndicatorId());
            }
            if (center.getPreSalePermitId() == null) {
                center.setPreSalePermitId(target.getPreSalePermitId());
            }
            if (center.getBuildEngineeringId() == null) {
                center.setBuildEngineeringId(target.getBuildEngineeringId());
            }
            if (center.getBuildEquipmentId() == null) {
                center.setBuildEquipmentId(target.getBuildEquipmentId());
            }
            if (center.getBuildingPermitId() == null) {
                center.setBuildingPermitId(target.getBuildingPermitId());
            }
            if (center.getBuildingConstructionPermitId() == null) {
                center.setBuildingConstructionPermitId(target.getBuildingConstructionPermitId());
            }
            if (StringUtils.isEmpty(center.getType())) {
                center.setType(target.getType());
            }
            if (StringUtils.isEmpty(center.getCreator())) {
                center.setCreator(target.getCreator());
            }
            if (center.getGmtCreated() == null) {
                center.setGmtCreated(target.getGmtCreated());
            }
        }

        return updateNull ? declareBuildEngineeringAndEquipmentCenterMapper.updateByPrimaryKey(center) == 1 : declareBuildEngineeringAndEquipmentCenterMapper.updateByPrimaryKeySelective(center) == 1;
    }


    public void deleteIds(List<Integer> ids) {
        DeclareBuildEngineeringAndEquipmentCenterExample example = new DeclareBuildEngineeringAndEquipmentCenterExample();
        example.createCriteria().andIdIn(ids);
        declareBuildEngineeringAndEquipmentCenterMapper.deleteByExample(example);
    }

    public List<DeclareBuildEngineeringAndEquipmentCenter> getDeclareBuildEngineeringAndEquipmentCenterList(DeclareBuildEngineeringAndEquipmentCenter declareBuildEngineeringAndEquipmentCenter) {
        DeclareBuildEngineeringAndEquipmentCenterExample example = new DeclareBuildEngineeringAndEquipmentCenterExample();
        MybatisUtils.convertObj2Example(declareBuildEngineeringAndEquipmentCenter, example);
        return declareBuildEngineeringAndEquipmentCenterMapper.selectByExample(example);
    }

    public List<DeclareBuildEngineeringAndEquipmentCenter> getDeclareBuildEngineeringAndEquipmentCenterList(String type, Integer buildEngineeringId, Integer buildEquipmentId) {
        DeclareBuildEngineeringAndEquipmentCenterExample example = new DeclareBuildEngineeringAndEquipmentCenterExample();
        DeclareBuildEngineeringAndEquipmentCenterExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (StringUtils.isNotBlank(type)) {
            criteria.andTypeLike(String.format("%s%s%s", "%", type, "%"));
        }
        if (buildEngineeringId != null) {
            criteria.andBuildEngineeringIdEqualTo(buildEngineeringId);
        }
        if (buildEquipmentId != null) {
            criteria.andBuildEquipmentIdEqualTo(buildEquipmentId);
        }
        return declareBuildEngineeringAndEquipmentCenterMapper.selectByExample(example);
    }


}
