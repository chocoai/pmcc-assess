package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatch;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatchExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicApplyBatchMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 方案主表
 * Created by 13426 on 2018/5/24.
 */
@Repository
public class BasicApplyBatchDao {
    @Autowired
    private BasicApplyBatchMapper basicApplyBatchMapper;

    /**
     * 获取已经提交的数据列表
     *
     * @param estateName
     * @return
     */
    public List<BasicApplyBatch> getCommitBasicApplyBatchListByName(String estateName,String userAccount, Boolean draftFlag) {
        BasicApplyBatchExample example = new BasicApplyBatchExample();
        BasicApplyBatchExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(estateName)) {
            criteria.andEstateNameLike(String.format("%s%s%s", "%", estateName, "%"));
        }
        if (StringUtils.isNotBlank(userAccount)) {
            criteria.andCreatorEqualTo(userAccount);
        }
        criteria.andPlanDetailsIdIsNull();
        criteria.andProcessInsIdIsNotNull();
        criteria.andDraftFlagEqualTo(false);
        criteria.andBisDeleteEqualTo(false);
        example.setOrderByClause("id desc");
        return basicApplyBatchMapper.selectByExample(example);
    }

    /**
     * 获取草稿数据列表
     *
     * @param estateName
     * @return
     */
    public List<BasicApplyBatch> getDraftBasicApplyBatchListByName(String estateName,String userAccount, Boolean draftFlag) {
        BasicApplyBatchExample example = new BasicApplyBatchExample();
        BasicApplyBatchExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(estateName)) {
            criteria.andEstateNameLike(String.format("%s%s%s", "%", estateName, "%"));
        }
        if (StringUtils.isNotBlank(userAccount)) {
            criteria.andCreatorEqualTo(userAccount);
        }
        criteria.andPlanDetailsIdIsNull();
        criteria.andProcessInsIdIsNull();
        criteria.andDraftFlagEqualTo(true);
        criteria.andBisDeleteEqualTo(false);
        example.setOrderByClause("id desc");
        return basicApplyBatchMapper.selectByExample(example);
    }

    /**
     * 获取未提交数据列表
     *
     * @param caseEstateId
     * @return
     */
    public List<BasicApplyBatch> getInfoListNotCommit(Integer caseEstateId) {
        BasicApplyBatchExample example = new BasicApplyBatchExample();
        BasicApplyBatchExample.Criteria criteria = example.createCriteria();
        criteria.andStatusIsNull();
        criteria.andDraftFlagEqualTo(false);
        criteria.andCaseEstateIdEqualTo(caseEstateId);
        return basicApplyBatchMapper.selectByExample(example);
    }

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public BasicApplyBatch getBasicApplyBatchById(Integer id) {
        return basicApplyBatchMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据
     *
     * @param basicApplyBatch
     * @return
     */
    public BasicApplyBatch getBasicApplyBatch(BasicApplyBatch basicApplyBatch) {
        BasicApplyBatchExample example = new BasicApplyBatchExample();
        BasicApplyBatchExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicApplyBatch, criteria);
        List<BasicApplyBatch> basicApplyBatchs = basicApplyBatchMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(basicApplyBatchs)) return basicApplyBatchs.get(0);
        return null;
    }

    /**
     * 获取数据列表
     *
     * @param basicApplyBatch
     * @return
     */
    public List<BasicApplyBatch> getInfoList(BasicApplyBatch basicApplyBatch) {
        BasicApplyBatchExample example = new BasicApplyBatchExample();
        BasicApplyBatchExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicApplyBatch, criteria);
        example.setOrderByClause("id desc");
        return basicApplyBatchMapper.selectByExample(example);
    }

    public List<BasicApplyBatch> getBasicApplyBatchsByPlanDetailsIds(List<Integer> planDetailsIdList) {
        BasicApplyBatchExample example = new BasicApplyBatchExample();
        BasicApplyBatchExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        criteria.andPlanDetailsIdIn(planDetailsIdList);
        example.setOrderByClause("id desc");
        return basicApplyBatchMapper.selectByExample(example);
    }

    /**
     * 获取数据（未引用）
     *
     * @param planDetailsId
     * @return
     */
    public BasicApplyBatch getBasicApplyBatchByPlanDetailsId(Integer planDetailsId) {
        BasicApplyBatchExample example = new BasicApplyBatchExample();
        BasicApplyBatchExample.Criteria criteria = example.createCriteria();
        if (planDetailsId!=null) {
            criteria.andPlanDetailsIdEqualTo(planDetailsId);
        }
        criteria.andBisDeleteEqualTo(false);
        example.setOrderByClause("id desc");
        List<BasicApplyBatch> basicApplyBatchs = basicApplyBatchMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(basicApplyBatchs)) return basicApplyBatchs.get(0);
        return null;
    }

    /**
     * 新增
     *
     * @param examineInfo
     * @return
     */
    public boolean addBasicApplyBatch(BasicApplyBatch examineInfo) {
        return basicApplyBatchMapper.insertSelective(examineInfo) > 0;
    }

    /**
     * 编辑
     *
     * @param examineInfo
     * @return
     */
    public boolean updateInfo(BasicApplyBatch examineInfo) {
        return basicApplyBatchMapper.updateByPrimaryKeySelective(examineInfo) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteInfo(Integer id) {
        BasicApplyBatch basicApplyBatch = getBasicApplyBatchById(id);
        if(basicApplyBatch==null) return false;
        basicApplyBatch.setBisDelete(true);
        return basicApplyBatchMapper.updateByPrimaryKeySelective(basicApplyBatch) > 0;
    }


}
