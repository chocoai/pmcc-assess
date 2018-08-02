package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeInfoDao;
import com.copower.pmcc.assess.dal.basis.entity.SchemeInfo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
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
public class SchemeInfoService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private SchemeInfoDao schemeInfoDao;

    /**
     * 保存信息
     *
     * @param schemeInfo
     * @throws BusinessException
     */
    public void saveSchemeInfo(SchemeInfo schemeInfo) throws BusinessException {
        if (schemeInfo == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (schemeInfo.getId() != null && schemeInfo.getId() > 0) {
            schemeInfoDao.updateInfo(schemeInfo);
        } else {
            schemeInfo.setCreator(commonService.thisUserAccount());
            schemeInfoDao.addInfo(schemeInfo);
        }
    }
}