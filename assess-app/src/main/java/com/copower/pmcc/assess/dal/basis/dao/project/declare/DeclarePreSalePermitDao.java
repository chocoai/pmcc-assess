package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclarePreSalePermit;
import com.copower.pmcc.assess.dal.basis.entity.DeclarePreSalePermitExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclarePreSalePermitMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/27 18:02
 * @Description:
 */
@Repository
public class DeclarePreSalePermitDao {
    @Autowired
    private DeclarePreSalePermitMapper declarePreSalePermitMapper;

    public Integer addDeclarePreSalePermit(DeclarePreSalePermit declarePreSalePermit){
        declarePreSalePermitMapper.insertSelective(declarePreSalePermit);
        return declarePreSalePermit.getId();
    }

    public DeclarePreSalePermit getDeclarePreSalePermitById(Integer id){
        return declarePreSalePermitMapper.selectByPrimaryKey(id);
    }

    public boolean updateDeclarePreSalePermit(DeclarePreSalePermit declarePreSalePermit){
        return declarePreSalePermitMapper.updateByPrimaryKeySelective(declarePreSalePermit)==1;
    }

    public boolean updateDeclarePreSalePermit(DeclarePreSalePermit declarePreSalePermit,boolean updateNull){
        if (updateNull){
            DeclarePreSalePermit target = getDeclarePreSalePermitById(declarePreSalePermit.getId()) ;
            if (declarePreSalePermit.getPlanDetailsId() == null){
                declarePreSalePermit.setPlanDetailsId(target.getPlanDetailsId());
            }
            if (StringUtils.isEmpty(declarePreSalePermit.getCreator())){
                declarePreSalePermit.setCreator(target.getCreator());
            }
            if (declarePreSalePermit.getGmtCreated() == null){
                declarePreSalePermit.setGmtCreated(target.getGmtCreated());
            }
        }
        return updateNull ? declarePreSalePermitMapper.updateByPrimaryKey(declarePreSalePermit) == 1 : declarePreSalePermitMapper.updateByPrimaryKeySelective(declarePreSalePermit) == 1;
    }

    public void removeDeclarePreSalePermit(DeclarePreSalePermit declarePreSalePermit){
        DeclarePreSalePermitExample example = new DeclarePreSalePermitExample();
        MybatisUtils.convertObj2Example(declarePreSalePermit, example);
        declarePreSalePermitMapper.deleteByExample(example);
    }

    public List<DeclarePreSalePermit> getDeclarePreSalePermitList(DeclarePreSalePermit declarePreSalePermit){
        DeclarePreSalePermitExample example = new DeclarePreSalePermitExample();
        MybatisUtils.convertObj2Example(declarePreSalePermit, example);
        return declarePreSalePermitMapper.selectByExample(example);
    }


    public List<DeclarePreSalePermit> getDeclarePreSalePermitByMasterId(Integer masterId){
        DeclarePreSalePermitExample example = new DeclarePreSalePermitExample();
        DeclarePreSalePermitExample.Criteria criteria = example.createCriteria();
        criteria.andMasterIdEqualTo(masterId);
        criteria.andIdIsNotNull();
        return declarePreSalePermitMapper.selectByExample(example) ;
    }

    public boolean deleteDeclarePreSalePermitById(Integer id){
        return declarePreSalePermitMapper.deleteByPrimaryKey(id) == 1;
    }

    public List<DeclarePreSalePermit> getDataIds(List<Integer> ids) {
        DeclarePreSalePermitExample example = new DeclarePreSalePermitExample();
        DeclarePreSalePermitExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return declarePreSalePermitMapper.selectByExample(example) ;
    }
}
