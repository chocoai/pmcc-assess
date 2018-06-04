package com.copower.pmcc.assess.service.csr;

import com.copower.pmcc.assess.dal.dao.csr.CsrGuarantorDao;
import com.copower.pmcc.assess.dal.entity.CsrBorrowerMortgage;
import com.copower.pmcc.assess.dal.entity.CsrGuarantor;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/6/1
 * @time: 17:11
 */
@Service
public class CsrGuarantorService {
    @Autowired
    private CsrGuarantorDao csrGuarantorDao;

    public CsrGuarantor saveCsrGuarantor(CsrGuarantor csrGuarantor) throws BusinessException
    {
        try {
            if(csrGuarantor.getId()==null && csrGuarantor.getId()<=0)
            {
                csrGuarantorDao.addCsrGuarantor(csrGuarantor);
            }
            else
            {
                csrGuarantorDao.updateCsrGuarantor(csrGuarantor);
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        return csrGuarantor;
    }

    public List<CsrGuarantor> getCsrGuarantor(Integer borrowerId)
    {
        CsrGuarantor csrGuarantor=new CsrGuarantor();
        csrGuarantor.setBorrowerId(borrowerId);
        List<CsrGuarantor> csrBorrowerMortgageList = csrGuarantorDao.getCsrGuarantorList(csrGuarantor);
        return csrBorrowerMortgageList;

    }
}
