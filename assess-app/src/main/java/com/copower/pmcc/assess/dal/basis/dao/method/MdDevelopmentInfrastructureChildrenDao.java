package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentInfrastructureChildren;
import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentInfrastructureChildrenExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdDevelopmentInfrastructureChildrenMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2019/7/31.
 */
@Repository
public class MdDevelopmentInfrastructureChildrenDao {

    @Autowired
    private MdDevelopmentInfrastructureChildrenMapper mapper;

    public void batchInset(List<MdDevelopmentInfrastructureChildren> list){
        mapper.batchInsert(list);
    }

    public boolean addMdDevelopmentInfrastructureChildren(MdDevelopmentInfrastructureChildren oo){
        return mapper.insertSelective(oo) == 1;
    }

    public boolean updateMdDevelopmentInfrastructureChildren(MdDevelopmentInfrastructureChildren oo){
        return mapper.updateByPrimaryKeySelective(oo)==1;
    }

    public MdDevelopmentInfrastructureChildren getMdDevelopmentInfrastructureChildrenById(Integer id){
        return mapper.selectByPrimaryKey(id) ;
    }

    public boolean deleteMdDevelopmentInfrastructureChildrenById(Integer id){
        return mapper.deleteByPrimaryKey(id) == 1;
    }


    public void removeMdDevelopmentInfrastructureChildren(MdDevelopmentInfrastructureChildren oo){
        MdDevelopmentInfrastructureChildrenExample example = new MdDevelopmentInfrastructureChildrenExample();
        MybatisUtils.convertObj2Example(oo, example);
        mapper.deleteByExample(example) ;
    }

    public List<MdDevelopmentInfrastructureChildren> getMdDevelopmentInfrastructureChildrenListByExample(MdDevelopmentInfrastructureChildren oo){
        MdDevelopmentInfrastructureChildrenExample example = new MdDevelopmentInfrastructureChildrenExample();
        MybatisUtils.convertObj2Example(oo, example);
        return mapper.selectByExample(example) ;
    }
    
}
