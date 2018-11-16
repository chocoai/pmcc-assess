package com.copower.pmcc.assess.service.base;

import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.basis.dao.base.BaseParameterDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseParameter;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.CacheConstant;
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
    @Autowired
    private BaseParameterDao baseParameterDao;

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
            baseParameter = LangUtils.singleCache(cacheKey, key, BaseParameter.class, input -> baseParameterDao.getBaseParameter(input));

        } catch (Exception e) {
            baseParameter = baseParameterDao.getBaseParameter(key);
        }
        if (baseParameter != null) {
            return baseParameter.getParValues();
        }
        return "";
    }

    /**
     * 统一获取参数
     * @param baseParameterEnum
     * @return
     * @throws BusinessException
     */
    public String getBaseParameter(BaseParameterEnum baseParameterEnum) throws BusinessException {
        BaseParameter baseParameter = baseParameterDao.getBaseParameter(baseParameterEnum.toString());
        if (baseParameter == null) {
            throw new BusinessException(String.format("没有配置[%s]参数", baseParameterEnum.getRemarks()));
        }

        return baseParameter.getParValues();
    }
}
