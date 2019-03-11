package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareApply;
import com.copower.pmcc.assess.dal.basis.entity.DeclareApplyExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareApplyMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeclareApplyDao {
    @Autowired
    private DeclareApplyMapper declareApplyMapper;

    public DeclareApply getDeclareApply(Integer id){
        return declareApplyMapper.selectByPrimaryKey(id);
    }
    
    public DeclareApply getDeclareApplyByProcessInsId(String processInsId){
        if(StringUtils.isBlank(processInsId)) return null;
        DeclareApplyExample example = new DeclareApplyExample();
        DeclareApplyExample.Criteria criteria = example.createCriteria();
        criteria.andProcessInsIdEqualTo(processInsId);
        List<DeclareApply> declareApplys = declareApplyMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(declareApplys))
            return declareApplys.get(0);
        return null;
    }

    public DeclareApply getDeclareApplyByProjectId(Integer projectId){
        if(projectId==null) return null;
        DeclareApplyExample example = new DeclareApplyExample();
        DeclareApplyExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(projectId);
        List<DeclareApply> declareApplys = declareApplyMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(declareApplys))
            return declareApplys.get(0);
        return null;
    }

    public boolean addDeclareApply(DeclareApply declareApply) {
        int i = declareApplyMapper.insert(declareApply);
        return i > 0;
    }

    public boolean updateDeclareApply(DeclareApply declareApply) {
        int i = declareApplyMapper.updateByPrimaryKeySelective(declareApply);
        return i > 0;
    }

    public boolean deleteDeclareApply(Integer id) {
        int i = declareApplyMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
