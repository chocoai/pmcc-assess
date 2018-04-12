package com.copower.pmcc.assess.dal.dao;


import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dal.mapper.BaseFormListFieldMapper;
import com.copower.pmcc.assess.dal.mapper.BaseFormListMapper;
import com.copower.pmcc.assess.dal.mapper.BaseFormMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/2/23
 * @time: 14:23
 */
@Repository
public class BaseFormDao {
    @Autowired
    private BaseFormMapper hrBaseFormMapper;
    @Autowired
    private BaseFormListMapper hrBaseFormListMapper;
    @Autowired
    private BaseFormListFieldMapper hrBaseFormListFieldMapper;

    //BaseForm=========================================================

    public List<BaseForm> getBaseForm(String search) {
        BaseFormExample example = new BaseFormExample();
        BaseFormExample.Criteria criteria = example.createCriteria();
        criteria.andBisEnableEqualTo(true);
        if (StringUtils.isNotBlank(search))
            criteria.andCnNameLike(String.format("%s%s%s", "%", search, "%"));
        return hrBaseFormMapper.selectByExample(example);
    }

    public void addBaseForm(BaseForm hrBaseForm) {
        hrBaseFormMapper.insertSelective(hrBaseForm);
    }

    public Boolean updateBaseFormByName(BaseForm hrBaseForm, String name) {
        BaseFormExample example = new BaseFormExample();
        example.createCriteria().andNameEqualTo(name);
        return hrBaseFormMapper.updateByExampleSelective(hrBaseForm, example) == 1;
    }
    //BaseFormList======================================================

    public BaseFormList getBaseFormList(Integer id){
        return hrBaseFormListMapper.selectByPrimaryKey(id);
    }

    public List<BaseFormList> getbaseFormList(String formName) {
        return getbaseFormList(formName, null);
    }

    public List<BaseFormList> getbaseFormList(String formName, String name) {
        BaseFormListExample example = new BaseFormListExample();
        BaseFormListExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(formName)) {
            criteria.andFormNameEqualTo(formName);
        }
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameEqualTo(name);
        }
        return hrBaseFormListMapper.selectByExample(example);
    }

    public List<BaseFormList> getbaseFormList(List<String> formNameList, List<String> nameList) {
        BaseFormListExample example = new BaseFormListExample();
        BaseFormListExample.Criteria criteria = example.createCriteria();
        if (CollectionUtils.isNotEmpty(formNameList)) {
            criteria.andFormNameIn(formNameList);
        }
        if (CollectionUtils.isNotEmpty(nameList)) {
            criteria.andNameIn(nameList);
        }
        return hrBaseFormListMapper.selectByExample(example);
    }


    //BaseFormListField======================================================
    public List<BaseFormListField> getBaseFormListFields(Integer formListId) {
        BaseFormListFieldExample example = new BaseFormListFieldExample();
        example.createCriteria().andFormListIdEqualTo(formListId).andBisDeleteEqualTo(false);
        example.setOrderByClause("sorting,id");
        return hrBaseFormListFieldMapper.selectByExample(example);
    }

    public List<BaseFormListField> getBaseFormListFields(List<Integer> formListIds) {
        BaseFormListFieldExample example = new BaseFormListFieldExample();
        example.createCriteria().andFormListIdIn(formListIds).andBisDeleteEqualTo(false);
        example.setOrderByClause("sorting,id");
        return hrBaseFormListFieldMapper.selectByExample(example);
    }

    public BaseFormListField getBaseFormListField(Integer id) {
        return hrBaseFormListFieldMapper.selectByPrimaryKey(id);
    }

    public Boolean addBaseFormListField(BaseFormListField hrBaseFormListField) {
        return hrBaseFormListFieldMapper.insertSelective(hrBaseFormListField) == 1;
    }

    public Boolean updateBaseFormListField(BaseFormListField hrBaseFormListField) {
        return hrBaseFormListFieldMapper.updateByPrimaryKeySelective(hrBaseFormListField) == 1;
    }


}
