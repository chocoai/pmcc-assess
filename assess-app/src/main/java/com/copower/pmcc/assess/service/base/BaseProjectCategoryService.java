package com.copower.pmcc.assess.service.base;

import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.dao.base.BaseProjectCategoryDao;
import com.copower.pmcc.assess.dal.entity.BaseProjectCategory;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.CacheConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/24
 * @time: 14:14
 */
@Service
public class BaseProjectCategoryService {
    @Autowired
    private BaseProjectCategoryDao baseProjectCategoryDao;

    public BaseProjectCategory getBidProjectCategoryById(Integer id) {
        String cacheKey = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_BASE_CATEGORY_ID, String.valueOf(id));
        try {
            return LangUtils.singleCache(cacheKey, id, BaseProjectCategory.class, input -> baseProjectCategoryDao.getBidProjectCategoryById(input));
        } catch (Exception e) {
            return baseProjectCategoryDao.getBidProjectCategoryById(id);
        }
    }

    public List<BaseProjectCategory> getBidProjectCategoryListByPid(Integer pid) {
        String cacheKey = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_BASE_CATEGORY_PID, String.valueOf(pid));
        try {
            return LangUtils.listCache(cacheKey, pid, BaseProjectCategory.class, input -> baseProjectCategoryDao.getBidProjectCategoryListByPid(input));
        } catch (Exception e) {
            return baseProjectCategoryDao.getBidProjectCategoryListByPid(pid);
        }
    }
}
