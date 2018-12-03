package com.copower.pmcc.assess.dal.basic.dao;

import com.copower.pmcc.assess.dal.basic.entity.BasicApply;
import com.copower.pmcc.assess.dal.basic.entity.BasicApplyExample;
import com.copower.pmcc.assess.dal.basic.mapper.BasicApplyMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kings on 2018-10-24.
 */
@Repository
public class BasicApplyDao {
    @Autowired
    private BasicApplyMapper basicApplyMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public BasicApply getBasicApplyById(Integer id) {
        return basicApplyMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     *
     * @param estateName
     * @return
     */
    public List<BasicApply> getBasicApplyListByName(String estateName, Boolean draftFlag) {
        BasicApplyExample example = new BasicApplyExample();
        BasicApplyExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(estateName)) {
            criteria.andEstateNameLike(String.format("%s%s%s", "%", estateName, "%"));
        }
        criteria.andDraftFlagEqualTo(draftFlag);
        example.setOrderByClause("id desc");
        return basicApplyMapper.selectByExample(example);
    }

    public List<BasicApply> getBasicApplyList(BasicApply basicApply) {
        BasicApplyExample example = new BasicApplyExample();
        MybatisUtils.convertObj2Example(basicApply, example);
        example.setOrderByClause("id desc");
        return basicApplyMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param basicApply
     * @return
     */
    public boolean addBasicApply(BasicApply basicApply) {
        return basicApplyMapper.insertSelective(basicApply) > 0;
    }

    public Integer saveBasicApply(BasicApply basicApply) {
        basicApplyMapper.insertSelective(basicApply);
        return basicApply.getId();
    }

    /**
     * 编辑
     *
     * @param basicApply
     * @return
     */
    public boolean updateBasicApply(BasicApply basicApply) {
        return basicApplyMapper.updateByPrimaryKeySelective(basicApply) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteBasicApply(Integer id) {
        return basicApplyMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 根据条件查询申请的数量
     *
     * @param basicApply
     * @param applyId
     * @return
     */
    public int getBasicApplyCount(BasicApply basicApply, Integer applyId) {
        BasicApplyExample example = new BasicApplyExample();
        example = MybatisUtils.convertObj2Example(basicApply, example);
        example.createCriteria().andIdNotEqualTo(applyId);
        return basicApplyMapper.countByExample(example);
    }
}
