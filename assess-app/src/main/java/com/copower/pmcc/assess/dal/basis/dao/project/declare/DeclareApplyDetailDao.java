package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareApplyDetail;
import com.copower.pmcc.assess.dal.basis.entity.DeclareApplyDetailExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareApplyDetailMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2019-12-10.
 */
@Repository
public class DeclareApplyDetailDao {
    @Autowired
    private DeclareApplyDetailMapper mapper;

    public boolean updateDeclareApplyDetail(DeclareApplyDetail oo, boolean updateNull) {
        return updateNull ? mapper.updateByPrimaryKey(oo) == 1 : mapper.updateByPrimaryKeySelective(oo) == 1;
    }

    public boolean deleteDeclareApplyDetailById(Integer id){
        return mapper.deleteByPrimaryKey(id)==1 ;
    }

    public void deleteDeclareApplyDetailByIds(List<Integer> ids){
        DeclareApplyDetailExample example = new DeclareApplyDetailExample();
        example.createCriteria().andIdIn(ids) ;
        mapper.deleteByExample(example);
    }

    public DeclareApplyDetail getDeclareApplyDetailById(Integer id){
        return mapper.selectByPrimaryKey(id) ;
    }

    public boolean saveDeclareApplyDetail(DeclareApplyDetail oo){
        return mapper.insertSelective(oo) ==1;
    }

    public List<DeclareApplyDetail> getDeclareApplyDetailByIds(List<Integer> ids){
        DeclareApplyDetailExample example = new DeclareApplyDetailExample();
        example.createCriteria().andIdIn(ids) ;
        return mapper.selectByExample(example) ;
    }

    public List<DeclareApplyDetail> getDeclareApplyDetailListByExample(DeclareApplyDetail oo){
        DeclareApplyDetailExample example = new DeclareApplyDetailExample();
        MybatisUtils.convertObj2Example(oo, example);
        example.setOrderByClause("id");
        return mapper.selectByExample(example) ;
    }
}
