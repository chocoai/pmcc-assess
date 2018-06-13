package com.copower.pmcc.assess.service.base;

import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.dao.base.BaseFormDao;
import com.copower.pmcc.assess.dal.entity.BaseForm;
import com.copower.pmcc.assess.dal.entity.BaseFormModule;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.CacheConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public BaseForm getBaseForm(String name){
        return baseFormDao.getBaseFormByName(name);
    }
    public List<BaseForm> getBaseFormList() {
        return baseFormDao.getBaseForm("");
    }

    //BaseFormList======================================================
    public List<BaseFormModule> getBaseFormModuleList(Integer formId) {
        return baseFormDao.getBaseFormModuleList(formId);
    }

    public List<BaseFormModule> getBaseFormModuleList(List<Integer> ids) {
        return baseFormDao.getBaseFormModuleList(ids);
    }

    public BaseFormModule getBaseFormModule(Integer id){
        return baseFormDao.getBaseFormModule(id);
    }

    /**
     * 从缓存中获取动态表单信息
     * @param id
     * @return
     */
    public BaseFormModule getCacheFormModuleById(Integer id){
        String rdsKey = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_DYNAMIC_FORM_MODULE_ID, String.valueOf(id));
        try {
            BaseFormModule baseFormModule = LangUtils.singleCache(rdsKey, id, BaseFormModule.class, o -> baseFormDao.getBaseFormModule(o));
            return baseFormModule;
        } catch (Exception e) {
            return baseFormDao.getBaseFormModule(id);
        }
    }
}
