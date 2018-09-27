package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclarePreSalePermit;
import com.copower.pmcc.assess.dal.basis.entity.DeclarePreSalePermitExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclarePreSalePermitMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
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
}
