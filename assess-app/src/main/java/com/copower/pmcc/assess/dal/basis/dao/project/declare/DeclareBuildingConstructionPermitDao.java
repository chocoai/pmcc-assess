package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildingConstructionPermit;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildingConstructionPermitExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareBuildingConstructionPermitMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/27 18:00
 * @Description:
 */
@Repository
public class DeclareBuildingConstructionPermitDao {
    @Autowired
    private DeclareBuildingConstructionPermitMapper declareBuildingConstructionPermitMapper;

    public Integer addDeclareBuildingConstructionPermit(DeclareBuildingConstructionPermit declareBuildingConstructionPermit){
        declareBuildingConstructionPermitMapper.insertSelective(declareBuildingConstructionPermit);
        return declareBuildingConstructionPermit.getId();
    }

    public DeclareBuildingConstructionPermit getDeclareBuildingConstructionPermitById(Integer id){
        return declareBuildingConstructionPermitMapper.selectByPrimaryKey(id);
    }

    public List<DeclareBuildingConstructionPermit> getDataIds(List<Integer> ids){
        DeclareBuildingConstructionPermitExample example = new DeclareBuildingConstructionPermitExample();
        DeclareBuildingConstructionPermitExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids) ;
        return declareBuildingConstructionPermitMapper.selectByExample(example) ;
    }

    public boolean updateDeclareBuildingConstructionPermit(DeclareBuildingConstructionPermit declareBuildingConstructionPermit){
        return declareBuildingConstructionPermitMapper.updateByPrimaryKeySelective(declareBuildingConstructionPermit)==1;
    }

    public boolean updateDeclareBuildingConstructionPermit(DeclareBuildingConstructionPermit declareBuildingConstructionPermit, boolean updateNull){

        if (updateNull) {
            DeclareBuildingConstructionPermit target = getDeclareBuildingConstructionPermitById(declareBuildingConstructionPermit.getId());
            if (declareBuildingConstructionPermit.getPlanDetailsId() == null) {
                declareBuildingConstructionPermit.setPlanDetailsId(target.getPlanDetailsId());
            }
            if (StringUtils.isEmpty(declareBuildingConstructionPermit.getCreator())) {
                declareBuildingConstructionPermit.setCreator(target.getCreator());
            }
            if (declareBuildingConstructionPermit.getGmtCreated() == null) {
                declareBuildingConstructionPermit.setGmtCreated(target.getGmtCreated());
            }
        }

        return updateNull ? declareBuildingConstructionPermitMapper.updateByPrimaryKey(declareBuildingConstructionPermit) == 1 : declareBuildingConstructionPermitMapper.updateByPrimaryKeySelective(declareBuildingConstructionPermit) == 1;
    }

    public void removeDeclareBuildingConstructionPermit(DeclareBuildingConstructionPermit declareBuildingConstructionPermit){
        DeclareBuildingConstructionPermitExample example = new DeclareBuildingConstructionPermitExample();
        MybatisUtils.convertObj2Example(declareBuildingConstructionPermit, example);
        declareBuildingConstructionPermitMapper.deleteByExample(example);
    }

    public boolean deleteDeclareBuildingConstructionPermitById(Integer id){
        return declareBuildingConstructionPermitMapper.deleteByPrimaryKey(id) == 1;
    }

    public List<DeclareBuildingConstructionPermit> getDeclareBuildingConstructionPermitList(DeclareBuildingConstructionPermit declareBuildingConstructionPermit){
        DeclareBuildingConstructionPermitExample example = new DeclareBuildingConstructionPermitExample();
        MybatisUtils.convertObj2Example(declareBuildingConstructionPermit, example);
        return declareBuildingConstructionPermitMapper.selectByExample(example);
    }

    public List<DeclareBuildingConstructionPermit> getDeclareBuildingConstructionPermitByMasterId(Integer masterId){
        DeclareBuildingConstructionPermitExample example = new DeclareBuildingConstructionPermitExample();
        DeclareBuildingConstructionPermitExample.Criteria criteria = example.createCriteria();
        criteria.andMasterIdEqualTo(masterId);
        criteria.andIdIsNotNull();
        return declareBuildingConstructionPermitMapper.selectByExample(example) ;
    }

}
