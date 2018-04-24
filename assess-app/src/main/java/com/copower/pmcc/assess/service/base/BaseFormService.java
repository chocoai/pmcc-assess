package com.copower.pmcc.assess.service.base;


import com.copower.pmcc.assess.dal.dao.BaseFormDao;
import com.copower.pmcc.assess.dal.entity.BaseForm;
import com.copower.pmcc.assess.dal.entity.BaseFormModule;
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
    private BaseFormDao baseFormDao;
    //BaseForm=========================================================
    public BaseForm getBaseForm(Integer id){
        return baseFormDao.getBaseForm(id);
    }
    public List<BaseForm> getBaseFormList() {
        return baseFormDao.getBaseForm("");
    }

    //BaseFormList======================================================

    public List<BaseFormModule> getBaseFormModuleList(Integer formId) {
        return baseFormDao.getBaseFormModuleList(formId);
    }

    public BaseFormModule getBaseFormModule(Integer id){
        return baseFormDao.getBaseFormModule(id);
    }

}
