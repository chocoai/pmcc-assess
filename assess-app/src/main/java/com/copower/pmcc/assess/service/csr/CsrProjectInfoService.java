package com.copower.pmcc.assess.service.csr;

import com.copower.pmcc.assess.dal.entity.CsrProjectInfo;
import com.copower.pmcc.assess.dal.dao.CsrProjectInfoDao;
import org.springframework.stereotype.Service;

/**
 * Created by kings on 2018-5-31.
 */
@Service
public class CsrProjectInfoService {
    private CsrProjectInfoDao csrProjectInfoDao;

    /**
     * 保存债权项目信息
     *
     * @param csrProjectInfo
     */
    public void saveCsrProjectInfo(CsrProjectInfo csrProjectInfo) {
        if (csrProjectInfo.getId() != null && csrProjectInfo.getId() > 0) {
            csrProjectInfoDao.updateCsrProjectInfo(csrProjectInfo);
        } else {
            csrProjectInfoDao.addCsrProjectInfo(csrProjectInfo);
        }
    }


}
