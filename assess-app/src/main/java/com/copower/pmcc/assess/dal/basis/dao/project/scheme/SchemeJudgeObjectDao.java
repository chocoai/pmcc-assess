package com.copower.pmcc.assess.dal.basis.dao.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObjectExample;
import com.copower.pmcc.assess.dal.basis.mapper.SchemeJudgeObjectMapper;
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

    public boolean addSchemeJudgeObject(SchemeJudgeObject schemeJudgeObject) {
        return mapper.insertSelective(schemeJudgeObject) == 1;
    }

    public boolean removeSchemeJudgeObject(Integer id) {
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean updateSchemeJudgeObject(SchemeJudgeObject schemeJudgeObject) {
        return mapper.updateByPrimaryKeySelective(schemeJudgeObject) == 1;
    }

    public SchemeJudgeObject getSchemeJudgeObject(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }


    public List<SchemeJudgeObject> getSchemeJudgeObjectList(Integer areaGroupId) {
        SchemeJudgeObjectExample example = new SchemeJudgeObjectExample();
        example.createCriteria().andIdIsNotNull().andAreaGroupIdEqualTo(areaGroupId);
        example.setOrderByClause("sorting");
        return mapper.selectByExample(example);
    }

}
