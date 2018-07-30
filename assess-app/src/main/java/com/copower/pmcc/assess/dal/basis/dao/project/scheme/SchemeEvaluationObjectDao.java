package com.copower.pmcc.assess.dal.basis.dao.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeEvaluationObject;
import com.copower.pmcc.assess.dal.basis.entity.SchemeEvaluationObjectExample;
import com.copower.pmcc.assess.dal.basis.mapper.SchemeEvaluationObjectMapper;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeEvaluationObjectDto;
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
    private SchemeEvaluationObjectMapper mapper;
    @Autowired
    private SchemeEvaluationObjectMapper schemeEvaluationObjectMapper;

    public List<SchemeEvaluationObject> getDataListByGroupId(Integer groupId, Integer projectId) {
        SchemeEvaluationObjectExample example = new SchemeEvaluationObjectExample();
        example.createCriteria().andAreaGroupIdEqualTo(groupId).andProjectIdEqualTo(projectId);
        return schemeEvaluationObjectMapper.selectByExample(example);
    }

    public int add(SchemeEvaluationObject schemeEvaluationObject) {
        mapper.insertSelective(schemeEvaluationObject);
        int id = schemeEvaluationObject.getId();
        return id;
    }

    public boolean remove(Integer id) {
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean update(SchemeEvaluationObjectDto dto) {
        return mapper.updateByPrimaryKey(change(dto)) == 1;
    }

    public SchemeEvaluationObjectDto get(Integer id) {
        return change(mapper.selectByPrimaryKey(id));
    }

    public SchemeEvaluationObjectDto change(SchemeEvaluationObject oo) {
        SchemeEvaluationObjectDto dto = new SchemeEvaluationObjectDto();
        BeanUtils.copyProperties(oo, dto);
        return dto;
    }

    public SchemeEvaluationObject change(SchemeEvaluationObjectDto dto) {
        SchemeEvaluationObject oo = new SchemeEvaluationObject();
        BeanUtils.copyProperties(dto, oo);
        return oo;
    }

    public SchemeEvaluationObject getSchemeEvaluationObjectById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }
}
