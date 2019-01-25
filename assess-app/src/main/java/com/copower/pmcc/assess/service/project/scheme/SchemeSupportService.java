package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeSupportDao;
import com.copower.pmcc.assess.dal.basis.entity.SchemeSupport;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 方案主表
 * Created by 13426 on 2018/5/24.
 */
@Service
public class SchemeSupportService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private SchemeSupportDao schemeSupportDao;

    /**
     * 保存信息
     *
     * @param schemeSupport
     * @throws BusinessException
     */
    public void saveSchemeSupport(SchemeSupport schemeSupport) {
        if (schemeSupport.getId() != null && schemeSupport.getId() > 0) {
            schemeSupportDao.updateSupport(schemeSupport);
        } else {
            schemeSupport.setCreator(commonService.thisUserAccount());
            schemeSupportDao.addSupport(schemeSupport);
        }
    }

    public SchemeSupport getSchemeSupportByPlanDetailsId(Integer planDetailsId) {
        SchemeSupport where = new SchemeSupport();
        where.setPlanDetailsId(planDetailsId);
        SchemeSupport schemeSupport = schemeSupportDao.getSchemeSupport(where);
        return schemeSupport;
    }
}