package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.SysFeedback;
import com.copower.pmcc.assess.dal.basis.entity.SysFeedbackExample;
import com.copower.pmcc.assess.dal.basis.mapper.SysFeedbackMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author Red
 * @version 1.0
 * @date: 2017/09/13 09:18
 */
@Repository
public class SysFeedbackDao {
    @Autowired
    private SysFeedbackMapper sysFeedbackMapper;

    public List<SysFeedback> getSysFeedbackList(Integer systemType, String questionTitle, String feedbackPerson, Integer status, String creator) {
        SysFeedbackExample example = new SysFeedbackExample();
        SysFeedbackExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (systemType != null) {
            criteria.andSystemTypeEqualTo(systemType);
        }
        if (status != null) {
            criteria.andStatusEqualTo(status);
        }
        if (StringUtils.isNotEmpty(questionTitle)) {
            criteria.andQuestionTitleLike(String.format("%s%s%s", "%", questionTitle, "%"));
        }
        if (StringUtils.isNotEmpty(feedbackPerson)) {
            criteria.andFeedbackPersonLike(String.format("%s%s%s", "%", feedbackPerson, "%"));
        }
        if (StringUtils.isNotEmpty(creator)) {
            criteria.andCreatorLike(String.format("%s%s%s", "%", creator, "%"));
        }
        example.setOrderByClause("id desc");
        return sysFeedbackMapper.selectByExample(example);
    }

    public List<SysFeedback> getListObject(SysFeedback sysFeedback) {
        SysFeedbackExample example = new SysFeedbackExample();
        MybatisUtils.convertObj2Example(sysFeedback, example);
        return sysFeedbackMapper.selectByExample(example);
    }

    public SysFeedback getSingleObject(SysFeedback sysFeedback) {
        SysFeedbackExample example = new SysFeedbackExample();
        MybatisUtils.convertObj2Example(sysFeedback, example);
        List<SysFeedback> vacationTypeList = sysFeedbackMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(vacationTypeList)) return null;
        return vacationTypeList.get(0);
    }

    public SysFeedback getSingleObject(Integer id) {
        return sysFeedbackMapper.selectByPrimaryKey(id);
    }

    public boolean addObject(SysFeedback bidCostInfo) {
        return sysFeedbackMapper.insertSelective(bidCostInfo) == 1;
    }

    public boolean updateObject(SysFeedback bidCostInfo) {
        return sysFeedbackMapper.updateByPrimaryKeySelective(bidCostInfo) == 1;
    }

    public boolean deleteObject(Integer id) {
        return sysFeedbackMapper.deleteByPrimaryKey(id) == 1;
    }
}
