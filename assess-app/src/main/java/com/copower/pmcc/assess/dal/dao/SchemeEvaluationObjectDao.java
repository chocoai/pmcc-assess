package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.SchemeEvaluationObject;
import com.copower.pmcc.assess.dal.entity.SchemeEvaluationObjectExample;
import com.copower.pmcc.assess.dal.mapper.SchemeEvaluationObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zly on 2018/5/21.
 */
@Repository
public class SchemeEvaluationObjectDao {

    @Autowired
    SchemeEvaluationObjectMapper schemeEvaluationObjectMapper;

    public List<SchemeEvaluationObject> getSchemeEvaluationObjectByProjectId(Integer projectId) {
        SchemeEvaluationObjectExample example = new SchemeEvaluationObjectExample();
        example.createCriteria().andProjectIdEqualTo(projectId);
        return schemeEvaluationObjectMapper.selectByExample(example);
    }

    public List<SchemeEvaluationObject> getSchemeEvaluationObjectByGroupId(Integer groupId, Integer projectId) {
        SchemeEvaluationObjectExample example = new SchemeEvaluationObjectExample();
        example.createCriteria().andAreaGroupIdEqualTo(groupId).andProjectIdEqualTo(projectId);
        return schemeEvaluationObjectMapper.selectByExample(example);
    }
}
