package com.copower.pmcc.assess.dal.basis.dao.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObjectHistory;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObjectHistoryExample;
import com.copower.pmcc.assess.dal.basis.mapper.SchemeJudgeObjectHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:21
 * @Description:案例基础数据
 */
@Repository
public class SchemeJudgeObjectHistoryDao {

    @Autowired
    private SchemeJudgeObjectHistoryMapper schemeJudgeObjectHistoryMapper;

    public SchemeJudgeObjectHistory getSchemeJudgeObjectHistoryById(Integer id) {
        return schemeJudgeObjectHistoryMapper.selectByPrimaryKey(id);
    }

    public void addSchemeJudgeObjectHistory(SchemeJudgeObjectHistory SchemeJudgeObjectHistory) {
        schemeJudgeObjectHistoryMapper.insertSelective(SchemeJudgeObjectHistory);
    }

    public boolean updateSchemeJudgeObjectHistory(SchemeJudgeObjectHistory SchemeJudgeObjectHistory, boolean updateNull) {
        return updateNull ? schemeJudgeObjectHistoryMapper.updateByPrimaryKey(SchemeJudgeObjectHistory) == 1 : schemeJudgeObjectHistoryMapper.updateByPrimaryKeySelective(SchemeJudgeObjectHistory) == 1;
    }

    public boolean deleteSchemeJudgeObjectHistory(Integer id) {
        return schemeJudgeObjectHistoryMapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean deleteSchemeJudgeObjectHistoryByProjectId(Integer projectId) {
        if (projectId == null) return false;
        SchemeJudgeObjectHistoryExample example = new SchemeJudgeObjectHistoryExample();
        example.createCriteria().andProjectIdEqualTo(projectId);
        return schemeJudgeObjectHistoryMapper.deleteByExample(example) > 0;
    }
    
    public List<SchemeJudgeObjectHistory> getListByProjectId(Integer projectId) {
        if (projectId == null) return null;
        SchemeJudgeObjectHistoryExample example = new SchemeJudgeObjectHistoryExample();
        SchemeJudgeObjectHistoryExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(projectId);
        return schemeJudgeObjectHistoryMapper.selectByExample(example);
    }

    public List<SchemeJudgeObjectHistory> getListByAreaGroupId(Integer areaGroupId) {
        if (areaGroupId == null) return null;
        SchemeJudgeObjectHistoryExample example = new SchemeJudgeObjectHistoryExample();
        SchemeJudgeObjectHistoryExample.Criteria criteria = example.createCriteria();
        criteria.andAreaGroupIdEqualTo(areaGroupId);
        return schemeJudgeObjectHistoryMapper.selectByExample(example);
    }
}
