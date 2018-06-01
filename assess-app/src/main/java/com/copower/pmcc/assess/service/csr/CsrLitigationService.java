package com.copower.pmcc.assess.service.csr;

import com.copower.pmcc.assess.dal.dao.csr.CsrLitigationDao;
import com.copower.pmcc.assess.dal.dao.csr.CsrcalculationDao;
import com.copower.pmcc.assess.dal.entity.CsrLitigation;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/6/1
 * @time: 17:11
 */
@Service
public class CsrLitigationService {
    @Autowired
    private CsrLitigationDao csrLitigationDao;

    public CsrLitigation saveCsrLitigation(CsrLitigation csrLitigation) throws BusinessException
    {
        try {
            if(csrLitigation.getId()==null && csrLitigation.getId()<=0)
            {
                csrLitigationDao.addCsrLitigation(csrLitigation);
            }
            else
            {
                csrLitigationDao.updateCsrLitigation(csrLitigation);
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        return csrLitigation;
    }
}
