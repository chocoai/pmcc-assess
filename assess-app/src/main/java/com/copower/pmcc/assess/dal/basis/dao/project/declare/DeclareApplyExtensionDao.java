package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareApplyExtension;
import com.copower.pmcc.assess.dal.basis.entity.DeclareApplyExtensionExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareApplyExtensionMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2019-12-10.
 */
@Repository
public class DeclareApplyExtensionDao {

    @Autowired
    private DeclareApplyExtensionMapper mapper;

    public boolean updateDeclareApplyExtension(DeclareApplyExtension oo, boolean updateNull) {
        return updateNull ? mapper.updateByPrimaryKey(oo) == 1 : mapper.updateByPrimaryKeySelective(oo) == 1;
    }

    public boolean deleteDeclareApplyExtensionById(Integer id){
        return mapper.deleteByPrimaryKey(id)==1 ;
    }

    public void deleteDeclareApplyExtensionByIds(List<Integer> ids){
        DeclareApplyExtensionExample example = new DeclareApplyExtensionExample();
        example.createCriteria().andIdIn(ids) ;
        mapper.deleteByExample(example);
    }

    public DeclareApplyExtension getDeclareApplyExtensionById(Integer id){
        return mapper.selectByPrimaryKey(id) ;
    }

    public boolean saveDeclareApplyExtension(DeclareApplyExtension oo){
        return mapper.insertSelective(oo) ==1;
    }

    public List<DeclareApplyExtension> getDeclareApplyExtensionByIds(List<Integer> ids){
        DeclareApplyExtensionExample example = new DeclareApplyExtensionExample();
        example.createCriteria().andIdIn(ids) ;
        return mapper.selectByExample(example) ;
    }

    public List<DeclareApplyExtension> getDeclareApplyExtensionListByExample(DeclareApplyExtension oo){
        DeclareApplyExtensionExample example = new DeclareApplyExtensionExample();
        MybatisUtils.convertObj2Example(oo, example);
        example.setOrderByClause("id");
        return mapper.selectByExample(example) ;
    }
}
