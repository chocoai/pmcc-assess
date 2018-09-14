package com.copower.pmcc.assess.dal.basis.dao.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.dal.basis.entity.SchemeAreaGroupExample;
import com.copower.pmcc.assess.dal.basis.mapper.SchemeAreaGroupMapper;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 区域分组
 * Created by 13426 on 2018/5/14.
 */
@Repository
public class SchemeAreaGroupDao {
    @Autowired
    private SchemeAreaGroupMapper mapper;

    public SchemeAreaGroup get(Integer id){
        return mapper.selectByPrimaryKey(id);
    }

    public int add(SchemeAreaGroup schemeAreaGroup){
        mapper.insertSelective(schemeAreaGroup);
        return schemeAreaGroup.getId();
    }


    public boolean update(SchemeAreaGroup schemeAreaGroup){
        return mapper.updateByPrimaryKeySelective(schemeAreaGroup)==1;
    }

    public boolean remove(Integer id){
        return mapper.deleteByPrimaryKey(id)==1;
    }

    public List<SchemeAreaGroup> getSchemeAreaGroupByIds(List<Integer> ids) {
        SchemeAreaGroupExample example = new SchemeAreaGroupExample();
        example.createCriteria().andIdIn(ids);
        return mapper.selectByExample(example);
    }

    public List<SchemeAreaGroup> getSchemeAreaGroupByPid(Integer pid) {
        SchemeAreaGroupExample example = new SchemeAreaGroupExample();
        example.createCriteria().andPidEqualTo(pid);
        return mapper.selectByExample(example);
    }

    public List<SchemeAreaGroup> getSchemeAreaGroupByProjectId(Integer projectId) {
        SchemeAreaGroupExample example = new SchemeAreaGroupExample();
        example.createCriteria().andProjectIdEqualTo(projectId).andBisEnableEqualTo(true);
        return mapper.selectByExample(example);
    }
}
