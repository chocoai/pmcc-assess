package com.copower.pmcc.assess.dal.basis.dao.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObjectExample;
import com.copower.pmcc.assess.dal.basis.mapper.SchemeJudgeObjectMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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

    public List<SchemeJudgeObject> getJudgeObjectListByQuery(String name,String certName, String seat,String ownership,Integer areaGroupId,List<Integer> ids) {
        SchemeJudgeObjectExample example = new SchemeJudgeObjectExample();
        SchemeJudgeObjectExample.Criteria criteria = example.createCriteria().andBisEnableEqualTo(true);
        if(areaGroupId!=null){
            criteria.andAreaGroupIdEqualTo(areaGroupId);
        }
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike(String.format("%%%s%%", name));
        }
        if (StringUtils.isNotBlank(seat)) {
            criteria.andSeatLike(String.format("%%%s%%", seat));
        }
          if (StringUtils.isNotBlank(certName)) {
            criteria.andCertNameLike(String.format("%%%s%%", certName));
        }
          if (StringUtils.isNotBlank(ownership)) {
            criteria.andOwnershipLike(String.format("%%%s%%", ownership));
        }
        if(CollectionUtils.isNotEmpty(ids)){
            criteria.andIdIn(ids);
        }
        return mapper.selectByExample(example);
    }

    public List<SchemeJudgeObject> getSchemeJudgeObjectListAll(String name,String certName, String seat,String ownership,Integer areaGroupId,List<Integer> ids) {
        SchemeJudgeObjectExample example = new SchemeJudgeObjectExample();
        SchemeJudgeObjectExample.Criteria criteria = example.createCriteria().andBisEnableEqualTo(false);
        if(areaGroupId!=null){
            criteria.andAreaGroupIdEqualTo(areaGroupId);
        }
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike(String.format("%%%s%%", name));
        }
        if (StringUtils.isNotBlank(seat)) {
            criteria.andSeatLike(String.format("%%%s%%", seat));
        }
          if (StringUtils.isNotBlank(certName)) {
            criteria.andCertNameLike(String.format("%%%s%%", certName));
        }
          if (StringUtils.isNotBlank(ownership)) {
            criteria.andOwnershipLike(String.format("%%%s%%", ownership));
        }
        if(CollectionUtils.isNotEmpty(ids)){
            criteria.andIdIn(ids);
        }
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

    public List<SchemeJudgeObject> getSchemeJudgeObjectList(Integer areaGroupId, String number, String ownership, String seat) {
        SchemeJudgeObjectExample example = new SchemeJudgeObjectExample();
        SchemeJudgeObjectExample.Criteria criteria = example.createCriteria();
        criteria.andAreaGroupIdEqualTo(areaGroupId).andBisEnableEqualTo(true);
        if (StringUtils.isNotBlank(number)) {
            criteria.andNumberLike(String.format("%%%s%%", number));
        }
        if (StringUtils.isNotBlank(ownership)) {
            criteria.andOwnershipLike(String.format("%%%s%%", ownership));
        }
        if (StringUtils.isNotBlank(seat)) {
            criteria.andSeatLike(String.format("%%%s%%", seat));
        }
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

    public List<SchemeJudgeObject> getMergeListByAreaId(Integer areaGroupId) {
        SchemeJudgeObjectExample example = new SchemeJudgeObjectExample();
        example.createCriteria().andAreaGroupIdEqualTo(areaGroupId).andBisMergeEqualTo(true);
        example.setOrderByClause("sorting,split_number");
        return mapper.selectByExample(example);
    }

    public int getNotSetFunctionCount(Integer projectId) {
        SchemeJudgeObjectExample example = new SchemeJudgeObjectExample();
        example.createCriteria().andProjectIdEqualTo(projectId).andBisEnableEqualTo(true).andBisSetFunctionEqualTo(false);
        return mapper.countByExample(example);
    }

    public List<SchemeJudgeObject> getListByDeclareIds(List<Integer> declareIds) {
        SchemeJudgeObjectExample example = new SchemeJudgeObjectExample();
        example.createCriteria().andBisMergeEqualTo(false).andBisSplitEqualTo(false).andDeclareRecordIdIn(declareIds);
        example.setOrderByClause("sorting,split_number");
        return mapper.selectByExample(example);
    }

    public int getAreaGroupMaxNumber(Integer projectId, Integer areaGroupId) {
        SchemeJudgeObjectExample example = new SchemeJudgeObjectExample();
        example.createCriteria().andProjectIdEqualTo(projectId).andAreaGroupIdEqualTo(areaGroupId).andBisMergeEqualTo(false).andBisSplitEqualTo(false);
        example.setOrderByClause("number desc");
        List<SchemeJudgeObject> judgeObjects = mapper.selectByExample(example);
        if (CollectionUtils.isEmpty(judgeObjects)) return 0;
        return Integer.valueOf(judgeObjects.get(0).getNumber());
    }

    public int getCountByAreaGroupId(Integer areaGroupId){
        SchemeJudgeObjectExample example = new SchemeJudgeObjectExample();
        example.createCriteria().andAreaGroupIdEqualTo(areaGroupId);
        return mapper.countByExample(example);
    }

    public int getCountBySplitFrom(Integer judgeObjectId){
        SchemeJudgeObjectExample example = new SchemeJudgeObjectExample();
        example.createCriteria().andSplitFromEqualTo(judgeObjectId);
        return mapper.countByExample(example);
    }

    public int getCountByPid(Integer pid){
        SchemeJudgeObjectExample example = new SchemeJudgeObjectExample();
        example.createCriteria().andPidEqualTo(pid);
        return mapper.countByExample(example);
    }
}
