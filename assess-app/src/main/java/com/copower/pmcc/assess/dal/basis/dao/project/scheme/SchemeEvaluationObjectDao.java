package com.copower.pmcc.assess.dal.basis.dao.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeEvaluationObject;
import com.copower.pmcc.assess.dal.basis.entity.SchemeEvaluationObjectExample;
import com.copower.pmcc.assess.dal.basis.mapper.SchemeEvaluationObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 委估对象 （合并的记录）
 * Created by 13426 on 2018/5/21.
 */
@Repository
public class SchemeEvaluationObjectDao {
    @Autowired
    private SchemeEvaluationObjectMapper schemeEvaluationObjectMapper;

    public List<SchemeEvaluationObject> getDataListByGroupId(Integer groupId, Integer projectId) {
        SchemeEvaluationObjectExample example = new SchemeEvaluationObjectExample();
        example.createCriteria().andAreaGroupIdEqualTo(groupId).andProjectIdEqualTo(projectId);
        return schemeEvaluationObjectMapper.selectByExample(example);
    }

    public int add(SchemeEvaluationObject schemeEvaluationObject) {
        schemeEvaluationObjectMapper.insertSelective(schemeEvaluationObject);
        int id = schemeEvaluationObject.getId();
        return id;
    }

    public boolean remove(Integer id) {
        return schemeEvaluationObjectMapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean update(SchemeEvaluationObject schemeEvaluationObject) {
        return schemeEvaluationObjectMapper.updateByPrimaryKey(schemeEvaluationObject) == 1;
    }

    public SchemeEvaluationObject get(Integer id) {
        return schemeEvaluationObjectMapper.selectByPrimaryKey(id);
    }

    public SchemeEvaluationObject getSchemeEvaluationObjectById(Integer id) {
        return schemeEvaluationObjectMapper.selectByPrimaryKey(id);
    }
}
