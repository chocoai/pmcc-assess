package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildingPermit;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildingPermitExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareBuildingPermitMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/27 18:01
 * @Description:
 */
@Repository
public class DeclareBuildingPermitDao {
    @Autowired
    private DeclareBuildingPermitMapper declareBuildingPermitMapper;

    public Integer addDeclareBuildingPermit(DeclareBuildingPermit declareBuildingPermit) {
        declareBuildingPermitMapper.insertSelective(declareBuildingPermit);
        return declareBuildingPermit.getId();
    }

    public DeclareBuildingPermit getDeclareBuildingPermitById(Integer id) {
        return declareBuildingPermitMapper.selectByPrimaryKey(id);
    }

    public boolean updateDeclareBuildingPermit(DeclareBuildingPermit declareBuildingPermit) {
        return declareBuildingPermitMapper.updateByPrimaryKeySelective(declareBuildingPermit) == 1;
    }

    public boolean updateDeclareBuildingPermit(DeclareBuildingPermit declareBuildingPermit, boolean updateNull) {
        if (updateNull) {
            DeclareBuildingPermit target = getDeclareBuildingPermitById(declareBuildingPermit.getId());
            if (declareBuildingPermit.getPlanDetailsId() == null) {
                declareBuildingPermit.setPlanDetailsId(target.getPlanDetailsId());
            }
            if (StringUtils.isEmpty(declareBuildingPermit.getCreator())) {
                declareBuildingPermit.setCreator(target.getCreator());
            }
            if (declareBuildingPermit.getGmtCreated() == null) {
                declareBuildingPermit.setGmtCreated(target.getGmtCreated());
            }
        }
        return updateNull ? declareBuildingPermitMapper.updateByPrimaryKey(declareBuildingPermit) == 1 : declareBuildingPermitMapper.updateByPrimaryKeySelective(declareBuildingPermit) == 1;
    }

    public void removeDeclareBuildingPermit(DeclareBuildingPermit declareBuildingPermit) {
        DeclareBuildingPermitExample example = new DeclareBuildingPermitExample();
        MybatisUtils.convertObj2Example(declareBuildingPermit, example);
        declareBuildingPermitMapper.deleteByExample(example);
    }

    public boolean deleteDeclareBuildingPermitById(Integer id){
        return declareBuildingPermitMapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean deleteDeclareBuildingPermitById(List<Integer> ids){
        DeclareBuildingPermitExample example = new DeclareBuildingPermitExample();
        DeclareBuildingPermitExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return declareBuildingPermitMapper.deleteByExample(example) > 0;
    }

    public List<DeclareBuildingPermit> getDeclareBuildingPermitList(DeclareBuildingPermit declareBuildingPermit) {
        DeclareBuildingPermitExample example = new DeclareBuildingPermitExample();
        MybatisUtils.convertObj2Example(declareBuildingPermit, example);
        return declareBuildingPermitMapper.selectByExample(example);
    }

    public List<DeclareBuildingPermit> getDeclareBuildingPermitByMasterId(Integer masterId){
        DeclareBuildingPermitExample example = new DeclareBuildingPermitExample();
        DeclareBuildingPermitExample.Criteria criteria = example.createCriteria();
        criteria.andMasterIdEqualTo(masterId);
        criteria.andIdIsNotNull();
        return declareBuildingPermitMapper.selectByExample(example) ;
    }

    public List<DeclareBuildingPermit> getDataIds(List<Integer> ids){
        DeclareBuildingPermitExample example = new DeclareBuildingPermitExample();
        DeclareBuildingPermitExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return declareBuildingPermitMapper.selectByExample(example) ;
    }
}
