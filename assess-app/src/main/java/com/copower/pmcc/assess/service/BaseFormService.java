package com.copower.pmcc.assess.service;


import com.copower.pmcc.assess.dal.dao.BaseFormDao;
import com.copower.pmcc.assess.dal.entity.BaseForm;
import com.copower.pmcc.assess.dal.entity.BaseFormList;
import com.copower.pmcc.assess.dal.entity.BaseProcessForm;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/2/23
 * @time: 15:55
 */
@Service
public class BaseFormService {
    @Autowired
    private BaseFormDao hrBaseFormDao;
    //BaseForm=========================================================

    public List<BaseForm> getBaseForm() {
        return hrBaseFormDao.getBaseForm(null);
    }

    //BaseFormList======================================================

    public List<BaseFormList> getbaseFormList(String formName) {
        return hrBaseFormDao.getbaseFormList(formName);
    }

    public List<BaseFormList> getbaseFormList(List<BaseProcessForm> hrProcessForms){
        if(CollectionUtils.isEmpty(hrProcessForms)) return null;
        HashSet<String> nameList= Sets.newHashSet();
        HashSet<String> formNameList= Sets.newHashSet();
        for (BaseProcessForm hrProcessForm : hrProcessForms) {
            nameList.add(hrProcessForm.getName());
            formNameList.add(hrProcessForm.getProcess());
        }
        return hrBaseFormDao.getbaseFormList(Lists.newArrayList(formNameList),Lists.newArrayList(nameList));
    }

}
