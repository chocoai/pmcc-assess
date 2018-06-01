package com.copower.pmcc.assess.service.csr;

import com.copower.pmcc.assess.dal.dao.csr.CsrcalculationDao;
import com.copower.pmcc.assess.dal.entity.CsrCalculation;
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
public class CsrCalculationService {
    @Autowired
    private CsrcalculationDao csrcalculationDao;

    public CsrCalculation saveCsrCalculation(CsrCalculation csrCalculation) throws BusinessException
    {
        try {
            if(csrCalculation.getId()==null && csrCalculation.getId()<=0)
            {
                csrcalculationDao.addCsrCalculation(csrCalculation);
            }
            else
            {
                csrcalculationDao.updateCsrCalculation(csrCalculation);
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        return csrCalculation;
    }
}
