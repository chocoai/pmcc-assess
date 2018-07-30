package com.copower.pmcc.assess.dal.basis.dao.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObjectExample;
import com.copower.pmcc.assess.dal.basis.mapper.SchemeJudgeObjectMapper;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeJudgeObjectDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 估价对象
 * Created by 13426 on 2018/5/21.
 */
@Repository
public class SchemeJudgeObjectDao {

    @Autowired
    private SchemeJudgeObjectMapper mapper;

    public boolean add(SchemeJudgeObject schemeJudgeObject) {
        return mapper.insertSelective(schemeJudgeObject) == 1;
    }

    public boolean remove(Integer id) {
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean update(SchemeJudgeObject schemeJudgeObject) {
        return mapper.updateByPrimaryKeySelective(schemeJudgeObject) == 1;
    }

    public SchemeJudgeObject get(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<SchemeJudgeObject> getSchemeJudgeObjectList(Integer evaluationId) {
        SchemeJudgeObjectExample example = new SchemeJudgeObjectExample();
        example.createCriteria().andEvaluationIdEqualTo(evaluationId);
        example.setOrderByClause("group_number,number");
        return mapper.selectByExample(example);
    }

    public List<SchemeJudgeObject> list(Integer areaGroupId) {
        SchemeJudgeObjectExample example = new SchemeJudgeObjectExample();
        example.createCriteria().andIdIsNotNull().andAreaGroupIdEqualTo(areaGroupId);
        example.setOrderByClause("group_number,number");
        return mapper.selectByExample(example);
    }

    public List<SchemeJudgeObject> getByProjectIdAndAreaGroupId(Integer ProjectId, Integer AreaGroupId, Integer groupNumber) {
        SchemeJudgeObjectExample example = new SchemeJudgeObjectExample();
        example.createCriteria().andProjectIdEqualTo(ProjectId).andAreaGroupIdEqualTo(AreaGroupId).andGroupNumberEqualTo(groupNumber);
        return mapper.selectByExample(example);
    }

}
