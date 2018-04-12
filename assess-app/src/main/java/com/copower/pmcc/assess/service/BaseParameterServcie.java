package com.copower.pmcc.assess.service;

import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.CacheConstant;
import com.copower.pmcc.assess.constant.CrmCacheConstant;
import com.copower.pmcc.assess.dal.entity.BaseParameter;
import com.copower.pmcc.assess.dal.dao.BaseParameterDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/11/20
 * @time: 13:58
 */
@Service
public class BaseParameterServcie {
    private static final Logger logger = LoggerFactory.getLogger(BaseParameterServcie.class);
    @Autowired
    private BaseParameterDao cmsBaseParameterDao;

    /**
     * cache获取参数对象
     *
     * @param key
     * @return
     * @tbidows Exception
     */
    public String getParameterValues(String key) {
        BaseParameter baseParameter;
        try {
            String cacheKey = CacheConstant.getCostsKeyPrefix(CrmCacheConstant.PMCC_ASSESS_PARAMETER_KEY, key);
            baseParameter = LangUtils.singleCache(cacheKey, key, BaseParameter.class, input -> cmsBaseParameterDao.getBaseParameter(input));

        } catch (Exception e) {
            baseParameter = cmsBaseParameterDao.getBaseParameter(key);
        }
        if (baseParameter != null) {
            return baseParameter.getParValues();
        }
        return "";
    }


}
