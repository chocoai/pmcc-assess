package com.copower.pmcc.assess.dal.dao;


import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dal.mapper.BaseFormMapper;
import com.copower.pmcc.assess.dal.mapper.BaseFormModuleFieldMapper;
import com.copower.pmcc.assess.dal.mapper.BaseFormModuleMapper;
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
    private BaseFormModuleMapper hrBaseFormModuleMapper;
    @Autowired
    private BaseFormModuleFieldMapper hrBaseFormModuleFieldMapper;

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
    //BaseFormModule======================================================
    public BaseFormModule getBaseFormModule(Integer id) {
        return hrBaseFormModuleMapper.selectByPrimaryKey(id);
    }

    public List<BaseFormModule> getBaseFormModuleList(Integer formId) {
        if (formId == null) return null;
        BaseFormModuleExample example = new BaseFormModuleExample();
        BaseFormModuleExample.Criteria criteria = example.createCriteria();
        criteria.andBisEnableEqualTo(true).andFormIdEqualTo(formId);
        return hrBaseFormModuleMapper.selectByExample(example);
    }
    public List<BaseFormModule> getBaseFormModuleList(List<Integer> formModuleIds) {
        BaseFormModuleExample example = new BaseFormModuleExample();
        example.createCriteria().andIdIn(formModuleIds).andBisEnableEqualTo(true);
        return hrBaseFormModuleMapper.selectByExample(example);
    }

    //BaseFormModuleField======================================================
    public List<BaseFormModuleField> getBaseFormModuleFields(Integer formModuleId) {
        BaseFormModuleFieldExample example = new BaseFormModuleFieldExample();
        example.createCriteria().andFormModuleIdEqualTo(formModuleId).andBisDeleteEqualTo(false);
        example.setOrderByClause("sorting,id");
        return hrBaseFormModuleFieldMapper.selectByExample(example);
    }

    public List<BaseFormModuleField> getBaseFormModuleFields(List<Integer> formModuleIds) {
        BaseFormModuleFieldExample example = new BaseFormModuleFieldExample();
        example.createCriteria().andFormModuleIdIn(formModuleIds).andBisDeleteEqualTo(false);
        example.setOrderByClause("sorting,id");
        return hrBaseFormModuleFieldMapper.selectByExample(example);
    }

    public BaseFormModuleField getBaseFormModuleField(Integer id) {
        return hrBaseFormModuleFieldMapper.selectByPrimaryKey(id);
    }

    public Boolean addBaseFormModuleField(BaseFormModuleField baseFormModuleField) {
        return hrBaseFormModuleFieldMapper.insertSelective(baseFormModuleField) == 1;
    }

    public Boolean updateBaseFormModuleField(BaseFormModuleField baseFormModuleField) {
        return hrBaseFormModuleFieldMapper.updateByPrimaryKeySelective(baseFormModuleField) == 1;
    }


}
