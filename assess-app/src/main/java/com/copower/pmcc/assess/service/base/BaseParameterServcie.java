package com.copower.pmcc.assess.service.base;

import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.basis.dao.base.BaseParameterDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseParameter;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.CacheConstant;
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
            String cacheKey = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_PARAMETER_KEY, key);
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
