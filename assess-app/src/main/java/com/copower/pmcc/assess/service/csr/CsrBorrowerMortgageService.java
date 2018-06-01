package com.copower.pmcc.assess.service.csr;

import com.copower.pmcc.assess.dal.dao.csr.CsrBorrowerMortgageDao;
import com.copower.pmcc.assess.dal.dao.csr.CsrcalculationDao;
import com.copower.pmcc.assess.dal.entity.CsrBorrowerMortgage;
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
public class CsrBorrowerMortgageService {
    @Autowired
    private CsrBorrowerMortgageDao csrBorrowerMortgageDao;

    public CsrBorrowerMortgage saveCsrBorrowerMortgage(CsrBorrowerMortgage csrBorrowerMortgage) throws BusinessException
    {
        try {
            if(csrBorrowerMortgage.getId()==null && csrBorrowerMortgage.getId()<=0)
            {
                csrBorrowerMortgageDao.addCsrBorrowerMortgage(csrBorrowerMortgage);
            }
            else
            {
                csrBorrowerMortgageDao.updateCsrBorrowerMortgage(csrBorrowerMortgage);
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        return csrBorrowerMortgage;
    }
}
