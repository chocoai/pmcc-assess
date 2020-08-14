package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareLandUsePermit;
import com.copower.pmcc.assess.dal.basis.entity.DeclareLandUsePermitExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareLandUsePermitMapper;
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
public class DeclareLandUsePermitDao {
    @Autowired
    private DeclareLandUsePermitMapper declareLandUsePermitMapper;

    public Integer addDeclareLandUsePermit(DeclareLandUsePermit declareLandUsePermit) {
        declareLandUsePermitMapper.insertSelective(declareLandUsePermit);
        return declareLandUsePermit.getId();
    }

    public DeclareLandUsePermit getDeclareLandUsePermitById(Integer id) {
        return declareLandUsePermitMapper.selectByPrimaryKey(id);
    }

    public boolean updateDeclareLandUsePermit(DeclareLandUsePermit declareLandUsePermit) {
        return declareLandUsePermitMapper.updateByPrimaryKeySelective(declareLandUsePermit) == 1;
    }

    public boolean updateDeclareLandUsePermit(DeclareLandUsePermit declareLandUsePermit, boolean updateNull) {
        if (updateNull){
            DeclareLandUsePermit target = getDeclareLandUsePermitById(declareLandUsePermit.getId()) ;
            if (declareLandUsePermit.getPlanDetailsId() == null){
                declareLandUsePermit.setPlanDetailsId(target.getPlanDetailsId());
            }
            if (StringUtils.isEmpty(declareLandUsePermit.getCreator())){
                declareLandUsePermit.setCreator(target.getCreator());
            }
            if (declareLandUsePermit.getGmtCreated() == null){
                declareLandUsePermit.setGmtCreated(target.getGmtCreated());
            }
        }
        return updateNull ? declareLandUsePermitMapper.updateByPrimaryKey(declareLandUsePermit) == 1 : declareLandUsePermitMapper.updateByPrimaryKeySelective(declareLandUsePermit) == 1;
    }

    public void removeDeclareLandUsePermit(DeclareLandUsePermit declareLandUsePermit) {
        DeclareLandUsePermitExample example = new DeclareLandUsePermitExample();
        MybatisUtils.convertObj2Example(declareLandUsePermit, example);
        declareLandUsePermitMapper.deleteByExample(example);
    }

    public boolean deleteDeclareLandUsePermitById(Integer id){
        return declareLandUsePermitMapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean deleteDeclareLandUsePermitById(List<Integer> ids){
        DeclareLandUsePermitExample example = new DeclareLandUsePermitExample();
        DeclareLandUsePermitExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return declareLandUsePermitMapper.deleteByExample(example) >= 1;
    }

    public List<DeclareLandUsePermit> getDeclareLandUsePermitList(DeclareLandUsePermit declareLandUsePermit) {
        DeclareLandUsePermitExample example = new DeclareLandUsePermitExample();
        MybatisUtils.convertObj2Example(declareLandUsePermit, example);
        return declareLandUsePermitMapper.selectByExample(example);
    }

    public List<DeclareLandUsePermit> getDeclareLandUsePermitByMasterId(Integer masterId){
        DeclareLandUsePermitExample example = new DeclareLandUsePermitExample();
        DeclareLandUsePermitExample.Criteria criteria = example.createCriteria();
        criteria.andMasterIdEqualTo(masterId);
        criteria.andIdIsNotNull();
        return declareLandUsePermitMapper.selectByExample(example) ;
    }

    public List<DeclareLandUsePermit> getDataIds(List<Integer> ids){
        DeclareLandUsePermitExample example = new DeclareLandUsePermitExample();
        DeclareLandUsePermitExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return declareLandUsePermitMapper.selectByExample(example) ;
    }
    
    
}
