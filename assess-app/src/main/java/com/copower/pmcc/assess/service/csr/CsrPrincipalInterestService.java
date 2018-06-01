package com.copower.pmcc.assess.service.csr;

import com.copower.pmcc.assess.dal.dao.csr.CsrPrincipalInterestDao;
import com.copower.pmcc.assess.dal.entity.CsrPrincipalInterest;
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
public class CsrPrincipalInterestService {
    @Autowired
    private CsrPrincipalInterestDao csrPrincipalInterestDao;

    public CsrPrincipalInterest saveCsrPrincipalInterest(CsrPrincipalInterest csrPrincipalInterest) throws BusinessException
    {
        try {
            if(csrPrincipalInterest.getId()==null && csrPrincipalInterest.getId()<=0)
            {
                csrPrincipalInterestDao.addCsrPrincipalInterest(csrPrincipalInterest);
            }
            else
            {
                csrPrincipalInterestDao.updateCsrPrincipalInterest(csrPrincipalInterest);
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        return csrPrincipalInterest;
    }
}
