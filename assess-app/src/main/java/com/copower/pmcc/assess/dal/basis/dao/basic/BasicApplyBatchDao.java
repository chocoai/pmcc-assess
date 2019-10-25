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
     * 获取数据列表
     *
     * @param estateName
     * @return
     */
    public List<BasicApplyBatch> getBasicApplyBatchListByName(String estateName,String userAccount, Boolean draftFlag) {
        BasicApplyBatchExample example = new BasicApplyBatchExample();
        BasicApplyBatchExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(estateName)) {
            criteria.andEstateNameLike(String.format("%s%s%s", "%", estateName, "%"));
        }
        if (StringUtils.isNotBlank(userAccount)) {
            criteria.andCreatorEqualTo(userAccount);
        }
        criteria.andDraftFlagEqualTo(draftFlag);
        example.setOrderByClause("id desc");
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
        MybatisUtils.convertObj2Example(basicApplyBatch, example);
        List<BasicApplyBatch> basicApplyBatchs = basicApplyBatchMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(basicApplyBatchs)) return basicApplyBatchs.get(0);
        return null;
    }

    /**
     * 获取数据列表
     *
     * @param examineInfo
     * @return
     */
    public List<BasicApplyBatch> getInfoList(BasicApplyBatch examineInfo) {
        BasicApplyBatchExample example = new BasicApplyBatchExample();
        MybatisUtils.convertObj2Example(examineInfo, example);
        return basicApplyBatchMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param examineInfo
     * @return
     */
    public boolean addInfo(BasicApplyBatch examineInfo) {
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
        return basicApplyBatchMapper.deleteByPrimaryKey(id) > 0;
    }


}
