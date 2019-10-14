package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRecordGroup;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecordGroupExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareRecordGroupMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2019-10-14.
 */
@Repository
public class DeclareRecordGroupDao {

    @Autowired
    private DeclareRecordGroupMapper mapper;

    public boolean addDeclareRecordGroup(DeclareRecordGroup oo){
        return mapper.insertSelective(oo) == 1;
    }

    public boolean updateDeclareRecordGroup(DeclareRecordGroup oo){
        return mapper.updateByPrimaryKeySelective(oo)==1;
    }

    public DeclareRecordGroup getDeclareRecordGroupById(Integer id){
        return mapper.selectByPrimaryKey(id) ;
    }

    public void deleteDeclareRecordGroup(List<Integer> ids){
        DeclareRecordGroupExample example = new DeclareRecordGroupExample();
        example.createCriteria().andIdIn(ids);
        mapper.deleteByExample(example) ;
    }

    public boolean deleteDeclareRecordGroupById(Integer id){
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public void removeDeclareRecordGroup(DeclareRecordGroup oo){
        DeclareRecordGroupExample example = new DeclareRecordGroupExample();
        MybatisUtils.convertObj2Example(oo, example);
        mapper.deleteByExample(example) ;
    }

    public List<DeclareRecordGroup> getDeclareRecordGroupListByExample(DeclareRecordGroup oo){
        DeclareRecordGroupExample example = new DeclareRecordGroupExample();
        MybatisUtils.convertObj2Example(oo, example);
        return mapper.selectByExample(example) ;
    }


    public List<DeclareRecordGroup> getDeclareRecordGroupListByType(String type){
        DeclareRecordGroupExample example = new DeclareRecordGroupExample();
        example.createCriteria().andTypeEqualTo(type);
        return mapper.selectByExample(example) ;
    }
    
}
