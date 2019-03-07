package com.copower.pmcc.assess.dal.basis.dao.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObjectExample;
import com.copower.pmcc.assess.dal.basis.mapper.SchemeJudgeObjectMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
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

    public List<SchemeJudgeObject> getJudgeObjectList(SchemeJudgeObject schemeJudgeObject) {
        SchemeJudgeObjectExample example = new SchemeJudgeObjectExample();
        MybatisUtils.convertObj2Example(schemeJudgeObject, example);
        example.setOrderByClause("sorting,split_number");
        return mapper.selectByExample(example);
    }

    public boolean updateSchemeJudgeObject(Integer oldAreaGroupId, Integer newAreaGroupId) {
        SchemeJudgeObjectExample example = new SchemeJudgeObjectExample();
        example.createCriteria().andAreaGroupIdEqualTo(oldAreaGroupId);
        SchemeJudgeObject schemeJudgeObject = new SchemeJudgeObject();
        schemeJudgeObject.setAreaGroupId(newAreaGroupId);
        return mapper.updateByExampleSelective(schemeJudgeObject, example) > 0;
    }

    public boolean deleteJudgeObjectByAreaId(Integer areaGroupId) {
        SchemeJudgeObjectExample example = new SchemeJudgeObjectExample();
        example.createCriteria().andAreaGroupIdEqualTo(areaGroupId);
        return mapper.deleteByExample(example) > 0;
    }

    /**
     * 区域还原
     *
     * @param originalAreaGroupId
     * @return
     */
    public boolean areaGroupReduction(Integer originalAreaGroupId) {
        SchemeJudgeObjectExample example = new SchemeJudgeObjectExample();
        example.createCriteria().andOriginalAreaGroupIdEqualTo(originalAreaGroupId);
        SchemeJudgeObject schemeJudgeObject = new SchemeJudgeObject();
        schemeJudgeObject.setAreaGroupId(originalAreaGroupId);
        schemeJudgeObject.setOriginalAreaGroupId(null);
        schemeJudgeObject.setNumber(schemeJudgeObject.getOriginalNumber());
        schemeJudgeObject.setOriginalNumber(null);
        return mapper.updateByExampleSelective(schemeJudgeObject, example) > 0;
    }

    public SchemeJudgeObject getSchemeJudgeObject(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }


    public List<SchemeJudgeObject> getSchemeJudgeObjectList(Integer areaGroupId) {
        SchemeJudgeObjectExample example = new SchemeJudgeObjectExample();
        example.createCriteria().andAreaGroupIdEqualTo(areaGroupId).andBisEnableEqualTo(true);
        example.setOrderByClause("sorting,split_number");
        return mapper.selectByExample(example);
    }

    public List<SchemeJudgeObject> getListByPid(Integer pid) {
        SchemeJudgeObjectExample example = new SchemeJudgeObjectExample();
        example.createCriteria().andPidEqualTo(pid);
        example.setOrderByClause("sorting,split_number");
        return mapper.selectByExample(example);
    }

    public List<SchemeJudgeObject> getListByNumber(Integer projectId, Integer areaGroupId, String number) {
        SchemeJudgeObjectExample example = new SchemeJudgeObjectExample();
        example.createCriteria().andProjectIdEqualTo(projectId).andAreaGroupIdEqualTo(areaGroupId).andNumberEqualTo(number);
        example.setOrderByClause("split_number");
        return mapper.selectByExample(example);
    }

    public List<SchemeJudgeObject> getListByIds(List<Integer> ids) {
        SchemeJudgeObjectExample example = new SchemeJudgeObjectExample();
        example.createCriteria().andIdIn(ids);
        example.setOrderByClause("sorting,split_number");
        return mapper.selectByExample(example);
    }

    public int getNotSetFunctionCount(Integer projectId) {
        SchemeJudgeObjectExample example = new SchemeJudgeObjectExample();
        example.createCriteria().andProjectIdEqualTo(projectId).andBisEnableEqualTo(true).andBisSetFunctionEqualTo(false);
        return mapper.countByExample(example);
    }

}
